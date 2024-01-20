import Vue from "vue";
import VueRouter from "vue-router";
import NProgress from "nprogress";
import routes from "./router";

const router = new VueRouter({
  routes
});

router.beforeEach((to, from, next) => {
  NProgress.start();
  next();
});

router.afterEach(() => {
  NProgress.done();
});

Vue.use(VueRouter);
export default router;
