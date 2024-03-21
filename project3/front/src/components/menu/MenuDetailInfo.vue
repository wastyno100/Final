<script setup>
import { onMounted, ref } from 'vue'
import axios from 'axios'

const menu = ref([]);

const {dataObj} = history.state;
const getData = async() => {
  const res = await axios.get(`/api/menuDetail?menuNo=${dataObj.menuNo}`)
  menu.value = res.data[0];
  console.log(menu.value)
}

onMounted(() => {
  getData()
});

console.log(menu.value);

</script>

<template>
<!--        <img src="src{{menu.mdImg}}"></img>-->
  {{menu.menuContent}}
  <h2>{{menu.mimg}}</h2>
    {{menu.mdImg}}
</template>

<style scoped>

</style>