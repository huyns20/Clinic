import axios from 'axios';

// Đọc URL Backend từ file .env (Vite: VITE_API_BASE_URL)
// Khi chạy npm run dev, biến này thường trỏ tới http://localhost:8080/api/v1
// Khi chạy Docker Nginx, mặc định fallback là '/api/v1' qua reverse proxy
export const API_BASE_URL = (import.meta.env && import.meta.env.VITE_API_BASE_URL) 
  ? import.meta.env.VITE_API_BASE_URL 
  : '/api/v1';

console.log(`[Clinic Client] Khởi tạo kết nối Backend REST API: ${API_BASE_URL}`);

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
  timeout: 15000,
});

// Interceptor log request để dễ debug trên UI DevTools
api.interceptors.request.use(
  (config) => {
    console.debug(`[API Request] ${config.method?.toUpperCase()} ${config.baseURL}${config.url}`, config.params || config.data || '');
    return config;
  },
  (error) => {
    console.error('[API Request Error]', error);
    return Promise.reject(error);
  }
);

// Interceptor xử lý response và chi tiết lỗi
api.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    const errorMsg = error.response?.data?.message || error.response?.data?.error || error.message || 'Lỗi mạng không xác định';
    console.error(`[API Response Error] ${error.config?.method?.toUpperCase()} ${error.config?.url} -> Status: ${error.response?.status || 'Network Error'} - ${errorMsg}`);
    return Promise.reject(error);
  }
);

export const patientApi = {
  getAll: () => api.get('/benh-nhan'),
  search: (keyword, page = 0, size = 20) => api.get('/benh-nhan/search', { params: { keyword, page, size } }),
  getById: (id) => api.get(`/benh-nhan/${id}`),
  getHoSo360: (maBN) => api.get(`/benh-nhan/${maBN}/ho-so-360`),
  create: (data) => api.post('/benh-nhan', data),
  update: (id, data) => api.put(`/benh-nhan/${id}`, data),
  delete: (id) => api.delete(`/benh-nhan/${id}`),
};

export const masterApi = {
  // Bác sĩ
  getDoctors: () => api.get('/master/bac-sy'),
  createDoctor: (data) => api.post('/master/bac-sy', data),
  deleteDoctor: (id) => api.delete(`/master/bac-sy/${id}`),

  // Y tá
  getNurses: () => api.get('/master/y-ta'),
  createNurse: (data) => api.post('/master/y-ta', data),
  deleteNurse: (id) => api.delete(`/master/y-ta/${id}`),

  // Danh mục bệnh
  getDiseases: () => api.get('/master/danh-muc-benh'),
  createDisease: (data) => api.post('/master/danh-muc-benh', data),
  deleteDisease: (id) => api.delete(`/master/danh-muc-benh/${id}`),

  // Thiết bị y tế
  getDevices: () => api.get('/master/thiet-bi'),
  createDevice: (data) => api.post('/master/thiet-bi', data),
  deleteDevice: (id) => api.delete(`/master/thiet-bi/${id}`),

  // Dịch vụ y tế
  getServices: () => api.get('/master/dich-vu'),
  createService: (data) => api.post('/master/dich-vu', data),
  deleteService: (id) => api.delete(`/master/dich-vu/${id}`),

  // Phòng khám
  getRooms: () => api.get('/master/phong-kham'),
  createRoom: (data) => api.post('/master/phong-kham', data),
  deleteRoom: (id) => api.delete(`/master/phong-kham/${id}`),

  // Giường bệnh
  getBeds: () => api.get('/master/giuong-benh'),
  createBed: (data) => api.post('/master/giuong-benh', data),
  deleteBed: (id) => api.delete(`/master/giuong-benh/${id}`),
};

export const clinicApi = {
  // Danh mục dùng chung
  getDepartments: () => api.get('/khoa'),
  getDoctors: () => api.get('/bac-sy'),
  getDiseases: () => api.get('/danh-muc-benh'),
  getRooms: () => api.get('/phong-kham'),
  getBeds: () => api.get('/giuong-benh'),
  getAvailableBeds: () => api.get('/giuong-benh/trong'),

  // Khám & Sự kiện Y tế
  getEvents: () => api.get('/su-kien-y-te'),
  createExamination: (data) => api.post('/lan-kham', data),
  getPatientHistory: (maBN) => api.get(`/benh-nhan/${maBN}/lich-su-kham`),

  // Đợt điều trị & Lần chữa bệnh
  getTreatmentCourses: (trangThai) => api.get('/dot-dieu-tri', { params: trangThai ? { trangThai } : {} }),
  createTreatmentCourse: (data) => api.post('/dot-dieu-tri', data),
  closeTreatmentCourse: (maDot, ngayKetThuc) => api.put(`/dot-dieu-tri/${maDot}/dong`, null, { params: ngayKetThuc ? { ngayKetThuc } : {} }),
  createTreatmentSession: (data) => api.post('/lan-chua-benh', data),

  // Kho Dược & Kê đơn
  getMedicines: () => api.get('/thuoc'),
  getLowStockMedicines: () => api.get('/thuoc/canh-bao-ton'),
  createMedicine: (data) => api.post('/thuoc', data),
  restockMedicine: (maThuoc, soLuongNhap) => api.put(`/thuoc/${maThuoc}/nhap-kho`, { soLuongNhap }),
  prescribeMedicine: (data) => api.post('/thuoc/ke-don', data),

  // Thu ngân & Viện phí
  getInvoices: (trangThaiTT) => api.get('/hoa-don', { params: trangThaiTT ? { trangThaiTT } : {} }),
  getInvoiceDetails: (maSuKien) => api.get(`/hoa-don/${maSuKien}`),
  payInvoice: (maSuKien) => api.put(`/hoa-don/${maSuKien}/thanh-toan`),

  // Báo cáo & Thống kê
  getStats: () => api.get('/thong-ke/tong-quan'),
  getDepartmentRevenue: () => api.get('/thong-ke/doanh-thu-khoa'),
  getDiseasesMonthly: (thang) => api.get('/thong-ke/benh-theo-thang', { params: thang ? { thang } : {} }),
  getDetailedRevenue: (thang) => api.get('/thong-ke/doanh-thu-chi-tiet', { params: thang ? { thang } : {} }),
  getSalaries: (thang) => api.get('/thong-ke/bang-luong-chi-tiet', { params: thang ? { thang } : {} }),
  getDetailedSalaries: (thang) => api.get('/thong-ke/bang-luong-chi-tiet', { params: thang ? { thang } : {} }),
  getStaffSalaries: () => api.get('/thong-ke/luong-nhan-vien'),
};

export default api;
