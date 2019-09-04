package com.example.midx.controller;

import com.example.midx.annotation.ApiIdempotent;
import com.example.midx.common.ServerResponse;
import com.example.midx.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;


    @GetMapping
    public ServerResponse token() {
        return tokenService.createToken();
    }

    @ApiIdempotent
    @GetMapping("/test")
    public ServerResponse test() {
        return tokenService.test();
    }

}
