<template>
  <view class="container">
    
    
    <view class="divination-list">
      <view 
        class="divination-item" 
        v-for="(item, index) in divinationList" 
        :key="index"
        @click="viewDetail(item)"
      >
        <image class="item-image" :src="item.imgUrl" mode="aspectFill"></image>
        <view class="item-info">
          <text class="item-title">{{item.title}}</text>
          <text class="item-author">作者: {{item.author}}</text>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      divinationList: [
     
      ]
    }
  },
  onLoad() {
  	this.getData()
  },
  methods: {
    getData(){
		uni.request({
			url:this.$elyasApi+'daily/hexagram/findByModal',
			method:'POST',
			data:this.postpar,
			header:{
			    "content-type":"application/json"
			},
			success:(data)=> {	
				
				this.divinationList = data.data.data.list
						
				
			}
			
		})
	},
	viewDetail(data){
		uni.navigateTo({
			url:'/pages/blueLine/blueLine?id='+data.id
		})
	}
  }
}
</script>

<style>
.container {
  padding: 20rpx;
  background-color: #f5f5f5;
}

.header {
  margin-bottom: 30rpx;
}

.title {
  font-size: 36rpx;
  font-weight: bold;
  color: #333;
}

.divination-list {
  display: flex;
  flex-direction: column;
  gap: 20rpx;
}

.divination-item {
  display: flex;
  background-color: #fff;
  border-radius: 12rpx;
  overflow: hidden;
  box-shadow: 0 4rpx 12rpx rgba(0, 0, 0, 0.1);
}

.item-image {
  width: 200rpx;
  height: 150rpx;
}

.item-info {
  flex: 1;
  padding: 20rpx;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.item-title {
  font-size: 32rpx;
  color: #333;
  font-weight: bold;
}

.item-author {
  font-size: 24rpx;
  color: #999;
}
</style>