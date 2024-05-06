package com.luluy233;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

@DataType
@Data
@Accessors(chain = true)
public class Retailer {
    @Property
    private String retailerId;

    @Property
    private String retailerName;

    @Property
    private String retailerTel;

    @Property
    private String retailerLocation;

    @Property
    private long retailTime;

}