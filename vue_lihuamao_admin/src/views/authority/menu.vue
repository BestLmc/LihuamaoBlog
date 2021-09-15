<template>
  <div class="maincontainer">
    <!-- 查询和其他操作 -->
    <div class="filter-container" style="margin: 10px 0 10px 0">
      <el-input
        clearable
        class="filter-item"
        style="width: 200px"
        v-model="keyword"
        placeholder="请输入菜单名"
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
      <el-table-column prop="name" label="菜单名称" width="120">
      </el-table-column>
      <el-table-column prop="menuLevel" label="菜单级别" width="120">
      </el-table-column>
      <el-table-column prop="introduce" label="简介" width="120">
      </el-table-column>
      <el-table-column prop="parentUid" label="父uid" width="180">
      </el-table-column>
      <el-table-column prop="menuType" label="菜单类型" width="180">
      </el-table-column>

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
        <el-form-item
          label="菜单名称"
          :label-width="formLabelWidth"
          prop="name"
        >
          <el-input v-model="form.name" auto-complete="off"></el-input>
        </el-form-item>

        <el-form-item
          label="菜单等级"
          :label-width="formLabelWidth"
          prop="menuLevel"
        >
          <el-select v-model="form.menuLevel" placeholder="请选择">
            <!-- <el-option
              v-for="item in menuLevelDictList"
              :key="item.uid"
              :label="item.dictLabel"
              v-if="item.dictValue != 3"
              :value="parseInt(item.dictValue)"
            ></el-option> -->
          </el-select>
        </el-form-item>

        <el-form-item
          v-if="form.menuLevel == 2"
          label="父菜单名"
          :label-width="formLabelWidth"
          prop="parentUid"
        >
          <el-select
            v-model="form.parentUid"
            filterable
            clearable
            remote
            reserve-keyword
            placeholder="请输入父菜单名"
            :remote-method="remoteMethod"
            :loading="loading"
          >
            <el-option
              v-for="item in menuOptions"
              :key="item.uid"
              :label="item.name"
              :value="item.uid"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item
          label="菜单介绍"
          :label-width="formLabelWidth"
          prop="summary"
        >
          <el-input v-model="form.summary" auto-complete="off"></el-input>
        </el-form-item>

        <el-form-item label="图标" :label-width="formLabelWidth" prop="icon">
          <el-input v-model="form.icon" placeholder="请输入前图标名称">
            <el-button
              slot="append"
              icon="el-icon-setting"
              @click="openIconsDialog('prefix-icon')"
            >
              选择
            </el-button>
          </el-input>
        </el-form-item>

        <el-form-item label="路由" :label-width="formLabelWidth" prop="url">
          <el-input v-model="form.url" auto-complete="off"></el-input>
        </el-form-item>

        <el-form-item
          label="是否显示"
          :label-width="formLabelWidth"
          prop="isShow"
        >
          <el-radio-group v-model="form.isShow" size="small">
            <el-radio
              v-for="item in yesNoDictList"
              :key="item.uid"
              :label="parseInt(item.dictValue)"
              border
              >{{ item.dictLabel }}</el-radio
            >
          </el-radio-group>
        </el-form-item>
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
  getMenuPageList,
  addMenu,
  deleteMenu,
  getMenu,
} from "@/api/authority/menu";
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
      },
    };
  },
  methods: {
    initData() {
      getMenuPageList(this.params).then((res) => {
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
    // handRest: function (row) {
    //   var that = this;
    //   this.$confirm("此操作将会将该用户密码重置为默认密码, 是否继续?", "提示", {
    //     confirmButtonText: "确定",
    //     cancelButtonText: "取消",
    //     type: "warning",
    //   })
    //     .then(() => {
    //       let params = {};
    //       params.uid = row.uid;
    //     })
    //     .catch(() => {
    //       this.$commonUtil.message.info("已取消删除");
    //     });
    // },
    // handleEdit: function (row) {
    //   this.dialogFormVisible = true;
    //   getAdmin(row.uid)
    //     .then((res) => {
    //       console.log(res);
    //       if (res.code == 200) {
    //         this.form = res.data;
    //       }
    //     })
    //     .catch(() => {
    //       this.$message.error("查询出错！");
    //     });
    // },
    // handleDelete: function (row) {
    //   this.$confirm("此操作将该管理员删除, 是否继续?", "提示", {
    //     confirmButtonText: "确定",
    //     cancelButtonText: "取消",
    //     type: "warning",
    //   })
    //     .then((res) => {
    //       console.log(res);
    //       console.log(row);
    //       //   let params = new URLSearchParams();
    //       //   adminUids.push(row.uid);
    //       //   params.append("uid", row.uid);
    //       console.log(row.uid);
    //       deleteAdmin(row.uid).then((res) => {
    //         console.log(res);
    //         if (res.code == 200) {
    //           this.$message({
    //             type: "success",
    //             message: "删除成功!",
    //           });
    //           this.initData();
    //         }
    //       });
    //     })
    //     .catch(() => {
    //       this.$commonUtil.message.info("已取消删除");
    //     });
    // },
    // handleCurrentChange: function (val) {
    //   this.currentPage = val;
    //   this.initData();
    // },
    // submitForm: function () {
    //   this.$refs.form.validate((valid) => {
    //     if (!valid) {
    //       console.log("校验出错");
    //     } else {
    //       console.log(this.form.nickName);
    //       console.log(this.form);
    //       addAdmin(this.form).then((response) => {
    //         if (response.code == 200) {
    //           this.$message({
    //             message: response.message,
    //             type: "success",
    //           });
    //           this.dialogFormVisible = false;
    //           this.initData();
    //         } else {
    //           this.$commonUtil.message.error(response.message);
    //         }
    //       });
    //     }
    //   });
    // },
  },
};
</script>

<style>
</style>