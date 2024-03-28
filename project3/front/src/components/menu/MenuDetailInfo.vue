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
  <img src="/images/선물세트.jpg" style="display: block; margin: 0 auto;"><br>
  {{items.menuContent}}
</div>
  <h2>{{items.mimg}}</h2>
    {{items.mdImg}}
</template>

<style scoped>
  #menuContent {
    /* 줄 간격 조정 */
    line-height: 1.5;
    /* 너비를 초과하는 부분 생략하고 생략 부호 (...) 표시 */
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

</style>