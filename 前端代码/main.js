// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
//import 'element-ui/lib/theme-default/index.css'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import animate from 'animate.css'
import './assets/icon/iconfont.css'
import './assets/icon/iconfont.js'
import echarts from 'echarts'

Vue.prototype.$http = axios
Vue.prototype.$axios = axios
Vue.prototype.$echarts=echarts
Vue.config.productionTip = false

/* eslint-disable no-new */

Vue.use(ElementUI)

new Vue({
  el: '#app',
  router,
  created(){
    axios.defaults.baseURL='http://localhost:8080'
  },
  components: { App },
  template: '<App/>'
})
