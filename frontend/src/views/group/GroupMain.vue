<template>
  <center>
    <v-card height="100%">
      <v-toolbar
        color="#FFA000"
        dark
        flat
        prominent
      >
        <div align="center" justify="center">
          <v-toolbar-title>
            <h1 class="my-15 mx-15">TRABLOCK</h1>
          </v-toolbar-title>
        </div>
        <v-spacer></v-spacer>

        <v-btn icon>
          <v-icon>mdi-account-outline</v-icon>
        </v-btn>

        <v-btn icon>
          <v-icon>mdi-blinds</v-icon>
        </v-btn>

        <div class="my-2">
          <router-link
            :to="{path: 'group'}">
            <v-btn large color="green darken-1">Blog</v-btn>
          </router-link>
        </div>

        <template v-slot:extension>
          <v-tabs
            v-model="tab"
            align-with-title
          >
            <v-tabs-slider color="yellow"></v-tabs-slider>

            <v-tab>모임 관리</v-tab>
            <v-tab>계좌 관리</v-tab>
          </v-tabs>
        </template>
      </v-toolbar>

      <v-tabs-items v-model="tab">
        <v-tab-item>
          <v-card flat min-height="90vh">
            <v-row>
              <v-col align="right">
                <router-link :to="{path: 'group/create'}" class="text-decoration-none">
                <v-btn 
                  rounded color="grey" dark
                  class="mx-5"
                >
                  <v-icon dark>mdi-plus</v-icon>            
                </v-btn></router-link>
              </v-col>
              
            </v-row>

            <v-navigation-drawer
              v-model="drawer"
              :expand-on-hover="expandOnHover"
              :mini-variant="miniVariant"
              :right="right"
              :permanent="permanent"
              absolute
              width="40vh"
            >
              <v-list
                dense
                nav
                class="py-0"
              >
                <v-list-item :class="miniVariant && 'px-0'">
                  <v-list-item-content class="elevation-5 mt-5 mb-2">
                    <v-list-item-title><h3 class="my-2">나의 모임</h3></v-list-item-title>
                  </v-list-item-content>
                </v-list-item>

                <v-divider></v-divider>

                <div v-if="groups.length == 0" class="mt-3">등록한 모임이 없습니다. </div>
                <v-list-item
                  v-for="item in groups"
                  :key="item.title"
                  link :to="{name: 'groupdetail',query: { groupId: item.id }}"
                >
                  <v-list-item-icon>
                    <v-icon>mdi-view-dashboard</v-icon>
                  </v-list-item-icon>

                  <v-list-item-content>
                    <v-list-item-title>{{ item.name }}</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list>
            </v-navigation-drawer>
            <v-card min-height="40vh" class="mx-15">
              <h1>HOW TO USE TRABLCOK</h1>
            </v-card>
          </v-card>
        </v-tab-item>
        <v-tab-item>
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
                            <v-divider></v-divider>
                            <v-row>
                                <v-col cols="5" class="text-left py-1 pl-5 pr-0">가입일</v-col>
                                <v-col cols="7" class="text-right py-1 pr-5 pl-0">{{U.created}}</v-col>
                            </v-row>
                            <v-divider></v-divider>
                        </v-card-text>
                        <v-card-actions class="d-flex justify-end pt-0">
                            <v-btn color="blue" text small class="font-weight-bold" @click.stop="dialog = true">update</v-btn>
                            <!-- 수정모달 -->
                            <v-dialog v-model="dialog" max-width="400">
                                  <v-card>
                                    <v-card-title class="headline">Edit Profile</v-card-title>
                                    <v-card-text class="pb-0">
                                      <v-col cols="12" class="py-0 mt-10">
                                        <v-text-field
                                          label="Nickname" :placeholder="U.nickname" v-model="U.nickname"
                                          filled rounded dense></v-text-field>
                                      </v-col>
                                      <v-col cols="12" class="py-0">
                                        <v-text-field
                                          type="password"
                                          label="Password" :placeholder="U.password" v-model="U.password"
                                          hint="이전 비밀번호 혹은 변경할 비밀번호를 입력해주세요(필수)"
                                          :rules="[v => !!v  || 'Password is required', v => v.length >= 5 || 'Password is too short']"
                                          required
                                          persistent-hint                                          
                                          filled rounded dense></v-text-field>
                                      </v-col>
                                    </v-card-text>

                                    <v-card-actions class="pt-5">
                                      <v-spacer></v-spacer>
                                      <v-btn color="orange" text @click="editProfile" >Edit</v-btn>
                                      <v-btn color="grey" text @click="dialog = false" >Close</v-btn>
                                    </v-card-actions>
                                  </v-card>
                                </v-dialog>
                        </v-card-actions>
                    </v-card>
                    <v-card class="mx-auto mt-2">
                        <v-expansion-panels accordion>
                        <v-expansion-panel>
                            <v-expansion-panel-header>more...</v-expansion-panel-header>
                            <v-expansion-panel-content class="px-0">
                                <v-row class="text-center">
                                    <v-col cols="12" class="py-0">
                                        <v-btn small color="orange" @click="logOut" block dark>Log out</v-btn>                                   
                                    </v-col>
                                    <v-col cols="12">
                                        <v-btn small color="red" block dark @click.stop="dialogforwithdraw = true">withdraw</v-btn>                                   
                                    </v-col>
                                    <v-dialog v-model="dialogforwithdraw" max-width="290" >
                                          <v-card>
                                            <v-card-title class="headline">Alert</v-card-title>
                                            <v-card-text>
                                              정말 탈퇴하시겠습니까?
                                            </v-card-text>

                                            <v-card-actions>
                                              <v-spacer></v-spacer>

                                              <v-btn color="red" text @click="confirmwithdraw" >
                                                Yes
                                              </v-btn>

                                              <v-btn color="grey" text @click="dialogforwithdraw = false" >
                                                No
                                              </v-btn>
                                            </v-card-actions>
                                          </v-card>
                                        </v-dialog>
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
            <v-col cols="12" class="py-1 text-h5">PROFILE</v-col>
            <v-col cols="12" class="py-1 text-h4 font-weight-bold">MY Wallet</v-col>
          </v-row>
          <v-row class="mt-10">
            <v-col cols="12" class="py-1 text-h6">wallet 이름</v-col>
            <v-col cols="4" class="ml-1 mt-3">
                <img src="@/assets/user.png" alt="user-image" class="profile-image">
            </v-col>
            <br>
            <v-col cols="12">
              <v-simple-table dense>
                <template v-slot:default>
                  <tbody>
                  <tr>
                      <td>지갑 주소</td>
                      <td>{{Wallet.address}}</td>
                    </tr>
                    <tr>
                      <td>잔액</td>
                      <td>{{Wallet.balance}}</td>
                      <td><v-btn @click="charge">충전</v-btn></td>
                    </tr>
                  </tbody>
                </template>
              </v-simple-table>              
            </v-col>
          </v-row>
                      </v-container>
                    </v-card>
                </v-col>
          </v-row>

          <v-row v-else>
            <v-col cols="12">
            <v-card class="mx-auto" flat min-height="70vh">
              <br><br><br><br>
              <v-chip
              class="ma-2"
              color="grey darken-3"
              label
              outlined
            >
              개인 지갑이 생성되지 않았습니다.
            </v-chip>
            <br>
              <v-chip
              class="ma-2"
              color="grey darken-3"
              label
              outlined
            >
              <v-icon left>mdi-label</v-icon>
              계좌 생성하기
            </v-chip>
              <br><br>
              <v-btn color="orange" class="font-weight-bold" fab x-large dark @click="createWallet">
                <v-icon>fas fa-wallet</v-icon>
              </v-btn>
            </v-card>
            </v-col>
          </v-row>
        </v-tab-item>
      </v-tabs-items>                        
    </v-card>
  </center>
