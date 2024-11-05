package com.fdi17.common.enums;

import lombok.Getter;

/**
 * 邮件类型枚举
 */
@Getter
public enum EmailTypeEnum {

    EMAIL_EXCEPTION("默认异常邮件发送", "{0}:<br><br>&nbsp;&nbsp;&nbsp;&nbsp;{1}执行出现异常，请通过FD17系统，<a href={2}>{2}</a>，管理看板页面查看任务执行日志信息。<br><br><div style='text-align: right;'>批量监控组<br><br>{3}</div>"),
    EMAIL_CHECK_RULE_ERROR("检核规则阻断邮件发送", "你好:<br><br>&nbsp;&nbsp;&nbsp;&nbsp;{0}发现异常数据，检验不合格，请通过FD17系统，<a href={1}>{1}</a>，检核管理页面查询具体检核信息。<br><br><div style=\"text-align: right;\">批量监控组<br><br>{2}</div>"),
    //EMAIL_CHECK_RULE_ERROR("检核规则阻断邮件发送", "{0}:<br><br>&nbsp;&nbsp;&nbsp;&nbsp;{1}发现异常数据，检验不合格，请通过FD17系统，<a href={2}>{2}</a>，检核管理页面查询具体检核信息。<br><br><div style='text-align: right;'>批量监控组<br><br>{3}</div>"),
    //EMAIL_PROCEDURE_CALL("存储过程异常邮件发送", "{0}:<br><br>&nbsp;&nbsp;&nbsp;&nbsp;{1}，请通过FD17系统，<a href={2}>{2}</a>，检核管理页面查询具体检核信息。<br><br><div style='text-align: right;'>批量监控组<br><br>{3}</div>"),
    EMAIL_PROCEDURE_CALL("存储过程异常邮件发送", "你好:<br><br>&nbsp;&nbsp;&nbsp;&nbsp;{0}发现异常数据，检验不合格，请通过FD17系统，<a href={1}>{1}</a>，检核管理页面查询具体检核信息。<br><br><div style=\"text-align: right;\">批量监控组<br><br>{2}</div>"),
    ;

    private final String remark;
    private final String value;

    EmailTypeEnum(String remark, String value) {
        this.remark = remark;
        this.value = value;
    }

}
