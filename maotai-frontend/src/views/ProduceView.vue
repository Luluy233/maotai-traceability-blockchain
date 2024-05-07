<template>
  <el-container>
    <el-main>
      <h2>请输入生产信息</h2>
      <el-row>
        <el-col :span="4"></el-col>
        <el-col :span="16"><ProduceForm /></el-col>
        <el-col :span="4"></el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script setup>
import ProduceForm from "@/components/produce/ProduceForm.vue";

</script>

<style scoped>
  h2 {
    text-align: center;
    margin: 0px auto 20px auto;
  }
</style>