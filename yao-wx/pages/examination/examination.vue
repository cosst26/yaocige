<template>
  <view class="container">

    <view class="background"></view>
    

    <view class="header">
      <image class="title-image" src="/static/熊猫吃惊 (1).png" mode="aspectFit"></image>
      <text class="title-text">测测你的MBTI</text>
    </view>
    

    <view class="quiz-box">
   
      <view class="progress-info">
        <text class="current-question">{{currentQuestion}}</text>
        <text class="total-questions">/{{totalQuestions}}</text>
      </view>
      

      <view class="progress-bar">
        <view class="progress-fill" :style="{width: progressWidth}"></view>
      </view>
      
 
      <view class="question-title">{{currentData.question}}</view>
      

      <view class="options">
        <view 
          v-for="(option, index) in currentData.options" 
          :key="index"
          class="option"
          :class="{selected: selectedOption === index}"
          @click="selectOption(index)"
        >
          <text class="option-text">{{option}}</text>
        </view>
      </view>
    </view>
    

    <view class="footer">
      <text class="btn prev-btn" @click="prevQuestion" :class="{disabled: currentQuestion === 1}">
        上一题
      </text>
      <text class="btn next-btn" @click="nextQuestion" :class="{disabled: currentQuestion === totalQuestions}">
        下一题
      </text>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      currentQuestion: 1,
      totalQuestions: 24,
      selectedOption: null,
      questions: [
        {
          question: "你通常——？",
          options: [
            "A 容易和大家打成一片",
            "B 独处的时候更多",
            "C 视情况而定",
            "D 介于两者之间"
          ]
        },
   
        {
          question: "第二题示例？",
          options: [
            "A 选项1",
            "B 选项2",
            "C 选项3",
            "D 选项4"
          ]
        }
      ]
    }
  },
  computed: {
    currentData() {
      return this.questions[this.currentQuestion - 1] || this.questions[0];
    },
    progressWidth() {
      return `${(this.currentQuestion / this.totalQuestions) * 100}%`;
    }
  },
  methods: {
    selectOption(index) {
      this.selectedOption = index;
    },
    prevQuestion() {
      if (this.currentQuestion > 1) {
        this.currentQuestion--;
        this.selectedOption = null;
      }
    },
    nextQuestion() {
      if (this.currentQuestion < this.totalQuestions) {
        this.currentQuestion++;
        this.selectedOption = null;
      }
    }
  }
}
</script>

<style scoped>

page {
  height: 100%;
  background: linear-gradient(to bottom, #FFF8B7, #D3F9FC);
}

.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 100vh;
  padding: 20rpx;
  box-sizing: border-box;
  position: relative;
}

.background {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: linear-gradient(to bottom, #FFF8B7, #D3F9FC);
  z-index: -1;
}


.header {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 40rpx;
  margin-bottom: 40rpx;
}

.title-image {
  width: 180rpx;
  height: 180rpx;
}

.title-text {
  font-size: 40rpx;
  font-weight: bold;
  color: #333;
  margin-top: 20rpx;
  text-shadow: 2rpx 2rpx 4rpx rgba(0,0,0,0.1);
}


.quiz-box {
  width: 90%;
  background-color: #fff;
  border-radius: 20rpx;
  padding: 40rpx;
  box-shadow: 0 4rpx 12rpx rgba(0,0,0,0.08);
  margin-bottom: 60rpx;
}

.progress-info {
  margin-bottom: 20rpx;
}

.current-question {
  font-size: 48rpx;
  font-weight: bold;
  color: #000;
}

.total-questions {
  font-size: 36rpx;
  color: #000;
}

.progress-bar {
  height: 12rpx;
  background-color: #E8E8E8;
  border-radius: 6rpx;
  margin-bottom: 40rpx;
  overflow: hidden;
}

.progress-fill {
  height: 100%;
  background-color: #FEA24A;
  border-radius: 6rpx;
  transition: width 0.3s ease;
}

.question-title {
  font-size: 36rpx;
  font-weight: bold;
  color: #000;
  margin-bottom: 40rpx;
  line-height: 1.5;
}

.options {
  display: flex;
  flex-direction: column;
  gap: 20rpx;
}

.option {
  background-color: #F8F8F8;
  border-radius: 12rpx;
  padding: 25rpx;
  transition: all 0.2s ease;
}

.option.selected {
  background-color: #FEA24A;
  color: #fff;
}

.option-text {
  font-size: 32rpx;
  color: #333;
  line-height: 1.4;
}

.option.selected .option-text {
  color: #fff;
}


.footer {
  display: flex;
  justify-content: space-between;
  width: 90%;
  margin-bottom: 40rpx;
  position: fixed;
  bottom: 20rpx;
  left: 5%;
  padding: 0;
}

.btn {

  font-size: 32rpx;
  font-weight: bold;
  color: #333;
  background: none;
  border: none;
  width: 180rpx;
  text-align: center;
  
}



.btn.disabled {
  color: #999;
  pointer-events: none;
}
</style>