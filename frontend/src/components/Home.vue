<template>
  <div class="home-container">
    <!-- Header -->
    <header class="header">
      <div class="logo">
        <img src="/assets/logo.png" alt="Hospital Logo" />
        <h1>Bệnh viện</h1>
      </div>
      <nav class="nav-menu">
        <router-link to="/">Trang chủ</router-link>
        <router-link to="/login">Đăng nhập</router-link>
        <router-link to="/register">Đăng ký</router-link>
      </nav>
    </header>

    <!-- Banner -->
    <section class="banner" :style="{ backgroundImage: 'url(/assets/banner.jpg)' }">
      <div class="banner-content">
        <h2>Chăm sóc sức khỏe của bạn</h2>
        <p>Đặt lịch khám dễ dàng, đội ngũ bác sĩ chuyên môn cao, dịch vụ tận tâm.</p>
        <div class="button-group">
          <button class="btn-login" @click="goToLogin">Đăng nhập</button>
          <button class="btn-register" @click="goToRegister">Đăng ký</button>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
const router = useRouter();

const goToLogin = () => {
  router.push('/login');
};

const goToRegister = () => {
  router.push('/register');
};
</script>

<style scoped>
/* General Styling */
.home-container {
  font-family: 'Arial', sans-serif;
  color: #333;
}

/* Header */
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem 2rem;
  background-color: #007bff;
  color: white;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.logo {
  display: flex;
  align-items: center;
}


.logo img {
  width: 50px;
  margin-right: 12px;
}

.banner {
  height: 300px;
  background-size: cover;
  background-position: center;
  display: flex;
  align-items: center;
  justify-content: center;
}

.button-group {
  margin-top: 1.5rem;
}

.btn-login,
.btn-register {
  margin: 0 10px;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  font-size: 1rem;
  color: white;
  cursor: pointer;
}

.btn-login {
  background-color: #007bff;
}

.btn-register {
  background-color: #28a745;
}



/* Footer */
.footer {
  background-color: #343a40;
  color: white;
  text-align: center;
  padding: 1rem;
  margin-top: 2rem;
}

/* Responsive */
@media (max-width: 768px) {
  .header {
    flex-direction: column;
    text-align: center;
  }

  .nav-menu {
    margin-top: 1rem;
  }

  .nav-menu a {
    margin: 0 0.5rem;
  }

  .banner-content h2 {
    font-size: 1.8rem;
  }

  .banner-content p {
    font-size: 1rem;
  }
}
</style>