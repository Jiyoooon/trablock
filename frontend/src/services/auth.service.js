import axios from 'axios';
import authHeader from '@/services/auth-header.js';

const API_URL = 'http://j3a101.p.ssafy.io/api/user/';
const API_URL2 = 'http://j3a101.p.ssafy.io/api'
// const API_URL = 'http://localhost:8080/api/user/';
// const API_URL2 = 'http://localhost:8080/api'
// id, email, password, created, nickname
class AuthService {
  login(user) {
    return axios
      .post(API_URL + 'login', {
        email : user.email,
        password: user.password
      })
      .then(response => {
        
        if (response.data) {
          localStorage.setItem('user', JSON.stringify(response.data));
          localStorage.setItem('token', response.headers.token);
        }
        if(response.status == 401){
          //console.log();
        }
        else if(response.status == 400){
          //console.log();
        }
        return response.data;
      });
  }
  
  edit(user){
    return axios.post(API_URL + 'editprofile', {
        email : user.email,
        password : user.password,
        nickname : user.nickname
      });
  }

  logout() {
    localStorage.removeItem('user');
  }

  register(user) {
    return axios.post(API_URL + 'join', {
      email: user.email,
      password: user.password,
      nickname: user.nickname
    }).then(error =>{
      if(error.response.status == 400) {
        //console.log(error.response.status);
      }
      
    });
  }

  withdraw() {
    return axios.delete(API_URL2 + '/token/wallets',
     {//header 
      headers: authHeader() 
      }).then(({ data }) => {
        console.log(data);
        localStorage.removeItem('user');
    })
  }
}

export default new AuthService();
