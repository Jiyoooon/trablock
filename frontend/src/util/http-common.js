import axios from 'axios';

export default axios.create({
    // baseURL : 'http://460af2995189.ngrok.io/api',
    baseURL : 'http://localhost:8080/api',
    headers:{
        'Content-type' : 'application/json',
    },
});