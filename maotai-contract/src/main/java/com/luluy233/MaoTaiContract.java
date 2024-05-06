package com.luluy233;


import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.*;
import org.hyperledger.fabric.shim.ledger.KeyValue;
import com.alibaba.fastjson.JSON;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.shim.ledger.QueryResultsIterator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Contract(
        name = "MaoTaiContract",
        info = @Info(
                title = "MaoTai Contract",
                description = "The hyperlegendary maotai contract",
                version = "0.0.1-SNAPSHOT",
                license = @License(
                        name = "Apache 2.0 License",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"),
                contact = @Contact(
                        email = "f.carr@example.com",
                        name = "F Carr",
                        url = "https://hyperledger.example.com")
        )
)
@Default
@Log
public class MaoTaiContract implements ContractInterface {


        /**
         * 初始化账单
         * @param ctx
         */
        @Transaction
        public void initLedger(final Context ctx){

        }


        /**
         * 生产茅台
         * @param ctx
         * @param producer
         */
        @Transaction
        public String produce(Context ctx, Producer producer) {
                String bottleId = UUID.randomUUID().toString();
                MaotaiWine maotaiWine = new MaotaiWine()
                        .setBottleId(bottleId)
                        .setProducer(producer)
                        .setConsumer(null)
                        .setRetailer(null)
                        .setStatus(Status.PRODUCED);

                ctx.getStub().putStringState(bottleId, JSON.toJSONString(maotaiWine));

                return bottleId;
        }


        /**
         * 出售茅台
         * @param ctx
         * @param bottleId
         * @param retailer
         */
        @Transaction
        public void sell(Context ctx, String bottleId, Retailer retailer) {
                String maotaiWineState = ctx.getStub().getStringState(bottleId);

                if (StringUtils.isBlank(maotaiWineState)) {
                        String errorMessage = String.format("MaotaiWine with bottleId " + bottleId + " does not exist");
                        System.out.println(errorMessage);
                        throw new ChaincodeException(errorMessage);
                }

                MaotaiWine maotaiWine = JSON.parseObject(maotaiWineState, MaotaiWine.class);
                maotaiWine.setRetailer(retailer).setStatus(Status.ONSELL);

                ctx.getStub().putStringState(bottleId, JSON.toJSONString(maotaiWine));
        }


        /**
         * 购买茅台
         * @param ctx
         * @param bottleId
         * @param consumer
         */
        @Transaction
        public void consume(Context ctx, String bottleId, Consumer consumer) {
                String maotaiWineState = ctx.getStub().getStringState(bottleId);

                if (StringUtils.isBlank(maotaiWineState)) {
                        String errorMessage = String.format("MaotaiWine with bottleId " + bottleId + " does not exist");
                        System.out.println(errorMessage);
                        throw new ChaincodeException(errorMessage);
                }

                MaotaiWine maotaiWine = JSON.parseObject(maotaiWineState, MaotaiWine.class);
                maotaiWine.setConsumer(consumer).setStatus(Status.SOLD);

                ctx.getStub().putStringState(bottleId, JSON.toJSONString(maotaiWine));
        }


        /**
         * 茅台酒溯源：单瓶
         * @param ctx
         * @param bottleId
         * @return
         */
        @Transaction
        public MaotaiWine getMaotaiWineById(Context ctx, String bottleId) {
                String maotaiWineState = ctx.getStub().getStringState(bottleId);

                if (StringUtils.isBlank(maotaiWineState)) {
                        String errorMessage = String.format("MaotaiWine with bottleId " + bottleId + " does not exist");
                        System.out.println(errorMessage);
                        throw new ChaincodeException(errorMessage);
                }

                return JSON.parseObject(maotaiWineState, MaotaiWine.class);
        }


        /**
         * 获取指定状态的茅台酒列表
         * @param ctx
         * @param status Status.PRODUCED/ONSELL/SOLD
         * @return
         */
        @Transaction
        public List<MaotaiWine> getMaotaiWines(Context ctx, Status status) {
                QueryResultsIterator<KeyValue> results = ctx.getStub().getStateByRange("", "");

                List<MaotaiWine> maotaiWines = new ArrayList<>();

                for (KeyValue result : results) {
                        String maotaiWineState = result.getStringValue();
                        MaotaiWine maotaiWine = JSON.parseObject(maotaiWineState, MaotaiWine.class);
                        if(maotaiWine.getStatus() == status){
                                maotaiWines.add(maotaiWine);
                        }
                }

                return maotaiWines;
        }


        /**
         * 获取指定用户购买的茅台酒列表
         * @param ctx
         * @param consumerId
         * @return
         */
        @Transaction
        public List<MaotaiWine> getMaotaiWinesByComsumer(Context ctx, String consumerId) {
                QueryResultsIterator<KeyValue> results = ctx.getStub().getStateByRange("", "");

                List<MaotaiWine> maotaiWines = new ArrayList<>();

                for (KeyValue result : results) {
                        String maotaiWineState = result.getStringValue();
                        MaotaiWine maotaiWine = JSON.parseObject(maotaiWineState, MaotaiWine.class);
                        if(maotaiWine.getStatus() == Status.SOLD && maotaiWine.getConsumer().getConsumerId() == consumerId){
                                maotaiWines.add(maotaiWine);
                        }
                }

                return maotaiWines;
        }
}
