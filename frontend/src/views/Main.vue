<template>
    <body class="fill-height">
      <div class="fill-height">
        <video muted autoplay loop>
          <source src="../assets/preview1.mp4" type="video/mp4">
          <strong>Your browser does not support the video tag.</strong>
        </video>
        <v-container id="signinup-form" class="fill-height" style="position : center;">
          <Notification
            :message="snackbarMessage"
            :snackbar="snackbar"
            :type="snackbarType"
          />
        <v-row align="center" justify="center" no-gutters>
          <v-col cols="12" sm="8" md="8" class="">
            <v-card class="elevation-10 card">
              <v-window v-model="step">
                <!--SignIn-->
                <v-window-item :value="1">
                  <v-row class="">
                    <v-col cols="12" md="8" class="pt-6 pb-6">
                      <v-card-text>
                        <v-form class="signup-form-form" @submit.prevent="signin">
                          <h1
                            class="text-center display-1 mb-10"
                            :class="`${bgColor1}--text`"
                          >
                            Sign in
                          </h1>
                          <div class="text-center" mt-4>
                              <v-btn class="mx-2" fab color="primary" outlined>
                                  <v-icon>fab fa-facebook-f</v-icon>
                              </v-btn>
                              <v-btn class="mx-2" fab color="primary" outlined>
                                  <v-icon>fab fa-google-plus-g</v-icon>
                              </v-btn>
                              <v-btn class="mx-2" fab color="amber accent-3" outlined>
                                  <v-icon>fab fa-kaggle</v-icon>
                              </v-btn>
                          </div>
                          <v-text-field
                            placeholder="ID를 입력해주세요"
                            id="email"
                            v-model="user.email"
                            label="email"
                            name="email"
                            type="text"
                            :color="bgColor1"
                          />
                          <v-text-field
                            placeholder="비밀번호를 입력해주세요"
                            id="password"
                            v-model="user.password"
                            label="password"
                            name="password"
                            type="password"
                            :color="bgColor1"
                          />
                          <div class="text-center">
                            <a
                              href="#"
                              class="mt-3 overline no-text-decoration"
                              :class="`${bgColor1}--text`"
                              @click="step = 3"
                            >
                              비밀번호를 잊으셨나요?
                            </a>
                          </div>
                          <div class="text-center mt-6">
                            <v-btn @click="handleLogin" type="submit" large :color="bgColor1" dark
                              >Sign In</v-btn
                            >
                          </div>
                        </v-form>
                      </v-card-text>
                    </v-col>
                    <v-col
                      cols="12"
                      md="4"
                      class="darken-2 vcenter"
                      :class="`${bgColor1}`"
                    >
                      <div>
                        <v-card-text :class="`${fgColor}--text`">
                          <h1 class="text-center headline mb-3">처음 방문이신가요?</h1>
                          <h5 class="text-center overline mb-3">
                            계속하기 위해
                            <br>
                            회원가입을 진행해주세요!
                          </h5>
                        </v-card-text>
                        <div class="text-center mb-6">
                          <v-btn dark outlined @click="step = 2">Sign Up</v-btn>
                        </div>
                      </div>
                    </v-col>
                  </v-row>
                </v-window-item>
                <!--SignUp-->
                <v-window-item :value="2">
                  <v-row class="fill-height">
                    <v-col
                      cols="12"
                      md="4"
                      class="darken-2 vcenter"
                      :class="`${bgColor2}`"
                    >
                      <div>
                        <v-card-text :class="`${fgColor}--text`">
                          <h1 class="text-center headline mb-3">이미 회원이신가요?</h1>
                          <h5 class="text-center overline mb-3">
                            그렇다면
                            <br>
                            로그인 해주세요 :)</h5>
                        </v-card-text>
                        <div class="text-center mb-6">
                          <v-btn dark outlined @click="step = 1">Sign In</v-btn>
                        </div>
                      </div>
                    </v-col>
                    <v-col cols="12" md="8" class=" pt-6 pb-6">
                      <v-card-text>
                        <h1
                          class="text-center display-1 mb-10"
                          :class="`${bgColor2}--text`"
                        >
                          Sign Up
                        </h1>
                        <div class="text-center" mt-4>
                              <v-btn class="mx-2" fab color="primary" outlined>
                                  <v-icon>fab fa-facebook-f</v-icon>
                              </v-btn>
                              <v-btn class="mx-2" fab color="primary" outlined>
                                  <v-icon>fab fa-google-plus-g</v-icon>
                              </v-btn>
                              <v-btn class="mx-2" fab color="amber accent-3" outlined>
                                  <v-icon>fab fa-kaggle</v-icon>
                              </v-btn>
                          </div>
                        <v-form class="signup-form-form" @submit.prevent="signup">
                          <v-text-field
                            id="email"
                            v-model="user2.email"
                            :items="email"
                            :rules="rules.email"
                            required
                            label="email"
                            name="email"
                            type="email"
                          >
                          <template v-slot:append>
                            <v-fade-transition leave-absolute>
                              <v-progress-circular
                                v-if="loadingE"
                                size="24"
                                color="info"
                                indeterminate
                              ></v-progress-circular>
                              <v-icon v-if="!loadingE && eCheck==0" class="mx-2" width="24" height="24" @click="checkEmail" color="light-blue">fas fa-check</v-icon>
                              <v-icon v-if="eCheck==1" class="mx-2" width="24" height="24" @click="checkEmail" color="red">fas fa-times</v-icon>
                              <v-icon v-if="eCheck==2" class="mx-2" width="24" height="24" @click="checkEmail" color="green">fas fa-check</v-icon>
                            </v-fade-transition>
                          </template>
                          </v-text-field>

                          <v-text-field
                            id="password"
                            v-model="user2.password"
                            :items="password"
                            :rules="rules.password"
                            required
                            label="password"
                            name="password"
                            type="password"
                          />
                          <v-text-field
                            id="nickname"
                            v-model="user2.nickname"
                            :items="nickname"
                            :rules="rules.nickname"
                            required
                            label="nickname"
                            name="nickname"
                            type="text"
                          />
                          <div class="text-center mt-6">
                            <v-btn @click="handleRegister" large :color="bgColor2" dark>
                              Sign Up</v-btn
                            >
                          </div>
                        </v-form>
                      </v-card-text>
                    </v-col>
                  </v-row>
                </v-window-item>
                <!--PW Rest-->
                <v-window-item :value="3">
                  <v-row class="fill-height">
                    <v-col
                      cols="12"
                      md="4"
                      class="darken-2 vcenter"
                      :class="`${bgColor2}`"
                    >
                      <div>
                        <v-card-text :class="`${fgColor}--text`">
                          <h1 class="text-center headline mb-3">이미 회원이신가요?</h1>
                          <h5 class="text-center overline mb-3">
                            그렇다면
                            <br>
                            로그인 해주세요 :)
                          </h5>
                        </v-card-text>
                        <div class="text-center mb-6">
                          <v-btn dark outlined @click="step = 1">Sign In</v-btn>
                        </div>
                      </div>
                    </v-col>
                    <v-col cols="12" md="8" class="pt-6 pb-6">
                      <v-card-text>
                        <v-form class="signup-form-form">
                          <h1
                            class="text-center display-1 mb-10"
                            :class="`${bgColor2}--text`"
                          >
                            Reset Password
                          </h1>
                          <v-text-field
                            id="login"
                            v-model="login"
                            label="Username / eMail"
                            name="login"
                            type="text"
                            :color="bgColor2"
                            class="v-input__icon--double"
                          />
                          <div class="text-center mt-6">
                            <v-btn large :color="bgColor2" dark
                              >Reset Password</v-btn
                            >
                          </div>
                        </v-form>
                      </v-card-text>
                    </v-col>
                  </v-row>
                </v-window-item>
              </v-window>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </div>
  </body>
