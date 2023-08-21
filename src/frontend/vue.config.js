const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})
module.exports = {
    // https://cli.vuejs.org/config/#devserver-proxy
    devServer: {
        port: 3000,
        proxy: {
            '/api': {
                target: 'http://127.0.0.1:8080',
                ws: true,
                changeOrigin: true
            }
        }
    }
}