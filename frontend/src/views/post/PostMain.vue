<template>
  <center>
    <v-card height="100%">
      <v-toolbar
        color="green darken-1"
        dark
        flat
        prominent
      >
        <div align="center" justify="center">
          <v-toolbar-title>
            <h1 class="my-15">TRABLOCK</h1>
          </v-toolbar-title>
        </div>
        <v-spacer></v-spacer>

        <v-btn icon>
          <v-icon>far fa-user</v-icon>
        </v-btn>

        <v-btn icon>
          <v-icon>far fa-bell</v-icon>
        </v-btn>

        <div class="my-2">
            <router-link
            :to="{path: 'group'}" class="py-0 text-center text-h6 text-decoration-none">
                <v-btn large color="amber darken-1">Group</v-btn>
            </router-link>
        </div>

        <template v-slot:extension>
          <v-tabs
            v-model="tab"
            align-with-title
          >
            <v-tabs-slider color="yellow"></v-tabs-slider>

            <v-tab class="tabinfo navbarlistitemlink" @click="gotoPostMain">포스트</v-tab>
            <v-tab class="tabinfo navbarlistitemlink" @click="gotoPostManage">포스트 관리</v-tab>
            <!-- <v-tab class="tabinfo navbarlistitemlink">포스트 관리</v-tab> -->
          </v-tabs>
        </template>
      </v-toolbar>

      <v-tabs-items v-model="tab">
        <v-tab-item>
          <v-card flat min-height="90vh">
            <v-row>
              <v-col align="right">
                <router-link :to="{path: 'post/create'}" class="py-0 text-center text-h6 text-decoration-none">
                <v-btn 
                  rounded color="grey" dark
                  class="mx-5"
                >
                  <v-icon dark>mdi-plus</v-icon>            
                </v-btn></router-link>
              </v-col>
              
            </v-row>

            <v-navigation-drawer
            :mini-variant="miniVariant"
            permanent
            expand-on-hover
            left
            absolute
            >
            <v-divider></v-divider>

            <v-list
              nav
              dense
              class="py-0"
            >
              <v-list-item link>
                <v-list-item-icon>
                  <v-icon class="fas fa-star"></v-icon>
                </v-list-item-icon>
                <v-list-item-title>인기 포스트</v-list-item-title>
              </v-list-item>
              <v-list-item link>
                <v-list-item-icon>
                  <v-icon class="fas fa-hand-holding-heart"></v-icon>
                </v-list-item-icon>
                <v-list-item-title>추천 포스트</v-list-item-title>
                </v-list-item>
                <v-list-item link>
                  <v-list-item-icon>
                    <v-icon class="fas fa-search"></v-icon>
                  </v-list-item-icon>
                  <v-list-item-title>포스트 검색</v-list-item-title>
                </v-list-item>
            </v-list>
            </v-navigation-drawer>
                <v-card min-height="100%" class="mx-5">
                  <v-container style="padding-left: 10px; min-height: 100vh;">
                  <h1 style="text-align: left;">인기 포스트</h1>
                  <br>
                  <v-row
                    v-for="n in 2"
                    :key="n"
                    :class="n === 1 ? 'mb-6' : ''"
                    no-gutters
                  >
                    <v-col
                      v-for="k in 3"
                      :key="k"
                    >
                      <v-card
                      max-width="344"
                      
                        class="pa-2"
                        outlined
                        tile
                      >
                      <v-list-item>
                          <v-list-item-avatar color="grey"></v-list-item-avatar>
                          <v-list-item-content>
                            <v-list-item-title class="headline">Our Changing Planet</v-list-item-title>
                            <v-list-item-subtitle>by Kurt Wagner</v-list-item-subtitle>
                          </v-list-item-content>
                        </v-list-item>

                        <v-img
                          src="https://cdn.vuetifyjs.com/images/cards/mountain.jpg"
                          height="194"
                        ></v-img>

                        <v-card-text>
                          Visit ten places on our planet that are undergoing the biggest changes today.
                        </v-card-text>

                        <v-card-actions>
                          <v-btn
                            text
                            color="deep-purple accent-4"
                          >
                            Read
                          </v-btn>
                          <v-btn
                            text
                            color="deep-purple accent-4"
                          >
                            Bookmark
                          </v-btn>
                          <v-spacer></v-spacer>
                          <v-btn icon>
                            <v-icon>mdi-heart</v-icon>
                          </v-btn>
                          <v-btn icon>
                            <v-icon>mdi-share-variant</v-icon>
                          </v-btn>
                        </v-card-actions>
                      </v-card>
                    </v-col>
                  </v-row>
                  </v-container>
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
  name: 'PostMain',
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
      }
    },
  methods: {
    // 상단 탭 : 클릭 이용성 개선
    gotoPostMain() {  
        this.$router.push('/post')
    },
    gotoPostManage() {
        this.$router.push('/post/manage')
    },
  },
};
</script>
<style>

</style>
