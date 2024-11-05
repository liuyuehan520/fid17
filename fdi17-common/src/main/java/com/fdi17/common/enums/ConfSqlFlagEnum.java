package com.fdi17.common.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * CONF_SQL_INFO sqlFlag枚举
 */
@Getter
public enum ConfSqlFlagEnum {
    /**
     * 获取通知数据
     */
    MESSAGE_CONFIG("SYS-60547247597b48d8b34cfdd743822ce2-getMessageData", "获取通知数据"),
    /**
     * 获取收件人
     */
    MESSAGE_RULE("SYS-cc0173b1faf34155bf949ea8bd2a6sa5-getMessageData", "获取收件人"),
    /**
     * 查询准备金任务进度
     */
    OUTERDB_GRS_SQL_COUNT("OUTERDB-grsdata_sql_count", "查询准备金任务进度");

    private final String sqlFlag;
    private final String remark;

    ConfSqlFlagEnum(String sqlFlag, String remark) {
        this.sqlFlag = sqlFlag;
        this.remark = remark;
    }

    /**
     * 根据值查找对应的枚举对象
     */
    public static ConfSqlFlagEnum getEnum(String sqlFlag) {
        if (StringUtils.isNotEmpty(sqlFlag)) {
            for (ConfSqlFlagEnum sqlFlagEnum : ConfSqlFlagEnum.values()) {
                if (sqlFlagEnum.getSqlFlag().equals(sqlFlag)) {
                    return sqlFlagEnum;
                }
            }
        }
        return null;
    }

}
