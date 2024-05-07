<template>
  <el-container>
    <el-main>
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