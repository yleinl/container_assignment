import axios from 'axios';

const instance = axios.create({
  baseURL: import.meta.env.VITE_BASE_API,
  timeout: 5000,
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded',
  },
  // headers: {
  //   'Content-Type': 'application/json',
  // },
});

instance.interceptors.request.use(

);

instance.interceptors.response.use(
)

export default instance;
