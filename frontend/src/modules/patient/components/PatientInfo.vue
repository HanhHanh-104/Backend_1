<template>
  <div v-if="patient">
    <h2>Thông tin bệnh nhân</h2>
    <div class="detail-item">
      <span class="label">Mã BN:</span>
      <span>{{ patient.maBenhNhan }}</span>
    </div>
    <div class="detail-item">
      <span class="label">Họ tên:</span>
      <span>{{ patient.hoTen }}</span>
    </div>
    <!-- Thêm các thông tin khác tương tự -->
  </div>
  <div v-else>
    <p>Loading...</p>
  </div>
</template>

<script>
export default {
  props: {
    patient: Object
  }
}
</script>