</template>

<script>
import User from '../models/user';
import http from '@/util/http-common.js'
// import Notification from './Notification'
export default {
  name: 'Main',
  components: {
    // Notification
  },
  props: {
    source: {
      type: String,
      default: ''
    },
    bgColor1: {
      type: String,
      default: 'green'
    },
    bgColor2: {
      type: String,
      default: 'amber'
    },
    fgColor: {
      type: String,
      default: 'white'
    }
  },
  async fetch({ store, error }, user) {
    try {
      await store.dispatch('users/signupUser', user)
    } catch (e) {
      error({
        statusCode: 503,
        message: 'Unable to sign up user. Please try again later.'
      })
    }
  },
  data: () => ({
    step: 1,
    username: '',
    email: '',
    password: '',
    login: '',
    snackbarType: 'success',
    snackbarMessage: '',
    snackbar: false,
    user: new User('', '', ''),
    user2: new User('', '', ''),
    rules: {
      email: [val => (val || '').length > 0 || '이메일을 입력해주세요!'],
      password: [val => (val || '').length > 0 || '비밀번호를 입력해주세요!', val => /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/.test(val) || 
               '비밀번호는 숫자, 영문, 특수문자(!@#$%^*+=-)를 조합한 8자 이상이어야 합니다!'],
      nickname: [val => (val || '').length > 0 || '닉네임을 입력해주세요!'],
    },
    loadingE: false,
    eCheck: 0,
    nCheck: 0,
  }),
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },
  created() {
    if (this.$store.state.auth.status.loggedIn) {
      this.$router.push('/group');
    }
  },
  methods: {
    handleLogin() {
      console.log("USER : " + this.user);
      if (this.user.email && this.user.password) {
        this.$store.dispatch('auth/login', this.user).then(
          () => {
            this.$store.state.auth.status.loggedIn = true
            console.log(this.$store.state.auth.status.loggedIn);
            this.$router.push('/group');
          },
          error => {
            this.loading = false;
            this.message =
              (error.response && error.response.data) ||
              error.message ||
              error.toString();
            this.$dialog.notify.error("없는 유저거나 비밀번호가 틀렸습니다.", {
              position: "bottom-right",
              timeout: 3000,
            });

            this.$router.push("/")

          }
        )
        .catch(() => {
          this.$router.push("/error")
        })
      }
    },

    handleRegister() {
      this.user = this.user2;
      this.$store.dispatch('auth/register', this.user).then(
          () => {
          //this.message = data.message;
          this.successful = true;
          
      },
      error => {    
      this.message = 
          (error.response && error.response.data) ||
          error.message ||
          error.toString();
          if(typeof(this.message.message) != 'undefined'){
            this.$dialog.notify.error(this.message.message, {
              position: "bottom-right",
              timeout: 3000,
            });
          }
          else{
            alert("회원가입 완료");
            this.step = 1;
            // this.$dialog.notify.success("회원가입 완료", {
            //   position: "bottom-right", timeout: 3000, });
            //   this.$router.push('/');
          }
      },
      );
      },


    signup() {
      this.$auth
        .signup({
          data: {
            user: {
              username: this.username,
              email: this.email,
              password: this.password
            }
          }
        })
        .catch((e) => {
          this.error = e + ''
        })
    },
    async signin() {
      try {
        const response = await this.$auth.loginWith('local', {
          data: {
            login: this.login,
            password: this.password
          }
        })
        this.snackbarType = response.data.type
        this.snackbarMessage = response.data.message
        this.snackbar = true
      } catch (err) {
        this.snackbarType = 'error'
        this.snackbarMessage = 'Error signing you in'
        this.snackbar = true
      }
    },
    
    checkEmail () {
      this.loadingE = true
      // console.log(this.user2.email);
      http.get(`/user/dup/email/${this.user2.email}`, {
        // email: 
      }).then(({ data }) => {
        console.log(data);
        // if(data.result == "fail"){
          // this.eCheck = 1;
        // }
        // else {
          this.eCheck = 2;
        // }
      }).catch((error) => {
        console.log(error);
      // if(error.response) {
        this.eCheck = 1;
        // this.$router.push("servererror")
      // }             
    });
    // alert(this.eCheck);

      setTimeout(() => {
        this.loadingE = false
      }, 2000)
    },
  }
}
</script>

<style scoped lang="scss">
video {
	position : fixed;
	top : -100px; 
	left : 0;
	min-width : 100%;
	min-height : 100%;
	width : auto;
	height : auto;
	// width : auto;
	// height : 100%;
	z-index : 0;
  filter:opacity(70%);
}
.v-input__icon--double .v-input__icon {
  margin-left: -4.25rem !important;
}
a.no-text-decoration {
  text-decoration: none;
}
#signinup-form {
  max-width: 75rem;
}
.signup-form-form {
  max-width: 23rem;
  margin: 0 auto;
}
.card {
  overflow: hidden;
}
.vcenter {
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
