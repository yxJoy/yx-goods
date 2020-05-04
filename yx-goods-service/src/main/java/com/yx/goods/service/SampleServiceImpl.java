package com.yx.goods.service;


import com.yx.goods.facade.SampleService;

public class SampleServiceImpl implements SampleService {

    @Override
    public String message() {
        return "Hello, Service slitecore";
    }
}
