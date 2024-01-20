<template>
  <div class="screen_bg" :style="style">
    <router-view />
  </div>
</template>

<script>
import { LARGESCREENCONFIG } from "../../config/global.config";
export default {
  name: "largeScreen",
  data() {
    return {
      style: {
        transform: "scale(1) translate(-50%, -50%)",
        width: `${LARGESCREENCONFIG.width}px`,
        height: `${LARGESCREENCONFIG.height}px`,
      },
    };
  },
  mounted() {
    this.setScale();
    window.onresize = this.Debounce(this.setScale, 1000);
  },
  methods: {
    Debounce: (fn, t) => {
      const delay = t || 100;
      let timer;
      return function () {
        const args = arguments;
        if (timer) {
          clearTimeout(timer);
        }
        const context = this;
        timer = setTimeout(() => {
          timer = null;
          fn.apply(context, args);
        }, delay);
      };
    },
    // 获取放大缩小比例
    getScale() {
      const w = (window.innerWidth / LARGESCREENCONFIG.width).toFixed(6);
      const h = (window.innerHeight / LARGESCREENCONFIG.height).toFixed(6);
      return w < h ? w : h;
    },
    // 设置比例
    setScale() {
      this.$set(
        this.style,
        "transform",
        `scale(${this.getScale()}) translate(-50%, -50%)`
      );
    },
  },
};
</script>
<style lang="less" scoped>
.screen_bg {
  position: absolute;
  top: 50%;
  left: 50%;
  transform-origin: 0 0;
  overflow: hidden;
}
</style>
