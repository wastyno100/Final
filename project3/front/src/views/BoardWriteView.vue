<script setup>
</script>

<template>
  <v-main>
    <v-container>
      <v-card height="500" elevation="3" class="mt-4">
        <v-sheet class="mx-auto" width="1000">
          <v-form>
            <v-row justify="center">
              <v-col cols="8" class="ma-3">
                <v-text-field label="제목" />
              </v-col>
              <v-col cols="2" class="ma-3">
                <v-text-field label="작성일" />
              </v-col>
            </v-row>
            <v-row justify="center">
              <v-col cols="11" class="ma-3">
                <v-textarea v-model="title" label="내용" counter single-line></v-textarea>
              </v-col>
            </v-row>
            <v-file-input
              label="File input"
              prepend-icon="mdi-camera"
              variant="filled"
            ></v-file-input>
            <v-btn type="submit" text="작성"/>
          </v-form>
        </v-sheet>
      </v-card>
    </v-container>
  </v-main>
</template>

<style scoped>
</style>
