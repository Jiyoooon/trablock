import axios from 'axios';

// const API_URL = 'http://j3a101.p.ssafy.io/api/user/';
const API_URL = 'http://localhost:8080/api/user/';
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
}

export default new AuthService();
