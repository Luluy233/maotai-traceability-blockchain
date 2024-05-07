<template>
    <div class="container">
        <img src="@/assets/FootLogo.png" class="center-image">
    </div>
    <el-row class="center">
        <el-col :span="24">
            <el-row>
                <el-col :span="24" class="center">联系地址：上海市嘉定区安亭镇4800号</el-col>
            </el-row>
            <el-row>
                <el-col :span="24" class="center">邮箱：wayi_star@163.com</el-col>
            </el-row>
            <el-row>
                <el-col :span="24" class="center">
                  <a href="https://github.com/Luluy233/maotai-traceability-blockchain" target="_blank" class="custom-link">Copyright © @Luluy233</a>
                </el-col>
            </el-row>
        </el-col>
    </el-row>
</template>

<script setup>
    import { ElCol, ElRow } from 'element-plus';
</script>

<style scoped>
    .center {
        display: flex;
        justify-content: center;
    }

    .container {
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .center-image {
        margin: 20px;
        max-width: 300px;
        max-height: 100%;
    }

    .custom-link {
      color: #000; /* 设置链接的默认颜色 */
      text-decoration: none; /* 移除下划线 */
      transition: color 0.3s; /* 添加颜色过渡效果 */
    }

    .custom-link:hover {
      color: rgb(55, 53, 174); /* 鼠标悬浮时的颜色 */
    }

</style>