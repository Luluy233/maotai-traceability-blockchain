<script setup>
import {computed, onMounted, ref} from 'vue'
import {getWinesByStatus} from "@/utils/api.js";
import {ElMessage} from "element-plus";

  const wines = ref([]);

  const emit = defineEmits(['shelf']);

  onMounted(()=>{
    getWinesByStatus('PRODUCED')
        .then(res =>{
          wines.value = res.data;
          ElMessage.success('获取已生产茅台列表成功！');
        })
        .catch(err =>{
          ElMessage.error('获取已生产茅台列表失败！');
        })
  })

  const currentPage = ref(1)  //当前页数，默认为第1页
  const pageSize = 10 //每页的图片数量，设置为8
  // 计算属性，计算imageList中图片对应的行；每行4列
  const items = computed(() => {
    const start=(currentPage.value - 1) * pageSize; //当前页的起始数据编号
    const end = start + pageSize;  //当前页的最后数据号
    const paginatedItems = wines.value.slice(start,end);
    return paginatedItems;
  })


  //上架茅台
  const showDialog = (index) =>{
    let param ={
      show_flag: true,
      wine_info: items.value[index]
    }
    //传递给父组件
    emit('shelf',param)
}
</script>


<template>
  <el-table :data="items" border stripe
            :default-sort="{ prop: 'produceTime', order: 'descending' }"
            >
    <el-table-column prop="producer.produceTime" label="生产时间" sortable></el-table-column>
    <el-table-column prop="bottleId" label="茅台编号"></el-table-column>
    <el-table-column prop="producer.producerId" label="生产商编号"></el-table-column>
    <el-table-column prop="producer.producerName" label="生产商"></el-table-column>
    <el-table-column prop="producer.produceLocation" label="生产地"></el-table-column>
    <el-table-column prop="producer.rawMaterials" label="原材料"></el-table-column>
    <el-table-column prop="shelf" label="上架">
      <template v-slot="scope">
        <el-button type="primary" @click="showDialog(scope.$index)" round>上架</el-button>
      </template>
    </el-table-column>
  </el-table>
  <!-- 分页 -->
  <el-pagination
      class="pagination"
      v-model:current-page="currentPage"
      v-model:page-size="pageSize"
      :total = "wines.length">
  </el-pagination>
</template>


<style scoped>
  .pagination{
    margin-left:10%;
    width:900px;
    margin-top:20px;
    margin-bottom:20px;
    justify-content: center;
    text-align:center;
  }
</style>