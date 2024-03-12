package com.teamide.demo.server.controller;


import com.yanzhenjie.andserver.annotation.GetMapping;
import com.yanzhenjie.andserver.annotation.RestController;
import com.yanzhenjie.andserver.http.HttpRequest;


@RestController
public class IndexController {

    @GetMapping(path = "/")
    String index(HttpRequest request) throws Exception {
        return request.getPath();
    }
    @GetMapping(path = "/api/{path}")
    String api(HttpRequest request) throws Exception {
        return request.getPath();
    }

}
