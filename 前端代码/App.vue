<!-- 展示模板 -->
<template>
  <div id="app">
    <!--<img src="./assets/logo.png">-->
    <router-view v-if="isRouterAlive"></router-view>
    <el-button type="danger" class="el-icon-RBs-disconnect"  circle style="position:fixed;right:97%;top:95%;" @click="goodBye"></el-button>
  </div>
</template>

<script>
  export default {
    name: 'app',
    data(){
      return{
        isRouterAlive:true
      }
    },
    methods:{
      addCookie: function (objName, objValue, objHours) {//添加cookie
        console.log("cookie名字:"+objName+"执行加入cookie"+" 值为"+objValue);
        var str = objName + "=" + decodeURI(objValue);
        if (objHours > 0) {//为0时不设定过期时间，浏览器关闭时cookie自动消失
          var date = new Date();
          var ms = objHours * 3600 * 1000;
          date.setTime(date.getTime() + ms);
          str += "; expires=" + date.toUTCString();
        }
        document.cookie = str;
      },
      getCookie: function (objName) {//获取指定名称的cookie的值
        var arrStr = document.cookie.split("; ");
        for (var i = 0; i < arrStr.length; i++) {
          var temp = arrStr[i].split("=");
          if (temp[0] == objName)
            return decodeURI(temp[1]);
        }
        return "";
      },
      delCookie: function (objname) {//为了删除指定名称的cookie，可以将其过期时间设定为一个过去的时间
        this.addCookie(objname,"",0);
      },
      clearAllCookie:function(){
        this.delCookie("IsAdminister");
        this.delCookie("Customer");
      },

      goodBye:function () {
        if(this.getCookie("Customer")!=="")
        {
          this.$confirm('是否注销当前账户?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$message({
              type: 'success',
              message: '注销成功!'
            });
            this.delCookie("Customer");
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消'
            });
          });
        }
        else
        {
          this.$message({
            message: '你需要先登录噢',
            type: 'warning'
          });
        }



      }
    }

  }
</script>
<!-- 样式代码 -->
<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }
</style>
