<script setup>
import {ref} from "vue";
import RetailerForm from "@/components/consume/RetailerForm.vue";
import ConsumeForm from "@/components/consume/ConsumeForm.vue";
import WineList from "@/components/consume/WineList.vue"


const showForm = ref(false);
const formData = ref();
const showDialog = (val) =>{
  console.log(val);
  showForm.value = val.show_flag;
  formData.value = val.wine_info;
}

</script>


<template>
  <el-container>
    <el-main>
      <h2>所有出售中茅台列表</h2>
      <!--  出售中状态的茅台    -->
      <el-row>
        <el-col :span="24">
          <WineList @buy="showDialog"></WineList>
        </el-col>
      </el-row>

      <!--   上架茅台表单   -->
      <el-dialog
          v-model="showForm"
          title="填写消费信息"
      >
        <el-divider>零售信息</el-divider>
        <RetailerForm :retailer-data="formData"></RetailerForm>

        <el-divider>消费者信息</el-divider>
        <ConsumeForm></ConsumeForm>

      </el-dialog>

    </el-main>
  </el-container>
</template>

<style scoped>
h2 {
  text-align: center;
  margin: 0px auto 20px auto;
}
</style>