<template>
	<view style="height: 100vh;background-color: #f7f7f7;">
		<view style="display: flex;align-items: center;justify-content: center">
			
			<view style="padding: 20rpx;width: 90%;background-color: #fff; box-shadow:0 0 10px #00000025;margin-top: 20px;">
				<u-form :model="form" ref="uForm">
			
					<u-form-item label="头像" label-width="200" label-position="left">
							<u-upload  ref="imgupload" :file-list="fileList"  :action="action" :before-upload="beforeUpload" max-count="1" style="float: right;"></u-upload>
					</u-form-item>
			
			
					<u-form-item label="姓名" label-position="left" label-width="200"><u-input v-model="form.userName"
							style="float: right;" input-align="right" /></u-form-item>
							
							<u-form-item label="密码" label-position="left" label-width="200"><u-input v-model="form.password"
									style="float: right;" input-align="right" /></u-form-item>
							
					<u-form-item label="性别" label-width="200" label-position="left">
						<view @click="showsex = true" style="float: right;">{{sextext}}</view>
						<u-select v-model="showsex" :list="sexlist" @confirm="confirmsex"></u-select>
					</u-form-item>
			
					<u-form-item label="电话" label-position="left" label-width="200"><u-input v-model="form.phone"
							style="float: right;" input-align="right" /></u-form-item>
					
							
				
			
			
			
					<view
						style="background-image: linear-gradient(to right, #EB554E, #ffaa00);color: #fff;height: 80rpx;width: 100%;text-align: center;line-height: 80rpx;margin-top: 100rpx;border-radius: 6px;"
						@click="submitBtn">完成</view>
			
				</u-form>
			</view>
			
		</view>
			<u-toast ref="uToast" />
		

		


	
	</view>
</template>

<script>
	export default {
		data() {
			return {
				show:false,
				action:this.$elyasApi+'file/uploadFile',
				fileList: [],
				form: {
					userName: '',
					password: '',
					email: '',
					phone: '',
					sex: '',
					
					avatar:'',
					id:''
				},
				buildingNumber: [],
				floor: [],
				sexlist:[
					{value:0,label:'女性'},
					{value:1,label:'男性'}
				],
				numbertext: '未选择楼号',
				showNumber: false,
				showFloor: false,
				florrtext: '未选择楼层',
				showsex: false,
				sextext: '未选择性别'
			}
		},
		onLoad() {
			
			this.getUserInfoByToken()
			
			
		},
		methods: {
			confirmNumber(e) {
				console.log(e)
				this.form.buildingNumberId = e[0].value
				this.numbertext = e[0].label
			},
			confirmFloor(e) {
				this.form.floorId = e[0].value
				this.florrtext = e[0].label
			},
			confirmsex(e){
				this.form.sex = e[0].value
				this.sextext = e[0].label
			},
			getBuilderNumber() {
				uni.request({
					url: this.$elyasApi + 'building/number/findByModal',
					method: 'POST',
					data: {},
					header: {
						"content-type": "application/json"
					},
					success: (data) => {
			
			
			
			
						data.data.data.list.forEach(item => {
							this.buildingNumber.push({
								value: item.id,
								label: item.name
							})
						})
			
					}
			
				})
			},
			getFloor() {
				uni.request({
					url: this.$elyasApi + 'floor/findByModal',
					method: 'POST',
					data: {},
					header: {
						"content-type": "application/json"
					},
					success: (data) => {
			
			
						data.data.data.list.forEach(item => {
							this.floor.push({
								value: item.id,
								label: item.name
							})
						})
			
			
					}
			
				})
			},
			async beforeUpload(index,list){
				// let that = this
				// var list = list
				
				//  // await var imgUrl = list[0].response.data
				// console.log(list)
				setTimeout(()=>{
					// 要赋值给谁
					this.form.avatar = list[0].response.data
				
				},3000)
				// for(var i = 0; i < list.length; i++) {
				// 	console.log(list[0].response,'1321')
					
				// }
			},
			deletePic(event) {
				this[`fileList${event.name}`].splice(event.index, 1)
			},
			getUserInfoByToken(){
				uni.request({
					url:this.$elyasApi+'user/detailByToken',
					method:'POST',
					data:{},
					header:{
						"content-type":"application/json",
						"accessToken":uni.getStorageSync('token')
					},
					success:(data)=> {
				
						this.form.userName = data.data.data.userName
						this.form.email = data.data.data.email
						this.form.password = data.data.data.password
						this.form.phone = data.data.data.phone
						this.form.avatar = data.data.data.avatar
						this.form.residential = data.data.data.residential
						this.form.buildingNumberId = data.data.data.buildingNumberId
						this.form.floorId = data.data.data.floorId
						this.form.houseNumber = data.data.data.houseNumber
						this.form.sex = data.data.data.sex
						this.form.id = data.data.data.id
						
						this.sextext = data.data.data.sex == 0 ? '女性' : '男性'
						
						this.numbertext = data.data.data.buildingNumberName
						
						this.florrtext = data.data.data.floorName
						
						
						if (this.form.avatar != '') {
							this.fileList.push({
								url: this.form.avatar
							})
						}
						
					
						
						
						
					}
					
				});
			},
			submitBtn(){
				uni.request({
					url: this.$elyasApi + 'user/update',
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
								title: '修改成功！',
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

<style scoped lang="scss">

</style>