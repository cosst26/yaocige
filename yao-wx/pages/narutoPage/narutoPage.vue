<template>
  <view class="container" style="background-color: #FFEBD3;">
  
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

   
    <view class="day-master">
      <view class="day-master-title" style="text-align: center;">壬辰日出生的自己</view>
      <view class="day-master-desc" style="text-align: center;">日主为壬，属阳水，如江河之水</view>
    </view>


    <view class="wealth-card">
      <view class="wealth-title">【钱来钱来多多来】</view>
      <view class="wealth-pattern">格局:月刃格·月刃用财</view>
      <view class="wealth-desc">有很大几率能够获得足够的财富，但也可能会因为一些需要大量消耗钱财的事而存不住钱。</view>
    </view>


    <view class="five-elements">
      <view class="elements-title">【身弱型】</view>
      

      <view class="elements-circle-container">

        <view class="element-circle fire">
          <view class="element-name">火</view>
          <view class="element-percent">36%</view>
        </view>
        

        <view class="arrow arrow-fire-wood">
          <image src="/static/arrow-curve.png" mode="aspectFit" style="width: 100%; height: 100%; transform: rotate(120deg);"></image>
        </view>
        

        <view class="elements-middle-row">
 
          <view class="element-circle wood">
            <view class="element-name">木</view>
            <view class="element-percent">24%</view>
          </view>
          
  
          <view class="arrow arrow-wood-water">
            <image src="/static/arrow-curve.png" mode="aspectFit" style="width: 100%; height: 100%; transform: rotate(200deg);"></image>
          </view>
          

          <view class="element-circle earth">
            <view class="element-name">土</view>
            <view class="element-percent">18%</view>
          </view>
          
      
          <view class="arrow arrow-earth-metal">
            <image src="/static/arrow-curve.png" mode="aspectFit" style="width: 100%; height: 100%; transform: rotate(-20deg);"></image>
          </view>
        </view>
        

        <view class="elements-bottom-row">

          <view class="element-circle water">
            <view class="element-name">水</view>
            <view class="element-percent">12%</view>
          </view>
          

          <view class="arrow arrow-water-metal">
            <image src="/static/arrow-curve.png" mode="aspectFit" style="width: 100%; height: 100%; transform: rotate(0deg);"></image>
          </view>
          

          <view class="element-circle metal">
            <view class="element-name">金</view>
            <view class="element-percent">10%</view>
          </view>
        </view>
      </view>
      
      <view class="elements-preference">【喜用水、金】</view>
      

      <view class="lucky-info-container">

        <view class="lucky-info">
          <view class="lucky-title">幸运颜色</view>
          <view class="color-circles">
            <view class="color-circle main-color"></view>
            <view class="color-circle secondary-color"></view>
          </view>
        </view>
        

        <view class="lucky-info">
          <view class="lucky-title">幸运方位</view>
          <view class="direction-box">
            <view class="bump-top"></view>
            <view class="bump-content">西北</view>
            <view class="bump-bottom"></view>
          </view>
        </view>
        

        <view class="lucky-info">
          <view class="lucky-title">幸运数字</view>
          <view class="number-box">
            <view class="bump-top"></view>
            <view class="bump-content">28</view>
            <view class="bump-bottom"></view>
          </view>
        </view>
      </view>
    </view>
	
	
	<view class="advantage-box">
	  <view class="advantage-header">
	    <image class="advantage-icon" src="/static/优点.png" mode="aspectFill"></image>
	    <view class="advantage-title">适合行业</view>
	  </view>
	  <view class="advantage-content">
	   程序员，外卖员，游戏开发，电影开发
	  </view>
	</view>
	
	
	
	
  </view>
</template>

