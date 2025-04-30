import { createRouter, createWebHistory } from 'vue-router'
import PatientEditPage from '../pages/PatientEditPage.vue'
import PatientListPage from '../pages/PatientListPage.vue'

const routes = [
    {
        path: '/login',
        name: 'Login',
        component: PatientEditPage,
        meta: { requiresGuest: true }
    },
    {
        path: '/register',
        name: 'Register',
        component: PatientListPage,
        meta: { requiresGuest: true }
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router