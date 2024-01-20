import { message } from "ant-design-vue";
/**
 * @description 系统提示
 * @param {string} msg 提示信息
 */
export function toast(msg) {
  message.success(msg);
}
toast.fail = (msg) => {
  return message.error(msg);
};

/**
 * @description: 初始化默认起始时间
 * @param {Object} 时间 (moment对象)
 * @returns
 */
export function formatStartTime(time) {
  if (time._isAMomentObject) {
    return time.format("YYYY-MM-DD") + " 00:00:00";
  } else {
    return time + " 00:00:00";
  }
}

/**
 * @description: 初始化默认结束时间
 * @param {Object} 时间 (moment对象)
 * @returns
 */
export function formatEndTime(time) {
  if (time._isAMomentObject) {
    return time.format("YYYY-MM-DD") + " 23:59:59";
  } else {
    return time + " 23:59:59";
  }
}

/**
 * @description: 数组转树
 * @param {Array} data 
 * @param {string} mid 自身id的字段名
 * @param {string} pid 父级id的字段名
 * @param {string} c 子类集合的字段名
 * @returns 
 */
export function ArrayToTree(array, mid = "id", pid = "parentId", c = "children") {
  const newList = []
  for (let i = 0; i < array.length; i++) {
      if (!array[i].parentId) { //获取最顶层元素，它的父节点ID=0
          newList.push(array[i])
      } else {
          const parent = array.find(item => item[mid] === array[i][pid])  // 获取当前节点的父节点
          if (parent) {
              // 把当前节点 加入到 父节点中
              if (parent[c]) {
                  parent[c].push(array[i])
              } else {
                  parent[c] = [array[i]]
              }
          }
      }
  }
  return newList
}

/**
* @description: 树转数组
* @param {*} tree 
* @returns 
*/
export function TreeToArray(tree) {
  // 判断 tree 是否有值，无返回 []
  if (!Array.isArray(tree) || !tree.length) return []
  let res = []
  tree.forEach(v => {
      // tree的每个元素都 放入到 res里面
      res.push(v)
      if (v.children) {
          // 有children 就把 children数据递归 返回  依次放到 res里面
          res.push(...TreeToArray(v.children))
      }
  })
  return res
}

