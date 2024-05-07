<script setup>
  const props = defineProps({
    traceData: Object
  })
</script>


<template>
    <el-card class="card">
      <el-divider>
        <span class="card-header">生产信息</span>
      </el-divider>
      <p>生产商编号：{{traceData.producer.producerId}}</p>
      <p>生产商名称：{{traceData.producer.producerName}}</p>
      <p>生产商电话：{{traceData.producer.producerTel}}</p>
      <p>生产地址：{{traceData.producer.produceLocation}}</p>
      <p>选用原料：{{traceData.producer.rawMaterials}}</p>
      <p>生产时间：{{traceData.producer.produceTime}}</p>

      <el-divider>
        <span class="card-header">零售信息</span>
      </el-divider>
      <p>零售商编号：{{traceData.retailer.retailerId}}</p>
      <p>零售商名称：{{traceData.retailer.retailerName}}</p>
      <p>零售商电话：{{traceData.retailer.retailerTel}}</p>
      <p>零售商地址：{{traceData.retailer.retailerLocation}}</p>
      <p>上架时间：{{traceData.retailer.retailTime}}</p>

      <el-divider>
        <span class="card-header">消费信息</span>
      </el-divider>
      <p>消费者编号：{{traceData.consumer.consumerId}}</p>
      <p>消费者名称：{{traceData.consumer.consumerName}}</p>
      <p>消费时间：{{traceData.consumer.consumeTime}}</p>
    </el-card>

</template>


<style scoped>

  .card-header{
    font-weight: bolder !important;
    font-size: larger !important;
    color: #6564c8 !important;
  }

  .card{
    padding-left: 50px;
    padding-right: 50px;
  }

</style>