
<script setup>

  import TraceInput from "@/components/trace/TraceInput.vue";

</script>


<template>
    <el-main>
      <el-row>
        <el-col :span="6"></el-col>
        <el-col :span="12">
          <TraceInput @trace="traceWine"></TraceInput>
        </el-col>
        <el-col :span="6"></el-col>
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


</style>
