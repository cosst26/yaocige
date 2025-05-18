<template>
  <view class="container" style="background-color: #C1D2FE;">
   
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

  
    <view class="user-info">
      <view class="user-name">日摩羯·月摩羯·升白羊的陈先生</view>
      <view class="user-desc">身心合一的行业明灯，内心住着细腻傲娇老baby</view>
    </view>

   
    <view class="destiny-card">
      <view class="destiny-title">天生总裁命</view>
      <view class="destiny-desc">
        仅 <text class="highlight">0.11%</text> 的人拥有此星图配置，小声告诉你你超适合当老大喔
      </view>
      <view class="divider"></view>
      
    
      <view class="triangle-container">
        <view class="triangle-item" v-for="(item, index) in triangleData" :key="index">
          <view class="triangle-top" :style="{ color: item.color }">{{ item.topText }}</view>
          <view class="triangle-wrapper" style="display: flex;align-items: center;flex-direction: column;">
            <svg class="triangle-svg" viewBox="0 0 200 113" preserveAspectRatio="xMidYMin meet">
				
              <defs>
                <linearGradient :id="'gradient-' + index" x1="50%" y1="0%" x2="30%" y2="100%">
                  <stop offset="0%" :stop-color="item.color" />
                  <stop offset="100%" :stop-color="item.color + '00'" />
                </linearGradient>
              </defs>
			
              <path 
                d="M100 0 
                   L200 173 
                   Q150 130 100 173 
                   Q50 130 0 173 
                   Z" 
                :fill="'url(#gradient-' + index + ')'"
              />
              
            </svg>
			   <view :style="{ color: item.color }">{{item.text}}</view>
          </view>
		
        </view>
      </view>
    </view>

   
    <view class="compare-container">
      <view class="compare-top">
        <view class="compare-box" v-for="(item, index) in topBoxes" :key="'top'+index">
          <view class="box-title">{{ item.title }}</view>
          <view class="box-subtitle">{{ item.subtitle }}</view>
        </view>
      </view>
      
      <view class="compare-middle">
        <view class="compare-box left-box">
          <view class="box-title">{{ leftBox.title }}</view>
          <view class="box-subtitle">{{ leftBox.subtitle }}</view>
        </view>
        <view class="compare-vs">
          <text>VS</text>
        </view>
        <view class="compare-box right-box">
          <view class="box-title">{{ rightBox.title }}</view>
          <view class="box-subtitle">{{ rightBox.subtitle }}</view>
        </view>
      </view>
      
      <view class="compare-bottom">
        <view class="compare-box" v-for="(item, index) in bottomBoxes" :key="'bottom'+index">
          <view class="box-title">{{ item.title }}</view>
          <view class="box-subtitle">{{ item.subtitle }}</view>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      activeCategory: 0,
      categories: ['生辰', '星宿', '紫微', '政余', '生肖', '八字', '塔罗', '星座'],
      triangleData: [
        { 
          text: '爱情', 
          topText: '爱的控场王', 
          color: '#E194A6'
        },
        { 
          text: '事业', 
          topText: '得天独厚', 
          color: '#89A8E0'
        },
        { 
          text: '财富', 
          topText: '努力就暴富', 
          color: '#5CC7B3'
        },
        { 
          text: '人际关系', 
          topText: '最佳"怪"友', 
          color: '#89A8E0'
        }
      ],
      topBoxes: [
        { title: '精力旺盛', subtitle: 'ASC 上升白羊' },
        { title: '直觉敏锐', subtitle: '月亮星座' },
        { title: '行动力强', subtitle: '火星相位' }
      ],
      leftBox: { title: '领导力', subtitle: '90分' },
      rightBox: { title: '同理心', subtitle: '85分' },
      bottomBoxes: [
        { title: '创造力', subtitle: '88分' },
        { title: '决策力', subtitle: '92分' },
        { title: '适应力', subtitle: '78分' }
      ]
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
  margin-bottom: 40rpx;
}

.category-list {
  display: inline-flex;
}

.category-item {
  display: inline-block;
  padding: 20rpx 30rpx;
  font-size: 32rpx;
  color: #556D9D;
  position: relative;
}

.category-item.active {
  color: #082557;
  font-weight: bold;
}

.category-item.active::after {
  content: '';
  position: absolute;
  bottom: 10rpx;
  left: 50%;
  transform: translateX(-50%);
  width: 60%;
  height: 6rpx;
  background-color: #082557;
  border-radius: 3rpx;
}


.user-info {
  text-align: center;
  margin-bottom: 40rpx;
}

.user-name {
  font-size: 40rpx;
  font-weight: bold;
  color: #082557;
  margin-bottom: 20rpx;
}

.user-desc {
  font-size: 32rpx;
  color: #7E90BB;
}


.destiny-card {
  background-color: rgba(255, 255, 255, 0.7);
  border: 1rpx solid white;
  border-radius: 20rpx;
  padding: 40rpx;
  margin-bottom: 40rpx;
}

.destiny-title {
  font-size: 38rpx;
  color: #4360AA;
  font-weight: bold;
  text-align: center;
  margin-bottom: 20rpx;
}

.destiny-desc {
  font-size: 30rpx;
  color: #4360AA;
  text-align: center;
  margin-bottom: 40rpx;
}

.highlight {
  color: #F86C2F;
  font-weight: bold;
}

.divider {
  height: 2rpx;
  background-color: white;
  margin: 40rpx 0;
}


.triangle-container {
  display: flex;
  justify-content: space-between;
  margin-top: 60rpx;
  padding: 0 20rpx;
}

.triangle-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 23%;
  min-height: 280rpx;
}

.triangle-top {
  font-size: 27rpx;
  font-weight: bold;
  margin-bottom: 20rpx;
  text-align: center;
  height: 80rpx;
  display: flex;
  align-items: center;
  justify-content: center;
}

.triangle-wrapper {
  width: 100%;
  height: 200rpx;
  display: flex;
  justify-content: center;
}

.triangle-svg {
  width: 100%;
  height: 100%;
}


.compare-container {
  background-color: #F4F7FE;
  border-radius: 20rpx;
  padding: 40rpx;
}

.compare-top, .compare-bottom {
  display: flex;
  justify-content: space-between;
  margin-bottom: 40rpx;
}

.compare-box {
  background-color: #fff !important;
  border-radius: 16rpx;
  padding: 24rpx;
  text-align: center;
  width: 30%;
}

.box-title {
  font-size: 30rpx;
  color: #8DA0BB;
  font-weight: 700;
  margin-bottom: 12rpx;
}

.box-subtitle {
  font-size: 32rpx;
  color: #E99C97;
  font-weight: bold;
}

.compare-middle {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 40rpx;
}

.left-box, .right-box {
  width: 30%;
}

.compare-vs {
  width: 150rpx;
  height: 150rpx;
  border-radius: 100%;
  /* margin: 0 40px; */
  background-color: white;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #82C1FD;
  font-weight: bold;
  font-size: 32rpx;
  background: linear-gradient(to bottom, #ced6e1, white);
}
</style>