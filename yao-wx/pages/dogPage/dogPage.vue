<template>
  <view class="container" style="background-color: #EAE2D5;">

    <scroll-view class="category-scroll" scroll-x="true" show-scrollbar="false">
      <view class="category-list">
        <view 
          v-for="(item, index) in categories" 
          :key="index" 
          class="category-item"
          :class="{ active: activeCategory === index }"
          @click="changeCategory(index)"
        >
          {{ item }}
        </view>
      </view>
    </scroll-view>

 
    <view class="dog-card">
      <view class="dog-title">忠诚之狗</view>
      <view class="dog-desc">属狗之人性格单纯、善良忠诚，仗义行事的风格深受他人信赖。</view>
      
 
      <view class="info-box-container">
        <view class="info-box">属相生辰:土</view>
        <view class="info-box">明珠出海格</view>
      </view>
      

      <image class="dog-image" src="/static/可爱小黄狗.png" mode="widthFix"></image>
    </view>


    <view class="harmony-container">
      <view class="harmony-box">
        <view class="harmony-title" style="color: #90AFBA;">和谐</view>
        <view class="circle-container">
          <view class="circle-box" v-for="(item, index) in harmonyItems" :key="'h'+index">
            {{ item }}
          </view>
        </view>
      </view>
      
      <view class="harmony-box">
        <view class="harmony-title" style="color: #E5B9A4;">磨合</view>
        <view class="circle-container">
          <view class="circle-box" v-for="(item, index) in frictionItems" :key="'f'+index">
            {{ item }}
          </view>
        </view>
      </view>
    </view>


    <view class="disclaimer">
      当前内容为免费内容，仅供您在娱乐中探索自我，不等于专业测评，不代表价值评判，无任何现实指导意义。
    </view>

  
    <view class="advantage-box">
      <view class="advantage-header">
        <image class="advantage-icon" src="/static/优点.png" mode="aspectFill"></image>
        <view class="advantage-title">优点</view>
      </view>
      <view class="advantage-content">
        属狗的人通常忠诚可靠，富有正义感，为人正直坦率。他们工作勤恳踏实，责任感强，对待朋友和家人极为忠诚。具有敏锐的直觉和判断力，在团队中往往是值得信赖的核心成员。
      </view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      activeCategory: 0,
      categories: ['生辰', '星宿', '紫微', '政余', '生肖', '八字', '星座'],
      harmonyItems: ['虎', '兔', '马'],
      frictionItems: ['龙', '鸡', '牛', '羊']
    }
  },
  methods: {
    changeCategory(index) {
      this.activeCategory = index;
    }
  }
}
</script>

<style>

.container {
  min-height: 100vh;
  padding: 20rpx;
  box-sizing: border-box;
  font-family: 'PingFang SC', sans-serif;
}


.category-scroll {
  white-space: nowrap;
  width: 100%;
  margin-bottom: 30rpx;
}

.category-list {
  display: inline-flex;
}

.category-item {
  display: inline-block;
  padding: 20rpx 30rpx;
  font-size: 32rpx;
  color: #9C8B65;
  position: relative;
}

.category-item.active {
  color: #9E7850;
  font-weight: bold;
}

.category-item.active::after {
  content: '';
  position: absolute;
  bottom: 10rpx;
  left: 50%;
  transform: translateX(-50%);
  width: 60%;
  height: 4rpx;
  background-color: #9E7850;
  border-radius: 2rpx;
}


.dog-card {
  background-color: #FFFFFF;
  border-radius: 16rpx;
  padding: 30rpx;
  margin-bottom: 30rpx;
  box-shadow: 0 4rpx 12rpx rgba(0,0,0,0.05);
}

.dog-title {
  font-size: 40rpx;
  font-weight: bold;
  color: #000000;
  margin-bottom: 16rpx;
  text-align: center;
}

.dog-desc {
  font-size: 28rpx;
  color: #8F8B86;
  margin-bottom: 30rpx;
  line-height: 1.6;
}

.info-box-container {
  display: flex;
  justify-content: space-between;
  margin-bottom: 30rpx;
}

.info-box {
  background-color: #9E794F;
  color: #FFFFFF;
  border-radius: 8rpx;
  padding: 16rpx 24rpx;
  font-size: 28rpx;
  width: 48%;
  text-align: center;
}

.dog-image {
  width: 100%;
  border-radius: 12rpx;
  margin-top: 20rpx;
}


.harmony-container {
  display: flex;
  justify-content: space-between;
  margin-bottom: 30rpx;
}

.harmony-box {
  background-color: #F7F4ED;
  border-radius: 12rpx;
  padding: 24rpx;
  width: 48%;
}

.harmony-title {
  font-size: 32rpx;
  font-weight: bold;
  margin-bottom: 20rpx;
  text-align: center;
}

.circle-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.circle-box {
  background-color: #FEFEFD;
  color: #A38748;
  border-radius: 50%;
  width: 80rpx;
  height: 80rpx;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 10rpx;
  font-size: 28rpx;
  box-shadow: 0 2rpx 8rpx rgba(0,0,0,0.1);
}


.disclaimer {
  background-color: #FFFFFF;
  border-radius: 12rpx;
  padding: 24rpx;
  font-size: 24rpx;
  color: #999999;
  line-height: 1.6;
  text-align: center;
  margin-bottom: 30rpx;
}


.advantage-box {
  background-color: #FFFFFF;
  border-radius: 16rpx;
  padding: 30rpx;
  box-shadow: 0 4rpx 12rpx rgba(0,0,0,0.05);
}

.advantage-header {
  display: flex;
  align-items: center;
  margin-bottom: 20rpx;
}

.advantage-icon {
  width: 60rpx;
  height: 60rpx;
  border-radius: 50%;
  margin-right: 20rpx;
}

.advantage-title {
  font-size: 36rpx;
  font-weight: bold;
  color: #000000;
}

.advantage-content {
  font-size: 28rpx;
  color: #666666;
  line-height: 1.6;
  padding-left: 80rpx; 
}
</style>