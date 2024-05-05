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
                <el-col :span="24" class="center">邮箱：1156504938@qq.com</el-col>
            </el-row>
            <el-row>
                <el-col :span="24" class="center">Copyright © @Luluy233</el-col>
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
</style>