<template>
  <div class="register-container">
    <h2>Đăng ký</h2>
    <form @submit.prevent="handleRegister">
      <input v-model="username" placeholder="Tên đăng nhập" required />
      <input v-model="password" type="password" placeholder="Mật khẩu" required />
      <button type="submit">Đăng ký</button>
      <p v-if="message">{{ message }}</p>
    </form>
  </div>
</template>

<script>
import { register } from './auth.api';

export default {
  data() {
    return {
      username: '',
      password: '',
      message: '',
    };
  },
  methods: {
    async handleRegister() {
      try {
        await register({ username: this.username, password: this.password });
        this.message = 'Đăng ký thành công! Hãy đăng nhập.';
        this.$router.push('/login');
      } catch (err) {
        this.message = err.response?.data?.message || 'Đăng ký thất bại';
      }
    }
  }
};
</script>

<style scoped>
.register-container {
  max-width: 400px;
  margin: 50px auto;
}
input, button {
  display: block;
  width: 100%;
  margin-bottom: 10px;
}
</style>
