package com.example.midx.aop;

import com.example.midx.annotation.ApiIdempotent;
import com.example.midx.common.ResponseCode;
import com.example.midx.exception.ServiceException;
import com.example.midx.util.JedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
public class ApiIdempotentAop {

    @Autowired
    private JedisUtil jedisUtil;

    @Pointcut("@annotation(com.example.midx.annotation.ApiIdempotent)")
    public void apiIdempotentPointCut() {

    }

    @Around("apiIdempotentPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        System.err.println("进来AOP了");
        Object[] args = joinPoint.getArgs();

        //获取request和response
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        String requestURI = request.getRequestURI();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        ApiIdempotent annotation = signature.getMethod().getAnnotation(ApiIdempotent.class);

        Object result;
        if(annotation != null) {
            String token = request.getHeader("token");
            if (StringUtils.isBlank(token)) {// header中不存在token
                token = request.getParameter("token");
                if (StringUtils.isBlank(token)) {// parameter中也不存在token
                    throw new ServiceException("参数不合法");
                }
            }

            if (!jedisUtil.exists(token)) {
                System.err.println("请勿重复操作");
                throw new ServiceException("请勿重复操作");
            }

            result = joinPoint.proceed();

            Long del = jedisUtil.del(token);
            if (del <= 0) {
                throw new ServiceException("请勿重复操作");
            }

            return result;
        }
        return null;
    }
}
