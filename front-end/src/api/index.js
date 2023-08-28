import axios from 'axios'

export const axiosApi = axios.create({
    baseURL: '/api'
})

axiosApi.defaults.withCredentials = true