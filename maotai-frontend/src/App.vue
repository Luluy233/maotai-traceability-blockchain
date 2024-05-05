<script setup>
import { RouterView } from 'vue-router'
import { ElContainer, ElHeader, ElMain, ElAside, ElFooter } from 'element-plus'
import AsideMenu from '@/components/common/AsideMenu.vue'
import MyFooter from '@/components/common/MyFooter.vue'
</script>

<template>
  <el-container id="app">

    <el-container>
      <el-aside class="aside">
        <AsideMenu></AsideMenu>
      </el-aside>

      <el-container>
        <el-main class="main">
          <router-view></router-view>
        </el-main>

        <el-footer class="footer">
          <MyFooter></MyFooter>
        </el-footer>
      </el-container>
      
    </el-container>

  </el-container>
</template>

<style>
  *{
    margin:0;
    padding:0;
    flex-shrink: 1 !important;
  }

  #app {
    display: flex;
    flex-direction: column;
    min-height: 100vh;
  }

  .aside{
    background: #6564c8;
    max-width: 250px;
  }

  .main{
    padding:0;
  }

  .footer{
    padding:0;
    min-height: 200px;
    background-color: #d6d5ff;

  }
</style>
