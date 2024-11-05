package com.fdi17.common.constant;

import java.util.regex.Pattern;

public class BeaseConstant {


    /**
     * 数字0
     */
    public static final Integer ZERO = 0;
    /**
     * 数字1
     */
    public static final Integer ONE = 1;

    public static final Integer TWO = 2;

    public static final Integer THREE = 3;
    public static final Integer FOUR = 4;


    public static final Integer BATCH_SIZE = 5;


    //匹配注释内容/**/
    public static final Pattern COMMENT_PATTERN =  Pattern.compile("/\\*.*?\\*/", Pattern.DOTALL);

    public static final String RRX_DELIMITER="#rrx.;#";

}
