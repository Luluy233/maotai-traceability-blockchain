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
      const traceData = ref({
        producerId: 'PRD-8432991243',
        producerName: '国窑197茅台厂',
        producerTel: '020-12345678',
        producerLocation: '贵州省遵义市茅台县',
        rawMaterials: '优质红高粱，软质白小麦，水',
        produceTime: '2023-05-01 10:00:49',

        retailerId: 'RTL-9484520346',
        retailerName: '人民路贵州茅台专卖区',
        retailerTel: '021-87654321',
        retailerLocation: '上海路壹品城1单元2层',
        retaileTime: '2023-05-04 11:23:49',

        consumerId: 'CUS-5493852385',
        consumerName: '张三',
        consumeTime: '2023-05-7 18:45:20',
      })
      console.log('TraceInput',traceData.value);

      let param = {
        wine_info: traceData.value,
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