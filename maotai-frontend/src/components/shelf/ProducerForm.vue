<script setup>

  const props = defineProps({
    producerData: {
      type: Object,
    }
  })

</script>


<template>
  <el-form :model="props.producerData"
           ref="formRef"
           label-width="auto"
           label-position="left">
    <el-form-item label="茅台编号">
      <el-input v-model="props.producerData.bottleId" disabled/>
    </el-form-item>
    <el-form-item label="生产商编号">
      <el-input v-model="props.producerData.producer.producerId" disabled/>
    </el-form-item>
    <el-form-item label="生产商名称">
      <el-input v-model="props.producerData.producer.producerName" disabled/>
    </el-form-item>
    <el-form-item label="生产商电话">
      <el-input v-model="props.producerData.producer.producerTel" disabled/>
    </el-form-item>
    <el-form-item label="生产商地址">
      <el-input v-model="props.producerData.producer.produceLocation" disabled/>
    </el-form-item>
    <el-form-item label="生产时间">
      <el-input v-model="props.producerData.producer.produceTime" disabled/>
    </el-form-item>
    <el-form-item label="原材料描述">
      <el-input v-model="props.producerData.producer.rawMaterials" disabled/>
    </el-form-item>
  </el-form>
</template>