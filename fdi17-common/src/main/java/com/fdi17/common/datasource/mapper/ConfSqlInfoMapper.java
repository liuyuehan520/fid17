package com.fdi17.common.datasource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fdi17.common.datasource.domain.ConfSqlInfo;
import com.fdi17.common.domain.sqlProvider.CustomProcedureSqlProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.StatementType;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ConfSqlInfoMapper
 * @Description: sql配置
 * @author jjt
 * @date 2023年5月7日 上午11:09:50
 */
@Mapper
public interface ConfSqlInfoMapper extends BaseMapper<ConfSqlInfo> {

	/**
	 * @Title: executeSql
	 * @Description: 执行sql
	 * @author jjt
	 * @date 2023年5月7日 上午11:18:04
	 * @param @param sql
	 * @param @return    设定文件
	 * @return List<Map<String,Object>>    返回类型
	 * @throws
	 */
	@Select("<script> ${sql} </script>")
    List<LinkedHashMap<String,Object>> executeSql(@Param("sql") String sql, @Param("param") Map<String, Object> param);

	/**
	 * @Title: saveBatch
	 * @Description: 批量保存
	 * @author jjt
	 * @date 2023年10月14日 下午7:53:46
	 * @param @param sql
	 * @param @param paramList
	 * @param @return    设定文件
	 * @return List<LinkedHashMap<String,Object>>    返回类型
	 * @throws
	 */
	@Select("<script> <foreach collection='paramList' item='param' index='index' open='' close='' separator=';'> "
			+ " ${sql} </foreach> </script>")
    List<LinkedHashMap<String,Object>> saveBatch(@Param("sql") String sql, @Param("paramList") List<Map<String, Object>> paramList);

	@Select("<script> ${sql} </script>")
	long countSql(@Param("sql") String sql);

	/**
	 * 动态根据sql调用存储过程
	 * @param param
	 */
	@Options(statementType = StatementType.CALLABLE)
	@SelectProvider(type = CustomProcedureSqlProvider.class, method = "provideCallProcedure")
	void executeProcedureSql(Map<String, Object> param);

//	调用存储过程硬编码
//	@Select({"call I17CHKCDE.SP_AE_FIN_RXD(#{P_I_TRANS_DATE,mode=IN,jdbcType=VARCHAR} , #{P_O_BATCH_NO,mode=OUT,jdbcType=VARCHAR})"})
//	@Options(statementType = StatementType.CALLABLE)
//	void executeProcedureSql1(Map<String, Object> param);
}
