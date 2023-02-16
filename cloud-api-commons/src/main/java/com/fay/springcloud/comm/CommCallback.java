package com.fay.springcloud.comm;

public class CommCallback {
    public String timeOutHandler(){
        return "/(ㄒoㄒ)/调用支付接口超时或异常：\t"+ "\t当前线程池名字" + Thread.currentThread().getName();
    }
}
