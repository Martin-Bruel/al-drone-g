<template>
  <template v-if="!truckConnected">
    <the-loader v-on:backendStatus="truckResponse($event)"></the-loader>
  </template>
  <template v-else>
    <router-view></router-view>
  </template>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component';
import { useStore } from 'vuex';
import { key } from './store/store';

import TheLoader from '@/components/TheLoader.vue';
import { Truck } from './types/types';

@Options({
    components: {
        TheLoader
    }
})
export default class App extends Vue {

  store = useStore(key);
  truckConnected = false;

  truckResponse(truck: Truck){
    if(truck.available){
      console.log('From App.vue: Truck is up', truck);
      truck.drones.forEach((d) => this.store.commit('updateDrone',d));
      this.truckConnected = true;
    }else{
      console.log('From App.vue: Truck seems down');
    }
  }

  mounted(){
    // Change the following ip to the one where the WS has been launched
    const env = process.env.APP_ENV;
    const map_host = env == 'prod' ? 'map' : 'localhost';
    const map_wsport = 3000;
    const connection = new WebSocket(`ws://${map_host}:${map_wsport}/`);
    connection.onopen = function() {
      console.log("ws::open : connection established ");
    }
    connection.onmessage = (event: MessageEvent) => {

      console.log('received: ', event);
      this.store.commit('updateDrone',JSON.parse(event.data));

    }
  }
}
</script>

<style lang="scss">
*{
  box-sizing: border-box;
}

html,body{
  font-family: sans-serif;
  margin: 0;
  overflow: hidden;
}
</style>
