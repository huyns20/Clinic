import { defineConfig, loadEnv } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig(({ mode }) => {
  const env = loadEnv(mode, process.cwd(), '');
  
  // Xác định backend target từ env hoặc mặc định port 8080
  const backendTarget = env.VITE_BACKEND_URL || 'http://localhost:8080';
  const serverPort = parseInt(env.VITE_PORT || env.PORT || '5173', 10);

  return {
    plugins: [react()],
    server: {
      host: '0.0.0.0',
      port: serverPort,
      proxy: {
        '/api': {
          target: backendTarget,
          changeOrigin: true,
          secure: false,
          configure: (proxy, _options) => {
            proxy.on('error', (err, _req, _res) => {
              console.log('[Vite Proxy Lỗi]: Không thể kết nối tới Backend:', backendTarget, err.message);
            });
          },
        },
      },
    },
    preview: {
      host: '0.0.0.0',
      port: serverPort,
    },
  };
});
