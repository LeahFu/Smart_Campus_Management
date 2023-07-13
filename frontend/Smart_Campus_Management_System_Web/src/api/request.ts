import axios from "axios"
const service = axios.create({
    baseURL: import.meta.env.VITE_APP_BASE_API,
    timeout: 3000000,
   // withCredentials: true
})
export default service