<template>
  <div>
    <van-row>
      <van-col span="2">
        <div class="search-con">
          <van-icon name="search" size="23"/>
        </div>
      </van-col>
      <van-col span="20">
        <div class="outer">
          <div class="inner">
            <div v-for="(item, index) in tabs" :key="index" class="tabberk">
              <div :class="{ active: active === index }" class="tabitem" @click="switchTab(index)">
                <router-link :to="{name:item.lnk}">{{ item.name }}</router-link>
              </div>
            </div>
          </div>
        </div>
      </van-col>
      <van-col span="2">
        <div class="elli-con">
          <van-icon name="ellipsis" size="25"/>
        </div>
      </van-col>
    </van-row>

    <div class="content-con" ref="cont">
      <div class="contentx " ref="cont0" :style="{display: disclea}">
        <router-view></router-view>
      </div>
      <!--<div class="contentx" ref="cont1" :style="{display: disnone}">
        <router-view></router-view>
      </div>
      <div class="contentx" ref="cont2" :style="{display: disnone}">
      </div>
      <div class="contentx" ref="cont3" :style="{display: disnone}">
        西甲
      </div>
      <div class="contentx " ref="cont4" :style="{display: disnone}">
        法甲
      </div>-->
    </div>
  </div>
</template>

<script>
  export default {
    name: 'ActiveItem',
    data() {
      return {
        active: 0,
        disnone: 'none',
        disclea: '',
        tabs: [],
      }
    },
    computed: {},
    mounted() {
      this.initTabs();
    },
    methods: {
      initTabs() {
        let $tab = this.tabs;
        let obj0 = {};
        obj0.lnk = "recomer";
        obj0.name = "推荐";
        $tab.push(obj0);
        let obj1 = {};
        obj1.lnk = "nba";
        obj1.name = "NBA";
        $tab.push(obj1);
        let obj2 = {};
        obj2.lnk = "premier";
        obj2.name = "英超";
        $tab.push(obj2);
        let obj3 = {};
        obj3.lnk = "cba";
        obj3.name = "CBA";
        $tab.push(obj3);

      },
      switchTab(index) {
        this.active = index;
        /*try {
          let tabCon = this.$refs["cont" + index];
          if (tabCon) {
            tabCon.style.display = this.disclea;
          }
          for (let i = 0; i < this.tabs.length; i++) {
            if (index !== i) {
              let tabi = this.$refs["cont" + i];
              if (tabi) {
                tabi.style.display = this.disnone;
              }
            }
          }
        } catch (error) {
          console.log(error);
        }*/
      }
    }
  }
</script>

<style lang="scss">

  .search-con {
    position: relative;
    top: 12px;
    left: 10px;
  }

  .elli-con {
    position: relative;
    top: 11px;
    right: 5.5px;
  }

  .outer {
    height: 1.17333rem;
    overflow: hidden;
  }

  .inner {
    height: 120%;
    overflow-x: auto;
    overflow-y: hidden;
    white-space: nowrap;

    .tabberk {
      display: inline-block;
      width: 1.3665555rem;
      height: 100%;
      margin-right: 10px;

      &:last-of-type {
        margin-right: 0;
      }

      .tabitem {
        text-align: center;
        height: 1.17333rem;
        line-height: 1.17333rem;
        font-size: 14px;
        font-weight: normal;

        a {
          color: #333;
        }
      }

      .active {
        font-size: 16px;
        color: #333;
        font-weight: bold;

        &::after {
          display: block;
          content: "";
          width: 25px;
          position: relative;
          top: -10px;
          left: 0.35633rem;
          height: 2px;
          background: rgb(54, 120, 255);
          border-radius: 3px;
        }
      }
    }
  }

  .content-con {
    height: 15.255555rem;
    overflow-y: auto;
    background: #39a9ed;
  }
</style>
