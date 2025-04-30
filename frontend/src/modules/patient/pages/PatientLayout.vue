template>
  <AuthLayout>
    <LoginForm @login="handleLogin" />
  </AuthLayout>
</template>

<script>
import PatientRegisterPage from './PatientRegisterPage.vue'
import PatientList from '../components/PatientList.vue'

export default {
  components: { AuthLayout: PatientRegisterPage, LoginForm: PatientList },
  methods: {
    async handleLogin(credentials) {
      try {
        await this.$store.dispatch('auth/login', credentials)
        this.$router.push('/')
      } catch (error) {
        console.error('Login failed:', error)
      }
    }
  }
}
</script>