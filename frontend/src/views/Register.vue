<template>
  <div class="main">
    <div v-if="showRegister" class="register-box">
      <div class="title">Register</div>
      <div class="input">
        <input type="text" placeholder="VUnetID" v-model="registerData.vunetId" />
      </div>
      <div class="input">
        <input type="text" placeholder="Student Name" v-model="registerData.studentName" />
      </div>
      <div class="input">
        <input type="email" placeholder="Email" v-model="registerData.email" />
      </div>
      <div class="input">
        <input type="password" placeholder="Password" v-model="registerData.password" />
      </div>
      <div class="btn-group">
        <button class="btn" @click="registerAction">Register</button>
      </div>
    </div>
    <div v-if="showChangePassword" class="change-password-box">
      <div class="title">Change Password</div>
      <div class="input">
        <input type="text" placeholder="VUnetID" v-model="changePasswordData.vunetId" />
      </div>
      <div class="input">
        <input type="password" placeholder="Old Password" v-model="changePasswordData.oldPassword" />
      </div>
      <div class="input">
        <input type="password" placeholder="New Password" v-model="changePasswordData.newPassword" />
      </div>
      <div class="btn-group">
        <button class="btn" @click="changePasswordAction">Change Password</button>
      </div>
    </div>

  </div>
</template>

<script setup>
import {reactive, ref} from "vue";
import { register , changePassword } from "../api/api";
import {useRouter} from "vue-router";


const showRegister = ref(false);
const showChangePassword = ref(false);

const router = useRouter();
const route = router.currentRoute.value;

if (route.query.action === "register") {
  showRegister.value = true;
} else if (route.query.action === "changePassword") {
  showChangePassword.value = true;
}
const registerData = reactive({
  vunetId: "",
  studentName: "",
  email: "",
  password: "",
});

const changePasswordData = reactive({
  vunetId: "",
  oldPassword: "",
  newPassword: "",
});

const registerAction = () => {
  register(registerData)
      .then((res) => {
        alert(res.data.message);
      })
      .catch((error) => {
        console.error("Error registering:", error);
        alert("Failed to register user");
      });
};

const changePasswordAction = () => {
  changePassword(changePasswordData)
      .then((res) => {
        alert(res.data.message);
      })
      .catch((error) => {
        console.error("Error changing password:", error);
        alert("Failed to change password");
      });
};
</script>

<style scoped>
.main {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.register-box {
  width: 300px;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f9f9f9;
}

.title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 20px;
  text-align: center;
}

.input {
  margin-bottom: 10px;
}

.input input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.btn-group {
  text-align: center;
}

.btn {
  padding: 10px 20px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.btn:hover {
  background-color: #0056b3;
}
</style>