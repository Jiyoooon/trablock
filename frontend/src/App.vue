<template>
  <v-app>
    <v-app-bar 
      app
      color = "#00796B"
    >
      <strong>WELCOME TO TRABLOCK :)</strong>
    </v-app-bar>

    <!-- Sizes your content based upon application components -->
    <v-main id="background">
      <!-- Provides the application the proper gutter -->
      <v-container 
        style="padding-bottom: 60px; padding-top:120px; min-height: 70vh;"
      >
        <v-card>
          <v-card-text class="grey lighten-4">
            <h1 class="ml-5 mt-5">WELCOME TO TRABLOCK :)</h1>
            <v-card
              class="mx-12 my-15"
              color="orange lighten-2"
            >
              <v-row>
                <v-col cols="12" sm="9">
                  <v-textarea
                    label="ID"
                    auto-grow
                    outlined
                    rows="1"
                    row-height="15"
                    class="ml-15 mt-12"
                    background-color="white"
                  ></v-textarea>
                  <v-textarea
                    label="PASSWORD"
                    auto-grow
                    outlined
                    rows="1"
                    row-height="15"
                    class="ml-15 mb-10"
                    background-color="white"
                  ></v-textarea>
                </v-col>
                <v-col cols="12" sm="3">
                  <div 
                    class="mt-12 ml-5"
                  >
                    <v-btn
                      width="120"
                      height="145"
                      color="success"
                    ><h3><strong>Log in</strong></h3></v-btn>
                  </div>
                </v-col>
              </v-row>
            </v-card>
          </v-card-text>
        </v-card>
        

        <!-- If using vue-router -->
        <router-view></router-view>
      </v-container>
    </v-main>

    <v-footer app>
      <!-- -->
    </v-footer>
  </v-app>
</template>

<script>

export default {
  name: 'App',
  components: {
    
  },
  data: () => ({
    
  }),
  methods: {
    
  },
};
</script>
<style>

#background {
  background: url(../public/timesquare2.jpg);
}
</style>