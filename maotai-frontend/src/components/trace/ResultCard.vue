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
      <p>生产商编号：{{traceData.producerId}}</p>
      <p>生产商名称：{{traceData.producerName}}</p>
      <p>生产商电话：{{traceData.producerTel}}</p>
      <p>生产地址：{{traceData.producerLocation}}</p>
      <p>选用原料：{{traceData.rawMaterials}}</p>
      <p>生产时间：{{traceData.produceTime}}</p>

      <el-divider>
        <span class="card-header">零售信息</span>
      </el-divider>
      <p>零售商编号：{{traceData.retailerId}}</p>
      <p>零售商名称：{{traceData.retailerName}}</p>
      <p>零售商电话：{{traceData.retailerTel}}</p>
      <p>零售商地址：{{traceData.retailerLocation}}</p>
      <p>上架时间：{{traceData.retaileTime}}</p>

      <el-divider>
        <span class="card-header">消费信息</span>
      </el-divider>
      <p>消费者编号：{{traceData.consumerId}}</p>
      <p>消费者名称：{{traceData.consumerName}}</p>
      <p>消费时间：{{traceData.consumeTime}}</p>
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