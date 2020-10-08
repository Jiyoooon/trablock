<template>
    <v-row v-if="wCheck==true">
        <v-col cols="2">
                <v-card class="mx-auto">
                    <v-card-subtitle class="pt-5 pb-0 text-center text-h5">사용자 정보</v-card-subtitle>
                    <v-card-text class="grey--text text-center pb-0">
                    <div>{{currentUser.email}}</div>
                    </v-card-text>
                    <v-card-text class="grey--text text-center">
                        <v-divider></v-divider>
                        <v-row>
                            <v-col cols="5" class="text-left py-1 pl-5">닉네임</v-col>
                            <v-col cols="7" class="text-right py-1 pr-5">{{currentUser.nickname}}</v-col>
                        </v-row>
                    </v-card-text>
                </v-card>
                <v-card class="mx-auto mt-2">
                    <v-expansion-panels accordion>
                    <v-expansion-panel>
                        <v-expansion-panel-header>More</v-expansion-panel-header>
                        <v-expansion-panel-content class="px-0">
                            <v-row class="text-center">
                                <v-col cols="12" class="py-0">
                                    <v-btn small color="orange" @click="handleLogout" block dark>Log out</v-btn>                                   
                                </v-col>
                                <v-col cols="12">
                                    <v-btn small color="red" block dark @click="handleWithdraw">withdraw</v-btn>                                   
                                </v-col>
                            </v-row>
                        </v-expansion-panel-content>
                    </v-expansion-panel>
                    </v-expansion-panels>
                </v-card>
            </v-col>
            <v-col cols="10">
                <v-card class="mx-auto" height="71vh">
                    <v-container>
                    <v-row>
        <v-col cols="12" class="pt-5 pb-0 text-center text-h3 font-weight-bold"><div>{{currentUser.nickname}}의 PROFILE</div></v-col>
        <v-col cols="12"></v-col>
        </v-row>

        <v-row>
            <v-col cols="12">
            <template>
                <v-card
                class="mx-auto"
                color="green"
                dark
                max-width="650"
                elevation="10"
                >
                <v-card-title>
                <v-col cols="6" align="left">
                  <v-icon
                  large
                  left
                  >
                  fas fa-wallet
                  </v-icon>
                  <span class="title font-weight-light">내 지갑</span>
                </v-col>
                <v-col cols="6" align="right">
                  <v-chip
                    @click="charge"
                    class="ma-2"
                    color="orange lighten-1"
                  >
                    <v-icon left>
                      mdi-heart
                    </v-icon>
                    충전하기
                  </v-chip>
                </v-col>
                </v-card-title>

                <v-card-text class="headline font-weight-light">
                <v-row align="left">
                    <v-col cols="12" class="display-0" align="right">
                    {{Wallet.address}}
                    </v-col>
                </v-row>
                </v-card-text>
                <v-card-text class="headline font-weight-bold">
                    <v-row align="center">
                    <v-col cols="3" class="display-0.5" align="left">
                        ETH
                    </v-col>
                    <v-col cols="9" class="display-3" align="right">
                        {{ (Number(Wallet.balance) / 1000000000000000000).toFixed(9) }}
                    </v-col>
                    </v-row>
                    <v-row align="center">
                    <v-col cols="3" class="display-0.5" align="left">
                        TBC
                    </v-col>
                    <v-col cols="9" class="display-3" align="right">
                        {{ Number(Wallet.TBC) }}
                    </v-col>
                    </v-row>
                </v-card-text>
                </v-card>
            </template>
            </v-col>
        </v-row>
              </v-container>
          </v-card>
      </v-col>
  </v-row>
</template>


<script>
import http from "@/util/http-common.js";
import authHeader from '@/services/auth-header.js';
export default {
  name: 'MyWalletDetail',
  components: {
    
  },
  
  data () {
      return {
        tab: null,
        drawer: true,
        groups: [],
        color: 'primary',

        right: false,
        permanent: true,
        miniVariant: false,
        expandOnHover: false,
        background: false,

        // 프로필 수정
        U: {
          email: '',
          password: '',
          nickname: '',
          created: ""
        },
        Wallet : {
          address: "",
          balance: "",
          TBC: ""
        },
        dialog: false,

        // wCheck: true,
        wCheck: false,
        access_token: this.$store.state.auth.user.accessToken,

        privateKey: '',
        dialogPK: false,
        btnName: "copy"
      }
    },
  created(){
    // console.log(this.$store.state.auth.status.loggedIn);
    this.U.email = this.$store.state.auth.user.email;
    this.U.password = this.$store.state.auth.user.password
    this.U.nickname= this.$store.state.auth.user.nickname

    //내 계좌 정보 가져오기
    http.get('/token/wallets', { 
        headers: authHeader() 
    }).then(({ data }) => {
      console.log(`data 데이터 : `);
      console.log(data);
      if(data.result == "fail"){
        this.wCheck = false;
      }
      else {
        this.wCheck = true;
        this.Wallet.address = data.address;
        this.Wallet.balance = data.balance;
        this.Wallet.TBC = Number(data.tbc);
      }
    })

  },
  
  methods: {
    charge(){
      // console.log(this.Wallet.address);
       //내 계좌 충전하기
      http.put(`/token/wallets/${this.Wallet.address}`, null,
      {//header 
          headers: authHeader() 
      }).then(({ data }) => {
        console.log(data);
        this.Wallet.balance = data.balance;
      })

    },
    handleLogout() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/');
    },
    handleWithdraw() {
      this.$store.dispatch('auth/withdraw');
      this.$router.push('/');
    },
    editProfile() {
          
    },

  },

  computed: {
      currentUser(){
        return this.$store.state.auth.user.data;
      },
    },
    
};
</script>

<style>
* { font-family: 'NanumSquareRound'; }
</style>