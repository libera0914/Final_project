<template>
  <div class="error-page">
    <img src="../static/404.jpg" style="width: 100%;height: 100%">
     <a-button type="primary" @click="back" class="back-btn">
      Back Home
    </a-button>
  </div>
</template>

<script>
  export default {
    methods: {
      back() {
        this.$router.push("/");
      }
    }
  }
</script>

<style lang="less" scoped>
.error-page {
  position: relative;
  .back-btn {
    position:absolute;
    bottom: 10px;
    left:50%;
    transform:translate(-50%,-50%);
    text-align:center;
  }
}
</style>