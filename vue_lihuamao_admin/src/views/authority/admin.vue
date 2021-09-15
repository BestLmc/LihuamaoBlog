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
        >查找</el-button
      >
      <el-button
        class="filter-item"
        type="primary"
        @click="handleAdd"
        icon="el-icon-edit"
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
      <el-table-column prop="email" label="邮箱" width="180"> </el-table-column>
      <el-table-column prop="birthday" label="出生日期" width="180">
      </el-table-column>

      <el-table-column prop="mobile" label="手机号码" width="120">
      </el-table-column>
      <el-table-column prop="status" label="状态" width="50"> </el-table-column>

      <el-table-column label="操作" fixed="right" min-width="250">
        <template slot-scope="scope">
          <el-button @click="handRest(scope.row)" type="warning" size="small"
            >重置密码</el-button
          >
          <el-button @click="handleEdit(scope.row)" type="primary" size="small"
            >编辑</el-button
          >
          <el-button @click="handleDelete(scope.row)" type="danger" size="small"
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
        </el-row>

        <el-row :gutter="24">
          <el-col :span="10">
            <el-form-item
              label="昵称"
              :label-width="formLabelWidth"
              prop="nickName"
            >
              <el-input
                v-model="form.nickName"
                placeholder="请输入昵称"
              ></el-input> </el-form-item
          ></el-col>
        </el-row>
        <el-row :gutter="24">
          <el-col :span="10">
            <el-form-item
              label="密码"
              :label-width="formLabelWidth"
              prop="password"
            >
              <el-input
                placeholder="请输入密码"
                v-model="form.passWord"
                show-password
              ></el-input>
            </el-form-item>
          </el-col> </el-row
        ><el-row :gutter="24">
          <el-col :span="10">
            <el-form-item
              label="确认密码"
              :label-width="formLabelWidth"
              prop="comfirmPassword"
            >
              <el-input
                placeholder="请重新输入密码"
                v-model="form.comfirmPassword"
                show-password
              ></el-input>
            </el-form-item>
          </el-col> </el-row
        ><el-row :gutter="24">
          <el-col :span="10">
            <el-form-item
              label="性别"
              :label-width="formLabelWidth"
              prop="gender"
            >
              <el-radio v-model="form.gender" label="1">男</el-radio>
              <el-radio v-model="form.gender" label="2">女</el-radio>
            </el-form-item>
          </el-col> </el-row
        ><el-row :gutter="24">
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
        </el-row>
        <el-row :gutter="24">
          <el-col :span="10">
            <el-form-item
              label="出生日期"
              :label-width="formLabelWidth"
              prop="birthday"
            >
              <el-date-picker
                v-model="form.birthday"
                value-format="yyyy-MM-dd HH:mm:ss"
                type="datetime"
                placeholder="选择日期"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="24">
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
          <el-col :span="120">
            <el-form-item
              label="自我介绍"
              :label-width="formLabelWidth"
              prop="introduce"
            >
              <el-input
                type="textarea"
                v-model="form.introduce"
                placeholder="请输入自我介绍"
              ></el-input>
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
import {
  getAdminPageList,
  addAdmin,
  deleteAdmin,
  getAdmin,
} from "@/api/authority/admin";
export default {
  created() {
    this.initData();
  },
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.form.passWord) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
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
        nickName: [
          { required: true, message: "昵称不能为空", trigger: "blur" },
          { min: 1, max: 20, message: "长度在1到20个字符" },
        ],
        passWord: [
          { required: true, message: "密码不能为空", trigger: "blur" },
          { min: 1, max: 20, message: "长度在1到20个字符" },
        ],
        comfirmPassword: [
          { required: true, validator: validatePass, trigger: "blur" },
          { min: 1, max: 20, message: "长度在1到20个字符" },
        ],
        dictValue: [
          { required: true, message: "字典键值不能为空", trigger: "blur" },
          { min: 1, max: 20, message: "长度在1到20个字符" },
        ],
        gender: [{ required: true, message: "性别不能为空", trigger: "blur" }],
        email: [
          {
            required: false,
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
      getAdminPageList(this.params).then((res) => {
        console.log(res);
        // console.log(res.data.code);
        let tableData = res.data.records;
        this.total = res.data.total;
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
    handleEdit: function (row) {
      this.dialogFormVisible = true;
      getAdmin(row.uid)
        .then((res) => {
          console.log(res);
          if (res.code == 200) {
            this.form = res.data;
          }
        })
        .catch(() => {
          this.$message.error("查询出错！");
        });
    },
    handleDelete: function (row) {
      this.$confirm("此操作将该管理员删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then((res) => {
          console.log(res);
          console.log(row);
          //   let params = new URLSearchParams();
          //   adminUids.push(row.uid);
          //   params.append("uid", row.uid);
          console.log(row.uid);
          deleteAdmin(row.uid).then((res) => {
            console.log(res);
            if (res.code == 200) {
              this.$message({
                type: "success",
                message: "删除成功!",
              });
              this.initData();
            }
          });
        })
        .catch(() => {
          this.$commonUtil.message.info("已取消删除");
        });
    },
    handleCurrentChange: function (val) {
      this.currentPage = val;
      this.initData();
    },
    submitForm: function () {
      this.$refs.form.validate((valid) => {
        if (!valid) {
          console.log("校验出错");
        } else {
          console.log(this.form.nickName);
          console.log(this.form);
          addAdmin(this.form).then((response) => {
            if (response.code == 200) {
              this.$message({
                message: response.message,
                type: "success",
              });
              this.dialogFormVisible = false;
              this.initData();
            } else {
              this.$commonUtil.message.error(response.message);
            }
          });
        }
      });
    },
  },
};
</script>