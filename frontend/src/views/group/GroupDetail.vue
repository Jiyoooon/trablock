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

        <v-btn icon>
          <v-icon>far fa-user</v-icon>
        </v-btn>

        <!-- <v-btn icon>
          <v-icon>mdi-blinds</v-icon>
        </v-btn> -->

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
                  @click="changeGroupId(item.id)"
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
              <v-row class="fill-height">
              </v-row>
              
              <v-row class="fill-height">
                <v-col  cols="12" sm="3">
                </v-col>
                <v-col cols="12" sm="9">
                  <v-row>
                    <v-col align="left">
                      <h1 class="mt-15">{{group.name}}</h1>
                      <h3 class="my-5">{{group.explanation}}</h3>
                    </v-col>
                  </v-row>
                  <v-divider></v-divider>
                  <v-row>
                    <v-col cols="12" sm="7" align="center">
                      <v-sheet height="64">
                        <v-toolbar flat color="white">
                          <v-btn fab text small color="grey darken-2" @click="prev">
                            <v-icon small>mdi-chevron-left</v-icon>
                          </v-btn>
                          <v-toolbar-title v-if="$refs.calendar">
                            {{ $refs.calendar.title }}
                          </v-toolbar-title>
                          <v-btn fab text small color="grey darken-2" @click="next">
                            <v-icon small>mdi-chevron-right</v-icon>
                          </v-btn>
                          <v-spacer></v-spacer>
                        </v-toolbar>
                      </v-sheet>
                      <v-sheet height="400">
                        <v-calendar
                          ref="calendar"
                          v-model="focus"
                          color="primary"
                          :events="events"
                          :event-color="getEventColor"
                          :type="type"
                          @click:event="showEvent"
                          @click:more="viewDay"
                          @click:date="viewDay"
                          @change="updateRange"
                        ></v-calendar>
                      </v-sheet>
                      <v-dialog
                        v-model="dialogMemo"
                        persistent
                        max-width="600px"
                      >
                        <v-card>
                          <v-card-title>
                            <v-btn icon>
                              <v-icon color="green">mdi-pencil</v-icon>
                            </v-btn>
                            <span class="headline">{{pickedDate}} Memo</span>
                          </v-card-title>
                          <v-card-text>
                            <v-container>
                              <v-row>
                                <v-col>
                                  <v-textarea
                                    outlined
                                    name="input-7-4"
                                    label="자유롭게 기록하세요."
                                    v-model="groupMemo"
                                  ></v-textarea>
                                </v-col>
                              </v-row>
                            </v-container>
                          </v-card-text>
                          <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn
                              color="blue darken-1"
                              text
                              @click="dialogMemo = false"
                            >
                              Close
                            </v-btn>
                            <v-btn
                              color="blue darken-1"
                              text
                              @click="saveMemo()"
                            >
                              Save
                            </v-btn>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>
                    </v-col>
                    <v-col cols="12" sm="5">
                      <h3 class="mt-3">회비납부 LOG</h3>
                      <v-card
                        class="mr-4 mt-6"
                        outlined
                        min-height="47vh"
                      >
                        <v-list-item three-line>
                          <v-list-item-content align="left">
                            <v-list-item-title class="headline my-3 mx-2">총 누적금액은 <strong>{{sum}}원</strong>입니다.</v-list-item-title>
                            <v-list-item-subtitle class="mx-2">2020.10.15까지 <strong>{{group.payAmount}}원</strong>을 입금해야 합니다.</v-list-item-subtitle>
                          </v-list-item-content>
                        </v-list-item>

                        <v-card
                          class="mx-5 mt-1"
                          outlined
                          align = "left"
                          min-height="27vh"
                        >
                          <h3 class="mx-5 mt-3 mb-6">2020.09.15 납부 현황</h3>
                          <h4 class="mx-8 my-3">
                            <span v-if="realBadMember.length > 0">
                              <v-icon color="red" class="mr-2">mdi-cancel</v-icon>
                              <span v-for="item in realBadMember" :key="item">{{item}},</span>님은 미납한 경험이 있는 회원입니다.
                            </span>
                          </h4>
                          <h4 class="mx-8 my-3">
                            <span v-if="goodMember.length > 0">
                              <v-icon color="green" class="mr-2">mdi-checkbox-marked-circle</v-icon>
                              <span v-for="item in goodMember" :key="item">{{item}}, </span>님이 정상납부하였습니다.
                            </span>
                          </h4>
                          <h4 class="mx-8 my-3">
                            <span v-if="badMember.length > 0">
                              <v-icon color="amber darken-2" class="mr-2">fas fa-circle-notch fa-spin</v-icon>
                              <span v-for="item in badMember" :key="item">{{item}},</span>님이 아직 미납하였습니다.
                            </span>
                          </h4>
                          <h4 class="mx-8 my-3">
                            <span v-if="realBadMember.length > 0">
                              <v-icon color="red" class="mr-2">mdi-cancel</v-icon>
                              <span v-for="item in realBadMember" :key="item">{{item}},</span>님은 미납한 경험이 있는 회원입니다.
                            </span>
                          </h4>
                        </v-card>
                        <v-col align="center">
                          <v-card-actions align="center">
                            <v-btn text @click="dialogPay = true">납부하기</v-btn>
                            <v-btn text @click="dialogTable=true">회원 납부 현황 보기</v-btn>
                            <v-btn text @click="dialogGetMoney = true">출금하기</v-btn>
                          </v-card-actions>
                        </v-col>
                      </v-card> 
                      <v-dialog
                        v-model="dialogPay"
                        max-width="500px"
                      >
                        <v-card>
                          <v-card-title>
                            {{regularPay}} 원을 납부하겠습니다.
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
                              @click="pay()"
                            >
                              pay
                            </v-btn>
                            <v-btn
                              color="primary"
                              text
                              @click="dialogPay = false"
                            >
                              Close
                            </v-btn>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>
                      <v-dialog
                        v-model="dialogGetMoney"
                        max-width="500px"
                      >
                        <v-card>
                          <v-card-title>
                            모임원들의 동의가 진행된 후 출금됩니다.
                          </v-card-title>
                          <v-card-text>  
                            <v-col cols="12" sm="8">                          
                              <v-text-field
                                label="출금할 금액을 입력하세요"
                                v-model="amount"
                              ></v-text-field>
                            </v-col>
                            <v-text-field
                              label="개인키를 입력하세요."
                              v-model="groupKey"
                            ></v-text-field>
                          </v-card-text>
                          <v-card-actions>
                            <v-btn
                              color="green"
                              text
                              @click="getAmount()"
                            >
                              출금하기
                            </v-btn>
                            <v-btn
                              color="primary"
                              text
                              @click="dialogGetMoney = false"
                            >
                              Close
                            </v-btn>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>
                      <v-dialog
                        v-model="dialogTable"
                        max-width="600px"
                      >
                        <v-card>
                          <v-card-title>
                            회원 납부 현황
                          </v-card-title>
                          <v-simple-table height="300px">
                            <thead>
                              <tr>
                                <th class="text-left">
                                  이름
                                </th>
                                <th class="text-left">
                                  납부 금액
                                </th>
                                <th class="text-left">
                                  최근 납부 여부
                                </th>
                                <th class="text-left">
                                  경고 여부
                                </th>
                              </tr>
                            </thead>
                            <tbody>
                              <tr v-for="item in group.memberlist" :key="item">
                                <td>{{item.name}}</td>
                                <td>{{item.payment}}원</td>
                                <td v-if="item.isPay">O</td>
                                <td v-else>X</td>
                                <td v-if="item.warning">X</td>
                                <td v-else>O</td>
                              </tr>
                            </tbody>
                          </v-simple-table>
                          <v-card-actions>
                            <v-btn
                              color="primary"
                              text
                              @click="dialogTable = false"
                            >
                              Close
                            </v-btn>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>

                    </v-col>
                  </v-row>
                </v-col>
              </v-row>
            </v-card>
          </v-card>
        </v-tab-item>
        <v-tab-item>
          <v-card flat min-height="90vh">
            <v-card-text> 하이하이</v-card-text>
          </v-card>
        </v-tab-item>
      </v-tabs-items>
    </v-card>
  </center>
