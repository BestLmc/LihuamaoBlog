<template>
  <div class="index">
    <el-menu
      :default-openeds="[]"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      :router="true"
      @select="handleSelect"
    >
      <el-submenu index="1">
        <template slot="title"
          ><i class="el-icon-setting"></i>系统管理</template
        >
        <el-menu-item index="/system/systemDict"
          ><i class="el-icon-sugar"></i>字典管理</el-menu-item
        >
      </el-submenu>
      <el-submenu index="/authority">
        <template slot="title"><i class="el-icon-key"></i>权限管理</template>
        <el-menu-item index="/authority/admin"
          ><i class="el-icon-user-solid"></i>管理员管理</el-menu-item
        >
        <el-menu-item index="/authority/role"
          ><i class="el-icon-s-custom"></i>角色管理</el-menu-item
        >
        <el-menu-item index="/authority/menu"
          ><i class="el-icon-menu"></i>菜单管理</el-menu-item
        >
      </el-submenu>
      <el-submenu index="3">
        <template slot="title"
          ><i class="el-icon-document"></i>博客管理</template
        >
      </el-submenu>
    </el-menu>
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
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
      // this.$router.push(keyPath);
    },
  },
};
</script>

<style>
</style>