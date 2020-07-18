package com.platform.common;

/**
 * @author shitou
 */
public class ResultUtil {
    /**
     * 成功且带数据
     **/
    public static RestResponse success(Object object) {
        RestResponse result = new RestResponse();
        result.setStatus(ResultEnum.SUCCESS.getStatus());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    /**
     * 成功但不带数据
     **/
    public static RestResponse success() {

        return success(null);
    }

    /**
     * 失败
     **/
    public static RestResponse error(Integer code, String msg) {
        RestResponse result = new RestResponse();
        result.setStatus(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 失败
     **/
    public static RestResponse error() {
        RestResponse result = new RestResponse();
        result.setStatus(ResultEnum.UNKNOWN_ERROR.getStatus());
        result.setMsg(ResultEnum.UNKNOWN_ERROR.getMsg());
        return result;
    }
    /**
     * 失败
     **/
    public static RestResponse error(String msg) {
        RestResponse result = new RestResponse();
        result.setStatus(ResultEnum.UNKNOWN_ERROR.getStatus());
        result.setMsg(msg);
        return result;
    }

}
