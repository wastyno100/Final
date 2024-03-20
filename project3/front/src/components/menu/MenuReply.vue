<script setup>

</script>

<template>
  <v-container>
    <v-row>
      <v-col>
        <v-card>
          <v-card-title>리뷰쓰기</v-card-title>
          <v-card-text>
            <v-textarea
              v-model="newComment"
              label="리뷰를 입력하세요"
              outlined
            ></v-textarea>
          </v-card-text>
          <v-card-actions>
            <v-btn @click="addComment" color="primary">리뷰쓰기</v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
<!--    <v-row>-->
<!--      <v-col cols="12" sm="6" v-for="comment in comments" :key="comment.id">-->
<!--        <v-card>-->
<!--          <v-card-title>{{ comment.text }}</v-card-title>-->
<!--          <v-card-actions>-->
<!--            <v-btn icon @click="deleteComment(comment.id)">-->
<!--              <v-icon>mdi-delete</v-icon>-->
<!--            </v-btn>-->
<!--          </v-card-actions>-->
<!--        </v-card>-->
<!--      </v-col>-->
<!--    </v-row>-->
  </v-container>
</template>

<style scoped>

</style>