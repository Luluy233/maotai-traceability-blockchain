package com.luluy233.maotaitraceability.vo;


import com.luluy233.maotaitraceability.dto.Consumer;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BuyVo {

    private String bottleId;

    private String consumerId;

    private String consumerName;

//    private long consumeTime;   //消费者购买茅台时间


}
