<template>
  <el-card shadow="never" class="border-0">
    <!-- 搜索框 -->
    <div class="mb-6 flex items-center">
      <el-input v-model="searchQuestionId" placeholder="请输入所属 MBTI 问题 ID 搜索" class="w-100 mr-2" />
      <el-button type="primary" @click="fetchOptions">搜索</el-button>
    </div>

    <!-- 操作按钮 -->
    <div class="flex items-center justify-between mb-4">
      <el-button type="primary" size="middle" @click="openAddModal">新增 MBTI 选项</el-button>
      <el-button text @click="fetchOptions">
        <el-icon :size="20">
          <Refresh />
        </el-icon>
      </el-button>
    </div>

    <!-- 表格 -->
    <el-table :data="options" stripe style="width: 100%">
      <el-table-column prop="content" label="选项内容" align="center">
        <template #default="scope">
          <span class="detailDss">{{ scope.row.content }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="questionId" label="所属 MBTI 问题 ID" align="center" />
      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button type="primary" size="small" @click="openEditModal(scope.row)">编辑</el-button>
          <el-button type="danger" size="small" @click="deleteOption(scope.row.id)">删除</el-button>
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

    <!-- 新增/编辑模态框 -->
    <el-dialog
      v-model="dialogVisible"
      :title="dialogTitle"
      width="60%"
      :close-on-click-modal="false"
    >
      <el-form :model="form" :rules="rules" ref="formRef" label-width="120px">
        <el-form-item label="选项内容" prop="content">
          <el-input type="textarea" v-model="form.content" placeholder="请输入选项内容" />
        </el-form-item>
        <el-form-item label="所属 MBTI 问题 ID" prop="questionId">
          <el-select v-model="form.questionId" placeholder="所属 MBTI 问题 ID">
                        <el-option v-for="item in ulist" :key="item.id" :label="item.title" :value="item.id">
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
  getOptions,
  createOption,
  getQuestions,
  updateOption,
  deleteOption as deleteApi
} from '~/api/manager';


const ulist = ref([])
getQuestions().then(res=>{
  ulist.value = res.data.list
})

// 表格数据
const options = ref([]);
// 总记录数
const total = ref(0);
// 当前页码
const currentPage = ref(1);
// 每页记录数
const pageSize = ref(10);
// 搜索所属 MBTI 问题 ID
const searchQuestionId = ref('');
// 模态框显示状态
const dialogVisible = ref(false);
// 模态框标题
const dialogTitle = ref('');
// 表单数据
const form = reactive({
  id: null,
  content: '',
  questionId: null
});
// 表单验证规则
const rules = {
  content: [
    { required: true, message: '请输入选项内容', trigger: 'blur' }
  ],
  questionId: [
    { required: true, message: '请输入所属 MBTI 问题 ID', trigger: 'blur' },
    { type: 'number', message: '请输入有效的数字', trigger: 'blur' }
  ]
};
// 表单引用
const formRef = ref(null);

// 获取 MBTI 选项数据
const fetchOptions = async () => {
  try {
    const res = await getOptions({
      page: currentPage.value,
      limit: pageSize.value,
      questionId: searchQuestionId.value
    });
    options.value = res.data.list;
    total.value = res.data.total;
  } catch (error) {
    toast('获取数据失败', 'error');
  }
};

// 分页变化处理
const handlePageChange = (page) => {
  currentPage.value = page;
  fetchOptions();
};

// 打开新增模态框
const openAddModal = () => {
  dialogTitle.value = '新增 MBTI 选项';
  form.id = null;
  form.content = '';
  form.questionId = null;
  dialogVisible.value = true;
};

// 打开编辑模态框
const openEditModal = (row) => {
  dialogTitle.value = '编辑 MBTI 选项';
  form.id = row.id;
  form.content = row.content;
  form.questionId = row.question_id;
  dialogVisible.value = true;
};

// 提交表单
const submitForm = async () => {
  await formRef.value.validate();
  try {
    if (form.id) {
      // 编辑操作
      await updateOption(form);
      toast('编辑成功');
    } else {
      // 新增操作
      await createOption(form);
      toast('新增成功');
    }
    dialogVisible.value = false;
    fetchOptions();
  } catch (error) {
    toast('操作失败', 'error');
  }
};

// 删除 MBTI 选项
const deleteOption = async (id) => {
  const confirm = await messageBox('确定要删除该 MBTI 选项吗？');
  if (confirm) {
    try {
      await deleteApi(id);
      toast('删除成功');
      fetchOptions();
    } catch (error) {
      toast('删除失败', 'error');
    }
  }
};

// 初始化数据
fetchOptions();
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