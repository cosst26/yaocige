<template>
	<view style="padding: 20px;">
		
		<view class="forms">
			<u-upload  ref="imgupload" :file-list="fileList"  :action="action" :before-upload="beforeUpload" max-count="1" ></u-upload>
			
			<div class="aiitem">
				<view class="ainame">名称</view>
				<input type="text" placeholder="请输入AI名称" v-model="form.name">
			</div>
			
			<div class="aiitem">
				<view class="ainame">描述</view>
				<input type="text" placeholder="请输入AI描述" v-model="form.aiDescribe">
			</div>
			
			<view class="newbtn" @click="addai">点击完成</view>
		</view>
		<u-toast ref="uToast" />
	</view>
</template>

<script>
	export default {
		data() {
			return {
				show:false,
				action:this.$elyasApi+'file/uploadSingle',
				fileList: [],
				form:{
					name: '',
					avatar: '',
					aiDescribe: '',
					
				}
			}
		},
		onLoad() {
			
		},
		methods: {
			
			async beforeUpload(index,list){
			
				setTimeout(()=>{
					// 要赋值给谁
					this.form.avatar = list[0].response.data
					console.log(list[0].response.data)
				},4000)
				
			},
			deletePic(event) {
				this[`fileList${event.name}`].splice(event.index, 1)
			},
			addai(){
				uni.request({
					url: this.$elyasApi + 'dialogue/add',
					method: 'POST',
					data: this.form,
					header: {
						"content-type": "application/json",
						"accessToken": uni.getStorageSync('token')
					},
					success: (data) => {
				
						if (data.data.code !== 200) {
							this.$refs.uToast.show({
								title: data.data.message,
								type: 'error',
				
							})
							return;
						} else {
							this.$refs.uToast.show({
								title: '新增成功！',
								type: 'success',
				
							})
							
							setTimeout(()=>{
								uni.navigateBack({delta:1})
							},1000)
						}
				
				
					}
				
				})
			}
		}
	}
</script>

<style scoped>
.newbtn{
		 background: linear-gradient(to right, 
		        #4C39FF, /* 最左边起始红色 */
		        #4C39FF, /* 过渡到偏浅一点的红色 */
		        #C73FFF, /* 再过渡到蓝色 */
		        #C73FFF /* 最右边的蓝色 */
		    );
	
		 color: #fff;
		 width: 100%;
		 height: 50px;
		 border-radius: 30px;
		 text-align: center;
		 line-height: 50px;
		
	}
	
	.forms{
		display: flex;
		align-items: center;
		flex-direction: column;
		gap: 40px;
		width: 100%;
	}
	
	.aiitem{
		width: 100%;
		
	}
	
	.aiitem .ainame {
		font-weight: 700;
		margin-bottom: 8px;
		
	}
	
	.aiitem{
		
		
		
	}
	
	.aiitem input {
		border: 1px solid #ddd;
		border-radius: 10px;
		padding: 15px;
		color: #666;
		font-weight: 700;
	}
</style>
