<template>
<div id="postmanage">
    <div id="header">
      <postheader/>
    </div>

      <v-tabs-items v-model="tab">
        <v-tab-item>
          <v-card flat min-height="90vh">
            <v-row>
              <v-col align="right">
                <router-link :to="{path: 'post/create'}">
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
          <!-- <v-list>
            <v-list-item class="px-2">
              <v-list-item-avatar>
                <v-img src="https://randomuser.me/api/portraits/women/85.jpg"></v-img>
              </v-list-item-avatar>
            </v-list-item>

            <v-list-item link>
              <v-list-item-content>
                <v-list-item-title class="title">Sandra Adams</v-list-item-title>
                <v-list-item-subtitle>sandra_a88@gmail.com</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
          </v-list> -->

          <v-divider></v-divider>

          <v-list
            nav
            dense
          >
            <v-list-item link>
              <v-list-item-icon>
                <v-icon>mdi-folder</v-icon>
              </v-list-item-icon>
              <v-list-item-title>My Files</v-list-item-title>
            </v-list-item>
            <v-list-item link>
              <v-list-item-icon>
                <v-icon>mdi-account-multiple</v-icon>
              </v-list-item-icon>
              <v-list-item-title>Shared with me</v-list-item-title>
            </v-list-item>
            <v-list-item link>
              <v-list-item-icon>
                <v-icon>mdi-star</v-icon>
              </v-list-item-icon>
              <v-list-item-title>Starred</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-navigation-drawer>
            <v-card min-height="40vh" class="mx-10">
              <h1>HOW TO USE TRABLCOK</h1>
            </v-card>
          </v-card>
        </v-tab-item>
        <!-- <v-tab-item>
          <v-card flat min-height="90vh">
            <v-card-text> 하이하이</v-card-text>
          </v-card>
        </v-tab-item> -->
      </v-tabs-items>
</div>
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