<style scoped>
  .MDmain{
    padding: 10px;
  }
  .heart{

  }
  .MDBTN{
    margin: 5px;
  }
</style>
<template>
  <v-main>
    <v-row>
    <v-col cols="6">
    <!--메뉴기본정보-->
    <v-carousel show-arrows="hover">
      <v-carousel-item
        src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
        cover
      ></v-carousel-item>
      <v-carousel-item
        src="https://cdn.vuetifyjs.com/images/cards/hotel.jpg"
        cover
      ></v-carousel-item>
      <v-carousel-item
        src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
        cover
      ></v-carousel-item>
    </v-carousel>
    </v-col>
    <v-col cols="6">
    <v-row class="MDmain">
      <v-col cols="12">
      <h3>{{ menu.menuTitle }}</h3><br>
        <h4>원산지 : 상세페이지 참조</h4>
        <v-col cols="12">
          <v-row>
            <h4 class="heart">{{menu.heart}}<v-btn
              class="ma-2"
              color="blue-lighten-2"
              icon="mdi-thumb-up"
              variant="text"
            ></v-btn></h4>
            <h4><a href="#">리뷰</a></h4>
          </v-row>
        </v-col>
      </v-col>
      <v-col cols="8" class="detailMain" >
        <v-col cols="6">
          <v-row>
          가격: {{ menu.menuPrice }}원<br>
          </v-row>
          <v-row>
          {{ menu.menuContent }}<br>
          </v-row>
        </v-col>
      </v-col>
      <v-col cols="4" class = "mb-5">
        <v-col class="MDBTN">
        <v-row>
          <v-container>
            <!-- 숫자 조절 버튼과 input 필드 -->
            <v-row align="center">
                <v-text-field id = "number" v-model="count" type="number" class="mx-2" style="width: 100px;"></v-text-field>
            </v-row>
          </v-container>
        </v-row>
        <v-row >
            <v-btn color="primary" variant = flat>
              구매하기
            </v-btn>
        </v-row>
        <v-row>
            <v-btn class="mCart" color="primary" variant = flat @click="addToCart">
              장바구니담기
            </v-btn>
        </v-row>
        </v-col>
      </v-col>
    </v-row>
    </v-col>
    </v-row>
    <v-divider></v-divider>
    <!--메뉴기본정보끝-->
    <v-tabs v-model="item" bg-color="primary">
      <v-tab value="one">상세정보</v-tab>
      <v-tab value="two">리뷰</v-tab>
      <v-tab value="three">문의사항</v-tab>
    </v-tabs>
    <v-card-text>
      <v-window v-model="item">
        <v-window-item value="one">
          <MenuDetailInfo />
        </v-window-item>
        <v-window-item value="two">
          <MenuReply />
        </v-window-item>
        <v-window-item value="three">
          <MenuQeustion />
        </v-window-item>

        <!--        <v-window-item value="three"> Three </v-window-item>-->
      </v-window>
    </v-card-text>
  </v-main>
</template>

<style scoped>

</style>
<script setup>
import axios from 'axios'
import { onMounted, ref } from 'vue'
import MenuDetailInfo from '../components/menu/MenuDetailInfo.vue'
import MenuQeustion from '@/components/menu/MenuQeustion.vue'
import MenuReply from '@/components/menu/MenuReply.vue'


// 게시글 목록에서 선택한 게시글 번호를 선물로 받음
const { dataObj } = history.state

const item = ref()
// 게시글 내용 넣기
const menu = ref({}) // DB
// 수량을 관리하는
const count = ref(1)

// 게시글 번호를 서버로 보내서 해당 게시물의 데이터들만 가져오자
// 게시글 업데이트도 put을 사용해서 해버리자
const getData = async () => {
  const res = await axios.get(`/api/menuDetail?menuNo=${dataObj.menuNo}`)
  menu.value = res.data[0]
}

onMounted(() => {
  getData()
})

const addToCart = async () => {
  try {
    // 장바구니 추가 API 호출, 예시로 count.value와 menuNo를 함께 보냄
    // 실제 API에 맞춰서 수정 필요
    const res = await axios.post('http://localhost:5173/Menucart', {
      menuNo: dataObj.menuNo,
      quantity: count.value
    })
    // 성공 메시지 처리
    alert('장바구니에 추가되었습니다.')
  } catch (error) {
    // 에러 처리
    console.error('장바구니 추가 실패:', error)
    alert('장바구니 추가에 실패했습니다.')
  }
}
</script>