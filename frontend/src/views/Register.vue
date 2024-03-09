<template>
  <div class="main">
    <div v-if="showRegister"  class="register_box">
      <div class="title">Register</div>
      <div class="input">
        <input type="text" placeholder="Please enter your vunetid" v-model="registerData.vunetId" />
      </div>
      <div class="input">
        <input type="text" placeholder="Please enter your student name" v-model="registerData.studentName" />
      </div>
      <div class="input">
        <input type="email" placeholder="Please enter your email" v-model="registerData.email" />
      </div>
      <div class="input">
        <input type="password" placeholder="Please enter your password" v-model="registerData.password" />
      </div>
      <div class="btn_group">
        <button class="btn" @click="registerAction">Register</button>
      </div>
    </div>

    <div v-if="showChangePassword"  class="change_password_box">
      <div class="title">Change Password</div>
      <div class="input">
        <input type="text" placeholder="Please enter your vunetid" v-model="changePasswordData.vunetId" />
      </div>
      <div class="input">
        <input type="password" placeholder="Please enter your old password" v-model="changePasswordData.oldPassword" />
      </div>
      <div class="input">
        <input type="password" placeholder="Please enter your new password" v-model="changePasswordData.newPassword" />
      </div>
      <div class="btn_group">
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
