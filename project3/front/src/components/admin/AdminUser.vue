<script setup>
</script>

<template>
      <v-card-title class="text-center">
        <h3>유저 관리</h3>
      </v-card-title>

      <v-card-text>
        <v-text-field
          :loading="loading"
          density="dense"
          variant="outlined"
          label="검색"
          append-icon="mdi-magnify"
          hide-details
        ></v-text-field>
      </v-card-text>

      <v-card height="500" elevation="3" class="mt-4">
        <v-card-item>dd</v-card-item>
      </v-card>
      <v-pagination :length="4"></v-pagination>
      <v-btn class="mt-4" block>버튼</v-btn>
</template>