<template>
  <div class="index">
    <el-dropdown
      style="border: 1px blue solid; display: flex; justify-content: flex-end"
      @command="handleMenu"
    >
      <span class="el-dropdown-link">
        <el-avatar :size="60" @error="errorHandler">
          <!-- src="require('../../assets/img/lihuamao.jpg')" -->
          <a href="#">
            <img
              id="admin-img"
              style="height: 60px; width: 60px"
              src="/static/default.png"
            />
          </a>
        </el-avatar>
      </span>
      <!-- <i
            class="el-icon-user"
            style="margin-right: 15px; font-size: 30px"
          ></i> -->
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item command="personalCenter">个人中心</el-dropdown-item>
        <el-dropdown-item command="logout">退出</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>


<script>
export default {
  data() {
    return {
      menuData: [],
      current: 0,
    };
  },
  created() {},
  computed: {},
  mounted() {},
  methods: {
    handleMenu(command) {
      console.log(command);
      if (command == "personalCenter") {
        this.$router.push({ path: "/system/personalCenter" });
      } else if (command == "logout") {
      } else {
        this.$router.push({ path: "/system/personalCenter" });
      }
    },
    errorHandler() {
      return true;
    },
  },
};
</script>

<style>
a:hover img {
  filter: alpha(Opacity=80);
  -moz-opacity: 0.8;
  opacity: 0.8;
}
</style>