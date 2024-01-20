export default [
  {
    path: "/",
    name: "Main",
    component: () => import("../../views/main.vue"),
  },
  {
    path: "/info",
    name: "Information",
    component: () => import("../../views/information.vue"),
  },
  {
    path: "/preparation",
    name: "Preparation",
    component: () => import("../../views/preparation.vue"),
  },
  {
    path: "/tuitionInquiry",
    name: "TuitionInquiry",
    component: () => import("../../views/tuitionInquiry.vue"),
  },
  {
    path: "/404",
    name: "404",
    component: () => import("../../views/notFound.vue"),
  },
];
