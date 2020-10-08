import axios from 'axios';

export default axios.create({
    baseURL : 'http://j3a101.p.ssafy.io/api',
    // baseURL : 'http://localhost:8080/api',
    headers:{
        'Content-type' : 'application/json',
    },
});