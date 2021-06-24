<template>
  <div class="admin-maincontainer">
    <!-- 查询和其他操作 -->
    <div class="filter-container" style="margin: 10px 0 10px 0">
      <el-input
        clearable
        class="filter-item"
        style="width: 200px"
        v-model="keyword"
        placeholder="请输入管理员名"
      ></el-input>
      <el-button
        class="filter-item"
        type="primary"
        icon="el-icon-search"
        @click="handleFind"
        v-permission="'/admin/getList'"
        >查找</el-button
      >
      <el-button
        class="filter-item"
        type="primary"
        @click="handleAdd"
        icon="el-icon-edit"
        v-permission="'/admin/add'"
        >添加</el-button
      >
    </div>

    <el-table :data="tableData" border style="width: 100%">
      <el-table-column label="序号" width="60" align="center">
        <template slot-scope="scope">
          <span>{{ scope.$index + 1 }}</span>
        </template>
      </el-table-column>
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
      <el-table-column prop="status" label="状态" width="50"> </el-table-column>

      <el-table-column label="操作" fixed="right" min-width="250">
        <template slot-scope="scope">
          <el-button
            @click="handRest(scope.row)"
            type="warning"
            size="small"
            v-permission="'/admin/restPwd'"
            >重置密码</el-button
          >
          <el-button
            @click="handleEdit(scope.row)"
            type="primary"
            size="small"
            v-permission="'/admin/edit'"
            >编辑</el-button
          >
          <el-button
            @click="handleDelete(scope.row)"
            type="danger"
            size="small"
            v-permission="'/admin/delete'"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <!--分页-->
    <div class="block">
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage"
        :page-size="pageSize"
        layout="total, prev, pager, next, jumper"
        :total="total"
      ></el-pagination>
    </div>

    <!-- 添加或修改对话框 -->
    <el-dialog :title="title" :visible.sync="dialogFormVisible">
      <el-form :model="form" :rules="rules" ref="form">
        <el-form-item label="用户头像" :label-width="formLabelWidth">
          <div class="imgBody">
            <i
              class="el-icon-error inputClass"
              v-show="icon"
              @click="deletePhoto()"
              @mouseover="icon = true"
            ></i>
          </div>
        </el-form-item>

        <el-row :gutter="24">
          <el-col :span="10">
            <el-form-item
              label="用户名"
              :label-width="formLabelWidth"
              prop="userName"
            >
              <el-input
                v-model="form.userName"
                placeholder="请输入用户名"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="昵称" :label-width="formLabelWidth">
              <el-input
                v-model="form.nickName"
                placeholder="请输入昵称"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="24">
          <el-col :span="10">
            <el-form-item
              label="邮箱"
              :label-width="formLabelWidth"
              prop="email"
            >
              <el-input
                v-model="form.email"
                placeholder="请输入邮箱"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item
              label="手机号"
              :label-width="formLabelWidth"
              prop="mobile"
            >
              <el-input
                v-model="form.mobile"
                placeholder="请输入手机号"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="24">
          <el-col :span="10">
            <el-form-item
              label="QQ号码"
              :label-width="formLabelWidth"
              prop="qqNumber"
            >
              <el-input
                v-model="form.qqNumber"
                placeholder="请输入QQ号码"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="职业" :label-width="formLabelWidth">
              <el-input
                v-model="form.occupation"
                placeholder="请输入职业"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="24">
          <el-col :span="10">
            <el-form-item
              label="网盘容量(MB)"
              :label-width="formLabelWidth"
              prop="maxStorageSize"
            >
              <el-input-number
                v-model="form.maxStorageSize"
                :min="0"
                label="用户最大网盘容量"
              ></el-input-number>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </div>
    </el-dialog>
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
      keyword: "",
      currentPage: 1,
      pageSize: 10,
      total: 0, //总数量
      params: { keyword: "", currentPage: 1, pageSize: 10 },
      tableData: [],
      dialogFormVisible: false, //控制弹出框
      form: {},
      formLabelWidth: "120px",
      title: "增加管理员",
      icon: true, //控制删除图标的显示
      rules: {
        userName: [
          { required: true, message: "用户名不能为空", trigger: "blur" },
          { min: 1, max: 20, message: "长度在1到20个字符" },
        ],
        dictValue: [
          { required: true, message: "字典键值不能为空", trigger: "blur" },
          { min: 1, max: 20, message: "长度在1到20个字符" },
        ],
        gender: [{ required: true, message: "性别不能为空", trigger: "blur" }],
        email: [
          {
            pattern: /\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/,
            message: "请输入正确的邮箱",
          },
        ],
        mobile: [
          {
            required: false,
            pattern: /0?(13|14|15|17|18)[0-9]{9}/,
            message: "请输入正确的手机号码",
          },
        ],
        qqNumber: [
          { pattern: /[1-9]([0-9]{5,11})/, message: "请输入正确的QQ号码" },
        ],
      },
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
    handleFind: function () {
      this.initData();
    },
    handleAdd: function () {
      this.dialogFormVisible = true;
    },
    handRest: function (row) {
      var that = this;
      this.$confirm("此操作将会将该用户密码重置为默认密码, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          let params = {};
          params.uid = row.uid;
        })
        .catch(() => {
          this.$commonUtil.message.info("已取消删除");
        });
    },
    handleDelete: function (row) {
      this.$confirm("此操作将该管理员删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          let params = new URLSearchParams();
          var adminUids = [];
          adminUids.push(row.uid);
          params.append("adminUids", adminUids);
        })
        .catch(() => {
          this.$commonUtil.message.info("已取消删除");
        });
    },
    handleDelete: function (row) {
      this.$confirm("此操作将该管理员删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          let params = new URLSearchParams();
          var adminUids = [];
          adminUids.push(row.uid);
          params.append("adminUids", adminUids);
        })
        .catch(() => {
          this.$commonUtil.message.info("已取消删除");
        });
    },
    handleCurrentChange: function (val) {
      this.currentPage = val;
      this.initData();
    },
  },
};
</script>