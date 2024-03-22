

<template>
  <v-model>
  <Carousel :autoplay="7000" :wrap-around="true" style="margin-top: 100px;">
    <Slide v-for="slide in slides" :key="slide">
      <div class="carousel__item">
        <img class="slideImg" :src="slide" />
      </div>
    </Slide>
    <template #addons>
      <Pagination />
    </template>
  </Carousel>
    <h2> 현재 시세</h2>
    
    <v-row>
      <v-col cols="12">
      <v-autocomplete
        class="mx-auto"
        density="comfortable"
        placeholder="검색어를 입력하세요"
        prepend-inner-icon="mdi-magnify"
        style="max-width: 350px"
        theme="light"
        variant="solo"
      ></v-autocomplete>
    </v-col>
  </v-row>
  
  <v-tabs v-model="quote" bg-color="primary">
      <v-tab value="on">생선</v-tab>
      <v-tab value="tw">게/새우류</v-tab>
      <v-tab value="th">조개/전복류</v-tab>
      <v-tab value="fo">멍게/해삼류</v-tab>
      <v-tab value="fi">낙지/문어류</v-tab>
      <v-tab value="si">김/미력류</v-tab>
    </v-tabs>
    <v-card-text>
      <v-window v-model="quote">
        <v-window-item value="on">
          <fish />
        </v-window-item>
        <v-window-item value="tw">
          <crab />
        </v-window-item>
        <v-window-item value="th">
          <clam />
        </v-window-item>
        <v-window-item value="fo">
          <squit/>
        </v-window-item>
        <v-window-item value="fi">
          <Octopus />
        </v-window-item>
        <v-window-item value="si">
          <seaweed />
        </v-window-item>
      </v-window>
    </v-card-text>
</v-model>
</template>
<script>
import MainItem from '../components/main/MainItem.vue'
import { defineComponent } from 'vue'
import { Carousel, Pagination, Slide } from 'vue3-carousel'
import 'vue3-carousel/dist/carousel.css'
import home_1 from "../assets/images/1.jpg";
import home_2 from "../assets/images/1.jpg";
import home_3 from "../assets/images/1.jpg";
import axios from "axios";
export default defineComponent({
  name: 'Autoplay',
  components: {
    Carousel,
    Slide,
    Pagination,
  },data() {
		return {
			slides: [home_1, home_2, home_3],
		};
	},
})
const getData = async () => {
  const res = await axios.get('/api/')
  testData.value = res.data;
  }
</script>
<style>

.carousel__item {
	color: white;
	font-size: 20px;
	border-radius: 8px;
	display: flex;
	justify-content: center;
	align-items: center;
}
.categoli_img{
  width: 100%; 
}
.carousel__slide {
	padding: 10px;
}

.carousel__prev,
.carousel__next {
	box-sizing: content-box;
	border: 5px solid white;
}
.wrapper {
	margin-top: 3vh;
}
</style>