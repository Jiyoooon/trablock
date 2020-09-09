import axios from 'axios';

export default axios.create({
    baseURL : 'http://i3a502.p.ssafy.io:8080/api',
    //baseURL : 'http://localhost:8080/api',
    headers:{
        'Content-type' : 'application/json',
    },
});