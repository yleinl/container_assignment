<template>
  <div class="course-list-container">
    <h2>Available Courses</h2>
    <div class="course-grid">
      <div v-for="course in courses" :key="course.courseId" class="course-card">
        <h3>{{ course.courseName }}</h3>
        <p><strong>Lecturer:</strong> {{ course.lecturer }}</p>
        <p>{{ course.studyGuide }}</p>
      </div>
    </div>
    <button class="logout-btn" @click="logout">Logout</button>
    <button class="select-btn" @click="select">Select Course</button>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import {getCourseList, getCourseName} from '../api/api';

import { useRouter } from 'vue-router';


export default {
  setup() {
    const courses = ref([]);
    const router = useRouter();
    const logout = () => {
      localStorage.removeItem('vunetId');
      router.push('/login');
    };

    const select = () => {
      router.push('/select');
    };

    const fetchCourses = async () => {
      try {
        const vunetId = localStorage.getItem('vunetId');
        const response = await getCourseName(vunetId);
        courses.value = response.data.data;
      } catch (error) {
        console.error('Error fetching courses:', error);
      }
    };

    onMounted(fetchCourses);

    return {
      courses,
      logout,
      select,
    };
  },
};
</script>

<style scoped>
.course-list-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.course-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  grid-gap: 20px;
}

.course-card {
  background-color: #f9f9f9;
  padding: 20px;
  border-radius: 5px;
}


.logout-btn,
.select-btn {
  display: block;
  margin-top: 20px;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.logout-btn {
  background-color: #ff4d4f;
  color: #fff;
}

.select-btn {
  background-color: #007bff;
  color: #fff;
}

.logout-btn:hover,
.select-btn:hover {
  background-color: #ccc;
}
</style>