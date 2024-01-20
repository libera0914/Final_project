// 请求头类型
export const CONTENT_TYPE_MAP = {
  JSON: "application/json; charset=utf-8",
  FORM: "application/x-www-form-urlencoded; charset=utf-8",
  FORMDATA: "multipart/form-data",
};

/**
 * @param {boolean} isHasToken 是否请求带token - 默认(true)
 * @param {boolean} isOpenPath 是否直接打开链接 - 默认(false)
 */
const DEDAULT_OPERATION = {
  isHasToken: true,
  isOpenPath: false
};

// 默认请求操作
export const DEDAULT_REQUEST_OPERATION = {
  ...DEDAULT_OPERATION,
  contentType: CONTENT_TYPE_MAP["JSON"],
};

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
 *
 */
export const DEDAULT_REQUEST_OPTION = {
  url: "",
  contentType: "JSON",
  method: "POST",
  data: {},
  params: {},
  onProgress: () => {},
  progressPrecision: 2
};

