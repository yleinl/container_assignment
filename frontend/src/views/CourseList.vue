<template>
  <div class="course-list">
    <h2>Available Courses</h2>
    <ul>
      <li v-for="course in courses" :key="course.courseId">
        <h3>{{ course.courseName }}</h3>
        <p><strong>Lecturer:</strong> {{ course.lecturer }}</p>
        <p>{{ course.studyGuide }}</p>
      </li>
    </ul>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import {getCourseList, getCourseName} from '../api/api';

export default {
  setup() {
    const courses = ref([]);

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
    };
  },
};
</script>

<style scoped>
.course-list {
}
</style>
