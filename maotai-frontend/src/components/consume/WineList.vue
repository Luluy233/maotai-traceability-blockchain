<script setup>
import {computed, onMounted, ref} from 'vue'
import {getWinesByStatus} from "@/utils/api.js";
import {ElMessage} from "element-plus";

  const wines = ref([]);

  const emit = defineEmits(['buy']);

  //获取茅台列表
  onMounted(()=>{
    getWinesByStatus('ONSALE')
        .then(res =>{
          wines.value = res.data;
          ElMessage.success('获取茅台出售列表成功！');
        })
        .catch(err =>{
          ElMessage.error('获取茅台出售列表失败！');
        })
  })

  //分页相关数据
  const currentPage = ref(1)  //当前页数，默认为第1页
  const pageSize = 10 //每页的图片数量，设置为8
  // 计算属性，计算imageList中图片对应的行；每行4列
  const items = computed(() => {
    const start=(currentPage.value - 1) * pageSize; //当前页的起始数据编号
    const end = start + pageSize;  //当前页的最后数据号
    const paginatedItems = wines.value.slice(start,end);
    return paginatedItems;
  })

  //购买茅台
  const showDialog = (index) =>{
    let param ={
      show_flag: true,
      wine_info: items.value[index]
    }
    //传递给父组件
    emit('buy',param)
  }

</script>


<template>
  <el-table :data="items" border stripe
            :default-sort="{ prop: 'retailTime', order: 'descending' }"
  >
    <el-table-column prop="retailer.retailTime" label="上架时间" sortable></el-table-column>
    <el-table-column prop="bottleId" label="茅台编号"></el-table-column>
    <el-table-column prop="retailer.retailerId" label="零售商商编号"></el-table-column>
    <el-table-column prop="retailer.retailerName" label="零售商"></el-table-column>
    <el-table-column prop="retailer.retailerLocation" label="零售商地址"></el-table-column>
    <el-table-column prop="buy" label="购买">
      <template v-slot="scope">
        <el-button type="primary" @click="showDialog(scope.$index)" round>购买</el-button>
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