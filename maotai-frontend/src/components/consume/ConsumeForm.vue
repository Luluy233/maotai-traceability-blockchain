<script setup>
import { reactive, ref } from 'vue';
import { ElForm, ElFormItem, ElMessage, ElInput } from 'element-plus'
import {buyWine} from "@/utils/api.js";

  const props = defineProps({
    bottleId: String,
  })

  //表单数据
  const ConsumerForm = reactive({
    bottleId: '',
    consumerId: '',
    consumerName: '',
  })

  //表单引用
  const formRef = ref(null);

  //表单规则
  const rules = reactive({
    consumerId:[
      { required: true, message: '消费者编号不能为空！', trigger: 'blur' },
    ],
    consumerName:[]
  })

  //提交表单
  const submitForm = () =>{
    ConsumerForm.bottleId = props.bottleId;
    formRef.value.validate((valid) => {
      if (valid){ //表单验证成功，可以提交
        // 提交表单
        buyWine(ConsumerForm)
            .then(res => {
              ElMessage.success('购买成功！');
            })
            .catch(err =>{
              ElMessage.error('购买失败，请重试！');
            })
      }else {
        // 表单验证不通过，给出错误提示
        ElMessage.error('请检查信息是否符合要求！')
      }
    });
  }

  //重置表单
  const resetForm = () =>{
    console.log('reset', ConsumerForm);
    formRef.value.resetFields();
  }
</script>


<template>
  <el-form :model="ConsumerForm"
           :rules="rules"
           ref="formRef"
           label-width="auto"
           label-position="left"
           size="large"
           status-icon>
    <el-form-item label="消费者编号" prop="consumerId">
      <el-input v-model="ConsumerForm.consumerId" placeholder="请输入消费者编号" />
    </el-form-item>
    <el-form-item label="消费者昵称" prop="consumerName">
      <el-input v-model="ConsumerForm.consumerName" placeholder="请输入消费者昵称" />
    </el-form-item>
    <el-form-item class="button-item">
      <el-row style="width: 60vw">
        <el-col :span="9" :offset="6">
          <el-button type="primary" @click="submitForm()">提交</el-button>
        </el-col>
        <el-col :span="9">
          <el-button @click="resetForm()">重置</el-button>
        </el-col>
      </el-row>
    </el-form-item>
  </el-form>

</template>



<style scoped>

</style>>