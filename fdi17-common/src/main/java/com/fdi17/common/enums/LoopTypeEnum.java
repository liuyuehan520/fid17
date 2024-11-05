package com.fdi17.common.enums;

import lombok.Getter;

/**
 * 循环类型枚举
 */
@Getter
public enum LoopTypeEnum {
    /** 例子：count_3  循环3次 */
//    LOOP_COUNT("count", "执行次数循环，根据loopTypeValue值进行循环"),
    /** args_arg  根据程序map中的arg参数，遍历循环 */
    LOOP_ARGS("loopArgs", "参数循环，根据loopTypeValue值从map中查询进行循环"),

    ;

    private final String code;
    private final String name;

    LoopTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

}
