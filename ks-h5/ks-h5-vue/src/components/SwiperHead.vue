<!-- swiper -->
<template>
  <div class="swipperhead" style="height: 100%">
    <swiper ref="mySwiper" :options="swiperOptions">
      <swiper-slide>Slide 1</swiper-slide>
      <swiper-slide>Slide 2</swiper-slide>
      <div class="swiper-pagination" slot="pagination"></div>
    </swiper>
  </div>
</template>
<style lang="scss" scoped>
  .swipperhead{
    .swiper-container {
      width: 94.5%;
      height: 100%;
      border-radius: 0.1rem;
      .swiper-slide {
        text-align: center;
        font-size: 18px;
        background: #fff;
        width: 80%;
        /* Center slide text vertically */
        display: -webkit-box;
        display: -ms-flexbox;
        display: -webkit-flex;
        display: flex;
        -webkit-box-pack: center;
        -ms-flex-pack: center;
        -webkit-justify-content: center;
        justify-content: center;
        -webkit-box-align: center;
        -ms-flex-align: center;
        -webkit-align-items: center;
        align-items: center;
        .swiper-slide:nth-child(2n) {
          width: 60%;
        }

        .swiper-slide:nth-child(3n) {
          width: 40%;
        }
      }
    }
  }






</style>
<script>
  export default {
    name: 'SwiperHead',
    data() {
      return {
        swiperOptions: {
          pagination: {
            el: '.swiper-pagination'
          },
        }
      }
    },
    computed: {
      swiper() {
        return this.$refs.mySwiper.$swiper
      }
    },
    mounted() {
      console.log('Current Swiper instance object', this.swiper);
      this.swiper.slideTo(0, 1000, false)
    }
  }
</script>
