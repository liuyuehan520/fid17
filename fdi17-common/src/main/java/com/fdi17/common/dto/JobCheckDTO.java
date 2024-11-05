package com.fdi17.common.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class JobCheckDTO {
    private String ruleId;
    private Map<String, Object> paramMap;
    private String notificationId;
}
