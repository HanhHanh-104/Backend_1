<template>
  <div class="login-container">
    <h2>Đăng nhập</h2>
    <form @submit.prevent="handleLogin">
      <input v-model="username" type="text" placeholder="Tên đăng nhập" required />
      <input v-model="password" type="password" placeholder="Mật khẩu" required />
      <button type="submit">Đăng nhập</button>
      <p v-if="error" class="error">{{ error }}</p>
    </form>
  </div>
</template>

<script>
import { login } from './auth.api';

export default {
  data() {
    return {
      username: '',
      password: '',
      error: null,
    };
  },
  methods: {
    async handleLogin() {
      try {
        const response = await login({ username: this.username, password: this.password });
        // Lưu token, chuyển trang, v.v.
        localStorage.setItem('token', response.data.token);
        this.$router.push('/'); // chuyển đến trang chính
      } catch (err) {
        this.error = err.response?.data?.message || 'Đăng nhập thất bại';
      }
    }
  }
};
</script>

<style scoped>
.login-container {
  max-width: 400px;
  margin: 50px auto;
}
input, button {
  display: block;
  width: 100%;
  margin-bottom: 10px;
}
.error {
  color: red;
}
</style>
