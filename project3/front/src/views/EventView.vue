<template>
  <div id="app" style="margin-top: 100px">
      <v-row justify="center">
        <v-col cols="8" class="css">
          <br>
          <br>
          <img src="/images/event.png" style="height: 500px;">
          <button class="btn" @click="checkAttendance"><span>출석체크</span></button>
        </v-col>
      </v-row>


  </div>
</template>

<script setup>
import axios from 'axios'
import { onMounted } from 'vue'
import { useStore } from 'vuex';

const store = useStore();
const userNo = store.state.loginStatus.userNo;
let userEvents = []; // 사용자의 이벤트를 저장할 변수

const checkAttendance = async () => {
  localStorage.clear();
  const today = new Date().toDateString();

  // 로컬 스토리지에서 이전 클릭 날짜를 가져옵니다.
  const lastClickedDate = localStorage.getItem('lastClickedDate');

  // 만약 이전에 클릭한 날짜가 오늘과 같지 않다면 버튼을 클릭할 수 있습니다.
  if (lastClickedDate !== today) {
    //사용자가 버튼을 클릭한 날짜를 로컬 스토리지에 저장합니다.
    localStorage.setItem('lastClickedDate', today);
    console.log(userNo);
    await axios.put(`/api/eventUpdate?userNo=${userNo}`)
    console.log("출석체크 완료");
    console.log('버튼 클릭이 허용됩니다.');


    const res = await axios.get(`/api/event?userNo=${userNo}`);
    userEvents = res.data;
    console.log(userEvents)
    console.log("여기 리스트")
    alert("현재 출석일은" + userEvents[0].event + "입니다")
    sessionStorage.setItem("eventCheck", "ok")
  } else {
    alert('하루에 한 번만 버튼을 클릭할 수 있습니다.');
  }

}


</script>
<style>
.css {
  padding-top: 10px;
  border-radius: 20px;
  text-align: center;
  background-color: #78c7d2;

}

.btn {
  border: none;
  display: block;
  text-align: center;
  cursor: pointer;
  text-transform: uppercase;
  outline: none;
  overflow: hidden;
  position: relative;
  color: #fff;
  font-weight: 700;
  font-size: 15px;
  background-color: #222;
  padding: 17px 60px;
  margin: 0 auto;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.20);
  border-radius: 5px;
}

.btn span {
  position: relative;
  z-index: 1;
}

.btn:after {
  content: "";
  position: absolute;
  left: 0;
  top: 0;
  height: 490%;
  width: 140%;
  background: #78c7d2;
  -webkit-transition: all .5s ease-in-out;
  transition: all .5s ease-in-out;
  -webkit-transform: translateX(-98%) translateY(-25%) rotate(45deg);
  transform: translateX(-98%) translateY(-25%) rotate(45deg);
}

.btn:hover:after {
  -webkit-transform: translateX(-9%) translateY(-25%) rotate(45deg);
  transform: translateX(-9%) translateY(-25%) rotate(45deg);
}
</style>

