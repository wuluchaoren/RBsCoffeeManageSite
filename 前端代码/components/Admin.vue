<template>
  <div class="admin">
    <el-container>
      <el-aside style="width: 20%;  text-align:left;">
        <div style="display: block; position: fixed; left: 0px;width: 20%; height:100%">
          <div style="">
            <el-menu
              class="el-menu-demo"
              mode="horizontal"
              text-color="#FFFFFF"
              background-color="#409EFF"
              collapse-transition
              active-text-color="#ffd04b">
              <el-menu-item index="1">Logo</el-menu-item>
              <el-menu-item index="2">管理界面</el-menu-item>
              <el-menu-item index="3"><router-link to="/" style="text-decoration: none">返回</router-link></el-menu-item>
            </el-menu>
          </div>

          <div style="height:100%">
            <el-menu default-active="1" class="el-menu-vertical-demo">
              <el-menu-item index="1">
                <i class="el-icon-menu"></i>
                <span slot="title" style="align:left">用户</span>
              </el-menu-item>
              <router-link to="/coffeeMenu" style="text-decoration: none">
              <el-menu-item index="2">
                <i class="el-icon-setting"></i>
                 <span slot="title">菜单</span>
              </el-menu-item>
              </router-link>
              <router-link to="/AdminOrder" style="text-decoration: none">
                <el-menu-item index="3">
                  <i class="el-icon-document"></i>
                  <span slot="title">订单</span>
                </el-menu-item>
              </router-link>
              <router-link to="/graph" style="text-decoration: none">
                <el-menu-item index="4">
                  <i class="el-icon-star-on"></i>
                  <span slot="title">店铺资讯</span>
                </el-menu-item>
              </router-link>
            </el-menu>

          </div>
        </div>
      </el-aside>

      <el-main>
        <div>
          <div style="margin-top:60px;">
            <el-breadcrumb separator-class="el-icon-arrow-right">
              <el-breadcrumb-item :to="{ path: '/' }">管理员</el-breadcrumb-item>
              <el-breadcrumb-item>用户管理</el-breadcrumb-item>
            </el-breadcrumb>
          </div>

          <div style="margin-top: 60px; padding:16px; background-color: #E9EEF3; text-align: left;">
            <el-form :inline="true" :model="formInline" label-width="50px" style="height:45px" >
              <el-form-item label="姓名:" style="width:250px">
                <el-input v-model="formInline.name" size="small" ></el-input>
              </el-form-item>
              <el-form-item style="width:100px">
                <el-button type="primary" size="small" @click="searchByName(formInline.name)">搜索</el-button>
              </el-form-item>
              <el-form-item style="width:100px;float: right" >
                <el-button type="success"  size="small" icon="el-icon-plus" @click="handleNew">添加用户</el-button>
              </el-form-item>
            </el-form>
          </div>

          <div style="margin-top: 40px">
            <el-table :data="tableData"  max-height="500" fit border stripe style="width: 100%">
              <el-table-column prop="username" label="用户账号" sortable></el-table-column>
              <el-table-column prop="password" label="密码" ></el-table-column>
              <el-table-column prop="name" label="姓名" ></el-table-column>
              <el-table-column prop="address" label="邮箱" ></el-table-column>
              <el-table-column prop="balance" label="余额" sortable ></el-table-column>
              <el-table-column label="操作" >
                <template slot-scope="scope">
                  <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                  <el-button type="danger" size="small" @click="confirmDelete(scope.$index, scope.row)">删除</el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>

          <el-dialog title="编辑用户信息" :visible.sync="dialogFormVisible" width="30%">
            <el-form :model="form">
              <el-form-item label="用户账号" :label-width="formLabelWidth">
                <el-input v-model="form.username" auto-complete="off" disabled="true"></el-input>
              </el-form-item>
              <el-form-item label="密码" :label-width="formLabelWidth">
                <el-input v-model="form.password" auto-complete="off" disabled="true"></el-input>
              </el-form-item>
              <el-form-item label="姓名" :label-width="formLabelWidth">
                <el-input v-model="form.name" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="邮箱" :label-width="formLabelWidth">
                <el-input v-model="form.address" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="余额" :label-width="formLabelWidth">
                <el-input v-model="form.balance" auto-complete="off"></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button type="primary" @click="confirmEdit">确 定</el-button>
              <el-button @click="dialogFormVisible = false">取 消</el-button>
            </div>
          </el-dialog>

          <el-dialog title="新建用户" :visible.sync="newdialogFormVisible" width="40%">
            <el-form :model="newform">
              <el-form-item label="用户账号" :label-width="formLabelWidth">
                <el-input v-model="newform.username" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="密码" :label-width="formLabelWidth">
                <el-input v-model="newform.password" auto-complete="off" ></el-input>
              </el-form-item>
              <el-form-item label="姓名" :label-width="formLabelWidth">
                <el-input v-model="newform.name" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="邮箱" :label-width="formLabelWidth">
                <el-input v-model="newform.address" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item label="余额" :label-width="formLabelWidth">
                <el-input v-model="newform.balance" auto-complete="off"></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button type="primary" @click="confirmNew">确 定</el-button>
              <el-button @click="newdialogFormVisible = false">取 消</el-button>
            </div>
          </el-dialog>

        </div>

      </el-main>
    </el-container>


  </div>
</template>

