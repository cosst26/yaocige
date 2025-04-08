<script  setup>

import { ref, reactive, computed,defineComponent } from 'vue'
import { toast, dateString, messageBox } from "~/composables/util"
import { Plus } from '@element-plus/icons-vue'
import { getArticle, createArticle, deleteArticle, updateArticle } from '~/api/manager'

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
    getArticle(pageInfo).then(res => {
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
    imgUrl: '',
  
  
})





const handleAvatarSuccess = (
    response,
    uploadFile
) => {
    form.imgUrl = uploadFile.response.data
    
}

const beforeAvatarUpload = (rawFile) => {
    
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
        imgUrl: '',
      
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
    const fun = modalId.value ? updateArticle({ id: modalId.value, title: form.title, content: form.content,imgUrl: form.imgUrl }) : createArticle(form)

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
        deleteArticle(id).then(res => {
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
            <!-- <el-table-column prop="id" label="id" align="center" width="50px"></el-table-column> -->

            <el-table-column label="图片" align="center">
                <template #default="{ row }">
                    <div class="flex items-center justify-center">
                        <el-avatar :size="50" :src="row.imgUrl">
                            <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png" alt="">
                        </el-avatar>
                    </div>
                </template>
            </el-table-column>

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

                <!-- <el-form-item prop="articleTypeId" label="分类">
                    <el-select v-model="form.articleTypeId" placeholder="分类">
                        <el-option v-for="item in roleData" :key="item.id" :label="item.name" :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item> -->
              
                <el-form-item prop="content" label="内容">
                    <textarea v-model="form.content" name="" id="" style="width: 100%;height: 300px;outline: none;border: 1px solid #ddd;padding: 5px;"></textarea>
                  
                </el-form-item>

          


                <el-form-item prop="avatar" label="图片">
                    <el-upload class="avatar-uploader" :action="$elyasApi+'/file/uploadFile'"
                        :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
                        <img v-if="form.imgUrl" :src="form.imgUrl" class="avatar" />
                        <el-icon v-else class="avatar-uploader-icon">
                            <Plus />
                        </el-icon>
                    </el-upload>
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