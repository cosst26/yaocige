<script  setup>

import { ref, reactive, computed,defineComponent } from 'vue'
import { toast, dateString, messageBox } from "~/composables/util"
import { Plus } from '@element-plus/icons-vue'
import { getAnnounce, createAnnounce, deleteAnnounce, updateAnnounce } from '~/api/manager'

import myEditor from '../components/myEditor.vue';


const tableData = ref([])
const roleData = ref([])


const pageInfo = reactive({
    page: 1,
    limit: 10,
    title: ''
})

// 分页数据
const total = ref(0)

// 模态框
const addFormDialog = ref(false)

function getData() {
    getAnnounce(pageInfo).then(res => {
        console.log(res, 'resrserse')
        tableData.value = res.data.list
        total.value = res.data.total
    })
}

getData()


const valueHtml = ref('')
console.log(valueHtml,'valueHtmlvalueHtmlvalueHtml')

// 分页
function handleCurrentChange(value) {
    pageInfo.page = value
    getData()
}

// 添加表单部分
const refForm = ref(null)

const form = reactive({
    title: '',
    content: valueHtml.value,

   
  
})





const handleAvatarSuccess = (
    response,
    uploadFile
) => {
    form.imgUrl = uploadFile.response.data
    
}

const beforeAvatarUpload = (rawFile) => {
    if (rawFile.type !== 'image/jpeg') {
        toast('Avatar picture must be JPG format!', "error")
        return false
    } else if (rawFile.size / 1024 / 1024 > 2) {
        toast('Avatar picture size can not exceed 2MB!', "error")
        return false
    }
    return true
}

// o 等于新增 等于当前id是修改
const modalId = ref(0)
const modalTitle = computed(() => modalId.value ? "修改" : "新增")

// 重置表单 数据回显
const resetForm = (row = false) => {
    if (refForm.value) refForm.value.clearValidate()
    if (row) {
        for (const key in form) {
            form[key] = row[key]
        }
    }
}



// 打开新增模态框
function openHandleCreateModal() {
    modalId.value = 0
    resetForm({
        title: '',
        content: '',
      
    })
    addFormDialog.value = true
}

// 修改
const handleEdit = (row) => {
    modalId.value = row.id
    resetForm(row)
    addFormDialog.value = true
}

// 提交新增表单
function handleSubmit() {
    const fun = modalId.value ? updateAnnounce({ id: modalId.value, title: form.title, content: form.content }) : createAnnounce(form)

        fun.then(res => {
            if (res.code !== 200) {
                toast(res.message, "error");
                return;
            } else {
                toast(modalTitle.value + '成功!')
                getData()
                refForm.value.resetFields()
                addFormDialog.value = false
            }

        })
}





// 删除
const handleDelete = (id) => {
    messageBox("确定要删除吗？").then(isres => {
        deleteAnnounce(id).then(res => {
            if (res.code !== 200) {
                toast(res.message, "error");
                return;
            } else {
                toast('删除成功!')
                getData()
            }
        })
    })
}



</script>

<template>
    <el-card shadow="never" class="border-0">

        <div class="mb-6 flex items-center">
            <el-input v-model="pageInfo.title" placeholder="请输入搜索内容" class="w-100 mr-2" />
            <el-button type="primary" @click="getData">
                搜索
            </el-button>
        </div>


        <!-- 新增 刷新 -->
        <div class="flex items-center justify-between mb4">
            <el-button type="primary" size="middle" @click="openHandleCreateModal">新增</el-button>
            <el-button text>
                <el-icon :size="20" @click="getData">
                    <Refresh></Refresh>
                </el-icon>
            </el-button>
        </div>


        <el-table :data="tableData" stripe style="width: 100%">
            
            
            <el-table-column prop="title" label="标题" align="center"></el-table-column>
            
           
            
          
            <el-table-column prop="content" label="内容" align="center">
                <template #default="scope">
                <div class="contentclass">{{ scope.row.content }}</div></template>
            </el-table-column>
           
          
           
            <el-table-column label="操作" align="center">
                <template #default="scope">
                    <el-button type="primary" size="small" @click="handleEdit(scope.row)">修改</el-button>
                    <el-button type="danger" size="small" @click="handleDelete(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <div class="flex items-center justify-center mt-5">
            <el-pagination background layout="prev,pager,next,total" :total="total" :current-page="pageInfo.page"
                :page-size="pageInfo.limit" @current-change="handleCurrentChange" />
        </div>


        <!-- 模态框 -->
        <el-dialog v-model="addFormDialog" :title="modalTitle" width="30%" 
            :close-on-click-modal="false">

            <el-form :model="form"  ref="refForm" label-width="80px">

                <el-form-item prop="title" label="标题">
                    <el-input v-model="form.title" placeholder="标题" />
                </el-form-item>
              
              
                <el-form-item prop="content" label="内容">
                    <el-input  type="textarea" v-model="form.content" placeholder="活动内容" />
                </el-form-item>

          



                
            </el-form>


            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="addFormDialog = false">取消</el-button>
                    <el-button type="primary" @click="handleSubmit">
                        确认
                    </el-button>
                </span>
            </template>
        </el-dialog>



    </el-card>
</template>


<style scoped>
.avatar-uploader .avatar {
    width: 178px;
    height: 178px;
    display: block;
}

.avatar-uploader .el-upload {
    border: 1px dashed var(--el-border-color);
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
    border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    text-align: center;
}

.detailDss{
width: 150px;
height: 40px;
font-size: 14px;
overflow: hidden;
text-overflow: ellipsis;
display: -webkit-box;
-webkit-line-clamp: 1;
-webkit-box-orient: vertical;
}

.contentclass {
    overflow: hidden;
text-overflow: ellipsis;
display: -webkit-box;
-webkit-line-clamp: 3;
-webkit-box-orient: vertical;
}
</style>