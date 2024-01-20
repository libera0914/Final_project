import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

import 'normalize.css/normalize.css'
import "./styles/global.css"
import "@wangeditor/editor/dist/css/style.css"
import "ant-design-vue/dist/antd.css";


import api from './api'
Vue.prototype.$api = api;

import { toast } from './utils/util'
Vue.prototype.$toast = toast;

import Antd from "ant-design-vue";
Vue.use(Antd);

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