</template>

<script>
import http from '@/util/http-common.js';
import authHeader from '@/services/auth-header.js';
export default {
  name: 'GroupMain',
  components: {
    
  },
  data () {
      return {
        tab: null,
        drawer: true,
        groups : [],
        color: 'primary',

        right: false,
        permanent: true,
        miniVariant: false,
        expandOnHover: false,
        background: false,

        groupId: this.$route.query.groupId,
        focus: '',
        type: 'month',
        typeToLabel: {
          month: 'Month',
          week: 'Week',
          day: 'Day',
          '4day': '4 Days',
        },
        selectedEvent: {},
        selectedElement: null,
        selectedOpen: false,
        events: [],
        colors: ['blue', 'indigo', 'deep-purple', 'cyan', 'green', 'orange', 'grey darken-1'],
        names: ['Meeting', 'Holiday', 'PTO', 'Travel', 'Event', 'Birthday', 'Conference', 'Party'],
        
        dialogPay : false,
        dialogMemo : false,
        pickedDate : '',
        groupMemo : '',
        group : {},
        memoList : [],
        regularPay : '50',
        groupKey : '',
        dialogGetMoney : false,
        amount : '',
        dialogTable : false,
        sum : '',
        goodMember : [],
        badMember : [],
        realBadMember : [],

        rich : '',
        richAmount : '',
      }
    },
  created() {
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
    
    // 모임 정보 가져오기
    http.get('/party/searchByPartyId', {
      params : {
        partyId : this.$route.query.groupId,
      }
    })
    .then(({data}) => {
      this.group = data
      if(data.withdraw) {
        var ok = confirm(this.rich+"이 "+this.richAmount+"의 출금을 요청합니다.")
        if(ok){
          http.get('/withdraw/agree', {
            params : {
              partyId : this.$route.query.groupId,
              userId : this.$store.state.auth.user.data.id,
              isagree : 1,
            }
          })
        }else{
          http.get('/withdraw/agree', {
            params : {
              partyId : this.$route.query.groupId,
              userId : this.$store.state.auth.user.data.id,
              isagree : 2,
            }
          })
        }
      }

      this.sum = Number(this.sum)
      this.group.memberlist.forEach(element => {
        this.sum += Number(element.payment)

        if(element.ispay) {
          this.goodMember.push(element.name)
        }else{
          this.badMember.push(element.name)
        }

        if(element.warning){
          this.realBadMember.push(element.name)
        }
      });

      if(!this.group.withdraw){
        this.rich = this.group.withdrawName
        this.richAmount = this.group.withdrawAmount
      }


      //다음 납부 날짜 계산하기

    })

    //메모 리스트 가져오기
    http.get('/token/memos', {
      params : {
        partyId : this.groupId
      },
      headers: authHeader()
    }).then(({data}) => {
      this.memoList = data.data
    })
  },

  methods: {
    viewDay ({date}) {
        this.dialogMemo = true
        this.pickedDate = date
      },
      getEventColor (event) {
        return event.color
      },
      setToday () {
        this.focus = ''
      },
      prev () {
        this.$refs.calendar.prev()
      },
      next () {
        this.$refs.calendar.next()
      },
      showEvent ({ nativeEvent, event }) {
        const open = () => {
          this.selectedEvent = event
          this.selectedElement = nativeEvent.target
          setTimeout(() => this.selectedOpen = true, 10)
        }

        if (this.selectedOpen) {
          this.selectedOpen = false
          setTimeout(open, 10)
        } else {
          open()
        }

        nativeEvent.stopPropagation()
      },
      updateRange () {
        const events = []
        if(this.group.startDate != null){
          events.push({
            name: '',
            start: this.group.startDate,
            end: this.group.endDate,
            color: "green",
          })
        }
        this.events = events
        this.events = []
      },
      rnd (a, b) {
        return Math.floor((b - a + 1) * Math.random()) + a
      },
    saveMemo(){
      http.post('/token/memo', { 
        memo : {
          partyId : this.groupId,
          date : this.pickedDate,
          description : this.groupMemo,
          isChecklist : false,
        },
        headers : authHeader()
        
      }).then(({data}) => {
        if(data.result == "success"){
          alert("저장이 완료되었습니다.")
          this.dialogMemo = false
          this.$router.go;
        }
      })
    },
    
    handleLogout() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/');
    },
    pay() {
      http.get('/party/pay', {
        partyId : this.groupId,
        privateKey : this.groupKey,
        value : this.regularPay,
      })
      .then(({data}) => {
        this.group = data
        this.groupKey = ''
      })
    },
    getAmount() {
      http.post('/withdraw', {
        userId : this.$store.state.auth.user.data.id,
        partyId : this.groupId,
        withdrawName : this.$store.state.auth.user.data.nickname,
        withdrawAmount : this.amount,
        privatekey : this.groupKey,
      })
      .then(() => {
        this.groupKey = ''
        alert('다른 인원들이 동의하면 출금이 자동으로 진행됩니다!')
      })
    },
    changeGroupId(id) {
      this.$router.push({name: 'groupdetail',query: { groupId: id }});
      this.$router.go()
    }
  },
};
</script>
<style>

</style>