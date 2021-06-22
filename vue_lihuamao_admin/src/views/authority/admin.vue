<template>
  <div class="admin-maincontainer">
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="username" label="用户名" width="120">
      </el-table-column>
      <el-table-column prop="nickName" label="昵称" width="120">
      </el-table-column>
      <el-table-column prop="gender" label="性别" width="120">
      </el-table-column>
      <el-table-column prop="email" label="邮箱" width="120"> </el-table-column>
      <el-table-column prop="birthday" label="出生日期" width="150">
      </el-table-column>

      <el-table-column prop="mobile" label="手机号码" width="120">
      </el-table-column>
      <el-table-column prop="status" label="状态" width="30"> </el-table-column>

      <el-table-column fixed="right" label="操作" width="100">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small"
            >查看</el-button
          >
          <el-button type="text" size="small">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { getAdminList } from "@/api/admin/admin";
export default {
  created() {
    this.initData();
  },
  data() {
    return {
      params: { keyword: "", currentPage: 1, pageSize: 10 },
      tableData: [],
    };
  },
  methods: {
    initData() {
      getAdminList(this.params).then((res) => {
        console.log(res);
        // console.log(res.data.code);
        let tableData = res.data;
        this.tableData = tableData;
      });
    },
    handleClick(row) {
      console.log(row);
    },
  },
};
</script>