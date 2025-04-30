<template>
  <AuthLayout>
    <ResetPasswordForm
        @request-code="handleRequestCode"
        @reset-password="handleResetPassword"
        :loading="loading"
        :error="error"
    />
  </AuthLayout>
</template>

<script>
import PatientRegisterPage from './PatientRegisterPage.vue'
import PatientSearch from '../components/PatientSearch.vue'
import { useAuthStore } from '../stores/patientStore.js'

export default {
  components: { AuthLayout: PatientRegisterPage, ResetPasswordForm: PatientSearch },
  data() {
    return {
      loading: false,
      error: null
    }
  },
  methods: {
    async handleRequestCode(data) {
      this.loading = true
      this.error = null

      try {
        await useAuthStore().forgotPassword(data)
        // Mã xác thực đã được gửi
      } catch (err) {
        this.error = err.response?.data?.message || 'Gửi mã xác thực thất bại'
      } finally {
        this.loading = false
      }
    },

    async handleResetPassword(data) {
      this.loading = true
      this.error = null

      try {
        await useAuthStore().resetPassword(data)
        this.$router.push('/login?reset-success=true')
      } catch (err) {
        this.error = err.response?.data?.message || 'Đặt lại mật khẩu thất bại'
      } finally {
        this.loading = false
      }
    }
  }
}
</script>