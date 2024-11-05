package com.fdi17.common.domain.sqlProvider;

import java.util.Map;

/**
 * @author zhangyunmu
 * @Description 存储过程sql处理类
 * @date 2024/9/21 12:51
 */
public class CustomProcedureSqlProvider {

    public String provideCallProcedure(Map<String, Object> param) {
        // 处理param.xxx 若存在，则删除。 param.xxx只是为了定时任务执行时可以输入参数，并不直接使用${sql}去处理
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        String sql = (String) param.get("sql");
        sql = sql.replaceAll("param.", "");
        builder.append(sql);
        builder.append("}");
        return builder.toString();
    }

}
