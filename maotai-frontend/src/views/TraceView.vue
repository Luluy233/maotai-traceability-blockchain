
<script setup>

  import TraceInput from "@/components/trace/TraceInput.vue";
  import ResultCard from "@/components/trace/ResultCard.vue";
  import {ref} from "vue";

  const traceData = ref('');
  const traceWine = (val) =>{
    traceData.value = val.wine_info;
  }

</script>


<template>
    <el-main>
      <!--  搜索框   -->
      <el-row>
        <el-col :span="6"></el-col>
        <el-col :span="12">
          <TraceInput @trace="traceWine"></TraceInput>
        </el-col>
        <el-col :span="6"></el-col>
      </el-row>

      <!--  溯源结果    -->
      <el-row class="result-row">
        <el-col :span="4"></el-col>
        <el-col :span="16">
          <ResultCard :trace-data="traceData" v-if="traceData!=''"></ResultCard>
        </el-col>
        <el-col :span="4"></el-col>
      </el-row>
    </el-main>
</template>

<style scoped>
  .trace-container {
    position: relative;
    min-height: 100vh;
  }

  .trace-container::after {
    content: "";
    background-image: url("@/assets/TraceImg.jpg");
    opacity: 0.5;
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    filter: blur(3px); /* 设置模糊效果的半径，这里的半径为 5px */
  }

  .result-row{
    margin:30px;
  }


</style>
