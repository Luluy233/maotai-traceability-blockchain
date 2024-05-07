package com.luluy233.maotaitraceability.service;


import com.luluy233.maotaitraceability.dto.MaotaiWine;
import com.luluy233.maotaitraceability.vo.*;
import org.hyperledger.fabric.gateway.ContractException;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @author Luluy233
 * @date 2024/5/6
 */
public interface MaoTaiService {

    /**
     * 生产茅台
     * @param produceVo
     * @return bottleId新生产的茅台Id
     */
    Response produceMaoTai(ProduceVo produceVo) throws ContractException, InterruptedException, TimeoutException;


    /**
     * 出售id为bottleId的茅台
     * @param shelfVo
     * @return MaotaiWine
     */
    Response sellMaoTai(ShelfVo shelfVo);


    /**
     * 购买id为bottleId的茅台
     * @param buyVo
     */
    Response consumeMaoTai(BuyVo buyVo);


    /**
     * 茅台酒溯源：指定bottleId
     * @param bottleId
     * @return
     */
    Response traceMaotaiById(String bottleId) throws UnsupportedEncodingException, ContractException;


    /**
     * 获取指定状态的茅台酒列表
     * @param status
     * @return
     */
    Response getMaotaiWineList(Status status);


    /**
     * 获取指定用户购买的茅台酒列表
     * @param cosumerId
     * @return
     */
    Response getMaotaiWineListByConsumer(String cosumerId);





}
