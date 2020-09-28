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
              <v-row class="fill-height">
              </v-row>
              
              <v-row class="fill-height">
                <v-col  cols="12" sm="3">
                </v-col>
                <v-col cols="12" sm="9">
                  <v-row>
                    <v-col align="left">
                      <h1 class="mt-15">민지와 함께 춤을</h1>
                      <h3 class="my-5">모임설명입니다. 좋은 모임입니다.</h3>
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
                        <v-menu
                          v-model="selectedOpen"
                          :close-on-content-click="false"
                          :activator="selectedElement"
                          offset-x
                        >
                          <v-card
                            color="grey lighten-4"
                            min-width="350px"
                            flat
                          >
                            <v-toolbar
                              :color="selectedEvent.color"
                              dark
                            >
                              <v-btn icon>
                                <v-icon>mdi-pencil</v-icon>
                              </v-btn>
                              <v-toolbar-title v-html="selectedEvent.name"></v-toolbar-title>
                              <v-spacer></v-spacer>
                              <v-btn icon>
                                <v-icon>mdi-heart</v-icon>
                              </v-btn>
                              <v-btn icon>
                                <v-icon>mdi-dots-vertical</v-icon>
                              </v-btn>
                            </v-toolbar>
                            <v-card-text>
                              <span v-html="selectedEvent.details"></span>
                            </v-card-text>
                            <v-card-actions>
                              <v-btn
                                text
                                color="secondary"
                                @click="selectedOpen = false"
                              >
                                Cancel
                              </v-btn>
                            </v-card-actions>
                          </v-card>
                        </v-menu>
                      </v-sheet>
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
                            <v-list-item-title class="headline my-3 mx-2">총 누적금액은 <strong>300원</strong>입니다.</v-list-item-title>
                            <v-list-item-subtitle class="mx-2">2020.10.15까지 <strong>50원</strong>을 입금해야 합니다.</v-list-item-subtitle>
                          </v-list-item-content>
                        </v-list-item>

                        <v-card
                          class="mx-5 mt-1"
                          outlined
                          align = "left"
                          min-height="27vh"
                        >
                          <h3 class="mx-5 mt-3 mb-6">2020.09.15 납부 현황</h3>
                          <h4 class="mx-8 my-3"><v-icon color="green" class="mr-2">mdi-checkbox-marked-circle</v-icon>건쁘, 함지박, 포도 님이 정상납부하였습니다.</h4>
                          <h4 class="mx-8 my-3"><v-icon color="amber darken-2" class="mr-2">fas fa-circle-notch fa-spin</v-icon>간지 님이 미납하였습니다.</h4>
                          <h4 class="mx-8 my-3"><v-icon color="red" class="mr-2">mdi-cancel</v-icon>춤을추는민지 님이 수수료 5,000,000원을 납부하고 퇴출되었습니다.</h4>
                        </v-card>

                        <v-card-actions>
                          <v-btn text>회원 납부 현황 보기</v-btn>
                          <v-btn text>나의 납부 현황 보기</v-btn>
                          <v-btn text>납부하기</v-btn>
                        </v-card-actions>
                      </v-card>

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
      }
    },
  mounted () {
    this.$refs.calendar.checkChange()
  },
  created() {

  },

  methods: {
    viewDay ({ date }) {
        this.focus = date
        this.type = 'day'
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
      updateRange ({ start, end }) {
        const events = []

        const min = new Date(`${start.date}T00:00:00`)
        const max = new Date(`${end.date}T23:59:59`)
        const days = (max.getTime() - min.getTime()) / 86400000
        const eventCount = this.rnd(days, days + 20)

        for (let i = 0; i < eventCount; i++) {
          const allDay = this.rnd(0, 3) === 0
          const firstTimestamp = this.rnd(min.getTime(), max.getTime())
          const first = new Date(firstTimestamp - (firstTimestamp % 900000))
          const secondTimestamp = this.rnd(2, allDay ? 288 : 8) * 900000
          const second = new Date(first.getTime() + secondTimestamp)

          events.push({
            name: this.names[this.rnd(0, this.names.length - 1)],
            start: first,
            end: second,
            color: this.colors[this.rnd(0, this.colors.length - 1)],
            timed: !allDay,
          })
        }

        this.events = events
        this.events = []
      },
      rnd (a, b) {
        return Math.floor((b - a + 1) * Math.random()) + a
      },
  },
};
</script>
<style>

</style>