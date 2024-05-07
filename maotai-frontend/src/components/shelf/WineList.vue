<script setup>
import {computed, ref} from 'vue'

  const wines = ref([]);

  const emit = defineEmits(['shelf']);

  // 生成20个数组元素
  for (let i = 0; i < 100; i++) {
    const bottleId = generateBottleId(); // 生成瓶号
    const producerId = generateProducerId(); // 生成生产商ID
    const producerName = generateProducerName(); // 生成生产商名称
    const produceLocation = generateProduceLocation(); // 生成生产地点
    const produceTime = generateProduceTime(); // 生成生产时间
    const rawMaterials = generateRawMaterials(); // 生成原材料
    wines.value.push({
      bottleId,
      producerId,
      producerName,
      produceLocation,
      produceTime,
      rawMaterials,
    });
  }

  // 随机生成瓶号
  function generateBottleId() {
    return 'MT' + Math.floor(Math.random() * 1000000);
  }

  // 随机生成生产商ID
  function generateProducerId() {
    return 'PID' + Math.floor(Math.random() * 1000);
  }

  // 随机生成生产商名称
  function generateProducerName() {
    const producerNames = ['茅台集团', '贵州茅台', '国窖1573', '茅台镇酒厂'];
    return producerNames[Math.floor(Math.random() * producerNames.length)];
  }

  // 随机生成生产地点
  function generateProduceLocation() {
    const locations = ['茅台镇', '贵州省遵义市', '中国贵州茅台酒厂'];
    return locations[Math.floor(Math.random() * locations.length)];
  }

  // 随机生成生产时间
  function generateProduceTime() {
    const startYear = 1950;
    const endYear = 2022;
    const year = Math.floor(Math.random() * (endYear - startYear + 1) + startYear);

    const month = (Math.floor(Math.random() * 12) + 1).toString().padStart(2, '0');
    const day = (Math.floor(Math.random() * 28) + 1).toString().padStart(2, '0');

    return `${year}-${month}-${day}`;
  }

  // 随机生成原材料
  function generateRawMaterials() {
    const rawMaterials = ['高粱', '小麦', '大米', '水'];
    const materialsCount = Math.floor(Math.random() * 3) + 1;
    const selectedMaterials = [];
    for (let i = 0; i < materialsCount; i++) {
      const material = rawMaterials[Math.floor(Math.random() * rawMaterials.length)];
      if (!selectedMaterials.includes(material)) {
        selectedMaterials.push(material);
      }
    }
    return selectedMaterials.join(', ');
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


  //上架茅台
  const showDialog = (index) =>{
    let param ={
      show_flag: true,
      wine_info: items.value[index]
    }
    console.log(param);
    //传递给父组件
    emit('shelf',param)
}

</script>



<template>
  <el-table :data="items" border stripe>
    <el-table-column prop="produceTime" label="生产时间"></el-table-column>
    <el-table-column prop="bottleId" label="茅台编号"></el-table-column>
    <el-table-column prop="producerId" label="生产商编号"></el-table-column>
    <el-table-column prop="producerName" label="生产商"></el-table-column>
    <el-table-column prop="produceLocation" label="生产地"></el-table-column>
    <el-table-column prop="rawMaterials" label="原材料"></el-table-column>
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