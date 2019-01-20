<template>
  <!---->
  <div class="hello">
    <!--Left-->
    <el-aside width="30%" >
      <div style="display: block; position: fixed; left: 0px; width:30%;background-attachment: fixed;background-color: #fffaf0;height: 100%">
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect" menu-trigger="click">
          <el-menu-item index="1" style="margin-left: 20px"><router-link to="/Home"><img src="../assets/logo1.png" width="50px" height="50px" /></router-link></el-menu-item>
          <el-submenu index="2" style="margin-left: 20px">
            <template slot="title" >我的账户</template>
            <router-link to="/SelfInformation" style="text-decoration: none"><el-menu-item index="2-1">个人中心</el-menu-item></router-link>
            <router-link to="/Order" style="text-decoration: none"><el-menu-item index="2-2">我的订单</el-menu-item></router-link>
            <el-menu-item index="2-3">管理员</el-menu-item>
          </el-submenu>
          <el-menu-item index="3" disabled>消息中心</el-menu-item>
          <el-menu-item index="4"><router-link to="/Recommand" style="text-decoration: none">菜单</router-link></el-menu-item>
        </el-menu>
        <div class="line"></div>

        <p style="text-align:left;margin-left:100px;margin-top:250px;font-size:25px;font-weight:bolder;font-family: 'Microsoft YaHei UI Light';color: #808080">登录或创建一个新账户</p>

        <el-col :span="1" :offset="4">
          <div>
            <div style="float: left"><router-link to="/Login"><el-button round style="margin-top: 50px" type="success" plain >登录</el-button></router-link></div>
            <div style="float: left"><el-button round style="margin-top: 50px" type="success" >注册</el-button></div>
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
          <!--gai dong-->
          <el-form :model="ruleForm2" status-icon :rules="rules2" ref="ruleForm2" label-width="70%">
            <el-form-item label="用户名" prop="user">
              <el-input id="username" v-model="ruleForm2.user"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="name">
              <el-input id="name" v-model="ruleForm2.name"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="value">
              <el-input id="email" v-model="ruleForm2.value" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="pass">
              <el-input id="password" type="password" v-model="ruleForm2.pass"  auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="checkPass">
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

<script>export default {
  data () {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.ruleForm2.checkPass !== '') {
          this.$refs.ruleForm2.validateField('checkPass')
        }
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.ruleForm2.pass) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    var reg = new RegExp('^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$')
    var validatePass3 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入邮箱地址'))
      } else if (!reg.test(value)) {
        callback(new Error('请输入有效邮箱地址!'))
      } else {
        callback()
      }
    }
    var validatePass4 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入用户名'))
      } else {
        callback()
      }
    }
    var validatePass5 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入姓名'))
      } else {
        callback()
      }
    }

    return {
      ruleForm2: {
        user: '',
        name: '',
        value: '',
        pass: '',
        checkPass: ''
      },
      rules2: {
        user: [
          {validator: validatePass4, trigger: 'blur'}
        ],
        name: [
          {validator: validatePass5, trigger: 'blur'}
        ],
        value: [
          {validator: validatePass3, trigger: 'blur'}
        ],
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitForm (formName) {
      var Username = document.getElementById('username').value
      var Password = document.getElementById('password').value
      var name = document.getElementById('name').value
      var email = document.getElementById('email').value


      this.$axios({
        method: 'get',
        url: '/userRegister?accountnumber=' + Username+ '&password=' + Password + '&name=' + name + '&email=' + email
      }).then(function (response) {
        console.log(email);
        console.log(response.data)
        if (response.data){
              alert('注册成功，请登录！')
              window.location.href = 'http://localhost:8081/#/Login'
        }else alert('注册失败')
        // window.location.href = 'http://localhost:8081/#/Selfinformation'
      }).catch(function (error) {
        console.log(error.response.data)
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    }

  }
}
</script>

<style>
  #jshao{
    background-image:url("../assets/back4.jpg");
    background-position: 300px;
    background-size:70% 100%;
    background-repeat:no-repeat ;
  }

</style>
