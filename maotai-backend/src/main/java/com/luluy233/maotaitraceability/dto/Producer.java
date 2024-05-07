package com.luluy233.maotaitraceability.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 生产商信息
 */
@Data
@Accessors(chain = true)
public class Producer {

    private String producerId;

    private String producerName;

    private String producerTel;

    private String rawMaterials;  //原材料

    private long produceTime;   //生产商生产该茅台的时间

    private String produceLocation;

}