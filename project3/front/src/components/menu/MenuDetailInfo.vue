<script setup>
import { onMounted, ref } from 'vue'
import axios from 'axios'

const items = ref([]);

const {dataObj} = history.state;
const getData = async() => {
  const res = await axios.post(`/api/menuDetail?menuNo=${dataObj.menuNo}`)
  items.value = res.data[0];
}

onMounted(() => {
  getData()
});

</script>

<template>
<!--        <img src="src{{menu.mdImg}}"></img>-->
<div id="menuContent">
  {{items.menuContent}}
</div>
  <h2>{{items.mimg}}</h2>
    {{items.mdImg}}
</template>

<style scoped>
  #menuContent{
    /* overflow-wrap: break-word; */
    /* text-overflow: ellipsis; */
  }
</style>