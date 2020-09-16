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
          <v-btn large color="green darken-1">Blog</v-btn>
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
            >
              <v-list
                dense
                nav
                class="py-0"
              >
                <v-list-item :class="miniVariant && 'px-0'">
                  <v-list-item-content class="elevation-5 mt-5 mb-2">
                    <v-list-item-title><h3>나의 모임</h3></v-list-item-title>
                  </v-list-item-content>
                </v-list-item>

                <v-divider></v-divider>

                <v-list-item
                  v-for="item in items"
                  :key="item.title"
                  link
                >
                  <v-list-item-icon>
                    <v-icon>{{ item.icon }}</v-icon>
                  </v-list-item-icon>

                  <v-list-item-content>
                    <v-list-item-title>{{ item.title }}</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list>
            </v-navigation-drawer>
            <v-row>
              <v-col  cols="12" sm="2">
              </v-col>
              <v-col  cols="12" sm="10">
                <v-card min-height="40vh" class="mx-10">
              <v-list three-line subheader>
                    <v-list-item>
                      <v-list-item-content>
                        <v-row>
                          <v-col cols="12" sm="3">
                            <v-text-field label="모임 이름을 입력해 주세요." v-model="groupName" class="mt-3 ml-10"></v-text-field>
                          </v-col>
                        </v-row>
                        <v-row>
                          <v-col cols="12" sm="7">
                            <v-text-field label="모임에 대한 간단한 설명을 입력해 주세요." v-model="groupExplanation" class="ml-10"></v-text-field>
                          </v-col>
                        </v-row>
                        <v-row>
                          <v-col cols="12" sm="3">
                            <div class="subheading"><small>여행 기간을 선택해 주세요.</small></div>
                            <v-date-picker v-model="dates" range color="amber darken-1" class="ml-15"></v-date-picker>
                          </v-col>
                          <v-col cols="12" sm="9">
                            <v-row>
                              <v-col cols="12" sm="4">
                                <v-text-field label="여행지를 입력해 주세요." v-model="groupTarget" class="ml-15" hint="국가명"></v-text-field>
                              </v-col>
                              <v-col cols="12" sm="4">
                                <v-text-field v-model="groupTarget" hint="도시명"></v-text-field>
                              </v-col>
                            </v-row>
                            <v-row>
                              <v-col cols="12" sm="4">
                                <v-text-field v-model="dateRangeText" label="Date range" readonly class="ml-15"></v-text-field>
                              </v-col>
                            </v-row>
                            <v-row>
                              <v-col cols="12" sm="4">
                                <v-text-field label="목표금액을 입력해 주세요." v-model="groupTarget" class="ml-15"></v-text-field>
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
                                ></v-select>
                              </v-col>
                              <v-col cols="12" sm="2" v-if="groupCycle == '주간'">
                                <v-select
                                  :items="week"
                                  label="요일 선택"
                                  v-model="groupPayDate"
                                ></v-select>
                              </v-col>
                            </v-row>
                            <v-row>
                              <v-col cols="12" sm="5">
                                <v-text-field label="월간 납입금액" v-model="groupMonthPay" class="ml-15" readonly></v-text-field>
                              </v-col>
                              <v-col cols="12" sm="5">
                                <v-text-field label="퇴출 수수료" v-model="groupMonthPay" readonly></v-text-field>
                              </v-col>
                            </v-row>
                          </v-col>
                        </v-row>
                        <v-row>
                          <v-col cols="12" sm="3" class="ml-10">
                            함께 여행 갈 회원들을 추가해 주세요.
                            <v-btn icon color="green" class="mb-1" @click="dialogUser=true;">
                              <v-icon>mdi-plus</v-icon>
                            </v-btn>
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
                                <v-radio-group v-model="pickedFriend" column>
                                  <div v-for="(item, i) in userList" v-bind:key="i">
                                    <v-radio
                                      v-bind:label="item.email"
                                      v-bind:value="item.id"
                                      class="my-1"
                                    ></v-radio>
                                  </div>
                                </v-radio-group>
                              </v-card-text>
                              <v-divider></v-divider>
                              <v-card-actions>
                                <v-btn color="blue darken-1" text @click="dialogUser = false">Close</v-btn>
                                <v-btn color="blue darken-1" text @click="dialogUser = false">Save</v-btn>
                              </v-card-actions>
                            </v-card>
                          </v-dialog>
                        </v-row>
                        <v-row>
                          <v-col align="right">
                            <v-btn color="amber darken-3" text @click="dialogUser = false"><h3>Close</h3></v-btn>
                            <v-btn color="amber darken-3" text @click="dialogUser = false"><h3>Save</h3></v-btn>
                          </v-col>
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

export default {
  name: 'GroupMain',
  components: {
    
  },
  data () {
      return {
        tab: null,
        drawer: true,
        items: [
          { title: '홍콩여행', icon: 'mdi-view-dashboard' },
          { title: '부산여행', icon: 'mdi-view-dashboard' },
          { title: '3일만에 세계일주', icon: 'mdi-view-dashboard' },
        ],
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
        groupMonthPay : '',

        week : ['월요일','화요일','수요일','목요일','금요일','토요일','일요일'],
        month : [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28],
        dates: ['2019-09-10', '2019-09-20'],

        dialogUser:false,
        friend: '',
        userList : [
          {
            id : 1,
            nickname : "지윤",
            email : "ganzi@gmail.com"
          },
          {
            id : 2,
            nickname : "지훈",
            email : "imjihoon@gmail.com"
          }
        ],
        sUserList : [],
        pickedFriend : '',
      }
    },
  methods: {
    searchUser () {
      this.userList.forEach(element => {
        if(element.email.includes(this.friend)){
          alert("g")
          this.sUserList.add(element)
        }
      });
    }
  },

  computed: {
    dateRangeText () {
      return this.dates.join(' ~ ')
    },
  },
};
</script>
<style>

</style>