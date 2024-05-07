<template>
      <el-menu
          default-active="/"
          @select="handleSelect"
          background-color="#6564c8"
      >
        <el-row>
          <el-col :span="24" >
            <img src="@/assets/AsideLogo.png" alt="MaotaiTracibility Logo" class="logo"/>
          </el-col>
        </el-row>

        <el-menu-item index="/">
          <span>首页</span>
        </el-menu-item>

        <el-menu-item index="/produce">
          <span>生产茅台</span>
        </el-menu-item>
        <el-menu-item index="/shelf">
          <span>上架茅台</span>
        </el-menu-item>
        <el-menu-item index="/consume">
          <span>购买茅台</span>
        </el-menu-item>
        <el-menu-item index="/trace">
          <span>溯源</span>
        </el-menu-item>
      </el-menu>
</template>

<script setup>
  import { ElMenu, ElMenuItem, ElCol, ElRow } from 'element-plus'
  import {computed, onUpdated} from 'vue'
  import { useRoute } from 'vue-router'
  import router from '@/router'


  const handleSelect = (key, keyPath) => {
    keyPath; // 用不到但是要写
    router.push(key);
  }

  // const route = useRoute();
  // const currentRoute = computed(() => {
  //   console.log(route.path)
  //   router.push(key);
  //   if(route.path === '')
  //     return '/';
  //   else
  //     return currentRoute;
  // });


</script>

<style scoped>
  .logo{
    width: 100%;
    height: auto;
  }

  .menu {
    position: absolute;
    top: 10%;
    left: 0%;
    width: 100%;
    background-color: rgb(55, 53, 174);
  }

  .el-menu-item {
    /* background-color: #506baf !important; */
    background-color: #6564c8 !important;
    color: white;
  }

  /* 设置选鼠标指针浮动在一级菜单的设置 */
  .el-menu-item:hover {
    background-color: #9e9bef !important;
  }

  /* 设置当前被选中的一级菜单 */
  .el-menu-item.is-active {
    color: #ffd04b !important;
    /* background: rgb(95, 56, 187) !important; */
  }


</style>