<template>
  <v-main>
    <v-container>
      <h3>{{ menu.menuNo }}번</h3><br>
      제목: {{ menu.menuTitle }}<br>
      내용: {{ menu.menuContent }}<br>
      카테고리: {{ menu.menuCate }}<br>
      작성일: {{ menu.regDate }}<br>
      <v-btn @click="
          router.push({
            name: 'update',
            state: {dataObj: { no: menu.menuNo }}
          })"
             text="수정" /><br>
      <v-btn @click="delData" text="삭제" />
    </v-container>

  </v-main>
</template>

<style scoped>

</style>
<script setup>
import axios from "axios";
import { onMounted, ref } from "vue";

// 게시글 목록에서 선택한 게시글 번호를 선물로 받음
const {dataObj} = history.state;

// 게시글 내용 넣기
const menu = ref({})

// 게시글 번호를 서버로 보내서 해당 게시물의 데이터들만 가져오자
// 게시글 업데이트도 put을 사용해서 해버리자
const getData = async() => {
  const res = await axios.get('/api/menuDetail?menuNo=${dataObj.menuNo}')
  menu.value = res.data[0];
}

onMounted(() => {
  getData()
});

</script>