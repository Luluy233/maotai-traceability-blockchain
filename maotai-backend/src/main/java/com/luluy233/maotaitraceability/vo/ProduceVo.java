package com.luluy233.maotaitraceability.vo;


import com.luluy233.maotaitraceability.dto.Producer;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProduceVo {

    private String producerId;

    private String producerName;

    private String producerTel;

    private String rawMaterials;  //原材料

//    private long produceTime;   //生产商生产该茅台的时间

    private String produceLocation;

}
