package com.luluy233;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

@DataType
@Data
@Accessors(chain = true)
public class Producer {
    @Property
    private String producerId;

    @Property
    private String producerName;

    @Property
    private String producerTel;

    @Property
    private String rawMaterials;

    @Property
    private long produceTime;

    @Property
    private String produceLocation;

}