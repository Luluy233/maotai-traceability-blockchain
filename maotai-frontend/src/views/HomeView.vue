<template>
  <el-main>
    <div class="centered-image">
      <img src="@/assets/home/head.png" alt="Image">
    </div>
    <el-carousel height="80vh">
      <el-carousel-item >
        <img src="@/assets/home/img1.png" alt="MaoTai Image" class="carousel-image" />
      </el-carousel-item>
      <el-carousel-item >
        <img src="@/assets/home/img2.png" alt="MaoTai Image" class="carousel-image" />
      </el-carousel-item>
      <el-carousel-item >
        <img src="@/assets/home/img3.png" alt="MaoTai Image" class="carousel-image" />
      </el-carousel-item>
      <el-carousel-item >
        <img src="@/assets/home/img4.png" alt="MaoTai Image" class="carousel-image" />
      </el-carousel-item>
    </el-carousel>
  </el-main>
</template>

<script setup>
import { ElCarousel, ElCarouselItem } from "element-plus"
</script>


<style scoped>
.carousel-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.centered-image {
  display: flex;
  justify-content: center;
  align-items: center;
}

.centered-image img {
  height: 10vh;
  margin-bottom: 20px;
}
</style>