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
            <router-link  :to="{name: 'groupmain'}" class="text-decoration-none">
              <h1 class="my-15 mx-15" style="color:white;">TRABLOCK</h1>
            </router-link>
          </v-toolbar-title>
        </div>
        <v-spacer></v-spacer>

        <div class="my-2">
            <v-btn large color="red darken-1" @click="handleLogout">Logout</v-btn>
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

            <v-card min-height="90vh" class="mx-15">
              <v-row class="fill-height"></v-row>

              <v-row class="fill-height">
                <v-col cols="12" sm="3"></v-col>
                <v-col cols="12" sm="9">
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
                  <v-row>
                    <v-col align="center">
                      <h1>그룹 페이지 입니다 :)</h1>
                      <br>
                      <h3>친구들과</h3>
                      <h3>모임을 만들고</h3>
                      <h3>여행 계획을 세워보세요!</h3>
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>
            </v-card>

          </v-card>
        </v-tab-item>
        <v-tab-item>
          <!--<component :is="wCheck ? 'MyWalletDetail' : 'MyWalletRegister'"></component> -->
          <my-wallet-register v-if="!wCheck" @child="setWcheck"></my-wallet-register>
          <my-wallet-detail v-if="wCheck"></my-wallet-detail>
        </v-tab-item>
      </v-tabs-items>                        
    </v-card>
  </center>
</template>

<script>
import http from "@/util/http-common.js";
// import Web3 from 'web3';
import authHeader from '@/services/auth-header.js';
import MyWalletRegister from './MyWalletRegister';
import MyWalletDetail from './MyWalletDetail';
import { mapGetters } from 'vuex'
// var web3 = new Web3(new Web3.providers.HttpProvider("https://ropsten.infura.io/v3/98aa6777fadd45949e67403767091144"));
// var web3 = new Web3(new Web3.providers.HttpProvider('https://api.infura.io/v1/jsonrpc/ropsten'));
// var web3 = new Web3(new Web3.providers.HttpProvider('http://j3a101.p.ssafy.io/geth'));
export default {
  name: 'GroupMain',
  components: {
    MyWalletRegister,
    MyWalletDetail
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

        privateKey: '',
        dialogPK: false,
        btnName: "copy"
      }
    },
  created(){
    this.U.email = this.$store.state.auth.user.email;
    this.U.password = this.$store.state.auth.user.password
    this.U.nickname= this.$store.state.auth.user.nickname

    //내 계좌 정보 가져오기
    http.get('/token/wallets', { 
        headers: authHeader() 
    }).then(({ data }) => {
      console.log(data);
      if(data.result == "fail"){
        this.wCheck = false;
      }
      else {
        this.wCheck = true;
        this.Wallet.address = data.address;
        this.Wallet.balance = data.balance;
      }
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
    setWcheck(value){
      this.wCheck = value;
    },

    editProfile() {
          
    },

    handleLogout() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/');
    },
    
  },

  computed: {
      currentUser(){
        return this.$store.state.auth.user.data;
      },
      ...mapGetters(['wCheck'])
    },
};
</script>
<style>

</style>