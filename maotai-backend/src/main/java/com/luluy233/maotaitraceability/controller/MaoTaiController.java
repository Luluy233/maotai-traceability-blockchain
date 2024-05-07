package com.luluy233.maotaitraceability.controller;

import com.luluy233.maotaitraceability.dto.MaotaiWine;
import com.luluy233.maotaitraceability.service.MaoTaiService;
import com.luluy233.maotaitraceability.vo.*;
import org.hyperledger.fabric.gateway.ContractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.TimeoutException;


/**
 * @author Luluy233
 * @date 2024/5/6
 */
@RestController
@RequestMapping("/maotai")
public class MaoTaiController {

    @Autowired
    private MaoTaiService maoTaiService;


    /**
     * 生产茅台酒
     * @param produceVo
     * @return
     */
    @PostMapping("produce")
    public Response produceMaoTai(@RequestBody ProduceVo produceVo) throws ContractException, InterruptedException, TimeoutException {
        return maoTaiService.produceMaoTai(produceVo);
    }


    /**
     * 商家上架茅台
     * @param shelfVo
     * @return
     */
    @PostMapping("shelf")
    public Response sellMaoTai(@RequestBody ShelfVo shelfVo) {
        return maoTaiService.sellMaoTai(shelfVo);
    }


    /**
     * 用户购买茅台酒
     * @param buyVo
     * @return
     */
    @PostMapping("buy")
    public Response buyMaoTai(@RequestBody BuyVo buyVo){
        return maoTaiService.consumeMaoTai(buyVo);
    }


    /**
     * 按bottleId溯源
     * @param bottleId
     * @return
     */
    @GetMapping("trace")
    public Response traceById(@RequestParam("bottleId") String bottleId) throws ContractException, UnsupportedEncodingException {
        return maoTaiService.traceMaotaiById(bottleId);
    }


    /**
     * 获得指定状态的茅台酒列表
     * @param status
     * @return
     */
    @GetMapping("status-wines")
    public Response getByStatus(@RequestParam("status") Status status){
        //Spring会自动尝试将字符串参数转换为枚举类型
        return maoTaiService.getMaotaiWineList(status);
    }


    /**
     * 按userId获取指定用户购买过的茅台列表
     * @param userId
     * @return
     */
    @GetMapping("user-wines")
    public Response getByUserId(@RequestParam("userId") String userId){
        return maoTaiService.getMaotaiWineListByConsumer(userId);
    }
}