</template>

<script>
import http from "@/util/http-common.js";
import Web3 from 'web3';
import authHeader from '@/services/auth-header.js';
// var web3 = new Web3(new Web3.providers.HttpProvider("https://ropsten.infura.io/v3/98aa6777fadd45949e67403767091144"));
// var web3 = new Web3(new Web3.providers.HttpProvider('https://api.infura.io/v1/jsonrpc/ropsten'));
var web3 = new Web3(new Web3.providers.HttpProvider('http://192.168.50.10:8545'));
export default {
  name: 'GroupMain',
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
          balance: ""
        },
        dialog: false,

        // wCheck: true,
        wCheck: false,
        access_token: this.$store.state.auth.user.accessToken,
      }
    },
  created(){
    this.U.email = this.$store.state.auth.user.email;
    this.U.password = this.$store.state.auth.user.password
    this.U.nickname= this.$store.state.auth.user.nickname
    console.log(this.$store.state.auth.user);
    console.log(this.$store.state.auth.user.accessToken);
    // http.get('/token/wallets/id', {
    //   params : {
    //     id : this.$store.state.auth.user.data.id
    //   }
    // });
    // console.log(authHeader());

    //내 계좌 정보 가져오기
    http.get('/token/wallets', { 
        headers: authHeader() 
    }).then(({ data }) => {
      console.log(data)
      this.Wallet.address = data.address;
      this.Wallet.balance = data.balance;
    })

    // 모임 가져오기
    http.get('/party/searchId', {
      params : {
        id : this.$store.state.auth.user.data.id
      }
    }).then(({ data }) => {
      this.groups = data;
    })
    .catch((error) => {
      if(error.response) {
        this.$router.push("servererror")
      } else if(error.request) {
        this.$router.push("error")
      } else{
        this.$router.push("/404");
      }                          
    });
  },
  
  methods: {
    createWallet() {
      const account = web3.eth.accounts.create();

      console.log(account)
      console.log(`Account : ${account.address}`);
      console.log(`Private key  : ${account.privateKey}`);

      //내 계좌 생성하기
      http.post('/token/wallets', 
      {//data
        "address": account.address
      },
      {//header 
          headers: authHeader() 
      }).then(({ data }) => {
        console.log(data.address+", "+data.balance);
        this.Wallet.address = data.address;
        this.Wallet.balance = data.balance;
      })
    },
    charge(){
      // console.log(this.Wallet.address);
       //내 계좌 충전하기
      http.put(`/token/wallets/${this.Wallet.address}`, null,
      {//header 
          headers: authHeader() 
      }).then(({ data }) => {
        console.log(data);
      })

    },

    editProfile() {
          
        },
    logOut() {
        this.$store.dispatch('auth/logout');
        this.$router.go();
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

</style>