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
            <!-- <v-tab>계좌 관리</v-tab> -->
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
            <v-row>
              <v-col  cols="12" sm="2">
              </v-col>
              <v-col  cols="12" sm="10">
                <v-card min-height="40vh" class="mx-15">
                  <v-list three-line subheader>
                    <v-list-item>
                      <v-list-item-content>
                        <v-row>
                          <v-col cols="12" sm="3">
                            <v-select
                              :items="groupTypes"
                              v-model="groupType"
                              label="어떤 모임인가요?"
                              outlined
                              class="mt-3 ml-15"
                            ></v-select>
                          </v-col>
                          <v-col cols="12" sm="3">
                            <v-text-field label="모임 이름을 입력해 주세요." v-model="groupName" class="mt-3"></v-text-field>
                          </v-col>
                        </v-row>
                        <v-row>
                          <v-col cols="12" sm="7">
                            <v-text-field label="모임에 대한 간단한 설명을 입력해 주세요." v-model="groupExplanation" class="ml-15"></v-text-field>
                          </v-col>
                        </v-row>
                        <v-row>
                          <v-col cols="12" sm="4" class="ml-3">
                            함께 여행 갈 회원들을 추가해 주세요.
                            <v-btn icon color="green" class="mb-1" @click="dialogUser=true;">
                              <v-icon>mdi-plus</v-icon>
                            </v-btn>
                          </v-col>
                          <v-col cols="12" sm="7" align="left">
                            <v-chip
                              class="mx-1"
                              label
                              v-for="(item, i) in pickedFriend" v-bind:key="i"
                            >
                              {{item}}
                            </v-chip>
                          </v-col>
                          <v-dialog v-model="dialogUser" scrollable max-width="400px">
                            <v-card>
                              <v-card-title>함께할 회원을 검색하세요.</v-card-title>
                              <v-text-field
                                v-model="friend"
                                filled
                                color="deep-purple"
                                label="이메일로 검색해 주세요."
                                style="min-height: 80px"
                                class="mx-3 mt-3"
                                @change="searchUser()"
                              ></v-text-field>
                              <v-divider></v-divider>
                              <v-card-text style="height: 400px;">
                                <v-chip
                                  class="ma-2"
                                  label
                                  v-for="(item, i) in pickedFriend" v-bind:key="i"
                                >
                                  {{item}}
                                </v-chip>
                                <v-checkbox 
                                  v-model="pickedFriend" 
                                  v-bind:label="item.nickname+'('+item.email+')'"
                                  v-bind:value="item.nickname"
                                  color="amber darken-2"
                                  v-for="(item, i) in sUserList" v-bind:key="i"></v-checkbox>
                              </v-card-text>
                              <v-divider></v-divider>
                              <v-card-actions>
                                <v-btn color="blue darken-1" text @click="dialogUser = false; pickedFriend = []">Close</v-btn>
                                <v-btn color="blue darken-1" text @click="dialogUser = false;">Save</v-btn>
                              </v-card-actions>
                            </v-card>
                          </v-dialog>
                        </v-row>
                        <v-row>
                          <v-col cols="12" sm="3">
                            <div class="subheading ml-14"><small>정기 납부를 종료할 날짜를 선택해 주세요.</small></div>
                            <v-date-picker v-model="finished" color="amber darken-1" class="ml-15" @change="updategroupDates()"></v-date-picker>
                          </v-col>
                          <v-col cols="12" sm="9">
                            <v-row>
                              <v-col cols="12" sm="4">
                                <v-text-field v-model="groupDateRangeText" label="Date range" readonly class="ml-15"></v-text-field>
                              </v-col>
                            </v-row>
                            <v-row>
                              <v-col cols="12" sm="4">
                                <v-text-field 
                                  label="목표금액을 입력해 주세요." 
                                  v-model="groupTarget" 
                                  class="ml-15"
                                  @change="updateRegularPay()"
                                  suffix="원"
                                ></v-text-field>
                              </v-col>
                              <v-col cols="12" sm="2">
                                <v-select
                                  :items="cycle"
                                  label="납입 주기"
                                  v-model="groupCycle"
                                ></v-select>
                              </v-col>
                              <v-col cols="12" sm="2" v-if="groupCycle == '월간'">
                                <v-select
                                  :items="month"
                                  v-model="groupPayDate"
                                  label="일 선택"
                                  @change="updateRegularPay()"
                                ></v-select>
                              </v-col>
                              <v-col cols="12" sm="2" v-if="groupCycle == '주간'">
                                <v-select
                                  :items="week"
                                  label="요일 선택"
                                  v-model="groupPayDate"
                                  @change="updateRegularPay()"
                                ></v-select>
                              </v-col>
                            </v-row>
                            <v-row v-if="groupCycle == '월간'">
                              <v-col cols="12" sm="4" >
                                <v-text-field label="월간 납입금액" v-model="groupRegularPay" class="ml-15" suffix="원"></v-text-field>
                              </v-col>
                              <v-col cols="12" sm="3">
                                <v-text-field 
                                  label="퇴출 수수료" 
                                  v-model="groupExitPay" 
                                  suffix="%" 
                                  hint="0부터 10 사이로 선택해 주세요."
                                  @change="checkExitPay()"
                                ></v-text-field>
                              </v-col>
                            </v-row>
                            <v-row v-if="groupCycle == '주간'">
                              <v-col cols="12" sm="4" >
                                <v-text-field label="주간 납입금액" v-model="groupRegularPay" class="ml-15" suffix="원"></v-text-field>
                              </v-col>
                              <v-col cols="12" sm="2">
                                <v-text-field 
                                  label="퇴출 수수료" 
                                  v-model="groupExitPay" 
                                  suffix="%" 
                                  hint="0부터 10 사이로 선택해 주세요."
                                  @change="checkExitPay()"
                                ></v-text-field>
                              </v-col>
                            </v-row>
                          </v-col>
                        </v-row>
                        <v-divider></v-divider>
                        <v-row v-if="groupType=='여행'">
                          <v-col cols="12" sm="3" class="mt-15">
                            <div class="subheading"><small>여행 기간을 선택해 주세요.</small></div>
                            <v-date-picker v-model="travelDates" range color="green" class="ml-15"></v-date-picker>
                          </v-col>
                          <v-col cols="12" sm="9" class="mt-15">
                            <v-row>
                              <v-col cols="12" sm="4">
                                <v-text-field label="여행지를 입력해 주세요." v-model="groupDestinationCountry" class="ml-15" hint="국가명"></v-text-field>
                              </v-col>
                              <v-col cols="12" sm="4">
                                <v-text-field v-model="groupDestinationCity" hint="도시명"></v-text-field>
                              </v-col>
                            </v-row>
                            <v-row>
                              <v-col cols="12" sm="4">
                                <v-text-field v-model="travelDateRangeText" label="Date range" readonly class="ml-15"></v-text-field>
                              </v-col>
                            </v-row>
                          </v-col>
                        </v-row>
                        <v-divider></v-divider>
                        <v-row>
                          <v-col align="right">
                            <router-link :to="{name: 'groupmain'}" class="text-decoration-none">
                              <v-btn color="amber darken-3" text @click="dialogUser = false"><h3>Close</h3></v-btn>
                            </router-link>
                              <v-btn color="amber darken-3" text @click="dialogCreate = true"><h3>Save</h3></v-btn>
                            
                          </v-col>
                          <v-dialog
                            v-model="dialogCreate"
                            max-width="500px"
                          >
                            <v-card>
                              <v-card-title>
                                모임 계좌를 생성하는 과정입니다.
                              </v-card-title>
                              <v-card-text>
                                <v-text-field
                                  label="개인키를 입력하세요."
                                  v-model="groupKey"
                                ></v-text-field>
                              </v-card-text>
                              <v-card-actions>
                                <v-btn
                                  color="green"
                                  text
                                  @click="createGroup()"
                                >
                                  create
                                </v-btn>
                                <v-btn
                                  color="primary"
                                  text
                                  @click="dialogCreate = false"
                                >
                                  Close
                                </v-btn>
                              </v-card-actions>
                            </v-card>
                          </v-dialog>
                        </v-row>
                      </v-list-item-content>
                    </v-list-item>
                    
                  </v-list>
                  <v-divider></v-divider>
                </v-card>
              </v-col>
            </v-row>
            
          </v-card>
        </v-tab-item>
        <!-- <v-tab-item>
          <v-card flat min-height="90vh">
            <v-card-text> 하이하이</v-card-text>
          </v-card>
        </v-tab-item> -->
      </v-tabs-items>
    </v-card>
  </center>
