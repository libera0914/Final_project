<template>
  <div id="app">
    <a-config-provider :locale="locale">
      <router-view />
    </a-config-provider>
  </div>
</template>
<script>
import en_GB from "ant-design-vue/lib/locale-provider/en_GB";
export default {
  data() {
    return {
      locale: en_GB,
    };
  },
};
</script>
