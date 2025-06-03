<template>
  <el-card shadow="never" class="border-0">
    <!-- 搜索框 -->
    <div class="mb-6 flex items-center">
      <el-input v-model="searchReceiver" placeholder="请输入接收人搜索" class="w-100 mr-2" />
      <el-select v-model="searchStatus" placeholder="请选择业务状态">
        <el-option label="未读" value="0" />
        <el-option label="已读" value="1" />
      </el-select>
      <el-button type="primary" @click="fetchMessages">搜索</el-button>
    </div>

    <!-- 操作按钮 -->
    <div class="flex items-center justify-between mb-4">
      <el-button type="primary" size="middle" @click="openAddModal">新增消息</el-button>
      <el-button text @click="fetchMessages">
        <el-icon :size="20">
          <Refresh />
        </el-icon>
      </el-button>
    </div>

    <!-- 表格 -->
    <el-table :data="messages" stripe style="width: 100%">
      <el-table-column prop="status" label="业务状态" align="center">
        <template #default="scope">
          <span>{{ scope.row.status === 0 ? '未读' : '已读' }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="content" label="消息内容" align="center" />
      <el-table-column prop="receivedBy" label="接受人" align="center" />
      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button type="primary" size="small" v-if="scope.row.status === 0" @click="markAsRead(scope.row.id)">标记已读</el-button>
          <el-button type="danger" size="small" @click="deleteMessage(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <div class="flex items-center justify-center mt-5">
      <el-pagination
        background
        layout="prev, pager, next, total"
        :total="total"
        :current-page="currentPage"
        :page-size="pageSize"
        @current-change="handlePageChange"
      />
    </div>

    <!-- 新增模态框 -->
    <el-dialog
      v-model="dialogVisible"
      :title="dialogTitle"
      width="60%"
      :close-on-click-modal="false"
    >
      <el-form :model="form" :rules="rules" ref="formRef" label-width="120px">
        <el-form-item label="业务状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择业务状态">
            <el-option label="未读" value="0" />
            <el-option label="已读" value="1" />
          </el-select>
        </el-form-item>
        <el-form-item label="消息内容" prop="content">
          <el-input type="textarea" v-model="form.content" placeholder="请输入消息内容" />
        </el-form-item>
        

          <el-form-item prop="receivedBy" label="接受人">
                    <el-select v-model="form.receivedBy" placeholder="接受人">
                        <el-option v-for="item in ulist" :key="item.id" :label="item.userName" :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>



      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="submitForm">确定</el-button>
        </span>
      </template>
    </el-dialog>
  </el-card>
</template>

<script setup>
import { ref, reactive, computed } from 'vue';
import { toast, messageBox } from '~/composables/util';
import { Plus, Refresh } from '@element-plus/icons-vue';
import {
  getMessages,
  createMessage,
  getUserList,
  updateMessageStatus,
  deleteMessage as deleteApi
} from '~/api/manager';

// 表格数据
const messages = ref([]);
// 总记录数
const total = ref(0);
// 当前页码
const currentPage = ref(1);
// 每页记录数
const pageSize = ref(10);
// 搜索接收人
const searchReceiver = ref('');
// 搜索业务状态
const searchStatus = ref(null);
// 模态框显示状态
const dialogVisible = ref(false);
// 模态框标题
const dialogTitle = ref('');
// 表单数据
const form = reactive({
  id: null,
  status: 0,
  content: '',
  receivedBy: ''
});
// 表单验证规则
const rules = {
  status: [
    { required: true, message: '请选择业务状态', trigger: 'change' }
  ],
  content: [
    { required: true, message: '请输入消息内容', trigger: 'blur' }
  ],
  receivedBy: [
    { required: true, message: '请输入接受人', trigger: 'blur' }
  ]
};


const ulist = ref([])
getUserList().then(res=>{
  ulist.value = res.data.list
})

// 表单引用
const formRef = ref(null);

// 获取消息数据
const fetchMessages = async () => {
  try {
    const res = await getMessages({
      page: currentPage.value,
      limit: pageSize.value,
      receivedBy: searchReceiver.value,
      status: searchStatus.value
    });
    messages.value = res.data.list;
    total.value = res.data.total;
  } catch (error) {
    toast('获取数据失败', 'error');
  }
};

// 分页变化处理
const handlePageChange = (page) => {
  currentPage.value = page;
  fetchMessages();
};

// 打开新增模态框
const openAddModal = () => {
  dialogTitle.value = '新增消息';
  form.id = null;
  form.status = 0;
  form.content = '';
  form.receivedBy = '';
  dialogVisible.value = true;
};

// 提交表单
const submitForm = async () => {
  await formRef.value.validate();
  try {
    if (form.id) {
      // 这里实际一般新增不会有 id，仅作逻辑完整性保留
      toast('新增消息不应有 ID，逻辑有误', 'error');
      return;
    }
    await createMessage(form);
    toast('新增成功');
    dialogVisible.value = false;
    fetchMessages();
  } catch (error) {
    toast('操作失败', 'error');
  }
};

// 标记消息为已读
const markAsRead = async (id) => {
  try {
    await updateMessageStatus(id, 1);
    toast('标记已读成功');
    fetchMessages();
  } catch (error) {
    toast('标记已读失败', 'error');
  }
};

// 删除消息
const deleteMessage = async (id) => {
  const confirm = await messageBox('确定要删除该消息吗？');
  if (confirm) {
    try {
      await deleteApi(id);
      toast('删除成功');
      fetchMessages();
    } catch (error) {
      toast('删除失败', 'error');
    }
  }
};

// 初始化数据
fetchMessages();
</script>

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

.detailDss {
  width: 150px;
  height: 40px;
  font-size: 14px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}
</style>