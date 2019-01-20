<template>
  <div class="AdminOrder">
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
          <el-menu default-active="3" class="el-menu-vertical-demo" >
            <router-link to="/Admin" style="text-decoration: none">
              <el-menu-item index="1">
                  <i class="el-icon-menu"></i>
                  <span slot="title" style="align:left">用户</span>
              </el-menu-item>
            </router-link>
            <router-link to="/coffeeMenu" style="text-decoration: none">
              <el-menu-item index="2">
                <i class="el-icon-setting"></i>
                <span slot="title">菜单</span>
              </el-menu-item>
            </router-link>
            <el-menu-item index="3">
              <i class="el-icon-document"></i>
              <span slot="title">订单</span>
            </el-menu-item>
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
            <el-breadcrumb-item>订单管理</el-breadcrumb-item>
          </el-breadcrumb>
        </div>

        <div style="margin-top: 60px; padding:16px; background-color: #E9EEF3; text-align: left;">
          <el-form :inline="true" :model="formInline" label-width="80px" style="height:45px" >
            <el-form-item label="用户账号:" style="width:300px">
              <el-input v-model="formInline.name" size="small" ></el-input>
            </el-form-item>
            <el-form-item style="width:100px">
              <el-button type="primary" size="small" @click="searchByName(formInline.name)">搜索</el-button>
            </el-form-item>
          </el-form>
        </div>

        <div style="margin-top: 40px">
          <el-table :data="tableData" max-height="500" fit border stripe style="width: 100%" :cell-style="cellStyle">
            <el-table-column prop="username" label="用户账号" sortable></el-table-column>
            <el-table-column prop="password" label="所点菜品" ></el-table-column>
            <el-table-column prop="name" label="数量" ></el-table-column>
            <el-table-column prop="address" label="支付时间" sortable></el-table-column>
            <el-table-column prop="balance" label="是否上菜" >
              <template slot-scope="scope">
              <el-tag
                :type="scope.row.balance === '已上菜' ? 'success' : 'danger'"
                disable-transitions>{{scope.row.balance}}</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="操作" >
              <template slot-scope="scope">
                <el-button size="small" @click="handleC(scope.$index, scope.row)">确认上菜</el-button>
                <el-button type="danger" size="small" @click="confirmDelete(scope.$index, scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>

      </div>

    </el-main>
  </el-container>

  </div>
</template>

<script>
  const SecretNumber="123456";

  import app from "../App"
    export default {
        name: "AdminOrder",
      data() {
        return {
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
            name: '',
          },

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

        }
      },

      created(){
        if(app.methods.getCookie("IsAdminister")==="") {
          window.location.href='http://localhost:8081/#/Admin'
        }
        else
          this.loadAllUsers();
      },

      methods: {

        //加载所有订单
        loadAllUsers:function() {
          let _this=this;

          this.$axios({
            method:'get',
            url:'/allOrderHistory',

          }).then(function (response) {
            _this.$data.tableData[0].username=response.data[0].usersaccountnumber;
            _this.$data.tableData[0].password=response.data[0].mealsname;
            _this.$data.tableData[0].name=response.data[0].amount;
            _this.$data.tableData[0].address=response.data[0].paytime;
            _this.$data.tableData[0].balance=(response.data[0].state=='Y')?'已上菜':'未上菜';

            for(var i=1;i<response.data.length;i++){
              var username=response.data[i].usersaccountnumber;
              var password=response.data[i].mealsname;
              var name=response.data[i].amount;
              var address=response.data[i].paytime;
              var state=(response.data[i].state=='Y')?'已上菜':'未上菜';

              var newItem={username:username,password:password,name:name,address:address,balance:state};
              _this.$data.tableData.push(newItem);
            }

          }).catch(function (error) {

          })
        },

        //删除时弹框确定
        confirmDelete:function(index, row){
          let _this=this;
          _this.$confirm('此操作将永久删除该订单, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            _this.handleDelete(index,row);
            _this.$message({
              type: 'success',
              message: '删除成功!'
            });
          }).catch(() => {
            _this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
        },

        //删除一条记录
        handleDelete:function (index, row) {
          var userAccount=row.username.toString();
          var itemName=row.password.toString();
          var time=row.address.toString();

          let _this=this;

          this.$axios({
            method:'get',
            url:'/deleteOrder?usersaccountnumber='+userAccount+'&mealsname='+itemName+'&paytime='+time,
            contentType:'application/json;charset=UTF-8',

          }).then(function (response) {

             _this.tableData.splice(index,1);
          }).catch(function (error) {

          })
        },

        //确认上菜
        handleC:function (index, row){
          let _this=this;
          _this.$data.currentIndex=index;

          this.$confirm('确定已经上菜了吗?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            let _this=this;

            var user=row.username;
            var item=row.password;
            var time=row.address;

            this.$axios({
              method:'get',
              url:'/serve?usersaccountnumber='+user+'&mealsname='+item+'&paytime='+time

            }).then(function (response) {

              _this.$data.tableData[index].balance='已上菜';
            }).catch(function (error) {

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

        //根据名字搜索
        searchByName:function(name){
          let _this=this;

          this.$axios({
            method:'get',
            url:'/userOrderHistory1?usersaccountnumber='+name,

          }).then(function (response) {
            _this.$data.tableData=response.data;
            for(var i=0;i<response.data.length;i++){
              _this.$data.tableData[i].username=response.data[i].usersaccountnumber;
              _this.$data.tableData[i].password=response.data[i].mealsname;
              _this.$data.tableData[i].name=response.data[i].amount;
              _this.$data.tableData[i].address=response.data[i].paytime;
              _this.$data.tableData[i].balance=response.data[i].state;
            }

          }).catch(function (error) {

          })
        },

        //未上菜的标红
        cellStyle({row, column, rowIndex, columnIndex}) {
          if (columnIndex === 4) {
            if (row.balance.toString() == 'N') {
              return 'color:red'
            }
          }
        },

      }
    }
</script>

<style scoped>

</style>
