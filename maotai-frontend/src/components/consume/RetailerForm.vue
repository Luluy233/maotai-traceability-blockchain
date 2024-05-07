<script setup>

const props = defineProps({
  retailerData: {
    type: Object,
  }
})

</script>


<template>
  <el-form :model="props.retailerData"
           ref="formRef"
           label-width="auto"
           label-position="left">
    <el-form-item label="茅台编号">
      <el-input v-model="props.retailerData.bottleId" disabled/>
    </el-form-item>
    <el-form-item label="零售商编号">
      <el-input v-model="props.retailerData.retailerId" disabled/>
    </el-form-item>
    <el-form-item label="零售商名称">
      <el-input v-model="props.retailerData.retailerName" disabled/>
    </el-form-item>
    <el-form-item label="零售商地址">
      <el-input v-model="props.retailerData.retailerLocation" disabled/>
    </el-form-item>
    <el-form-item label="上架时间">
      <el-input v-model="props.retailerData.retailTime" disabled/>
    </el-form-item>
  </el-form>

</template>