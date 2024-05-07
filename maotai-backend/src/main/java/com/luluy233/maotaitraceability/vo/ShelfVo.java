package com.luluy233.maotaitraceability.vo;


import com.luluy233.maotaitraceability.dto.Retailer;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ShelfVo {

    private String bottleId;

    private String retailerId;

    private String retailerName;

    private String retailerTel;

    private String retailerLocation;

//    private long retailTime;  //零售商获得该茅台的时间戳
}
