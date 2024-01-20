const { defineConfig } = require("@vue/cli-service"),
  Timestamp = new Date().getTime(),
  { NODE_ENV } = process.env;
module.exports = defineConfig({
  transpileDependencies: true,
  publicPath: NODE_ENV === "development" ? "/" : "./",
  outputDir: "dist",
  assetsDir: "static",
  indexPath: "index.html",
  productionSourceMap: false,
  lintOnSave: false,
  configureWebpack: {
    name: "Wayfinder",
    output: {
      filename: `static/js/[name].${Timestamp}.js`,
      chunkFilename: `static/js/[name].${Timestamp}.js`,
    },
  },
});
