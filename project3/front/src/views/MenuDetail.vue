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
        <!--이미지슬라이드-->
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
      <!--메뉴디비출력-->
      <v-col cols="6">
        <v-row class="MDmain">
          <v-col cols="12">
            <h3>{{ menu.menuNo }}</h3>
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
          <!--버튼들-->
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
                <v-btn color="primary" variant = flat @click="buy">
                  구매하기
                </v-btn>
              </v-row>
              <v-row>
                <v-dialog v-model="dialog" max-width="400" persistent>
                  <template v-slot:activator="{ props: activatorProps }">
                    <v-btn v-bind="activatorProps" color="primary" @click="saveCart"> 장바구니담기 </v-btn>
                  </template>

                  <v-card
                    prepend-icon="mdi-map-marker"
                    text="쇼핑을 계속 하시려면 확인 장바구니로 이동하고 싶으시면 장바구니를 클릭해주시길 바랍니다. "
                    title="쇼핑을 계속 하시겠습니까?"
                  >
                    <template v-slot:actions>
                      <v-spacer></v-spacer>

                      <v-btn color="primary" @click="addToCart"> 장바구니 </v-btn>

                      <v-btn color="primary" @click="dialog = false"> 확인 </v-btn>
                    </template>
                  </v-card>
                </v-dialog>
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
import { useRouter } from 'vue-router'
const router = useRouter();


// 게시글 목록에서 선택한 게시글 번호를 선물로 받음
const { dataObj } = history.state

const item = ref()
// 게시글 내용 넣기
const menu = ref({}) // DB
// 수량을 관리하는
const count = ref(1)

const dialog = ref(false);

// 게시글 번호를 서버로 보내서 해당 게시물의 데이터들만 가져오자
// 게시글 업데이트도 put을 사용해서 해버리자
const getData = async () => {
  const res = await axios.post(`/api/menuDetail?menuNo=${dataObj.menuNo}`)
  menu.value = res.data[0]
}

onMounted(() => {
  getData()
})
// function addToCart(){
//   router.push({
//     name: 'Cart',
//     state: { cartInfo: {
//         menuNo: menu.value.menuNo,
//         count: count.value // 현재 선택한 수량
//       }} //유저번호, 상품번호
//   })
// }
const saveCart = async () => {
  // 장바구니에 담을 데이터 준비
  const cartData = {
    menuValue: menu.value,
    menuCount: count.value // 현재 선택한 수량
  };
  try {
    // 서버에 장바구니 데이터 전송
    await axios.post('/api/cart', cartData);
    // 장바구니 페이지로 이동하거나 다른 행동을 취함
  } catch (error) {
    console.error('장바구니 담기 실패:', error);
    alert('장바구니에 담는 중 문제가 발생했습니다.');
  }
}
const addToCart = () => {
  router.push({
    name: 'Cart', // 장바구니 페이지의 라우터 이름
    state: {
      cartInfo: {
        menuNo: menu.value.menuNo, // 현재 페이지에서 선택한 메뉴 번호
        count: count.value, // 현재 선택한 수량
        userNo:user.userNo
      }
    }
  });
}

function buy() {
  router.push({
    name: 'BuyPage',
    state: { buyState:
        { menuNo: menu.value.menuNo,
          menuCount: count.value
        } } //유저번호, 상품번호 , 로그인 했을때만 구매 및 장바구니 가능.
  })
}


</script>