import { request } from "@/utils/request";
export default {

  getCourse: (data) => request({
    url: '/tuition/course',
    method: "post",
    data
  }),

  getMajor: (data) => request({
    url: '/tuition/major',
    method: "post",
    data
  }),

  getTutionByPage: (data) =>request({
    url: '/tuition/page',
    method: 'post',
    data
  })

}