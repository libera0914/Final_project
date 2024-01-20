const plugins = [
  {
    isOpen: false,
    config: {
      "postcss-px2vp": {
        viewportWidth: 750, 
        exclude: [/node_modules/],
      },
    },
  },
];
let postcssPlugins = {};

plugins.forEach((item) => {
  if (item.isOpen) {
    postcssPlugins = { ...postcssPlugins, ...item.config };
  }
});

module.exports = postcssPlugins;
