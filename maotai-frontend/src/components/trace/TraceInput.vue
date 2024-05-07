<script setup>

  import { Search } from '@element-plus/icons-vue'
  import {reactive, ref} from "vue";
  import {ElMessage} from "element-plus";

  const emit = defineEmits(['trace']);

  const bottleId = ref('');

  //溯源
  const onClick = () =>{

    if(bottleId.value === ''){
      ElMessage.error('茅台酒编号不能为空！');
    }
    else{
      //向后端请求数据：

      let param = {
        wine_info: Object,
      }
      emit('trace',param);

      ElMessage.success('溯源成功！' + bottleId.value);
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

<style scoped>

</style>