import { createRouter, createWebHashHistory } from 'vue-router';

export const constantRoutes = [
  {
    path: '/',
    redirect: '/login',
    name: 'Login',
    children: [
      {
        path: '/login',
        name: 'Login',
        component: () => import('../views/Login.vue'),

      },
    ],
  },
  {
    path: '/select',
    name: 'CourseSelection',
    component: () => import('../views/CourseSelection.vue'),
  },
  {
    path: '/list',
    name: 'CourseList',
    component: () => import('../views/CourseList.vue'),
  },
  {
    path: '/register',
    name: 'Registration',
    action: 'register',
    component: () => import('../views/Register.vue'),
  },
  {
    path: '/change',
    name: 'ChangePassword',
    action: 'changePassword',
    component: () => import('../views/Register.vue'),
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes: constantRoutes,
});


export default router;
