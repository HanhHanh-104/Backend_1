<template>
  <div class="appointments">
    <h2>Đặt lịch khám</h2>

    <!-- Chọn chuyên khoa -->
    <div class="form-group">
      <label>Chuyên khoa <span class="required">*</span></label>
      <select v-model="selectedSpecialty" @change="fetchDoctors">
        <option disabled value="">-- Chọn chuyên khoa --</option>
        <option v-for="ch in specialties" :key="ch" :value="ch">{{ ch }}</option>
      </select>
    </div>

    <!-- Chọn bác sĩ -->
    <div class="form-group" v-if="doctors.length">
      <label>Bác sĩ <span class="required">*</span></label>
      <select v-model="form.maBS">
        <option disabled value="">-- Chọn bác sĩ --</option>
        <option v-for="bs in doctors" :key="bs.maBS" :value="bs.maBS">
          {{ bs.hoTen }} - {{ bs.chuyenKhoa }}
        </option>
      </select>
    </div>

    <!-- Ngày khám -->
    <div class="form-group">
      <label>Ngày khám <span class="required">*</span></label>
      <input type="date" v-model="form.ngay" @change="fetchCaKham" :min="minDate" :max="maxDate" />
    </div>

    <!-- Chọn ca khám -->
    <div class="form-group" v-if="availableSlots.length">
      <label>Ca khám <span class="required">*</span></label>
      <select v-model="form.maCa">
        <option disabled value="">-- Chọn ca --</option>
        <option v-for="ca in availableSlots" :key="ca.maCa" :value="ca.maCa">
          {{ ca.tenCa }} ({{ ca.batDau }} - {{ ca.ketThuc }})
        </option>
      </select>
    </div>

    <!-- Thông tin người khám -->
    <div class="form-group checkbox-group">
      <label>
        <input type="checkbox" v-model="selfBooking" /> Khám cho chính tôi
      </label>
    </div>
    <div v-if="!selfBooking" class="form-group">
      <label>Họ tên người khám <span class="required">*</span></label>
      <input v-model="form.tenBN" required />
    </div>

    <!-- Ghi chú -->
    <div class="form-group">
      <label>Ghi chú</label>
      <textarea v-model="form.ghiChu" placeholder="Triệu chứng, yêu cầu..."></textarea>
    </div>

    <!-- Đặt lịch -->
    <button @click="submitAppointment" :disabled="loading" class="submit-btn">
      <span v-if="loading">Đang xử lý...</span>
      <span v-else>Đặt lịch hẹn</span>
    </button>

    <p v-if="message" class="message">{{ message }}</p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      selectedSpecialty: '',
      specialties: ['Nội tổng quát', 'Tim mạch', 'Sản khoa'],
      doctors: [],
      availableSlots: [],
      selfBooking: true,
      form: {
        maBS: '',
        maCa: '',
        ngay: '',
        tenBN: '',
        ghiChu: '',
      },
      message: '',
      loading: false,
    };
  },
  computed: {
    minDate() {
      return new Date().toISOString().split('T')[0];
    },
    maxDate() {
      const date = new Date();
      date.setDate(date.getDate() + 30);
      return date.toISOString().split('T')[0];
    },
  },
  methods: {
    async fetchDoctors() {
      // Gọi API bác sĩ theo chuyên khoa
      const res = await fetch(`http://localhost:8081/api/bacsi?chuyenKhoa=${this.selectedSpecialty}`);
      const data = await res.json();
      this.doctors = data.data || [];
    },
    async fetchCaKham() {
      // Gọi API danh sách ca khám hợp lệ theo ngày và bác sĩ (ví dụ giả định)
      const res = await fetch(`http://localhost:8081/api/cakham/ngay?maBS=${this.form.maBS}&ngay=${this.form.ngay}`);
      const data = await res.json();
      this.availableSlots = data.data || [];
    },
    async submitAppointment() {
      if (!this.form.maBS || !this.form.maCa || !this.form.ngay) {
        alert("Vui lòng điền đủ thông tin");
        return;
      }

      this.loading = true;
      this.message = '';
      try {
        const maBN = localStorage.getItem("maBenhNhan");
        const req = {
          maBN,
          maBS: this.form.maBS,
          maCa: this.form.maCa,
          ngayHen: `${this.form.ngay}T08:00:00`, // mặc định 8h cho ca sáng
          ghiChu: this.form.ghiChu,
        };

        const res = await fetch("http://localhost:8081/api/lichhen/dangky", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(req),
        });

        const result = await res.json();
        this.message = result.message;
      } catch (err) {
        this.message = "Đặt lịch thất bại";
      } finally {
        this.loading = false;
      }
    },
  },
};
</script>

