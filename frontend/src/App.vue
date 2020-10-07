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
@import url("https://fonts.googleapis.com/css2?family=Patrick+Hand&family=Nunito:wght@400;600;700;800;900&display=swap");
@font-face {
  font-family: "NanumSquareRound";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/NanumSquareRound.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: "NanumBarunpen";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/NanumBarunpen.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

:root {
  --primary-color: #37cdc2;
  --white-color: #fcfcfc;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

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