import App from './App'
import uView from 'uview-ui';
import store from './store/index.js'
Vue.use(uView)
Vue.prototype.$elyasApi = 'http://t3s9140079.goho.co/'

 import tabBar from "@/common/tabBar.vue"
Vue.component('tabBar', tabBar)
			
			
// #ifndef VUE3
import Vue from 'vue'
Vue.config.productionTip = false
App.mpType = 'app'
const app = new Vue({
	store,
    ...App
})
app.$mount()
// #endif


// #ifdef VUE3
import { createSSRApp } from 'vue'
export function createApp() {
  const app = createSSRApp(App)
  return {
    app
  }
}
// #endif