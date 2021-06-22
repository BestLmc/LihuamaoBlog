<template>
  <el-container>
    <!-- 左侧菜单栏start -->
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu
        :default-openeds="['1', '3']"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
      >
        <el-submenu index="1">
          <template slot="title"
            ><i class="el-icon-message"></i>系统管理</template
          >
        </el-submenu>
        <el-submenu index="2">
          <template slot="title"><i class="el-icon-menu"></i>权限管理</template>
          <el-menu-item index="1-1">管理员管理</el-menu-item>
          <el-menu-item index="1-2">角色管理</el-menu-item>
          <el-menu-item index="1-3">菜单管理</el-menu-item>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title"
            ><i class="el-icon-setting"></i>博客管理</template
          >
        </el-submenu>
      </el-menu>
    </el-aside>
    <!-- 左侧菜单栏end -->
    <el-container>
      <!-- 头部菜单start -->
      <el-header style="border: 1px red solid">
        <el-dropdown
          style="
            border: 1px blue solid;
            display: flex;
            justify-content: flex-end;
          "
        >
          <span class="el-dropdown-link">
            <a href="#">
              <img
                id="admin-img"
                style="margin-right: 15px; height: 60px; width: 60px"
                src="../assets/img/lihuamao.jpg"
              />
            </a>
          </span>
          <!-- <i
            class="el-icon-user"
            style="margin-right: 15px; font-size: 30px"
          ></i> -->
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>个人中心</el-dropdown-item>
            <el-dropdown-item>退出</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>
      <!-- 头部菜单 end -->
      <!-- 主体 start -->
      <el-main>
        <div class="home">
          <router-view />
        </div>
      </el-main>
      <!-- 主体 end -->
    </el-container>
  </el-container>
</template>


<script>
export default {};
</script>

<style scoped>
a:hover img {
  filter: alpha(Opacity=80);
  -moz-opacity: 0.8;
  opacity: 0.8;
}
</style>
