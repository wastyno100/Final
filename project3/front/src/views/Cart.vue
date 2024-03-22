<template>
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
    <v-list-item v-for="item in cart" :key="item.cartId">
      <v-list-item-content>
        <v-list-item-title>{{ item.name }}</v-list-item-title>
        <v-list-item-subtitle>{{ item.price }}원</v-list-item-subtitle>
      </v-list-item-content>
      <v-list-item-action>
        <v-btn icon @click="removeFromCart(item)">
          <v-icon>mdi-delete</v-icon>
        </v-btn>
      </v-list-item-action>
    </v-list-item>
  </v-list>
  <v-card>
    <v-card-title>결제 요약</v-card-title>
    <v-card-text>
      <div>총 금액: {{ totalPrice }}원</div>
      <v-btn color="primary">결제하기</v-btn>
    </v-card-text>
  </v-card>
</template>
<script setup>
import cart from '@/views/Cart.vue'

const { cartInfo } = history.state
import axios from 'axios';
import { onMounted, ref } from 'vue'
const menu = ref([]);

const getData = async () => {
  const res = await axios.post(`/api/cart?userNo=${cartInfo.userNo}`)
  console.log(res.data);
  menu.value = res.data[0]
}

onMounted(() => {
  getData();
  console.log(cartInfo);
});

</script>
<style scoped>

</style>