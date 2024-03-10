<template>
  <div class="course-selection">
    <h2>Course Selection</h2>
    <ul>
      <li v-for="course in courses" :key="course.courseId" class="course-item">
        <button @click="selectCourse(course)">
          {{ course.courseName }}
        </button>
      </li>
    </ul>
    <router-link to="/list" class="view-courses-link">View All Courses</router-link>
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

<style scoped>
.course-selection {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
}

.course-item {
  margin-bottom: 10px;
}

.view-courses-link {
  display: block;
  margin-top: 20px;
  text-align: center;
  color: #007bff;
  text-decoration: none;
}

.view-courses-link:hover {
  text-decoration: underline;
}
</style>