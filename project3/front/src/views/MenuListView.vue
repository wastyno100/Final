<style>
</style>
<template>
  <v-main>
    <H2>메뉴리스트</H2>
    <v-col cols="12">
      <v-autocomplete
        :items="items"
        class="mx-auto"
        density="comfortable"
        menu-icon=""
        placeholder="검색어를 입력하세요"
        prepend-inner-icon="mdi-magnify"
        style="max-width: 350px"
        theme="light"
        variant="solo"
        auto-select-first
        item-props
        rounded
        @input="searchItems"
      ></v-autocomplete>
    </v-col>
    <v-row class="testk">
      <v-col cols="12">
        <v-infinite-scroll :items="menu" :onLoad="menu > null ? load : null">
          <v-row>
            <v-col cols="12" md="3" v-for="item in menu" :key="item.menuNo">
              <v-card class="mx-auto mt-3 card" width="180px" height="250px" @click="goToPage(item.menuNo)">
                <v-img
                  weight="200px"
                  height="150px"
                  src=""
                  cover
                ></v-img>

                <!-- 데이터 바인딩을 item 객체의 속성으로 변경 -->
                <v-card-title> {{ item.menuTitle }} </v-card-title>
                <v-card-subtitle>{{ item.menuPrice }}원</v-card-subtitle>
                <v-card-subtitle>{{ item.menuContent }}</v-card-subtitle>

              </v-card>
            </v-col>
          </v-row>
        </v-infinite-scroll>
      </v-col>
    </v-row>
    <v-row ref="observer"></v-row>
  </v-main>

</template>

<script setup>
import { onMounted, ref } from 'vue'
import axios from 'axios';

//menudata
const menu = ref({

});

// 비동기 API 함수
const getData = async () => {
  console.log("탓냐!!!")
  const res = await axios.get('/api/menuList')
  menu.value = res.data;
  }


onMounted(()=>{
  getData();
});

//탭


</script>