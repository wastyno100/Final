<script setup>

</script>

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
    <v-list-item v-for="item in cartItems" :key="item.id">
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

<style scoped>

</style>