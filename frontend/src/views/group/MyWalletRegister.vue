<template>
    <v-row>
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
        <!-- <input type="hidden" id="hidden-area" :value="hiddenArea" /> -->
        <v-dialog
            v-model="dialogPK"
            persistent
            max-width="400"
        >
            <v-card>
            <v-card-title class="headline">
                지갑 PrivateKey를 기억하세요!
            </v-card-title>

            <textarea style="width:80%" @click="copyPK" id="pkzone" v-model="this.privateKey"/>

            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                color="green darken-1"
                text
                @click="movePK()"
                >
                {{this.btnName}}
                </v-btn>
            </v-card-actions>
            </v-card>
        </v-dialog>
    </v-row>
</template>


<script>
import http from "@/util/http-common.js";
import Web3 from 'web3';
import authHeader from '@/services/auth-header.js';
var web3 = new Web3(new Web3.providers.HttpProvider('http://j3a101.p.ssafy.io/geth'));
export default {
  name: 'MyWalletRegister',
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

  },
  
  methods: {
    createWallet() {
      const account = web3.eth.accounts.create();

      // console.log(account)
      // console.log(`Account : ${account.address}`);
      // console.log(`Private key  : ${account.privateKey}`);
      this.privateKey = `${account.privateKey}`;

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
        this.dialogPK = true;

        // this.$dialog.notify.success("계좌 생성에 성공했습니다! 😃", {
        //   position: "bottom-right",
        //   timeout: 3000,
        // });
      })
    },
    
    movePK() {
      if(this.btnName == "copy"){
        var obj = document.getElementById("pkzone");
        obj.select();
        document.execCommand("copy");
        obj.setSelectionRange(0, 0);
        this.btnName = "close"
        this.$dialog.notify.success("Privat Key가 복사되었습니다! 은밀한 곳에 보관하세요 ^0^", {
          position: "bottom-right", timeout: 3000, });
      }else{
        this.dialogPK = false;
        this.$emit("child", true);

      }
      // alert("계좌 생성에 성공했습니다! 😃");
      // this.$router.go();
    },

    copyPK() {
      var obj = document.getElementById("pkzone");
      obj.select(); //인풋 컨트롤의 내용 전체 선택

      try {
        document.execCommand("copy");
        obj.setSelectionRange(0, 0);
        this.$dialog.notify.success("Privat Key가 복사되었습니다! 은밀한 곳에 보관하세요 ^0^", {
          position: "bottom-right", timeout: 3000, });
        // document.body.removeChild(el);
        // alert("Privat Key가 복사되었습니다! 은밀한 곳에 보관하세요 ^0^")
        // this.text = emoji + " 가 복사되었습니다!\nCtrl+V 로 사용하세요!";
        // this.snackbar = true;
      } catch (err) {
        // this.text = "복사에 실패하였습니다 😰";
        // this.snackbar = true;
      }
    },
  },

  computed: {
      currentUser(){
        return this.$store.state.auth.user.data;
      },
    },
    
};
</script>