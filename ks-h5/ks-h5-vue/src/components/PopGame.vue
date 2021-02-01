<!-- swiper -->
<template>
  <div class="popgame" style="height: 100%">

    <div class="pop-outer">
      <div class="pop-inner">
        <div class="pop-item">
          <div class="item-main first-info">
            <div class="item-top">
              12月13日
            </div>
            <div class="item-mid">
              热门比赛
            </div>
            <div class="item-bottom">
              28场
            </div>
          </div>
        </div>
        <div v-for="(item, index) in popgames" class="pop-item">
          <div class="item-main item-li">
            <div class="item-top main-child">
              <div class="item-cat">{{item.cat}}</div>
              <div class="item-ses">{{item.ses}}</div>
            </div>
            <div class="item-first main-child">
              <div class="team-icon"><img src="@/assets/index/langdui.jpg"></div>
              <div class="team-info">狼队</div>
              <div class="team-score">{{index}}</div>
            </div>
            <div class="item-first main-child">
              <div class="team-icon"><img src="@/assets/index/manlian.jpg"></div>
              <div class="team-info">曼联</div>
              <div class="team-score">{{index}}</div>
            </div>
          </div>
        </div>
        <div class="pop-item pop-last">
          <div class="last-main">
              查看更多
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style lang="scss" scoped>
  .popgame {
    display: flex;
    white-space: nowrap;

    .hot-info {
      width: 105.432px;
    }

    .pop-outer {
      overflow: hidden;
      display: flex;
      white-space: nowrap;

      .pop-inner {
        height: 120%;
        overflow-x: auto;
        overflow-y: hidden;
        display: flex;
        white-space: nowrap;
        margin-left: 1px;
        .pop-last {
          text-align: center;
          background-color: #f5f5f5;
          border: 0.05333rem solid #f5f5f5;
          height: 2.74667rem;
          .last-main {
            width: 26.5px;
            line-height: 21.5px;
            font-size: 16px;
            white-space: normal !important;
            margin-top: 9.3px;

          }
        }

        .pop-item {
          margin-right: 0.34667rem;

          &:last-child {
            margin-right: 0px !important;
          }

          .first-info {
            width: 72px !important;
            height: 105px;
            text-align: center;
            font-size: 16px;

            .item-top {
              height: 29.5px;
              border-bottom: 1px solid #f5f5f5;
              line-height: 29.5px;
              background-color: #f5f5f5;
            }

            .item-mid {
              height: 44px;
              border-top: 1px solid #f5f5f5;
              border-bottom: 1px solid #f5f5f5;
              line-height: 44px;
              font-weight: bold;
            }

            .item-bottom {
              height: 29.5px;
              line-height: 29.5px;
            }
          }

          .item-li {
            .main-child {
              display: flex;
              white-space: nowrap;

              img {
                width: 35px;
                height: 30px;
                margin-top: 4.3px;
              }
            }

            .item-first {
              height: 36.5px;
              padding-left: 14.5px;
              padding-right: 14.5px;

              .team-icon {
                width: 25%;
                height: 100%;
                font-size: 14px;
                line-height: 36.5px;
              }

              .team-info {
                width: 25%;
                height: 100%;
                font-size: 16.5px;
                line-height: 36.5px;
                margin-left: 5px;
              }

              .team-score {
                height: 100%;
                width: 50%;
                color: #f44336;
                font-size: 15px;
                font-weight: bold;
                text-align: right;
                line-height: 36.5px;
              }
            }

            .item-two {
              height: 36.5px;
            }

            .item-top {
              height: 29.5px;
              line-height: 29.5px;
              background-color: #ffffff;
              padding-left: 13.5px;
              padding-right: 7.5px;

              .item-cat {
                font-size: 16px;
                color: #9ca1a8;
                width: 50%;
              }

              .item-ses {
                text-align: right;
                width: 50%;
                font-size: 13px;
                color: #f44336;
              }

            }

            .item-mid {
              height: 44px;
              line-height: 44px;
            }

            .item-bottom {
              height: 29.5px;
              line-height: 29.5px;
            }
          }

          .item-main {
            width: 180px;
            height: 103px;
            border: 2px solid #f5f5f5;

          }
        }

      }
    }
  }


</style>
<script>
  export default {
    name: 'PopGame',

    data() {
      return {
        popgames: []
      }
    },
    computed: {},
    mounted() {
      this.initgames();
    },
    methods: {
      initgames() {
        let $game = this.popgames;
        let obj0 = {};
        obj0.cat = "英超第一轮";
        obj0.ses = "明天 03:15";
        $game.push(obj0);
        let obj1 = {};
        obj1.cat = "英超第二轮";
        obj1.ses = "今天 06:15";
        $game.push(obj1);
        let obj2 = {};
        obj2.cat = "英超第三轮";
        obj2.ses = "明天 20:15";
        $game.push(obj2);
        let obj3 = {};
        obj3.cat = "英超第四轮";
        obj3.ses = "明天 22:15";
        $game.push(obj3);
      }
    }
  }
</script>
