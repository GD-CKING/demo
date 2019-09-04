package com.example.midx.service;

import com.example.midx.common.ServerResponse;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {

    void checkToken(HttpServletRequest request);

    ServerResponse createToken();

    ServerResponse test();

}
