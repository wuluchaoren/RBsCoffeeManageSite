<template>
  <div class="SelfInformation">
    <el-container>
      <!--Left-->
      <el-aside width="30%" >
        <div style="display: block; position: fixed; left: 0px; width:30%;background-attachment: fixed;background-color: #fffaf0;height: 100%">
          <div style="width: 100%;position: relative">
          <el-menu class="el-menu-demo" mode="horizontal" menu-trigger="click">
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
          </div>
          <div class="line"></div>

          <el-col :span="1" :offset="8">
            <div>
              <div style="float: left"><el-button round style="margin-top: 350px;" type="info" >个人中心</el-button></div>
              <router-link to="/Order"><div style="float: left"><el-button round style="margin-top: 50px" type="info" plain>我的订单</el-button></div></router-link>
            </div>
          </el-col>

          <!--<div id="Rocket1">-->
            <!--<img src="../assets/Rocket.jpg" width="100px" height="100px"/>-->
          <!--</div>-->
        </div>

      </el-aside>

      <!--Right-->
      <el-main width="70%" height="100%" >
        <div style="position: relative;margin-top: 50px">
          <div id="Rocket">
            <img src="../assets/Rocket.jpg" width="100px" height="100px"/>
          </div>
        </div>
        <div style="height: 650px">
          <el-card :body-style="{ padding: '100px' }" style="margin-top: 200px;margin-left: 200px; width: 800px;box-shadow: 0px 0px 10px #aaa">
            <div style="text-align: left">
              <!--个人信息表单-->
              <el-form ref="form" :model="form" label-width="80px">
                <el-form-item label="姓名：">
                  <span>{{form.name}}</span>
                </el-form-item>
                <el-form-item label="账号：">
                  <span>{{form.phone}}</span>
                </el-form-item>
                <el-form-item label="邮箱：">
                  <span>{{form.address}}</span>
                </el-form-item>
                <el-form-item label="余额：">
                  <span>{{form.balance}}</span>
                </el-form-item>

                <el-form-item style="text-align: center">
                  <el-button type="primary" @click="upDateName">修改姓名</el-button>
                  <el-button type="primary" @click="upDatePassword">修改密码</el-button>
                </el-form-item>
              </el-form>
            </div>
          </el-card>
        </div>

        <el-dialog title="修改密码" :visible.sync="dialogFormVisible" width="30%" style="text-align: left;">
          <el-form :model="ruleForm2" status-icon :rules="rules2" ref="ruleForm2" label-width="100px" class="demo-ruleForm">
            <el-form-item label="请输入旧密码" prop="oldPass">
              <el-input type="password" v-model="ruleForm2.oldPass" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="pass">
              <el-input type="password" v-model="ruleForm2.pass" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="checkPass">
              <el-input type="password" v-model="ruleForm2.checkPass" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item style="margin-top: 40px">
              <el-button type="primary" @click="submitForm('ruleForm2')">提交</el-button>
              <el-button @click="resetForm('ruleForm2')">重置</el-button>
            </el-form-item>
          </el-form>
        </el-dialog>

      </el-main>
    </el-container>

  </div>
</template>

