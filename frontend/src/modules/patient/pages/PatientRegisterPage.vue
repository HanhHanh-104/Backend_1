<template>
  <div>
    <h1>Tạo bệnh nhân mới</h1>
    <PatientForm @submit="createPatient" />
  </div>
</template>

<script>
import PatientForm from '../components/PatientForm.vue';
import { usePatientStore } from '../stores/patientStore';

export default {
  components: { PatientForm },
  methods: {
    async createPatient(patientData) {
      const store = usePatientStore();
      try {
        await store.createPatient(patientData);
        this.$router.push('/patients');
      } catch (error) {
        console.error('Error creating patient:', error);
      }
    }
  }
}
</script>