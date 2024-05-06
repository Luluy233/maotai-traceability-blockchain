package com.luluy233;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

@DataType
@Data
@Accessors(chain = true)
public class Consumer {
    @Property
    private String consumerId;

    @Property
    private String consumerName;

    @Property
    private long consumeTime;

}