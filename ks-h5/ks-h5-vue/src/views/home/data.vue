<!-- home -->
<template>
  <van-row>
    <van-col span="2">
      <van-icon name="search" size="25"/>
    </van-col>
    <van-col span="20">
      <van-tabs>
        <van-tab v-for="index in tabs" :title="'标签 ' + index" :name="'name ' +index">
          内容 {{ index }}
        </van-tab>
      </van-tabs>
    </van-col>
    <van-col span="2">
      <van-icon name="ellipsis" size="25" />
    </van-col>
  </van-row>



</template>
<style type="text/css">
  .tabs-header{

  }
</style>
<script>
  export default {
    data() {
      return {
        activeName: '2',
        tabs:[],
      }
    },

    computed: {},

    mounted() {
      this.getData();
    },


    methods: {
        getData(){
          for (let i = 0; i < 10; i++) {
            this.tabs.push(i);
          }
        }
    }
  }
</script>