<script>
  import app from "../App"
    export default {
        name: "SelfInformation",
      data() {
        var validatePass = (rule, value, callback) => {
          if (value === '') {
            callback(new Error('请输入密码'));
          } else {
            if (this.ruleForm2.checkPass !== '') {
              this.$refs.ruleForm2.validateField('checkPass');
            }
            callback();
          }
        };
        var validatePass2 = (rule, value, callback) => {
          if (value === '') {
            callback(new Error('请再次输入密码'));
          } else if (value !== this.ruleForm2.pass) {
            callback(new Error('两次输入密码不一致!'));
          } else {
            callback();
          }
        };
        return {
          form: {
            name: '',
            phone: '',
            password:'',
            address:''
          },
          cookie_username:'13645215545',

          //修改密码框是否可见
          dialogFormVisible: false,

          ruleForm2: {
            pass: '',
            checkPass: '',
            oldPass: ''
          },
          rules2: {
            pass: [
              { validator: validatePass, trigger: 'blur' }
            ],
            checkPass: [
              { validator: validatePass2, trigger: 'blur' }
            ],
          }
        }
      },
      created(){
        //处理Cookie
        app.methods.delCookie("IsAdminister");
        if(app.methods.getCookie("Customer")==="")
        {
          this.$message({
            message: '当前尚未登录，请登陆后查看',
            type: 'warning'
          });
          window.location.href="http://localhost:8081/#/Login";
        }
        else
        {
          this.cookie_username=app.methods.getCookie("Customer");
        }

        this.getInfo();
      },

      methods: {
        //加载用户信息
        getInfo:function() {
          let _this=this;
          let account=_this.$data.cookie_username;

          this.$axios({
            method:'get',
            url:'/selectUserByAccount?accountnumber='+account,

          }).then(function (response) {
            _this.$data.form.phone=response.data[0].usersaccountnumber;
            _this.$data.form.name=response.data[0].usersname;
            _this.$data.form.address=response.data[0].usersemail;
            _this.$data.form.balance=response.data[0].usersbalance;

          }).catch(function (error) {
            console.log(error.response.data)
          })
        },

        //用户修改姓名
        upDateName() {
          let _this=this;

          this.$prompt('请输入新的姓名', '修改姓名', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            inputPattern:/^[\u4e00-\u9fa5]{1,6}$/,
            inputValue:this.$data.form.name,
            inputErrorMessage: '姓名必须为长度1-6的汉字'
          }).then(({ value }) => {

            _this.$confirm('确定要修改姓名为 '+value+" 吗?", '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              _this.$data.form.name=value;
              this.$axios({
                method:'get',
                url:'/alterUser?name='+value+'&usersaccountnumber='+_this.$data.cookie_username,

              }).then(function (response) {
                console.log(response.data);
              }).catch(function (error) {
                console.log(error.response.data)
              });
              _this.$message({
                type: 'success',
                message: '修改姓名为: ' + value
              });
            }).catch(() => {
              _this.$message({
                type: 'info',
                message: '已取消修改'
              });
            });
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '已取消修改'
              });
            });
        },

        //用户修改密码
        upDatePassword(){
          let _this=this;
          _this.$data.dialogFormVisible = true
        },

        //判断cookie
        InitialPage(){
          var str=app.getCookie("currentUser");
          if(str==null)
          {
            this.$alert('请登录么么', '尚未登录', {
              confirmButtonText: '确定',
              callback: action => {
                this.$message({
                  type: 'warning',
                  message: `请登录或注册`
                });
                window.location.assign("http://localhost:8081/#/Login")
              }
            });
          }
          else{
            //待完成
          }
        },

        //提交修改密码
        submitForm(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              let _this=this;
              this.$confirm('确定要修改密码吗?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                var userNumber=_this.$data.form.phone;
                var oldpassword = _this.$data.ruleForm2.oldPass;
                var newpassword = _this.$data.ruleForm2.checkPass;
                this.$axios({
                  method:'get',
                  url:'/alterUserPassword?usersaccountnumber='+userNumber+'&oldpassword='+oldpassword+'&newpassword='+newpassword,

                }).then(function (response) {
                  let resData=response.data;
                  console.log(resData);
                  if(resData.toString()==='true'){
                    _this.$data.dialogFormVisible = false;
                    _this.$message({
                      type: 'success',
                      message: '修改成功!'
                    });
                    _this.$data.ruleForm2.oldPass='';
                    _this.$data.ruleForm2.pass='';
                    _this.$data.ruleForm2.checkPass='';
                  }
                  else{
                    _this.$message({
                      type: 'error',
                      message: '修改错误,请检查原密码是否正确'
                    });
                    _this.$data.ruleForm2.oldPass='';
                    _this.$data.ruleForm2.pass='';
                    _this.$data.ruleForm2.checkPass='';
                  }
                }).catch(function (error) {
                  console.log(error.response.data)
                });
              }).catch(() => {
                this.$message({
                  type: 'info',
                  message: '已取消'
                });
                _this.$data.ruleForm2.oldPass='';
                _this.$data.ruleForm2.pass='';
                _this.$data.ruleForm2.checkPass='';
              });

            } else {
              this.$message.error('请确认两次输入密码是否相同');
              return false;
            }
          });
        },


        //重置表单
        resetForm(formName) {
          this.$refs[formName].resetFields();
        },


        }

    }
</script>

<style scoped>

  #Rocket{
    width:100px;
    height:100px;
    position: absolute;
    left:50px;
    -webkit-animation:RocketAnimation 10s infinite; /* Safari and Chrome */
  }

  @-webkit-keyframes RocketAnimation /* Safari 和 Chrome */
  {
    0%   { left:50px; top:0px; transform: rotate(0deg)}
    5%   { left:50px; top:0px; transform: rotate(90deg)}
    20%  { left:1100px; top:0px;transform: rotate(90deg)}
    25%  { left:1100px; top:0px;transform: rotate(180deg)}
    45%  { left:1100px; top:700px;transform: rotate(180deg)}
    50%  { left:1100px; top:700px;transform: rotate(270deg)}
    70% { left:50px; top:700px;transform: rotate(270deg)}
    75%  { left:50px; top:700px;transform: rotate(360deg)}
    100% { left:50px; top:0px;transform: rotate(360deg)}
  }

  #Rocket1{
    width:100px;
    height:100px;
    position: absolute;
    left:50px;
    top:800px;
  }

  #Rocket1:active{
    -webkit-animation:RocketAnimation1 1s; /* Safari and Chrome */
    -webkit-animation-timing-function:ease; /* Safari 和 Chrome */
  }

  @-webkit-keyframes RocketAnimation1 /* Safari 和 Chrome */
  {
    0%   { left:50px; top:800px; }
    100% { left:50px; top:0px; }
  }

</style>
