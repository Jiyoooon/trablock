<template>
    <!-- 큰화면일때 -->
    <div class="d-none d-sm-block">

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
          </v-tabs>
        </template>
      </v-toolbar>
    </div>
</template>
 
<script>
export default {
  name: 'postheader',
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
}
</script>
 
<style scoped>
</style>

