<script setup>
import {computed, ref} from 'vue'

const wines = ref([]);

const emit = defineEmits(['buy']);

// 生成20个数组元素
for (let i = 0; i < 21; i++) {
  const bottleId = generateBottleId(); // 生成瓶号
  const retailerId = generateRetailerId(); // 生成生产商ID
  const retailerName = generateRetailerName(); // 生成生产商名称
  const retailerLocation = generateRetailerLocation(); // 生成生产地点
  const retailTime = generateRetailTime(); // 生成生产时间
  wines.value.push({
    bottleId,
    retailerId,
    retailerName,
    retailerLocation,
    retailTime,
  });
}

// 随机生成瓶号
function generateBottleId() {
  return 'MT-' + Math.floor(Math.random() * 10000000000);
}

// 随机生成生产商ID
function generateRetailerId() {
  return 'RTL-' + Math.floor(Math.random() * 10000000000);
}

// 随机生成生产商名称
function generateRetailerName() {
  const retailerNames = ['遵南大道贵州茅台专卖店', '人民路贵州茅台专卖店', '上海路贵州茅台专卖店', '东欣大道贵州茅台专卖店'];
  return retailerNames[Math.floor(Math.random() * retailerNames.length)];
}

// 随机生成生产地点
function generateRetailerLocation() {
  const locations = ['人民路中海九樾1栋19号商铺', '上海路壹品城1单元2层', '龙坑街道爱特购智慧新城A区一层','保利未来城市四街区B'];
  return locations[Math.floor(Math.random() * locations.length)];
}

// 随机生成生产时间
function generateRetailTime() {
  const startYear = 2024;
  const endYear = 2024;
  const year = Math.floor(Math.random() * (endYear - startYear + 1) + startYear);

  const month = (Math.floor(Math.random() * 1) + 5).toString().padStart(2, '0');
  const day = (Math.floor(Math.random() * 8) + 1).toString().padStart(2, '0');

  const hours = (Math.floor(Math.random() * 24)).toString().padStart(2, '0');
  const minutes = (Math.floor(Math.random() * 60)).toString().padStart(2, '0');
  const seconds = (Math.floor(Math.random() * 60)).toString().padStart(2, '0');

  return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
}


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
  console.log(param);
  //传递给父组件
  emit('buy',param)
}

</script>



<template>
  <el-table :data="items" border stripe
            :default-sort="{ prop: 'retailTime', order: 'descending' }"
  >
    <el-table-column prop="retailTime" label="生产时间" sortable></el-table-column>
    <el-table-column prop="bottleId" label="茅台编号"></el-table-column>
    <el-table-column prop="retailerId" label="零售商商编号"></el-table-column>
    <el-table-column prop="retailerName" label="零售商"></el-table-column>
    <el-table-column prop="retailerLocation" label="零售商地址"></el-table-column>
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