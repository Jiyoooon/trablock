<template>
<v-app>
  <v-content>
    <router-view />
  </v-content>
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
.jb-box { width: 100%; height: 500px; overflow: hidden;margin: 0px auto; position: relative; }
video { 
  position : fixed;
	top : 0; 
	left : 0;
	min-width : 100%;
	min-height : 100%;
	width : auto;
	height : auto;
	z-index : 0;
 }
/* #background {
  background: url(../public/timesquare2.jpg);
} */
.container{
  z-index : 2;
}
</style>