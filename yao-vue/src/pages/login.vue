<script setup>
import { ref, reactive, onMounted, onBeforeMount } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import { toast } from '~/composables/util'
import { getRole } from '~/api/manager'


const router = useRouter()
const store = useStore()
const role = ref([])

// 提交数据表单
const form = reactive({
    userName: 'admin',
    password: '123456',
    roleId: 1
})

function getUserRole() {
    getRole().then(res => {
        role.value = res.data.list
    })
}
getUserRole()

// 验证规则
const rules = {
    userName: [
        { required: true, message: '用户名不能为空', trigger: 'blur' },
    ],
    password: [
        { required: true, message: '密码不能为空', trigger: 'blur' },
    ]
}

// 获取ref节点
const refForForm = ref(null);
const loading = ref(false)

// 提交登录按钮
const onSubmit = () => {
    store.dispatch("login", form).then(res => {
        console.log(res, 'resrsersee')
        toast("登录成功")
        if (res.roleId == 2) {
            toast("请用小程序", "error")
        } else {
            router.push('/')

        }



    }).finally(() => {

    })
}

// 监听回车事件
function onKeyUp(e) {
    console.log(e, 'keyup')
    if (e.key == "Enter") onSubmit()
}

// 添加键盘监听
onMounted(() => {
    document.addEventListener("keyup", onKeyUp)
})

// 移除键盘事件
onBeforeMount(() => {
    document.removeEventListener("keyup", onKeyUp)
})

</script>


<template>
    <div class="app-container">
        <div class="login-box">
            <h2 class="login-title">爻辞阁后台管理系统</h2>
            <div class="register-link">
                <a href="javascript:;" @click="router.push('/register')">没有账号？注册</a>
            </div>
            <div class="input-group">
                <label for="username">账号</label>
                <input type="text" id="username" placeholder="请输入账号" v-model="form.userName">
            </div>
            <div class="input-group">
                <label for="password">密码</label>
                <input type="password" id="password" placeholder="请输入密码" v-model="form.password">
            </div>
            <div class="input-group">
                <label for="permission">选择权限</label>
                <select id="permission" v-model="form.roleId">
                    <option value="1">管理员</option>
                    <option value="2">学生</option>
                </select>
            </div>
            <button class="login-button" @click="onSubmit">登录</button>
        </div>
    </div>
</template>

<style scoped>
/* 整体容器样式 */
.app-container {
    min-height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background: url(../assets/cao.png) center/cover no-repeat;
    /* 你可以替换为实际的中草药背景图片 */
    animation: fadeInBackground 1s ease;
}

@keyframes fadeInBackground {
    from {
        opacity: 0.5;
    }

    to {
        opacity: 1;
    }
}

/* 登录框容器样式 */
.login-box {
    background: rgba(255, 255, 255, 0.95);
    padding: 40px 60px;
    border-radius: 20px;
    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.15);
    width: 450px;
    animation: slideIn 1s cubic-bezier(0.175, 0.885, 0.32, 1.275);
    position: relative;
    overflow: hidden;
}

@keyframes slideIn {
    from {
        transform: translateY(100px);
        opacity: 0;
    }

    to {
        transform: translateY(0);
        opacity: 1;
    }
}

/* 登录框装饰元素 */
.login-box::before {
    content: '';
    position: absolute;
    top: -100px;
    left: -100px;
    width: 200px;
    height: 200px;
    background: rgba(106, 153, 78, 0.1);
    border-radius: 50%;
    animation: float 5s infinite alternate;
}

@keyframes float {
    from {
        transform: translate(0, 0);
    }

    to {
        transform: translate(20px, 20px);
    }
}

/* 标题样式 */
.login-title {
    text-align: center;
    font-size: 32px;
    color: #333;
    margin-bottom: 30px;
    font-family: '宋体', serif;
    letter-spacing: 3px;
    text-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

/* 输入组样式 */
.input-group {
    margin-bottom: 25px;
    position: relative;
}

.input-group label {
    display: block;
    font-size: 16px;
    color: #666;
    margin-bottom: 8px;
    font-weight: 500;
}

.input-group input,
.input-group select {
    width: 100%;
    padding: 15px 20px;
    border: 1px solid #ddd;
    border-radius: 10px;
    font-size: 16px;
    transition: all 0.3s ease;
}

.input-group input:focus,
.input-group select:focus {
    border-color: #6a994e;
    outline: none;
    box-shadow: 0 0 10px rgba(106, 153, 78, 0.2);
}

/* 注册链接样式 */
.register-link {
    text-align: right;
    margin-bottom: 25px;
}

.register-link a {
    color: #6a994e;
    text-decoration: none;
    font-size: 14px;
    transition: color 0.3s ease;
}

.register-link a:hover {
    color: #386641;
}

/* 登录按钮样式 */
.login-button {
    width: 100%;
    padding: 15px;
    background-color: #6a994e;
    border: none;
    border-radius: 10px;
    color: white;
    font-size: 18px;
    font-weight: 600;
    cursor: pointer;
    transition: background-color 0.3s ease;
    box-shadow: 0 5px 15px rgba(106, 153, 78, 0.3);
}

.login-button:hover {
    background-color: #386641;
    box-shadow: 0 5px 20px rgba(56, 102, 65, 0.4);
}
</style>