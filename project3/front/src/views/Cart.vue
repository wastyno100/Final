<template>
  <v-main>
    <v-container>
      <!-- 장바구니 제목 -->
      <v-row justify="center" class="mb-5">
        <v-col cols="12">
          <h1 class="display-2 text-center">장바구니</h1>
        </v-col>
      </v-row>

      <!-- 장바구니 아이템 리스트와 결제 정보 요약 -->
      <v-row justify="center" align="center">
        <!-- 장바구니 아이템 리스트 -->
        <v-col cols="12" md="6">
          <v-card class="elevation-12" height="550">
            <v-list dense>
              <v-list-item v-for="item in showItem" :key="item.id" class="mb-3" style="border-bottom: 2px solid lightgray;">
                <!-- 장바구니 아이템 이미지와 정보 -->
                <v-row align="center">
                  <v-col cols="auto">
                    <v-list-item-avatar>
                      <v-img :src="item.menuImg" style="width: 100px; height: 100px" aspect-ratio="1" cover></v-img>
                    </v-list-item-avatar>
                  </v-col>
                  <v-col style="color: black;">

                      <v-list-item-title class="mb-4"><strong>{{ item.menuTitle }}</strong></v-list-item-title>
                      <p style="font-size: 14px;"><strong>단가: </strong>{{ item.menuPrice }}원</p>
                      <p style="font-size: 14px;"><strong>개수: </strong>{{ item.menuCount }}개</p>
                      <p style="font-size: 14px;"><strong>금액: </strong>{{ item.menuPrice * item.menuCount }}원</p>

                  </v-col>
                </v-row>

                <v-list-item-action class="mt-3 mb-3">
                  <v-btn small color="primary" @click="plusCart(item)" class="mr-3">
                    <v-icon>mdi-plus</v-icon>
                  </v-btn>

                  <v-btn small color="primary" @click="minusCart(item)" class="mr-3">
                    <v-icon>mdi-minus</v-icon>
                  </v-btn>
                  
                  <v-btn small color="error" @click="deleteCart(item)" class="mr-3">
                    <v-icon>mdi-delete</v-icon>
                  </v-btn>
                </v-list-item-action>
              </v-list-item>
            </v-list>
          </v-card>
        </v-col>
        
        <!-- 결제 정보 요약 -->
        <v-col cols="12" md="3" class="text-center" align="center">
          <v-card class="elevation-12 primary lighten-2">
            <v-card-title class="headline white--text" style="font-weight: bold; font-size: 22px;">결제 요약</v-card-title>
                <div v-if="showItem.length > 0" style="display: flex; justify-content: center;">
                  <v-img :src="menu[0].menuImg" style="width: 250px; height: 250px" aspect-ratio="1" cover></v-img>
                </div>

            <v-card-text class="text-center" style="font-size: 18px;">
              <div v-if="showItem.length > 0">{{ `${menu[0].menuTitle} 외 ${menu.length - 1}건` }}</div><br><br><br><br>
              <div class="display-2 font-weight-bold">총 금액: {{ totalPrice }}원</div>
              <v-btn large block color="primary" class="mt-5" @click="goBuy">결제하기</v-btn>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>

      <!-- 페이지네이션 -->
      <v-row justify="center">
        <v-col cols="12">
          <v-pagination v-model="currentPage" :length="allPage" @input="pageUpdate" />
        </v-col>
      </v-row>
    </v-container>
  </v-main>
</template>
<script setup>
import axios from 'axios';
import { computed, onMounted, ref, watch } from 'vue'
import { useRouter } from 'vue-router'
const menu = ref([]);
const router = useRouter();

const totalPrice = ref(0)

const goBuy = () => {
  sessionStorage.setItem("menu", JSON.stringify(menu.value))
  sessionStorage.setItem("totalPrice", JSON.stringify(totalPrice.value))
  router.push("/BuyPage")
}

const plusCart = async (item) => {
  await axios.put("/api/plusCart", { menuCount: item.menuCount, cartId: item.cartId })
  .then(() => { 
    getData() 
  })
}

const minusCart = async (item) => {
  console.log(item)
  await axios.put("/api/minusCart", { menuCount: item.menuCount, cartId: item.cartId })
  .then(() => { 
    getData() 
  })
}

const deleteCart = async (item) => {
  await axios.delete(`/api/deleteCart?cartId=${item.cartId}`)
  .then(() => { 
    getData() 
  })
}

const updateTotalPrice = () => {
  totalPrice.value = 0;
  menu.value.forEach((item) => {
    totalPrice.value += item.menuPrice * item.menuCount;
  });
}

const getData = async () => {
  try {
    console.log(sessionStorage.userNo);
    const res = await axios.get(`/api/cart?userNo=${sessionStorage.userNo}`);
    console.log(res.data);
    menu.value = res.data;

    menu.value.forEach( async (item) => {
      console.log("sadasdsadsadsad",item)
    item.menuImg = JSON.parse(item.menuImg)

    await axios.get(`/api/getMImage/${item.menuImg}`)
      .then((res) => {

        item.menuImg = res.data

      });
  });

    updateTotalPrice();
  } catch (error) {
    console.error(error);
    alert("장바구니에 담기 위해서는 로그인을 해야합니다.로그인창으로 이동합니다.")
    router.push('/login')
  }
}

//페이지네이션
const currentPage = ref(1);
const pageGroup = 3;
const allPage = computed(() => {
  return Math.ceil(menu.value.length / pageGroup)
});

const showItem = computed(() => {
  const start = (currentPage.value - 1) * pageGroup
  const end = start + pageGroup
  return menu.value.slice(start, end)

})

const pageUpdate = () => {
  const start = (currentPage.value - 1) * pageGroup
  const end = start + pageGroup
  showItem.value = menu.value.slice(start, end)
}

watch(currentPage, () => {
  sessionStorage.setItem('cart', currentPage.value)
})

//자동계산
onMounted(() => {
  getData();
  currentPage.value = JSON.parse(sessionStorage.getItem('cart')) || 1
});


</script>
<style scoped>

</style>