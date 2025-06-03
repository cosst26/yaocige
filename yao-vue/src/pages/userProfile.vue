<template>
  <el-card shadow="never" class="border-0">
    <!-- 搜索框 -->
    <div class="mb-6 flex items-center">
      <el-input v-model="searchUserName" placeholder="请输入昵称搜索" class="w-100 mr-2" />
      <el-select v-model="searchSex" placeholder="请选择性别">
        <el-option label="女" value="0" />
        <el-option label="男" value="1" />
      </el-select>
      <el-button type="primary" @click="fetchUserProfiles">搜索</el-button>
    </div>

    <!-- 操作按钮 -->
    <div class="flex items-center justify-between mb-4">
      <el-button type="primary" size="middle" @click="openAddModal">新增用户档案</el-button>
      <el-button text @click="fetchUserProfiles">
        <el-icon :size="20">
          <Refresh />
        </el-icon>
      </el-button>
    </div>

    <!-- 表格 -->
    <el-table :data="userProfiles" stripe style="width: 100%">
      <el-table-column prop="userName" label="昵称" align="center" />
      <el-table-column prop="relationship" label="关系" align="center" />
      <el-table-column prop="sex" label="性别" align="center">
        <template #default="scope">
          <span>{{ scope.row.sex === 0 ? '女' : '男' }}</span>
        </template>
      </el-table-column>
      
      <el-table-column prop="birthday" label="出生日期" align="center">
        <template #default="scope">
          <span>{{ dateString(scope.row.birthday) }}</span>
        </template>
      </el-table-column>


      <el-table-column prop="place" label="出生地点" align="center" />
      <el-table-column prop="residence" label="现居地" align="center" />
      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button type="primary" size="small" @click="openEditModal(scope.row)">编辑</el-button>
          <el-button type="danger" size="small" @click="deleteUserProfile(scope.row.id)">删除</el-button>
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
        <el-form-item label="昵称" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入昵称" />
        </el-form-item>
        <el-form-item label="关系" prop="relationship">
          <el-input v-model="form.relationship" placeholder="请输入关系" />
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-select v-model="form.sex" placeholder="请选择性别">
            <el-option label="女" value="0" />
            <el-option label="男" value="1" />
          </el-select>
        </el-form-item>
        <el-form-item label="出生日期" prop="birthday">
          <el-date-picker
            v-model="form.birthday"
            type="date"
            placeholder="请选择出生日期"
          />
        </el-form-item>
        <el-form-item label="出生地点" prop="place">
          <el-input v-model="form.place" placeholder="请输入出生地点" />
        </el-form-item>
        <el-form-item label="现居地" prop="residence">
          <el-input v-model="form.residence" placeholder="请输入现居地" />
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
import { toast, dateString, messageBox } from "~/composables/util"
import { Plus, Refresh } from '@element-plus/icons-vue';
import {
  getUserProfiles,
  createUserProfile,
  updateUserProfile,
  deleteUserProfile as deleteApi
} from '~/api/manager';

// 表格数据
const userProfiles = ref([]);
// 总记录数
const total = ref(0);
// 当前页码
const currentPage = ref(1);
// 每页记录数
const pageSize = ref(10);
// 搜索昵称
const searchUserName = ref('');
// 搜索性别
const searchSex = ref(null);
// 模态框显示状态
const dialogVisible = ref(false);
// 模态框标题
const dialogTitle = ref('');
// 表单数据
const form = reactive({
  id: null,
  userName: '',
  relationship: '',
  sex: null,
  birthday: null,
  place: '',
  residence: ''
});
// 表单验证规则
const rules = {
  userName: [
    { required: true, message: '请输入昵称', trigger: 'blur' }
  ],
  relationship: [
    { required: true, message: '请输入关系', trigger: 'blur' }
  ],
  sex: [
    { required: true, message: '请选择性别', trigger: 'change' }
  ],
  birthday: [
    { required: true, message: '请选择出生日期', trigger: 'change' }
  ],
  place: [
    { required: true, message: '请输入出生地点', trigger: 'blur' }
  ],
  residence: [
    { required: true, message: '请输入现居地', trigger: 'blur' }
  ]
};
// 表单引用
const formRef = ref(null);

// 获取用户档案数据
const fetchUserProfiles = async () => {
  try {
    const res = await getUserProfiles({
      page: currentPage.value,
      limit: pageSize.value,
      userName: searchUserName.value,
      sex: searchSex.value
    });
    userProfiles.value = res.data.list;
    total.value = res.data.total;
  } catch (error) {
    toast('获取数据失败', 'error');
  }
};

// 分页变化处理
const handlePageChange = (page) => {
  currentPage.value = page;
  fetchUserProfiles();
};

// 打开新增模态框
const openAddModal = () => {
  dialogTitle.value = '新增用户档案';
  form.id = null;
  form.userName = '';
  form.relationship = '';
  form.sex = null;
  form.birthday = null;
  form.place = '';
  form.residence = '';
  dialogVisible.value = true;
};

// 打开编辑模态框
const openEditModal = (row) => {
  dialogTitle.value = '编辑用户档案';
  form.id = row.id;
  form.userName = row.userName;
  form.relationship = row.relationship;
  form.sex = row.sex;
  form.birthday = row.birthday;
  form.place = row.place;
  form.residence = row.residence;
  dialogVisible.value = true;
};

// 提交表单
const submitForm = async () => {
  await formRef.value.validate();
  try {
    if (form.id) {
      // 编辑操作
      await updateUserProfile(form);
      toast('编辑成功');
    } else {
      // 新增操作
      await createUserProfile(form);
      toast('新增成功');
    }
    dialogVisible.value = false;
    fetchUserProfiles();
  } catch (error) {
    toast('操作失败', 'error');
  }
};

// 删除用户档案
const deleteUserProfile = async (id) => {
  const confirm = await messageBox('确定要删除该用户档案吗？');
  if (confirm) {
    try {
      await deleteApi(id);
      toast('删除成功');
      fetchUserProfiles();
    } catch (error) {
      toast('删除失败', 'error');
    }
  }
};

// 初始化数据
fetchUserProfiles();
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