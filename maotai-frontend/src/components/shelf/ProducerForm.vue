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
      <el-input v-model="props.producerData.producerId" disabled/>
    </el-form-item>
    <el-form-item label="生产商名称">
      <el-input v-model="props.producerData.producerName" disabled/>
    </el-form-item>
    <el-form-item label="生产商电话">
      <el-input v-model="props.producerData.producerTel" disabled/>
    </el-form-item>
    <el-form-item label="生产商地址">
      <el-input v-model="props.producerData.produceLocation" disabled/>
    </el-form-item>
    <el-form-item label="生产时间">
      <el-input v-model="props.producerData.produceTime" disabled/>
    </el-form-item>
    <el-form-item label="原材料描述">
      <el-input v-model="props.producerData.rawMaterials" disabled/>
    </el-form-item>
  </el-form>

</template>