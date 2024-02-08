<template>
  <div class="course-list">
    <h2>Available Courses</h2>
    <ul>
      <li v-for="course in courses" :key="course.courseId">{{ course.courseName }}</li>
    </ul>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import { getCourseList } from '../api/api';

export default {
  setup() {
    const courses = ref([]);

    const fetchCourses = async () => {
      try {
        const response = await getCourseList();
        courses.value = response.data.data.records;
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
