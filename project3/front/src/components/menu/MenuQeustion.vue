<script setup>

</script>

<template>
  <v-container>
    <v-row>
      <v-col>
        <v-card>
          <v-card-title>문의사항</v-card-title>
          <v-card-text>
            <v-textarea
              v-model="newQestiom"
              label="내용을 입력하세요"
              outlined
            ></v-textarea>
          </v-card-text>
          <v-card-actions>
            <v-btn @click="newQestiom" color="primary">보내기</v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>

</style>