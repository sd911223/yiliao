package com.platform.common;

import io.swagger.annotations.ApiModelProperty;

/**
 * 定义返回数据结构
 * 返回状态码默认使用 http code
 *
 * @author shitou
 */
public class RestResponse<T> {
    @ApiModelProperty("状态码")
    private Integer status;
    @ApiModelProperty("消息")
    private String msg;
    @ApiModelProperty("数据")
    private T data;

    public RestResponse() {
        super();
    }

    public RestResponse(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

}
