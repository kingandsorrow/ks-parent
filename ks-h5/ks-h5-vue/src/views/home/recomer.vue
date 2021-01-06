<!-- 推荐页面 -->
<template>
  <div>
    <van-pull-refresh v-model="refreshing" @refresh="onRefresh" :success-text="refreshText">
      <van-list v-model="loading" :finished="finished" finished-text="没有更多了">
        <div style="height: 4.5rem;background-color: burlywood">
          <SwiperHead></SwiperHead>
        </div>
        <div
          style="margin-top:0.2415rem;height: 2.986666666666667rem;background-color: #ffffff; padding-left: 0.2415rem; padding-right: 0.31400rem;padding-top: 5px">
          <PopGame></PopGame>
        </div>
      </van-list>
    </van-pull-refresh>
  </div>
</template>

<style type="text/css">

</style>
<script>
  import SwiperHead from '@/components/SwiperHead';
  import PopGame from '@/components/PopGame';

  export default {
    data() {
      return {
        list: [],
        loading: false,
        finished: false,
        refreshing: false,
        refreshText: ''

      };
    },
    components: {
      SwiperHead,
      PopGame
    },
    computed: {},

    mounted() {
    },
    methods: {
      onLoad() {
        setTimeout(() => {
          this.refreshing = false;
          this.loading = false;
          this.finished = true;
          this.refreshText = "刷新成功";
        }, 1000);
      },
      onRefresh() {
        // 清空列表数据
        this.finished = false;

        // 重新加载数据
        // 将 loading 设置为 true，表示处于加载状态
        this.loading = true;
        this.onLoad();

      },
    }
  }
</script>
