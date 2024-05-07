<script setup>

  import { Search } from '@element-plus/icons-vue'
  import { ref} from "vue";
  import {ElMessage} from "element-plus";
  import {traceWine} from "@/utils/api.js";

  const emit = defineEmits(['trace']);

  const bottleId = ref('');

  //溯源
  const onClick = () =>{
    if(bottleId.value === ''){
      ElMessage.error('茅台酒编号不能为空！');
    }
    else{
      //向后端请求数据：
      traceWine(bottleId.value)
          .then(res => {
            console.log(res.data);
            let param = {
              wine_info: res.data,
            }
            emit('trace',param);

            ElMessage.success('溯源成功！' + bottleId.value);
          })
          .catch(err =>{
            ElMessage.error('溯源失败！')
          })
    }
  }

</script>


<template>
  <el-input
      v-model="bottleId"
      size="large"
      placeholder="请输入茅台酒编号"
  >
    <template #append>
      <el-button :icon="Search" @click="onClick" type="primary"/>
    </template>
  </el-input>
</template>
