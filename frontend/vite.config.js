import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    open: false,
    port: 5171,
    https: false,
    hotOnly: false,
    proxy: {
      "/api": {
        target: "http://34.38.136.26:5000",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, "")
      },
    },
  }
})
