import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify';
import store from './store';
import VuetifyDialog from 'vuetify-dialog'
import 'vuetify-dialog/dist/vuetify-dialog.css'
import VueProgress from 'vue-progress-path'

Vue.config.productionTip = false

Vue.use(VueProgress);

Vue.use(VuetifyDialog, {
  context: {
    vuetify
  }
})

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
