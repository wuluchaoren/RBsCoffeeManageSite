<template>
  <!---->
  <div class="Login">
    <!--Left-->
    <el-aside width="30%" >
      <div style="display: block; position: fixed; left: 0px; width:30%;background-attachment: fixed;background-color: #fffaf0;height: 100%">
        <el-menu  class="el-menu-demo" mode="horizontal" menu-trigger="click">
          <el-menu-item index="1" style="margin-left: 20px"><router-link to="/"><img src="../assets/logo1.png" width="50px" height="50px" /></router-link></el-menu-item>
          <el-submenu index="2" style="margin-left: 20px">
            <template slot="title" >我的账户</template>
            <router-link to="/SelfInformation" style="text-decoration: none"><el-menu-item index="2-1">个人中心</el-menu-item></router-link>
            <router-link to="/Order" style="text-decoration: none"><el-menu-item index="2-2">我的订单</el-menu-item></router-link>
            <router-link to="/Admin" style="text-decoration: none"><el-menu-item index="2-3">管理员</el-menu-item></router-link>
          </el-submenu>
          <el-menu-item index="3" disabled>消息中心</el-menu-item>
          <el-menu-item index="4"><router-link to="/Recommand" style="text-decoration: none">菜单</router-link></el-menu-item>
        </el-menu>
        <div class="line"></div>

        <p style="text-align:left;margin-left:100px;margin-top:250px;font-size:25px;font-weight:bolder;font-family: 'Microsoft YaHei UI Light';color: #808080">登录或创建一个新账户</p>

        <el-col :span="1" :offset="4">
          <div>
            <div style="float: left"><el-button round style="margin-top: 50px" type="success"  >登录</el-button></div>
            <div style="float: left"><router-link to="/Register"><el-button round style="margin-top: 50px" type="success" plain>注册</el-button></router-link></div>
          </div>
        </el-col>
      </div>
    </el-aside>

    <!--Right-->
    <el-main width="70%">
      <div>
        <div id="jshao" style="margin-top:10px;margin-left: 400px;">
          <div style="text-align: center;font-size: 25px;color: #f8f3d1;font-weight:lighter">
            <br><br><br><br><br><br><br>
            Welcome!
            Rocket Boys Coffee<br>
            Founded in 2018-6-28<br>
            Provide many kinds<br>
            <br><br><br><br><br><br><br>
          </div>
        </div>

        <div style="margin-top: 100px;margin-right:450px">

          <el-form :model="ruleForm2" status-icon :rules="rules2" ref="ruleForm2" label-width="70%" class="demo-ruleForm">
            <el-form-item label="账号" prop="pass">
              <el-input type="password" v-model="ruleForm2.pass" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="checkPass">
              <el-input type="password" v-model="ruleForm2.checkPass" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm('ruleForm2')">提交</el-button>
              <el-button @click="resetForm('ruleForm2')">重置</el-button>
            </el-form-item>
          </el-form>

        </div>
      </div>
    </el-main>
  </div>
</template>

<script>
  import app from "../App"
  export default {
    data() {
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入账号'));
        }else callback();
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else callback();
      };
      return {
        ruleForm2: {
          pass: '',
          checkPass: '',
        },
        rules2: {
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        console.log(this.ruleForm2);

        let _this=this;
        this.$axios({
          method:'get',
          url:'http://localhost:8080/userLogin?username='+_this.ruleForm2.pass+"&password="+_this.ruleForm2.checkPass,
        }).then(function (response) {
          console.log(response);
          if(response.data==true)
          {
            app.methods.addCookie("Customer",_this.ruleForm2.pass,0);
            _this.$message({
              message: '登录成功，请随意点餐',
              type: 'success'
            });
            window.location.href="http://localhost:8081/#/Menu";
          }
          else{
            _this.ruleForm2.pass='';
            _this.ruleForm2.checkPass='';

            _this.$message({
              message: '账号或密码输入错误，请重新输入',
              type: 'warning'
            });
          }
        })
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>

<style>
  #jshao{
    background-image:url("../assets/back4.jpg");
    background-position:200px;
    background-size:70% 100%;
    background-repeat:no-repeat ;
  }
</style>
