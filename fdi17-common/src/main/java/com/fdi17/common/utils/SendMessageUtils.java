package com.fdi17.common.utils;

import com.fdi17.common.enums.EmailTypeEnum;

import java.text.MessageFormat;

public class SendMessageUtils {

    /**
     * 构建邮件消息内容
     *
     * @param emailType 邮件类型
     * @param args      动态参数
     */
    public static String buildEmailMsgContent(EmailTypeEnum emailType, Object... args) {
        return MessageFormat.format(emailType.getValue(), args);
    }

}