<style scoped>
/* Container */
.appointments {
  max-width: 600px;
  margin: 2rem auto;
  padding: 2rem;
  background-color: #ffffff;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  font-family: 'Roboto', sans-serif;
  background-color: #f5f7fa; /* Xám pastel nhạt */
}

/* Heading */
h2 {
  text-align: center;
  color: #81c9fa; /* Xanh pastel nhạt */
  font-size: 1.8rem;
  font-weight: 700;
  margin-bottom: 2rem;
}

/* Form Group */
.form-group {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
  margin-bottom: 1.5rem;
}

/* Checkbox Group */
.checkbox-group {
  display: flex;
  align-items: center;
  gap: 0.5rem;
}

/* Labels */
label {
  font-size: 0.95rem;
  font-weight: 500;
  color: #424242;
}

.required {
  color: #f8bbd0; /* Hồng pastel nhạt */
  font-size: 0.9rem;
}

/* Inputs, Select, Textarea */
input,
select,
textarea {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #d1d5db;
  border-radius: 6px;
  font-size: 1rem;
  color: #333;
  background-color: #e0f7fa; /* Xanh pastel nhạt */
  transition: border-color 0.3s ease, box-shadow 0.3s ease;
}

input:focus,
select:focus,
textarea:focus {
  outline: none;
  border-color: #ce93d8; /* Tím pastel nhạt */
  box-shadow: 0 0 0 3px rgba(206, 147, 216, 0.1);
}

textarea {
  min-height: 100px;
  resize: vertical;
}

input::placeholder,
textarea::placeholder {
  color: #9ca3af;
}

/* Submit Button */
.submit-btn {
  width: 100%;
  padding: 0.9rem;
  background-color: #ce93d8; /* Tím pastel nhạt */
  color: white;
  font-size: 1rem;
  font-weight: 500;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.1s ease;
}

.submit-btn:disabled {
  background-color: #d1d5db;
  cursor: not-allowed;
}

.submit-btn:hover:not(:disabled) {
  background-color: #ba68c8;
  transform: translateY(-2px);
}

.submit-btn:active:not(:disabled) {
  transform: translateY(0);
}

/* Message */
.message {
  text-align: center;
  margin-top: 1rem;
  padding: 0.8rem;
  border-radius: 6px;
  font-size: 1rem;
  font-weight: 500;
  background-color: #c8e6c9; /* Xanh lá pastel nhạt */
  color: #2e7d32;
}

/* Responsive */
@media (max-width: 768px) {
  .appointments {
    margin: 1rem;
    padding: 1.5rem;
  }

  h2 {
    font-size: 1.5rem;
  }

  input,
  select,
  textarea {
    font-size: 0.95rem;
    padding: 0.65rem;
  }

  .submit-btn {
    font-size: 0.95rem;
    padding: 0.8rem;
  }
}

@media (max-width: 480px) {
  .appointments {
    padding: 1rem;
  }

  h2 {
    font-size: 1.3rem;
  }

  label {
    font-size: 0.9rem;
  }

  input,
  select,
  textarea {
    font-size: 0.9rem;
    padding: 0.6rem;
  }

  .submit-btn {
    font-size: 0.9rem;
    padding: 0.7rem;
  }

  .message {
    font-size: 0.9rem;
  }
}
</style>