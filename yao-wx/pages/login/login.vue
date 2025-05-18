<template>
	<view>
		
	<view style="display:flex;align-items: center;justify-content: center;margin-top: 50rpx;flex-direction: column;gap: 12px;">
					<image src="../../static/shopicon/商城-2 (1).png" mode="aspectFill" style="width: 250rpx;height: 250rpx;border-radius: 100%;"></image>
			<view style="font-size: 21px;font-weight: 700;">爻辞阁</view>
	</view>
	
	
	<!-- 表单 -->
	
	<view style="padding: 20rpx 40rpx;margin-top: 40rpx;">
		<view>
			<input type="text" value="" style="border: 1px solid #ddd;padding: 0.7em;border-radius: 8px;" v-model="userLoginInfo.userName" />
		</view>
		
		<view style="margin-top: 10px;">
			<input type="text" value="" style="border: 1px solid #ddd;padding: 0.7em;border-radius: 8px;" v-model="userLoginInfo.password" />
		</view>
		
		<view style="margin: 10px 0;color: #ff7a45;float: right;" @click="toRegisterBtn">点击注册</view>
		<view style="background: linear-gradient(to right, #ff7a45, #ffaa00);color: #fff;height: 80rpx;width: 100%;text-align: center;line-height: 80rpx;margin-top: 100rpx;border-radius: 6px;" @click="toLogin">点击登录</view>
	</view>
		<u-toast ref="uToast" />
	</view>
</template>

<script>
	export default {
		data() {
			return {
				show: false,
				userLoginInfo:{
					userName:'user',
					password:'123456'
				}
			}
		},
		methods: {
			toLogin(){
				uni.request({
					url:this.$elyasApi+'user/login',
					method:'POST',
					data:this.userLoginInfo,
					header:{
					    "content-type":"application/json"
					},
					success:(data)=> {
						
						if (data.data.code !== 200) {
							this.$u.toast(data.data.message);
							return;
						}else {
							// console.log('登录成功')
							// console.log(data.data.data.token,'登录成功')
							uni.showToast({
								title:'登录成功'
							})
							
							// 保存token到本地
							uni.setStorageSync('token',data.data.data.token)
							
							// 登录后跳转
							if (data.data.data.roleId == 2){
								uni.redirectTo({
									url:'/pages/index/index'
								})
							}else {
								uni.redirectTo({
									url:'/pages/liaotian2/liaotian2'
								})
							}
						}
						
						
						
					}
					
				})
			},
			toRegisterBtn(){
				uni.navigateTo({
					url:'/pages/register/register'
				})
			},
			
		}
	}
</script>

<style>
	
	.formItem{
		height: 100rpx;
		padding-top: 56rpx;
		border-bottom: 1rpx solid #DD524D;
		margin: 0 100rpx;
		display: flex;
		align-items: center;
	}
	
	.formItem input{
		font-size: 28rpx;
	}
	
	.loginBtn {
		height: 86rpx;
		margin: 0 100rpx;
		color: #fff;
		background-color: #DD524D;
		text-align: center;
		line-height: 86rpx;
		font-size: 30rpx;
		margin-top: 60rpx;
	}

</style>
