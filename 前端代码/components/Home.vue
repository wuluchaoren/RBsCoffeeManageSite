
<template>
  <div class="hello">
    <el-container>

      <el-aside width="30%" >
        <div style="display: block; position: fixed; left: 0px; width:30%;background-attachment: fixed;background-color: #fffaf0;height: 100%" >

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

          <p style="text-align:left;margin-left:100px;margin-top:250px;font-size:25px;font-weight:bolder;font-family: 'Microsoft YaHei UI Light';color: #808080">欢迎光临</p>
          <p style="text-align:left;margin-left:100px;font-size:25px;font-weight:bolder;font-family: 'Microsoft YaHei UI Light';color: #808080">RBs Coffee</p>

          <el-row :gutter="20">
            <div style="margin-top:100px">
              <el-col :span="5" :offset="5"><router-link to="/Login"><el-button type="success" size="medium" round icon="el-icon-upload2">登录</el-button></router-link></el-col>
              <el-col :span="5"><router-link to="/Register"><el-button type="success" size="medium" round icon="el-icon-edit-outline">注册</el-button></router-link></el-col>
            </div>
          </el-row>
        </div>

      </el-aside>

      <el-main>
        <div class="animated bounceInRight">
          <div style="margin:10px auto;width: 80%">
            <el-carousel indicator-position="outside" height="500px">
              <el-carousel-item v-for="item in 4" :key="item"></el-carousel-item>
            </el-carousel>
          </div>

          <div style="margin-top: 100px;margin-bottom:90px;font-family: PingFang SC">
            Welcome!
            Rocket Boys Coffee<br>
            Founded in 2018-6-28<br>
            Provide many kinds<br>
          </div>
          <div style="margin:10px auto;width: 80%">
            <img src="../assets/coffeebackground.jpeg" width="100%"/>
          </div>
        </div>
      </el-main>
    </el-container>
  </div>
</template>


<script>
  import app from "../App"
  export default {
    created(){
      app.methods.delCookie("IsAdminister")
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .el-carousel__item:nth-child(4n) {
    background: url('../assets/BackgroundPic/snake2.jpg');
    background-repeat: no-repeat;
    background-size: cover;
  }
  .el-carousel__item:nth-child(4n+1) {
    background:url('../assets/BackgroundPic/coffee3_ps.png');
    background-repeat: no-repeat;
    background-size: cover;
 }
  .el-carousel__item:nth-child(4n+2) {
    background: url('../assets/BackgroundPic/snake3_ps.jpg');
    background-repeat: no-repeat;
    background-size: cover;
  }
  .el-carousel__item:nth-child(4n+3) {
    background: url('../assets/BackgroundPic/coffee2.png');
    background-repeat: no-repeat;
    background-size: cover;
  }

</style>
