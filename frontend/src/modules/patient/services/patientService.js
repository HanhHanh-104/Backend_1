import axios from 'axios'

const API_URL = 'http://localhost:8080/api/taikhoan'

export default {
    login(credentials) {
        return axios.post(`${API_URL}/dangnhap`, credentials)
    },

    register(userData) {
        return axios.post(`${API_URL}/dangky`, {
            maBenhNhan: userData.maBenhNhan,
            tenDangNhap: userData.tenDangNhap,
            matKhau: userData.matKhau,
            xacNhanMatKhau: userData.xacNhanMatKhau
        })
    },

    forgotPassword(data) {
        return axios.post(`${API_URL}/quenmatkhau`, data)
    },

    resetPassword(data) {
        return axios.post(`${API_URL}/doi-mat-khau`, data)
    }
}