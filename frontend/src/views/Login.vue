////login
<template>
  <div class="main">
    <div class="login_box">
      <div class="title">Login</div>
      <div class="input">
        <input type="text" placeholder="Please enter your vunetid" v-model="data.vunetId" />
      </div>
      <div class="input">
        <input
            type="password"
            placeholder="Pleas Enter the password"
            v-model="data.password"
        />
      </div>
      <div class="btn_group">
<!--        <button class="btn" @click="reg">Student Register</button>-->
        <button class="btn" @click="Login">Student Login</button>
        <button class="btn" @click="goToRegister">Register</button>
        <button class="btn" @click="goToChangePassword">Change Password</button>
      </div>
    </div>
  </div>
</template>
<script setup>
import { reactive } from "vue";
import { login } from "../api/api";
import { useRouter } from "vue-router";
const router = useRouter();

//Login

const data = reactive({
  vunetId: "",
  password: "",
});

// Student Login
const Login = () => {
  login({
    vunetId: data.vunetId,
    password: data.password,
  })
      .then((res) => {
        if (res.data.code == 200) {
          console.log(res.data);
          localStorage.setItem("vunetId", res.data.data.vunetId);
          localStorage.setItem("studentName", res.data.data.studentName);
          localStorage.setItem("email", res.data.data.email);
          router.push("/list");
        } else {
          alert(res.data.message);
        }
      })
      .catch((res) => {
        alert("Wrong id or password ");
      });
};


const goToRegister = () => {
  router.push({ path: '/register', query: { action: 'register' } });

};

const goToChangePassword = () => {
  router.push({ path: '/change', query: { action: 'changePassword' } });
};
</script>
<style scoped>
.main {
  width: 100%;
  height: 100%;
  background-color: rgb(41, 50, 70);
  display: flex;
  justify-content: center;
  align-items: center;
}

.login_box {
  width: 600px;
  height: 260px;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: #fff;
  border-radius: 20px;
  padding: 20px;
  box-sizing: border-box;
  position: relative;
}
.title {
  font-size: 30px;
  font-weight: 600;
  text-align: left;
  position: absolute;
  top: 10px;
  left: 50%;
  transform: translateX(-50%);
}
.input {
  width: 100%;
  margin-bottom: 10px;
  height: 40px;
  margin-top: 10px
}
input {
  padding: 0 5px;
  width: 100%;
  height: 100%;
  line-height: 40px;
  outline: none;
  border: 1px solid gray;
  box-sizing: border-box;
  margin-top: 20px
}
.btn_group {
  width: 110%;
  display: flex;
  justify-content: space-around;
  align-items: center;
  margin-top: 30px
}
.btn {
  width: 160px;
  height: 30px;
  text-align: center;
  line-height: 30px;
  font-size: 14px;
  color: #000;
  border-radius: 8px;
  background-color: rgb(126, 156, 255);
  margin-top: 10px;
  border: none;
}
.black_btn {
  background-color: yellowgreen;
  color: #000;
}
</style>
