package com.platform.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性别
 */
@AllArgsConstructor
@Getter
public enum SexType {
    MAN("1", "男"),

    GIRL("2", "女");

    // 成员变量
    private String code;

    private String name;
}
