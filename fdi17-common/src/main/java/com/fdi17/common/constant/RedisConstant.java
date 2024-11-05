package com.fdi17.common.constant;

public class RedisConstant {

    /**
     * 过期时间 天 24 * 3600L;
     */
    public static final long REDIS_EXPIRE_DAY = 24 * 3600L;

    public static final long REDIS_EXPIRE_SECONDS = 5L;


    public final static String REDIS_NODE = "rongTask:bulletinBoard:redisNode";

    public final static String REDIS_NODE_KEY = REDIS_NODE + ":queueId:%s:jobid:%s:nodeId:%s";

    public final static String REDIS_QUEUE_JOB_ID = REDIS_NODE + ":queueId:%s:jobid:%s";

    /**
     * 并行节点控制-普通节点  redis key
     */
    public static final String REDIS_PARALLEL_NODE_CONTROL = "parallel_node_control:";
    /**
     * 并行节点控制-循环节点  redis key
     */
    public static final String REDIS_PARALLEL_LOOP_NODE = "parallel_loop_node:";
    /**
     * 并行节点控制-重复节点  redis key
     */
    public static final String REDIS_PARALLEL_REPEAT_NODE = "parallel_repeat_node:";
}
