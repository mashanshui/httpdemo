package com.example.httpdemo;

import java.util.List;

/**
 * @author 马山水
 * @date 2018/3/22
 * @desc Http服务返回一个固定格式的数据，我们可以通过下面的泛型接收可变的数组数据
 */

public class RequestResultsList<T> {
    private int resultCode;
    private String resultMessage;

    private List<T> data;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
