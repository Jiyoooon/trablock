export default function authHeader() {
    let user = JSON.parse(localStorage.getItem('user'));
    let token = localStorage.getItem('token');
  
    if (user && token) {
      return { Authorization: 'Bearer ' + token }; // for Spring Boot back-end
    } else {
      return {};
    }
}