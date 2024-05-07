package com.luluy233.maotaitraceability.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 消费者信息
 */
@Data
@Accessors(chain = true)
public class Consumer {

    private String consumerId;

    private String consumerName;

    private long consumeTime;   //消费者购买茅台时间

}
