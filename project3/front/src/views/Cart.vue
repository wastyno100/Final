<template>
  <v-main>
  <v-container>
    <v-row>
      <v-col cols="12">
        <h1>장바구니</h1>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" md="8">
        <!-- 장바구니 아이템 리스트 -->
      </v-col>
      <v-col cols="12" md="4">
        <!-- 결제 정보 요약 -->
      </v-col>
    </v-row>
  </v-container>
  <v-list>
    <v-row>
    <v-list-item v-for="item in showItem"
                 v-bind:key="item">
      <v-list-item-content>
        <v-list-item-title>{{ item.menuTitle }}</v-list-item-title>
        <v-list-item-subtitle>단가:{{ item.menuPrice }}원</v-list-item-subtitle>
        <v-list-item-subtitle>총개수:{{ item.menuCount }}개</v-list-item-subtitle>
        <v-list-item-subtitle>총금액:{{ item.menuPrice * item.menuCount }}원</v-list-item-subtitle>
      </v-list-item-content>
      <v-list-item-action>
        <v-btn icon @click="removeFromCart(item)">
          <v-icon>mdi-delete</v-icon>
        </v-btn>
      </v-list-item-action>
    </v-list-item>
    </v-row>
    <v-pagination v-model="currentPage" :length="allPage" @input="pageUpdate" />
  </v-list>
  <v-card>
    <v-card-title>결제 요약</v-card-title>
    <v-card-text>
      <div>총 금액: {{ totalPrice }}원</div>
      <v-btn color="primary">결제하기</v-btn>
    </v-card-text>
  </v-card>
  </v-main>
</template>
<script setup>
import axios from 'axios';
import { computed, onMounted, ref, watch } from 'vue'
import { useRouter } from 'vue-router'
const menu = ref([]);
const router = useRouter();

const getData = async () => {
  try {
    console.log(sessionStorage.userNo);
    const res = await axios.get(`/api/cart?userNo=${sessionStorage.userNo}`);
    console.log(res.data);
    menu.value = res.data;
  } catch (error) {
    console.error(error);
    alert("장바구니에 담기 위해서는 로그인을 해야합니다.로그인창으로 이동합니다.")
    router.push('/login')
  }
}

//페이지네이션
const item = ref('');
const currentPage = ref(1);
const pageGroup = 5;
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