</template>

<script>
import moment from "moment";
import http from '@/util/http-common.js';
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

        cycle: ['월간', '주간'],
        groupName : '',
        groupExplanation :'',
        groupTarget : '',
        groupCycle : '',
        groupPayDate : '',
        groupRegularPay : '',
        groupDestinationCountry : '',
        groupDestinationCity : '',
        groupExitPay : '',
        groupTypes : [ "여행", "기타"],
        groupType : '',

        week : ['월요일','화요일','수요일','목요일','금요일','토요일','일요일'],
        month : [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28],
        groupDates: [],
        finished : '',
        travelDates : [],

        dialogUser:false,
        friend: '',
        userList : [],
        sUserList : [],
        pickedFriend : [],
        pickedFrinedId : [],
        pickedYoilIndex : '',
        dialogCreate : false,
        groupKey : '',
      }
    },
  created(){
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
    
    //userList 가져오기
    http.get('users')
    .then(({data}) => {
      var id = this.$store.state.auth.user.data.id
      data.forEach(element => {
        if(element.id != id) {
          this.userList.push(element)
          this.sUserList.push(element)
        }
      });
    })

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
    searchUser () {
      this.sUserList = []
      this.userList.forEach(element => {
        if(element.email.includes(this.friend) || element.nickname.includes(this.friend)){
          this.sUserList.push(element)
        }
      });
    },
    createGroup() {
      if(this.groupType.length == ''){
        alert("어떤 모임인지 선택해주세요.")
      }else if(this.groupName.length == ''){
        alert("모임 이름을 작성해 주세요.")
      }else if(this.finished.length == ''){
        alert("정기 납부를 종료할 날짜를 선택해주세요.")
      }else if(this.groupTarget.length == ''){
        alert("목표 금액을 입력해주세요.")
      }else if(this.groupCycle.length == ''){
        alert("정기납부 주기를 월간과 주간 중에 선택해주세요.")
      }else if(this.groupCycle.length == ''){
        alert("정기납부 주기를 월간과 주간 중에 선택해주세요.")
      }else if(this.groupPayDate.length == ''){
        alert("정기납부할 날짜를 선택해주세요.")
      }else if(this.groupExitPay.length == ''){
        alert("퇴출 수수료를 입력해주세요. 퇴출 수수료는 무단으로 모임을 나가거나 장기적으로 정기납부를 하지 않은 인원에게 부가되는 수수료입니다. 이러한 인원이 퇴출 시 현재까지 낸 금액의 퇴출수수료(%)만큼 제하고 퇴출 처리 됩니다.")
      }else{
        var canMake = false
        if(this.groupType == '여행'){
          if(this.travelDates.length == 0){
            alert('여행 기간을 선택해주세요.')
          }else if(this.groupDestinationCountry.length == ''){
            alert('여행지(국가명)을 입력해주세요.')
          }else if(this.groupDestinationCity.length == ''){
            alert('여행지(도시명)을 입력해주세요.')
          }else{
            canMake = true
          }
        }else{
          canMake = true
        }

        if(canMake){
          var ok = confirm("모임을 생성하시겠습니까?")
          if(ok) {
            this.pickedFrinedId.push(this.$store.state.auth.user.data.id); //나의 아이디 넣어줘야해
            this.pickedFriend.forEach(element => {
              this.userList.forEach(element2 => {
                if(element == element2.nickname){
                  this.pickedFrinedId.push(element2.id)
                }
              });
            });

            if(this.groupCycle =='월간'){
              this.pickedYoilIndex = this.groupPayDate
            }


            http.post('/party', 
              {
                name : this.groupName,
                explanation : this.groupExplanation,
                created : moment(new Date()).format("YYYY-MM-DD"),
                target : this.groupTarget,
                total_amount : 0,
                payCycle : true,
                pay_date : this.pickedYoilIndex,
                pay_amount : this.groupRegularPay,
                image : " ",
                start_date : this.travelDates[0],
                end_date : this.travelDates[1],
                destination : this.groupDestinationCountry+" "+this.groupDestinationCity,
                available : true,
                exitFee : this.groupExitPay,
                finished : this.finished,
                type : false,
                members : this.pickedFrinedId,
                privatekey : this.groupKey,
              },
              
            ).then(({ data }) => {
              console.log(data)
              this.$router.push("/group");
            });
          }
        }
      }
      
    },
    updateRegularPay() {
      if(this.groupCycle =='월간'){
        let created = moment(new Date()).format("YYYY-MM-DD")
        var sy = created.slice(0,4)
        var sm = created.slice(5,7)
        var sd = created.slice(8.10)

        var ey = this.finished.slice(0,4)
        var em = this.finished.slice(5,7)
        var ed = this.finished.slice(8,10)

        var cnt = 0
        if(ey > sy){
          cnt += Number(ey - sy - 1)*12
          if(sd > this.groupPayDate){
            cnt += Number(12 - sm)
          }else{
            cnt += Number(12 - sm + 1)
          }

          if(ed >= this.groupPayDate){
            cnt += Number(em)
          }else{
            cnt += Number(em + 1)
          }
        }else{
          if(ed >= this.groupPayDate){
            cnt += Number(em)
          }else{
            cnt += Number(em + 1)
          }

          if(sd > this.groupPayDate){
            cnt -= Number(sm)
          }else{
            cnt -= Number(sm - 1)
          }
        }
        if(cnt == 0) this.groupRegularPay = '모금 기간을 늘려주세요.'
        else this.groupRegularPay = Math.round((this.groupTarget/cnt) / (this.pickedFriend.length+1))
      }else if(this.groupCycle == '주간'){
        var yoil = moment(new Date()).format('dddd')
        var curYoilIndex = 0
        if(yoil == 'Monday') {
          curYoilIndex = 1
        }else if(yoil == 'Tuesday'){
          curYoilIndex = 2
        }else if(yoil == 'Wendnesday'){
          curYoilIndex = 3
        }else if(yoil == 'Thursday'){
          curYoilIndex = 4
        }else if(yoil == 'Friday'){
          curYoilIndex = 5
        }else if(yoil == 'Sunday'){
          curYoilIndex = 6
        }else{
          curYoilIndex = 7
        }

        this.pickedYoilIndex = 0
        if(this.groupPayDate == '월요일'){
          this.pickedYoilIndex = 1
        }else if(this.groupPayDate == '화요일'){
          this.pickedYoilIndex = 2
        }else if(this.groupPayDate == '수요일'){
          this.pickedYoilIndex = 3
        }else if(this.groupPayDate == '목요일'){
          this.pickedYoilIndex = 4
        }else if(this.groupPayDate == '금요일'){
          this.pickedYoilIndex = 5
        }else if(this.groupPayDate == '토요일'){
          this.pickedYoilIndex = 6
        }else if(this.groupPayDate == '일요일'){
          this.pickedYoilIndex = 7
        }

        var created = moment(new Date()).format("YYYY-MM-DD")

        var date1 = new Date(created);
        var date2 = new Date(this.finished);
        var diff = date2.getTime() - date1.getTime();
        diff = Math.ceil(diff / (1000 * 3600 * 24))+1;
        
        if(this.pickedYoilIndex > curYoilIndex){
          diff -= (this.pickedYoilIndex - curYoilIndex)
        }else if(this.pickedYoilIndex < curYoilIndex){
          diff -= (8 - curYoilIndex)
          diff -= this.pickedYoilIndex
        }

        this.groupRegularPay = Math.round((this.groupTarget/parseInt((diff+6)/7)) / (this.pickedFriend.length+1))
      }
    },
    checkExitPay() {
      if(this.groupExitPay > 10 || this.groupExitPay < 0){
        alert("퇴출 수수료는 0 이상 10 이하로 작성하셔야 합니다.")
        this.groupExitPay = 10
      }
    },
    updategroupDates() {
      this.groupDates = []
      this.groupDates.push(moment(new Date()).format("YYYY-MM-DD"))
      this.groupDates.push(this.finished)
    },

    handleLogout() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/');
    },
  },

  computed: {
    groupDateRangeText () {
      this.updateRegularPay()
      return this.groupDates.join(' ~ ')
    },
    travelDateRangeText () {
      return this.travelDates.join(' ~ ')
    },
  },
};
</script>
<style>

</style>