<script setup>
import WineList from "@/components/shelf/WineList.vue";
import {ref} from "vue";
import ShelfFormDialog from "@/components/shelf/ShelfForm.vue";
import ProducerForm from "@/components/shelf/ProducerForm.vue";

  const showForm = ref(false);
  const formData = ref();
  const showDialog = (val) =>{
    showForm.value = val.show_flag;
    formData.value = val.wine_info;
  }

</script>


<template>
  <el-container>
    <el-main>
        <h2>所有已生产茅台列表</h2>
      <!--  已生产状态的茅台    -->
      <el-row>
        <el-col :span="24">
          <WineList @shelf="showDialog"></WineList>
        </el-col>
      </el-row>

      <!--   上架茅台表单   -->
      <el-dialog
          v-model="showForm"
          title="填写上架信息"
      >
        <el-divider>生产信息</el-divider>
        <ProducerForm :producer-data="formData"></ProducerForm>

        <el-divider>上架信息</el-divider>
        <ShelfFormDialog :bottleId="formData.bottleId"></ShelfFormDialog>

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