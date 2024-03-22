<template>
    <div>
      <v-autocomplete 
        :items="items"
        class="mx-auto"
        density="comfortable"
        menu-icon=""
        placeholder="검색어를 입력하세요"
        prepend-inner-icon="mdi-magnify"
        style="max-width: 350px; margin-top: 80px;"
        theme="light"
        variant="solo"
        auto-select-first
        item-props
        rounded
        @input="searchItems"
      ></v-autocomplete>
  
      <v-tabs v-model="selectedTab" background-color="primary" dark>
        <v-tab v-for="(category, index) in categories" :key="index" :value="category.value">{{ category.label }}</v-tab>
      </v-tabs>
  
      <v-contain>
        <v-window v-model="selectedTab">
          <v-window-item v-for="(category, index) in categories" :key="index" :value="category.value">
            <component :is="category.component" />
          </v-window-item>
        </v-window>
      </v-contain>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        selectedTab: null,
        items: [], // 데이터 아이템
        categories: [ // 카테고리 목록
          { label: '생선', value: 'fish', component: 'fish' },
          { label: '게/새우류', value: 'crab', component: 'crab' },
          { label: '조개/전복류', value: 'clam', component: 'clam' },
          { label: '멍게/해삼류', value: 'squit', component: 'squit' },
          { label: '낙지/문어류', value: 'octopus', component: 'octopus' },
          { label: '김/미력류', value: 'seaweed', component: 'seaweed' }
        ]
      };
    },
    methods: {
      searchItems() {
        
      }
    }
  };
  </script>