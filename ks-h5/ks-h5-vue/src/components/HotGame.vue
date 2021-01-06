<!-- swiper -->
<template>
  <div class="hotgame" style="height: 100%">
    <div style="background-color: aquamarine" class="hot-info">
      固定内容
    </div>
    <div class="hot-swiper">
      <swiper ref="hotSwiper" :options="swiperOptions">
        <swiper-slide>Slide 1</swiper-slide>
        <swiper-slide>Slide 2</swiper-slide>
        <swiper-slide>Slide 3</swiper-slide>
        <swiper-slide>Slide 4</swiper-slide>
        <swiper-slide>Slide 5</swiper-slide>
        <div class="swiper-pagination" slot="pagination"></div>
      </swiper>
    </div>
  </div>
</template>
<style lang="scss" scoped>
  .hotgame {
    white-space: nowrap;
    display: flex;

    .hot-info {
      width: 105.432px;

    }

    .hot-swiper {
      .swiper-container {
        width: 100%;
        height: 95.5px;

        .swiper-slide {
          text-align: center;
          font-size: 18px;
          background: #fff;
          width: 100px !important;
          margin-right: 10px !important;

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

          &:nth-child(2n) {
            width: 60% !important;
          }

          &:nth-child(3n) {
            width: 40% !important;
          }
        }
      }

    }


  }
</style>
<script>
  export default {
    name: 'HotGame',
    data() {
      return {
        swiperOptions: {
          pagination: {
            el: '.swiper-pagination'
          },
          slidesPerView: 3,
          spaceBetween: 30,
          slidesWidth: 100
        }
      }
    },
    computed: {
      swiper() {
        return this.$refs.hotSwiper.$swiper
      }
    },
    mounted() {
      console.log('Current Swiper instance object', this.swiper);
      this.swiper.slideTo(0, 1000, false)
    }
  }
</script>