<script>
  const SecretNumber="123456";
  import app from "../App";

  export default {
    name: "admin",
    data() {
      return {
        IsLogin:false,

        //表格数据
        tableData:[{
          username:'',
          password:'',
          name:'',
          address:'',
          balance:''
        }],

        //查找框内数据
        formInline: {
          name:''
        },

        //编辑框是否可见
        dialogFormVisible: false,
        //新建框是否可见
        newdialogFormVisible: false,

        //编辑表单内数据
        form: {
          username:'',
          password:'',
          name:'',
          address:'',
          balance:''
        },
        formLabelWidth: '120px',

        //新建表单内数据
        newform: {
          username:'',
          password:'',
          name:'',
          address:'',
          balance:''
        },

        //当前正在编辑的Index
        currentIndex:'',

        //当前输入的密码
        currentSecretNumber:'',

      }
    },

    created(){
      if(app.methods.getCookie("IsAdminister")==="")
        this.CheckSecretNumber();
      else
        this.loadAllUsers();
    },

    methods: {

      //加载所有用户
      loadAllUsers:function() {
        let _this=this;

        this.$axios({
          method:'get',
          url:'/allusers',

        }).then(function (response) {
          _this.$data.tableData=response.data;
          for(var i=0;i<response.data.length;i++){
            _this.$data.tableData[i].username=response.data[i].usersaccountnumber;
            _this.$data.tableData[i].password="******";
            _this.$data.tableData[i].name=response.data[i].usersname;
            _this.$data.tableData[i].address=response.data[i].usersemail;
            _this.$data.tableData[i].balance=parseFloat(response.data[i].usersbalance);
          }

        }).catch(function (error) {
          console.log(error.response.data)
        })
      },

      //删除时弹框确定
      confirmDelete:function(index, row){
        this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.handleDelete(index,row);
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },

      //删除一个用户
      handleDelete:function (index, row) {

        var userAccount=row.usersaccountnumber.toString();
        let _this=this;

        this.$axios({
          method:'get',
          url:'/deleteUser?userAccount='+userAccount,
          contentType:'application/json;charset=UTF-8',

        }).then(function (response) {
          console.log(response.data);
          _this.tableData.splice(index,1);
        }).catch(function (error) {
          console.log(error.response.data)
        })
      },

      //编辑一个用户
      handleEdit:function (index, row){
        let _this=this;
        _this.$data.dialogFormVisible = true;
        _this.$data.currentIndex=index;
        _this.$data.form=row;
      },

      //编辑后的确定
      confirmEdit:function (){
        this.$confirm('确定要修改该用户吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let _this=this;
          let currentIndex=_this.$data.currentIndex;
          _this.$data.dialogFormVisible = false;
          _this.$data.tableData[currentIndex].username=_this.$data.form.username;
          _this.$data.tableData[currentIndex].password=_this.$data.form.password;
          _this.$data.tableData[currentIndex].name=_this.$data.form.name;
          _this.$data.tableData[currentIndex].address=_this.$data.form.address;
          _this.$data.tableData[currentIndex].balance=parseFloat(_this.$data.form.balance);

          var username=_this.$data.form.username;
          var name=_this.$data.form.name;
          var email=_this.$data.form.address;
          var balance=parseFloat(_this.$data.form.balance);

          this.$axios({
            method:'get',
            url:'/alterUserByAdmin?usersaccountnumber='+username+'&email='+email+'&name='+name+'&balance='+balance,

          }).then(function (response) {
            console.log(response.data);

          }).catch(function (error) {
            console.log(error.response.data)
          })

          this.$message({
            type: 'success',
            message: '修改成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消修改'
          });
        });
      },

      //新建一个用户
      handleNew:function (){
        let _this=this;
        _this.$data.newdialogFormVisible = true;
      },

      //新建后的确定
      confirmNew:function (){
        this.$confirm('确定要创建该用户吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let _this=this;
          _this.$data.newdialogFormVisible = false;

          var username=_this.$data.newform.username;
          var password=_this.$data.newform.password;
          var name=_this.$data.newform.name;
          var address=_this.$data.newform.address;
          var balance=parseFloat(_this.$data.newform.balance);

          var newItem={username:username,password:"******",name:name,address:address,balance:balance};
          _this.$data.tableData.unshift(newItem);

          this.$axios({
            method:'get',
            url:'/addUser?usersaccountnumber='+username+'&userspassword='+password+'&usersname='+name+'&usersemail='+address+'&usersbalance='+balance,

          }).then(function (response) {
            console.log(response.data);

          }).catch(function (error) {
            console.log(error.response.data)
          });

          this.$message({
            type: 'success',
            message: '成功创建该用户!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消创建'
          });
        });
      },

      //根据名字搜索
      searchByName:function(name){
        let _this=this;

        this.$axios({
          method:'get',
          url:'/selectUserByName?name='+name,

        }).then(function (response) {
          _this.$data.tableData=response.data;
          console.log(response.data);
          for(var i=0;i<response.data.length;i++){
            _this.$data.tableData[i].username=response.data[i].usersaccountnumber;
            _this.$data.tableData[i].password="******";
            _this.$data.tableData[i].name=response.data[i].usersname;
            _this.$data.tableData[i].address=response.data[i].usersemail;
            _this.$data.tableData[i].balance=parseFloat(response.data[i].usersbalance);
          }

        }).catch(function (error) {
          console.log(error.response.data)
        })
      },

      //输入密码
      CheckSecretNumber(){
        let _this=this;
        this.$prompt('请输入管理员密码', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
        }).then(({ value }) => {
          console.log("123");
          if(value===SecretNumber)
          {
            _this.$message({
              type: 'success',
              message: '登录成功',
            });

            _this.IsLogin=true;
            _this.loadAllUsers();
            app.methods.addCookie("IsAdminister","true",0);
          }
          else
          {
            _this.$message({
              type: 'error',
              message: '口令错误，请重新输入',
            });
            _this.CheckSecretNumber();
          }
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已返回首页'
          });

          window.location.assign("http://localhost:8081/#/")
        });
      },
    }
  }
</script>

<style scoped>
  .admin{
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }
</style>
