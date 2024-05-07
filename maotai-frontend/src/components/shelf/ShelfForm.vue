<script setup>
import { reactive, ref } from 'vue';
import { ElForm, ElFormItem, ElMessage, ElInput } from 'element-plus'
import {shelfWine} from "@/utils/api.js";

  const props = defineProps({
    bottleId: String,
  })

  //表单数据
  const RetailerForm = reactive({
    bottleId: '',
    retailerId: '',
    retailerName: '',
    retailerTel: '',
    retailerLocation: '',
  })

  //表单引用
  const formRef = ref(null);

  //电话合法性检验
  const checkTel = (rule, value, callback) => {
    const reg = /^1[3-9]\d{9}$/;
    if(reg.test(value)){
      callback();
    }
    else{
      callback(new Error('请输入11位合法手机号码'));
    }
  }

  //表单规则
  const rules = reactive({
    retailerId:[
      { required: true, message: '零售商编号不能为空！', trigger: 'blur' },
    ],
    retailerName:[
      { required: true, message: '零售商名称不能为空', trigger: 'blur' },
    ],
    retailerTel:[
      {required: true, message: '联系方式不能为空',trigger: 'blur'},
      {validator: checkTel, trigger: 'blur'},
    ],
    retailerLocation:[
      {required: true, message: '零售商地址不能为空', trigger: 'blur'},
    ]
  })

  //提交表单
  const submitForm = () =>{
    RetailerForm.bottleId = props.bottleId;
    formRef.value.validate((valid) => {
      if (valid){ //表单验证成功，可以提交
        // 提交表单
        shelfWine(RetailerForm)
            .then(res => {
              ElMessage.success('上架成功！');
            })
            .catch(err =>{
              ElMessage.error('上架失败，请重试！')
            })
      }else {
        // 表单验证不通过，给出错误提示
        ElMessage.error('请检查信息是否符合要求！')
      }
    });
  }

  //重置表单
  const resetForm = () =>{
    formRef.value.resetFields();
  }

</script>


<template>
    <el-form :model="RetailerForm"
             :rules="rules"
             ref="formRef"
             label-width="auto"
             label-position="left"
             size="large"
             status-icon>
      <el-form-item label="零售商编号" prop="retailerId">
        <el-input v-model="RetailerForm.retailerId" placeholder="请输入零售商ID" />
      </el-form-item>
      <el-form-item label="零售商名称" prop="retailerName">
        <el-input v-model="RetailerForm.retailerName" placeholder="请输入零售商名称"
                  maxlength="100" show-word-limit/>
      </el-form-item>
      <el-form-item label="零售商电话" prop="retailerTel">
        <el-input v-model="RetailerForm.retailerTel" placeholder="请输入零售商电话" />
      </el-form-item>
      <el-form-item label="零售商地址" prop="retailerLocation">
        <el-input v-model="RetailerForm.retailerLocation" placeholder="请输入零售商地址"
                  maxlength="100" show-word-limit />
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
