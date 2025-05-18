<template>
	<view>
		
		<!-- 聊天列表 -->
		<scroll-view scroll-y="true" :style="'height:'+scrollH+'px;'">
			
			<view class="flex align-start px-2 my-2" v-for="(item2,index2) in list" :key="index2" :style="item2.type ==  1 ? 'flex-direction: row-reverse;':''">
				<image :src="item2.senderAvatar" mode="aspectFill" style="width: 85rpx;height: 85rpx;" class="rounded-circle"></image>
				<view>
					
					<view class="p-2 rounded mx-2" style="max-width: 400rpx;min-width: 200rpx;background-color:#c5d9c3;word-break: break-all;" :style="item2.type ==  1 ? 'background-color: #FFD524;':''">{{item2.content}}</view>
				</view>
			</view>
			
			<!-- <view class="flex align-start px-2 my-2" style="flex-direction: row-reverse;" v-for="(item1,index1) in type1" :key="index1">
				<image :src="item1.senderAvatar" mode="aspectFill" style="width: 85rpx;height: 85rpx;" class="rounded-circle"></image>
				<view>
					<view class="p-2 rounded mx-2" style="max-width: 400rpx;min-width: 200rpx;background-color: #FFD524;display: flex;flex-wrap: wrap;height: auto;word-break: break-all;">{{item1.content}}</view>
				</view>
			</view> -->
			
			
		</scroll-view>
		
		
		
		<!-- 底部操作条 -->
		<view class="fixed-bottom flex align-center border-top bg-white" style="height: 100rpx;">
			<input type="text" value="" v-model="sendInfo.content" class="flex-1 rounded bg-light p-2 ml-2" placeholder="请输入内容"  />			<view style="width: 100rpx;display: flex;align-items: center;justify-content: center;">
				<view style="width: 100rpx;height: 50rpx;text-align: center;text-align: center;line-height: 50rpx;" @click="tosend">发送</view>
				
			</view>
		</view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				type1:[],
				type2:[],
				scrollH:500,
				shouID:'',
				userDetailInfo:{},
				messageList:[],
				sendInfo:{
					content:'',
					dialogueId:'',
				
				},
				list:[]
			}
		},
		onLoad(option) {
			this.sendInfo.dialogueId = option.id
			
			this.getUserData()
			this.getMessageList()
			uni.getSystemInfo({
				success: (res) => {
					this.scrollH = res.windowHeight - uni.upx2px(101)
				}
			})
			
			
		},
		methods: {
			getUserData(){
				uni.request({
					url:this.$elyasApi+'user/detailByToken',
					method:'POST',
					data:{},
					header:{
					    // "content-type":"application/json",
						"accessToken":uni.getStorageSync('token')
					},
					success:(data)=> {
						if (data.code !== 200) {
							this.code = 0
						}
						
						this.userDetailInfo = data.data.data
						this.sendInfo.senderId = data.data.data.id
						
						console.log(data,'用户详情')
						
					}
					
				})
			},
			getMessageList(){
				uni.request({
					url:this.$elyasApi+'dialogue/details/findByModal',
					method:'POST',
					data:{dialogueId:this.sendInfo.dialogueId,page:1,limit:999999},
					header:{
					   "content-type": "application/json",
					   "accessToken": uni.getStorageSync('token')
					},
					success:(data)=> {
						this.type1 = []
						this.type2 = []
						this.list = data.data.data.list
						data.data.data.list.forEach(item=>{
							if (item.type == 1) {
								this.type1.push(item)
							} else if (item.type == 2) {
								this.type2.push(item)
							}
						})
						
					}
					
				})
			},
			tosend(){
				uni.request({
					url:this.$elyasApi+'dialogue/chineseGPT',
					method:'POST',
					data:this.sendInfo,
					header:{
					   "content-type": "application/json",
					   "accessToken": uni.getStorageSync('token')
					},
					success:(data)=> {
						if (data.data.code !== 200) {
							uni.showToast({
								title:data.data.message,
								duration:1000
							})
							return;
						}else {
							this.getMessageList()
							this.sendInfo.content = ''
						}
						
						
					}
					
				})
			}
		}
	}
</script>

<style scoped>
	
	page {
		background-color: #F5F5F5;
	}
	
	.flexR{
		flex-direction: row-reverse;
	}

</style>
