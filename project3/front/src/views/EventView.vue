<template>
  <div id="app" style="margin-top: 300px">
    <header>
      <h1>이벤트 페이지</h1>
      <button @click="checkAttendance">출석체크</button>
    </header>
  </div>
</template>

<script setup>
import axios from 'axios'
import { onMounted } from 'vue'
import { useStore } from 'vuex';
const store = useStore();
const userNo = store.state.loginStatus.userId;
let userEvents = []; // 사용자의 이벤트를 저장할 변수

const getData = async () => {
  try {

    const res = await axios.post(`/api/event/${userNo}`);
    userEvents = res.data;
    console.log(userNo)
  }catch (error) {
    console.error("이벤트 데이터를 가져오는 중 오류 발생:", error);
  }
}

const checkAttendance = async () => {
  try {
    // 이벤트의 출석을 업데이트합니다.

    // 서버에 업데이트된 이벤트 데이터를 전송합니다.
    await axios.put(`/api/eventUpdate?event=${1}`);

    console.log("출석체크 완료");
  } catch (error) {
    console.error("출석체크 중 오류 발생:", error);
  }
}

onMounted(() => {
  getData();
})
</script>