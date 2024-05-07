package com.luluy233.maotaitraceability.dto;

import com.luluy233.maotaitraceability.vo.Status;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * 茅台酒溯源信息
 */
@Data
@Accessors(chain = true)
public class MaotaiWine {

    private String bottleId;

    private Status status;

    private Producer producer;

    private Retailer retailer;

    private Consumer consumer;
}
