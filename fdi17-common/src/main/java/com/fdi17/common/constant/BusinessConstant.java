package com.fdi17.common.constant;

/**
 * 业务常量
 */
public class BusinessConstant {

    /** 跑批类型-日结 */
    public static final String BATCH_TYPE_DAY = "1";
    /** 跑批类型-月结 */
    public static final String BATCH_TYPE_MONTH = "2";
    /** 队列执行状态 1-待执行 */
    public static final String QUEUE_JOB_STATUS_PENDING = "1";
    /** 队列执行状态 2-执行中 */
    public static final String QUEUE_JOB_STATUS_EXECUTING = "2";
    /** 队列执行状态 3-执行成功 */
    public static final String QUEUE_JOB_STATUS_SUCCESS = "3";
    /** 队列执行状态 4-执行失败 */
    public static final String QUEUE_JOB_STATUS_FAIL = "4";
    /** 任务节点执行状态-执行成功 */
    public static final String NODE_STATUS_SUCCESS = "success";
    /** 任务节点执行状态-执行异常 */
    public static final String NODE_STATUS_EXCEPTION = "exception";
    /** 任务节点执行状态-检核阻断 */
    public static final String NODE_STATUS_CHECK_FAIL_BLOCK = "checkFailBlock";
    /** 任务节点执行状态-检核非阻断 */
    public static final String NODE_STATUS_CHECK_FAIL_NO_BLOCK = "checkFailNoBlock";
    /** 任务节点执行状态-阻断-反参为空 */
    public static final String NODE_STATUS_SUCCESS_NULL = "successNull";
    /** 任务节点执行状态-执行中 */
    public static final String NODE_STATUS_EXECUTING = "executing";
    /** 任务节点类型 开始节点*/
    public static final String NODE_TYPE_START = "start";
    /** 任务节点类型 任务节点*/
    public static final String NODE_TYPE_TASK = "task";
    /** 任务节点类型 条件节点*/
    public static final String NODE_TYPE_SQL_CONDITION = "sql-condition";
    /** 任务节点类型 sql节点*/
    public static final String NODE_TYPE_SQL_SUCCESS = "sql-success";
    /** 任务节点类型 结束节点*/
    public static final String NODE_TYPE_END = "end";
    /** 重复节点执行参数名 */
    public static final String REPEAT_NODE_VALUE = "repeatNodeValue";
    /** 循环节点执行参数名 */
    public static final String LOOP_ARG = "loopArg";
}
