import axios from "axios";
import router from "../router";
import {
  CONTENT_TYPE_MAP,
  DEDAULT_REQUEST_OPTION,
  DEDAULT_REQUEST_OPERATION
} from "../config/request.config";
import { validURL } from "../utils/validate";
import { toast } from "../utils/util";
const { VUE_APP_BASE_API } = process.env;

const service = axios.create({
  // axios中请求配置有baseURL选项，表示请求URL公共部分
  baseURL: VUE_APP_BASE_API,
  // 超时
  timeout: 10000,
});

let optertion = JSON.parse(JSON.stringify(DEDAULT_REQUEST_OPERATION));

// request拦截器
service.interceptors.request.use(
  (config) => {
    const { isHasToken, contentType } = optertion;

    // 让每个请求携带自定义token
    if (isHasToken) {
      config.headers["Authorization"] =
        localStorage.getItem("token") || "";
    }

    config.headers["Content-Type"] = contentType;

    // 请求映射params参数
    if (config.params) {
      let url = config.url + "?" + tansParams(config.params);
      url = url.slice(0, -1);
      config.params = {};
      config.url = url;
    }
    return config;
  },
  (error) => {
    Promise.reject(error);
  }
);

// 响应拦截器
service.interceptors.response.use(
  (res) => {
    const { status, msg } = res,
      { isOpenPath, isAutoToastFail } = optertion;
    // 未设置状态码则默认成功状态
    const code = status || 200;

   

    if (code === 200) {
      if (isOpenPath && validURL(res.data)) {
        window.open(res.data);
      }
      return Promise.resolve(res.data);
    } else {
      isAutoToastFail && toast.fail(msg);
      return Promise.reject(res.data);
    }
  },
  (error) => {
    let { message } = error,
      msg = "";
    if (message == "Network Error") {
      msg = "服务器连接异常";
    } else if (message.includes("timeout")) {
      msg = "请求超时";
    } else if (message.includes("400")) {
      msg = "请求有误";
    } else if (message.includes("500")) {
      msg = "服务器错误";
    }
    toast.fail(msg);
    return Promise.reject({ message: msg });
  }
);

/**
 * @description 默认请求参数
 * @param {string} url 请求地址 - 默认("")
 * @param {string} contentType 请求头类型 JSON: json格式 FORM: form格式 FORMDATA:formData格式 - 默认(JSON)
 * @param {string} method 请求方式 - 默认(post) post put get delete
 * @param {object} data body请求数据 - 默认({})
 * @param {object} params url请求数据 - 默认({})
 * @param {function} isBackProgress 是否开启返回进度条 - 默认(false)
 * @param {function} onProgress 进度条回调函数 (val)=>{ console.log(val) } val: 进度百分比
 * @param {number} progressPrecision 进度条小数点精度
 * @param {boolean} isHasToken 是否请求带token - 默认(true)
 * @param {boolean} isAutoToastFail 是否自动提示报错 - 默认(false)
 * @param {boolean} isOpenPath 是否直接打开链接 - 默认(false)
 * @param {string} fileName 文件名称 - 默认(文件.txt)
 */
export function request(option) {
  const {
    url,
    contentType,
    method,
    data = {},
    params = {},
    isBackProgress,
    onProgress,
    progressPrecision,
    isOpenPath,
    isHasToken,
    isAutoToastFail,
  } = {
    ...DEDAULT_REQUEST_OPTION,
    ...option,
  };

  optertion = {
    isOpenPath,
    isHasToken,
    isAutoToastFail,
    contentType:
      CONTENT_TYPE_MAP[contentType.toString().toUpperCase()] ||
      "application/json; charset=utf-8",
  };

  let config = {
    method,
    url,
    data,
    params,
    onDownloadProgress: (progressEvent) => {
      if (progressEvent.lengthComputable) {
        onProgress(
          ((progressEvent.loaded / progressEvent.total) * 100).toFixed(
            progressPrecision < 0 || progressPrecision > 100
              ? 2
              : progressPrecision
          )
        );
      }
    },
  };

  if (isBackProgress) delete config.onDownloadProgress;

  return service(config);
}

/**
 * @description 参数处理
 * @param {object} params  参数
 */
function tansParams(params) {
  let result = "";
  for (const propName of Object.keys(params)) {
    const value = params[propName];
    var part = encodeURIComponent(propName) + "=";
    if (value !== null && typeof value !== "undefined") {
      if (typeof value === "object") {
        for (const key of Object.keys(value)) {
          if (value[key] !== null && typeof value[key] !== "undefined") {
            let params = propName + "[" + key + "]";
            var subPart = encodeURIComponent(params) + "=";
            result += subPart + encodeURIComponent(value[key]) + "&";
          }
        }
      } else {
        result += part + encodeURIComponent(value) + "&";
      }
    }
  }
  return result;
}


