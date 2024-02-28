<script setup>
function 디테일메뉴로() {
  location.href = "/MenuDetail";
}
</script>

<template>
  <v-main>
    <h1>메뉴라구요</h1>
    <v-pagination :length="4"></v-pagination>

    <v-img
        :width="400"
        aspect-ratio="16/9"
        cover
        src="https://cdn.vuetifyjs.com/images/parallax/material.jpg"
        @click="디테일메뉴로()"
    ></v-img>

    <v-date-picker></v-date-picker>
  </v-main>
</template>

<style scoped>

</style>