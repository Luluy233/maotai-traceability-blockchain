package com.luluy233.maotaitraceability.dto;

import lombok.Data;
import lombok.experimental.Accessors;


/**
 * 零售商信息
 */
@Data
@Accessors(chain = true)
public class Retailer {

    private String retailerId;

    private String retailerName;

    private String retailerTel;

    private String retailerLocation;

    private long retailTime;  //零售商获得该茅台的时间戳

}