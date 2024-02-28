import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  //빌드 결과물이 spring boot 프로젝트 내의 static 디렉토리에 생성되도록 설정
  //npm run build를 실행하면 boot 프로젝트 내의 static 폴더에 빌드 결과물이 생성
  build: {
    outDir:"../backend/src/main/resources/static",
  },
  server: {
    proxy: {
      '/api': {  //#1. axios라이브러리 등으로 http 요청인데 api로 시작하면,
        target: 'http://localhost:8080',  //#2. 이쪽 주소로 매핑하여 백그라운드로 보내라.
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ''),
        secure: false,
        ws: true
      }
    }
  }  
})