<script>
export default {
  data() {
    return {
      activeCategory: 0,
      categories: ['生辰', '星宿', '紫微', '政余', '生肖', '八字', '塔罗']
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
  color: #836C52;
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
  background-color: #836C52;
  border-radius: 2rpx;
}


.day-master {
  margin-bottom: 30rpx;
}

.day-master-title {
  font-size: 36rpx;
  font-weight: bold;
  color: #836C52;
  margin-bottom: 10rpx;
}

.day-master-desc {
  font-size: 28rpx;
  color: #8F8B86;
}


.wealth-card {
  background-color: #FFFCF7;
  border-radius: 16rpx;
  padding: 30rpx;
  margin-bottom: 30rpx;
}

.wealth-title {
  font-size: 34rpx;
  font-weight: bold;
  color: #836C52;
  margin-bottom: 15rpx;
}

.wealth-pattern {
  font-size: 28rpx;
  color: #836C52;
  margin-bottom: 20rpx;
}

.wealth-desc {
  font-size: 26rpx;
  color: #D4B697;
  line-height: 1.6;
}


.five-elements {
  background-color: #FFFCF7;
  border-radius: 16rpx;
  padding: 30rpx;
  position: relative;
}

.elements-title {
  font-size: 34rpx;
  font-weight: bold;
  color: #836C52;
  margin-bottom: 30rpx;
}

.elements-circle-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 40rpx;
  position: relative;
}

.elements-middle-row {
  display: flex;
  justify-content: space-between;
  width: 80%;
  margin-top: -20rpx;
  margin-bottom: -20rpx;
  position: relative;
}

.elements-bottom-row {
  display: flex;
  justify-content: space-between;
  width: 60%;
  position: relative;
}


.element-circle {
  width: 140rpx;  
  height: 140rpx; 
  border-radius: 50%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 15rpx;
  position: relative;
  z-index: 2;
}


.element-name {
  font-size: 38rpx; 
  font-weight: bold;
}

.element-percent {
  font-size: 28rpx; 
}


.arrow {
  position: absolute;
  z-index: 1;
  width: 100rpx;  
  height: 70rpx;  
}

.arrow-fire-wood {
  top: 120rpx;
  left: 50%;
  margin-left: -50rpx;
}

.arrow-wood-water {
  bottom: -10rpx;
  left: 70rpx;
}

.arrow-earth-metal {
  bottom: -10rpx;
  right: 70rpx;
}

.arrow-water-metal {
  bottom: 20rpx;
  left: 50%;
  margin-left: -50rpx;
}


.fire {
  background-color: #FFE7E3;
  color: #DE5F58;
  border: 2rpx solid #FC9F94;
}

.wood {
  background-color: #E7F5EB;
  color: #5EB491;
  border: 2rpx solid #ABD4C0;
}

.earth {
  background-color: #F6EDE5;
  color: #A17256;
  border: 2rpx solid #C7B39C;
}

.water {
  background-color: #E0F6FE;
  color: #4C83DE;
  border: 2rpx solid #BDDDF2;
}

.metal {
  background-color: #FFF0DD;
  color: #FDA033;
  border: 2rpx solid #F4CE99;
}

.elements-preference {
  font-size: 32rpx;
  font-weight: bold;
  color: #836C52;
  text-align: center;
  margin-bottom: 30rpx;
}


.lucky-info-container {
  display: flex;
  justify-content: space-between;
  margin-top: 30rpx;
}

.lucky-info {
  width: 30%;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.lucky-title {
  font-size: 26rpx;
  color: #A17B6F;
  margin-bottom: 15rpx;
}

.color-circles {
  display: flex;
}

.color-circle {
  width: 50rpx;
  height: 50rpx;
  border-radius: 50%;
  border: 2rpx solid #A17B6F;
}

.main-color {
  background-color: #A17B6F;
  margin-right: 10rpx;
}

.secondary-color {
  background-color: #FFFFFF;
}


.direction-box,
.number-box {
  width: 100%;
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.bump-top {
  width: 80%;
  height: 15rpx;
  background-color: #FFEBD2;
  border: 2rpx solid #EFCFC9;
  border-bottom: none;
  border-radius: 20rpx 20rpx 0 0;
}

.bump-content {
  width: 100%;
  background-color: #FFEBD2;
  border-left: 2rpx solid #EFCFC9;
  border-right: 2rpx solid #EFCFC9;
  color: #D38C7A;
  text-align: center;
  padding: 15rpx 0;
  font-size: 28rpx;
}

.bump-bottom {
  width: 60%;
  height: 15rpx;
  background-color: #FFEBD2;
  border: 2rpx solid #EFCFC9;
  border-top: none;
  border-radius: 0 0 20rpx 20rpx;
}


.advantage-box {
  background-color: #FFFCF7;
  border-radius: 16rpx;
  padding: 30rpx;
  margin-top: 15px;
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