import { defineStore } from 'pinia'
import authService from '../services/patientService.js'

export const useAuthStore = defineStore('auth', {
    state: () => ({
        user: null,
        token: localStorage.getItem('token') || null
    }),

    actions: {
        async login(credentials) {
            const response = await authService.login(credentials)
            this.token = response.data.token
            this.user = response.data.user
            localStorage.setItem('token', this.token)
        },

        logout() {
            this.token = null
            this.user = null
            localStorage.removeItem('token')
        },

        async register(userData) {
            const response = await authService.register({
                maBenhNhan: userData.maBenhNhan,
                tenDangNhap: userData.tenDangNhap,
                matKhau: userData.matKhau,
                xacNhanMatKhau: userData.xacNhanMatKhau
            })

            // Có thể tự động đăng nhập sau khi đăng ký nếu cần
            this.token = response.data.token
            this.user = response.data.user
            localStorage.setItem('token', this.token)

            return response
        },

        async forgotPassword(data) {
            const response = await authService.forgotPassword(data)
            return response
        },

        async resetPassword(data) {
            const response = await authService.resetPassword({
                maTaiKhoan: data.maTaiKhoan,
                matKhauCu: '', // Có thể bỏ nếu không yêu cầu
                matKhauMoi: data.matKhauMoi,
                maXacThuc: data.maXacThuc
            })

            // Có thể tự động đăng nhập sau khi reset
            this.token = response.data.token
            this.user = response.data.user
            localStorage.setItem('token', this.token)

            return response
        }
    },

    getters: {
        isAuthenticated: (state) => !!state.token
    }
})