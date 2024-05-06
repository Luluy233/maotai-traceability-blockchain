package com.luluy233;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

@DataType
@Data
@Accessors(chain = true)
public class MaotaiWine {
    @Property
    private String bottleId;

    @Property
    private Status status;

    @Property
    private Producer producer;

    @Property
    private Retailer retailer;

    @Property
    private Consumer consumer;
}