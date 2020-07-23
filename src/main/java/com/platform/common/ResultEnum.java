package com.platform.common;

/**
 * @author shitou
 */

public enum ResultEnum {
    //这里是可以自己定义的，方便与前端交互即可
    LOGIN_IS_OVERDUE(410000, "token失效"),
    UNKNOWN_ERROR(400, "未知错误"),
    SUCCESS(200, "成功"),
    USER_NOT_EXIST(1, "用户不存在"),
    USER_IS_EXISTS(2, "用户已存在"),
    EMAIL_IS_EXISTS(6, "邮箱已存在"),
    ID_NOT_EXISTS(8, "id不存在"),
    VCF_IS_EXISTS(9, "vcf为空"),
    VCF_FORMAT_ERROR(10, "vcf格式错误"),
    FILE_TYPE_CHANGE_ERROR(11, "文件类型转换错误"),
    IDCAR_IS_EXIST(12, "患者已将存在"),
    EMAIL_NOT_EXISTS(7, "邮箱不存在"),
    PASSWORD_NOT_IDENTICAL(4, "密码不一致"),
    VERIFICATION_ERROR(5, "验证码错误"),
    DATA_IS_NULL(3, "数据为空"),
    IMG_NOT_EMPTY(5001, "图片不能为空!"),
    ;
    private Integer status;
    private String msg;

    ResultEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
