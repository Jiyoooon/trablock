<template>
  <center>
    <v-card height="100%">
      <v-toolbar
        color="cyan"
        dark
        flat
        prominent
      >
        <v-row></v-row>

        <v-toolbar-title>
          <h1 class="my-15">TRABLOCK</h1>
        </v-toolbar-title>

        <v-spacer></v-spacer>

        <v-btn icon>
          <v-icon>mdi-account-outline</v-icon>
        </v-btn>

        <v-btn icon>
          <v-icon>mdi-blinds</v-icon>
        </v-btn>

        <div class="my-2">
          <v-btn large>Blog</v-btn>
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
                <v-btn 
                  rounded color="primary" dark
                  class="mx-5"
                >
                  +              
                </v-btn>
              </v-col>
              
            </v-row>

            <v-navigation-drawer
              v-model="drawer"
              :color="color"
              :expand-on-hover="expandOnHover"
              :mini-variant="miniVariant"
              :right="right"
              :permanent="permanent"
              :src="bg"
              absolute
              dark
            >
              <v-list
                dense
                nav
                class="py-0"
              >
                <v-list-item :class="miniVariant && 'px-0'">
                  <v-list-item-content class="elevation-5">
                    <v-list-item-title class="elevation-5"><h3>나의 모임</h3></v-list-item-title>
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
            <v-card min-height="40vh" class="mx-10">
              <h1>HOW TO 살짝 쓸 곳</h1>
            </v-card>
          </v-card>
        </v-tab-item>
        <v-tab-item>
          <v-card flat min-height="90vh">
            <v-card-text v-text="text"></v-card-text>
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
        colors: [
          'primary',
          'blue',
          'success',
          'red',
          'teal',
        ],
        right: false,
        permanent: true,
        miniVariant: false,
        expandOnHover: false,
        background: false,
      }
    },
  methods: {
    
  },
};
</script>
<style>

</style>