<template>
  <div class="course-selection">
    <h2>Course Selection</h2>
    <ul>
      <li v-for="course in courses" :key="course.courseId">
        <button @click="selectCourse(course)">
          {{ course.courseName }}
        </button>
      </li>
    </ul>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import { getCourseList, registerCourse } from '../api/api';

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

    const selectCourse = async (course) => {
      try {
        const vunetId = localStorage.getItem('vunetId');
        const response = await registerCourse({ courseId: course.courseId, vunetId: vunetId }, {
          'Content-Type': 'application/x-www-form-urlencoded'
        });
        console.log('Selected course:', course);
        console.log('Course registration response:', response.data);
      } catch (error) {
        console.error('Error registering course:', error);
      }
    };

    onMounted(fetchCourses);

    return {
      courses,
      selectCourse,
    };
  },
};
</script>
