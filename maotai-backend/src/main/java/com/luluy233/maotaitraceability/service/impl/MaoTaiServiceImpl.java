package com.luluy233.maotaitraceability.service.impl;


import com.luluy233.maotaitraceability.dto.Consumer;
import com.luluy233.maotaitraceability.dto.MaotaiWine;
import com.luluy233.maotaitraceability.dto.Producer;
import com.luluy233.maotaitraceability.dto.Retailer;
import com.luluy233.maotaitraceability.service.MaoTaiService;
import com.luluy233.maotaitraceability.utils.HyperLedgerFabricProperties;
import com.luluy233.maotaitraceability.vo.*;
import lombok.AllArgsConstructor;
import org.apache.commons.codec.binary.StringUtils;
import org.checkerframework.checker.units.qual.C;
import org.hyperledger.fabric.gateway.ContractException;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Contract;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @author Luluy233
 * @date 2024/5/6
 */
@Service
@Transactional
@AllArgsConstructor
public class MaoTaiServiceImpl implements MaoTaiService {

    private final Gateway gateway;

    private final Contract contract;

    private final HyperLedgerFabricProperties hyperLedgerFabricProperties;


    /**
     * 生产商生成茅台
     * @param produceVo
     * @return
     */
    @Override
    public Response produceMaoTai(ProduceVo produceVo){

        if(produceVo.getProducerId() == null || produceVo.getProducerId().isEmpty()){
            return Response.fail(ErrorCode.PARAMS_ERROR.getCode(), "producerId can't be empty or null!");
        }
        Producer producer = new Producer();
        //复制同名属性
        BeanUtils.copyProperties(produceVo, producer);
        //获取当前时间戳
        producer.setProduceTime(System.currentTimeMillis());

        byte[] bytes = new byte[0];
        try {
            bytes = contract.submitTransaction("produce", String.valueOf(producer));
        } catch (ContractException e) {
            return Response.fail(ErrorCode.SYSTEM_ERROR.getCode(), "contract exception!");
        } catch (TimeoutException e) {
            return Response.fail(ErrorCode.SYSTEM_ERROR.getCode(), "request time out!");
        } catch (InterruptedException e) {
            return Response.fail(ErrorCode.SYSTEM_ERROR.getCode(), "interrupted Exception");
        }
        String bottleId = StringUtils.newStringUtf8(bytes);

        return Response.success(bottleId);

    }


    /**
     * 零售商上架茅台
     * @param shelfVo
     * @return
     */
    @Override
    public Response sellMaoTai(ShelfVo shelfVo) {
        if(shelfVo.getBottleId() == null || shelfVo.getBottleId().isEmpty()){
            return Response.fail(ErrorCode.PARAMS_ERROR.getCode(), "bottleId can't be empty or null!");
        }
        if(shelfVo.getRetailerId() == null){
            return Response.fail(ErrorCode.PARAMS_ERROR.getCode(), "retailerId can't be null!");
        }

        Retailer retailer = new Retailer();
        //复制同名属性
        BeanUtils.copyProperties(shelfVo, retailer);
        //获取当前时间戳
        retailer.setRetailTime(System.currentTimeMillis());

        byte[] bytes = new byte[0];
        try {
            bytes = contract.submitTransaction("sell", shelfVo.getBottleId(), String.valueOf(retailer));
        } catch (ContractException e) {
            return Response.fail(ErrorCode.SYSTEM_ERROR.getCode(), "contract exception!");
        } catch (TimeoutException e) {
            return Response.fail(ErrorCode.SYSTEM_ERROR.getCode(), "request time out!");
        } catch (InterruptedException e) {
            return Response.fail(ErrorCode.SYSTEM_ERROR.getCode(), "interrupted Exception");
        }

        return Response.success(null);
    }


    /**
     * 用户购买茅台
     * @param buyVo
     * @return
     */
    @Override
    public Response consumeMaoTai(BuyVo buyVo) {
        if(buyVo.getBottleId() == null || buyVo.getBottleId().isEmpty()){
            return Response.fail(ErrorCode.PARAMS_ERROR.getCode(), "bottleId can't be empty or null!");
        }
        if(buyVo.getConsumerId() == null){
            return Response.fail(ErrorCode.PARAMS_ERROR.getCode(), "cosumerId can't be null!");
        }

        Consumer consumer = new Consumer();
        //复制同名属性
        BeanUtils.copyProperties(buyVo, consumer);
        //获取当前时间戳
        consumer.setConsumeTime(System.currentTimeMillis());

        byte[] bytes = new byte[0];
        try {
            bytes = contract.submitTransaction("consume", buyVo.getBottleId(), String.valueOf(consumer));
        } catch (ContractException e) {
            return Response.fail(ErrorCode.SYSTEM_ERROR.getCode(), ErrorCode.SYSTEM_ERROR.getMsg());
        } catch (TimeoutException e) {
            return Response.fail(ErrorCode.SYSTEM_ERROR.getCode(), "request time out!");
        } catch (InterruptedException e) {
            return Response.fail(ErrorCode.SYSTEM_ERROR.getCode(), ErrorCode.SYSTEM_ERROR.getMsg());
        }

        return Response.success(null);
    }


    /**
     * 根据bottleId溯源
     * @param bottleId
     * @return
     */
    @Override
    public Response traceMaotaiById(String bottleId) throws ContractException {

        //参数错误
        if (bottleId == null || bottleId.isEmpty()) {
            return Response.fail(ErrorCode.PARAMS_ERROR.getCode(), "trace Maotai with bottleId " + bottleId + " failed!");
        }

        byte[] maotaiWineBytes = contract.evaluateTransaction("getMaotaiWineById", bottleId);
        String maotaiWineState = StringUtils.newStringUtf8(maotaiWineBytes);

        return Response.success(maotaiWineState);
    }


    /**
     * 获取指定状态的茅台酒列表
     * @param status
     * @return
     */
    @Override
    public Response getMaotaiWineList(Status status) {

        byte[] maotaiWineBytes = new byte[0];
        try {
            maotaiWineBytes = contract.evaluateTransaction("getMaotaiWines", String.valueOf(status));
        } catch (ContractException e) {
            return Response.fail(ErrorCode.SYSTEM_ERROR.getCode(),
                    "get MaotaiList with status " + status + " failed!");
        }
        String maotaiWines = StringUtils.newStringUtf8(maotaiWineBytes);

        return Response.success(maotaiWines);
    }


    /**
     * 按cosumerId获取用户购买的茅台酒列表
     * @param consumerId
     * @return
     */
    @Override
    public Response getMaotaiWineListByConsumer(String consumerId) {
        byte[] maotaiWineBytes = new byte[0];
        try {
            maotaiWineBytes = contract.evaluateTransaction("getMaotaiWines", consumerId);
        } catch (ContractException e) {
            return Response.fail(ErrorCode.SYSTEM_ERROR.getCode(),
                    "get MaotaiList with cosumerId " + consumerId + " failed!");

        }
        String maotaiWines = StringUtils.newStringUtf8(maotaiWineBytes);
        return Response.success(maotaiWines);
    }
}
