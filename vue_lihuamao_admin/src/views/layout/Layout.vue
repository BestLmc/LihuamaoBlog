<template>
  <div class="main-container">
    <el-container style="height: 100vh; width: 100%; border: 1px red solid">
      <!-- 左侧菜单栏start -->
      <el-aside width="200px" style="background-color: #545c64">
        <Sidebar />
      </el-aside>

      <!-- 左侧菜单栏end -->
      <el-container>
        <!-- 头部菜单start -->
        <el-header style="border: 1px red solid">
          <Navbar />
        </el-header>
        <!-- 头部菜单 end -->
        <!-- 主体 start -->
        <el-main style="border: 1px red solid">
          <div class="home">
            <router-view />
          </div>
        </el-main>
        <!-- 主体 end -->
      </el-container>
    </el-container>
  </div>
</template>


<script>
import Navbar from "@/views/layout/components/Navbar.vue";
import Sidebar from "@/views/layout/components/Sidebar.vue";
export default {
  components: {
    Navbar,
    Sidebar,
  },
};
</script>

<style scoped>
.main-container {
  height: 100%;
  width: 100%;
}
</style>
