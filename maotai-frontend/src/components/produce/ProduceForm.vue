<script setup>
import { reactive, ref } from 'vue';
import { ElForm, ElFormItem, ElMessage, ElInput } from 'element-plus'

  //表单数据
  const ProduceForm = reactive({
    producerId: '',
    producerName: '',
    producerTel: '',
    produceLocation: '',
    rawMaterials: '',
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
      console.log("请输入合法手机号")
      callback(new Error('请输入11位合法手机号码'));
    }
  }

  //表单规则
  const rules = reactive({
    producerId:[
      { required: true, message: '生产商编号不能为空！', trigger: 'blur' },
    ],
    producerName:[
      { required: true, message: '生产商名称不能为空', trigger: 'blur' },
    ],
    producerTel:[
      {required: true, message: '联系方式不能为空',trigger: 'blur'},
      {validator: checkTel, trigger: 'blur'},
    ],
    produceLocation:[
      {required: true, message: '生产地址不能为空', trigger: 'blur'},
    ],
    rawMaterials:[]
  })

  //提交表单
  const submitForm = () =>{
    console.log('submit：', ProduceForm);
    formRef.value.validate((valid) => {
      if (valid){ //表单验证成功，可以提交
        // 提交表单
        ElMessage.success('生产成功！');

      }else {
        // 表单验证不通过，给出错误提示
        ElMessage.error('请检查生产信息是否符合要求！')
      }
    });
  }

  //重置表单
  const resetForm = () =>{
    console.log('reset', ProduceForm);
    formRef.value.resetFields();
  }
</script>


<template>
  <el-form :model="ProduceForm"
           :rules="rules"
           ref="formRef"
           label-width="auto"
           label-position="left"
           size="large"
           status-icon>
    <el-form-item label="生产商编号" prop="producerId">
      <el-input v-model="ProduceForm.producerId" placeholder="请输入生产商ID" />
    </el-form-item>
    <el-form-item label="生产商名称" prop="producerName">
      <el-input v-model="ProduceForm.producerName" placeholder="请输入生产商名称"
            maxlength="100" show-word-limit/>
    </el-form-item>
    <el-form-item label="生产商电话" prop="producerTel">
      <el-input v-model="ProduceForm.producerTel" placeholder="请输入生产商电话" />
    </el-form-item>
    <el-form-item label="生产商地址" prop="produceLocation">
      <el-input v-model="ProduceForm.produceLocation" placeholder="请输入生产商地址"
                maxlength="100" show-word-limit />
    </el-form-item>
    <el-form-item label="原材料描述" prop="rawMaterials">
      <el-input v-model="ProduceForm.rawMaterials" placeholder="请输入原材料" type="textarea"/>
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

</style>