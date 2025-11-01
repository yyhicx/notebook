<script setup>
import { ref, reactive, computed } from 'vue';
import { ElMessageBox } from 'element-plus';
import { listTree } from '@/api/app-department';
import { listSimple } from '@/api/app-role';
import { listPage, save, update, remove } from '@/api/app-user';
import CmTable from '@/components/CmTable.vue';
import useTableHandlers from '@/utils/use-table-handlers';

const filters = reactive({
  name: ''
});
const form = reactive({
  id: '',
  name: '',
  deptId: '',
  email: '',
  mobile: '',
  roleIds: [],
  status: true
});
const {
  t,
  tableRef,
  dialogVisible,
  isEdit,
  formLoading,
  formRef,
  doSearch,
  doAdd,
  doEdit,
  doSubmit,
  doRemove,
  doClose
} = useTableHandlers(form);
const departmentData = ref([]);
const roles = ref([]);

const columns = computed(() => [
  { type: 'selection' },
  { prop: 'id', label: t('tableHeader.ID'), minWidth: 50 },
  { prop: 'name', label: t('tableHeader.username'), minWidth: 120 },
  {
    prop: 'departmentName',
    label: t('tableHeader.department'),
    minWidth: 120,
    showOverflowTooltip: true
  },
  {
    prop: 'roleNames',
    label: t('tableHeader.role'),
    minWidth: 100,
    showOverflowTooltip: true
  },
  {
    prop: 'email',
    label: t('tableHeader.email'),
    minWidth: 120,
    showOverflowTooltip: true
  },
  { prop: 'mobile', label: t('tableHeader.mobile'), minWidth: 100 },
  {
    prop: 'status',
    label: t('tableHeader.status'),
    minWidth: 70,
    formatter: (row) => {
      return row.status ? t('status.on') : t('status.off');
    }
  }
]);
const contactValidator = (rule, value, callback) => {
  if (!form.email && !form.mobile) {
    callback(new Error(t('form.emailOrMobile')));
  }
  (!form.mobile || !form.email) &&
    formRef.value.clearValidate(rule.field === 'email' ? 'mobile' : 'email');
  callback();
};
const rules = computed(() => {
  return {
    name: [
      {
        required: true,
        message: t('form.usernameHolder'),
        trigger: ['change', 'blur']
      }
    ],
    departmentId: [
      {
        required: true,
        message: t('form.departmentRequired'),
        trigger: ['change', 'blur']
      }
    ],
    email: [
      {
        type: 'email',
        message: t('form.emailError'),
        trigger: ['change', 'blur']
      },
      {
        validator: contactValidator,
        message: t('form.emailOrMobile'),
        trigger: ['change', 'blur']
      }
    ],
    mobile: [
      {
        pattern: /^1[3-9]\d{9}$/,
        message: t('form.mobileError'),
        trigger: ['change', 'blur']
      },
      {
        validator: contactValidator,
        message: t('form.emailOrMobile'),
        trigger: ['change', 'blur']
      }
    ],
    roleIds: [
      {
        required: true,
        message: t('form.roleIdsRequired'),
        trigger: ['change', 'blur']
      }
    ]
  };
});

function initFormRequest() {
  findDepartmentTree();
  findRoles();
}

function handleAdd(row) {
  initFormRequest();
  doAdd(row);
}

function handleEdit(row) {
  initFormRequest();
  doEdit(row);
  form.roleIds = row.roleIds.split(',').map((v) => +v);
  form.status = !!row.status;
}

function findDepartmentTree() {
  listTree().then((response) => {
    departmentData.value = response.data;
  });
}

function findRoles() {
  listSimple().then((response) => {
    roles.value = response.data;
  });
}

function handleDelete(ids, callback) {
  doRemove(remove, ids, callback);
}

function handleSubmit() {
  doSubmit({ save, update, getParams }, (response) => {
    if (!isEdit.value) {
      ElMessageBox.alert(
        `${t('tips.success')}${t('form.username')}: ${response.data.name},${t(
          'form.password'
        )}: ${response.data.password}`,
        t('tips.title'),
        {
          confirmButtonText: t('action.confirm')
        }
      );
    }
  });
}

function getParams() {
  const params = { ...form };
  if (!isEdit.value) {
    delete params.id;
  }
  params.roleIds = form.roleIds.join(',');
  return params;
}
</script>

<template>
  <div class="main-body">
    <div class="toolbar">
      <el-form :inline="true" :model="filters">
        <el-form-item>
          <el-input
            v-model="filters.name"
            :placeholder="t('tableHeader.username')"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button icon="search" type="primary" @click="doSearch">{{
            t('action.search')
          }}</el-button>
        </el-form-item>
        <el-form-item>
          <el-button icon="plus" type="primary" @click="handleAdd">{{
            t('action.add')
          }}</el-button>
        </el-form-item>
      </el-form>
    </div>
    <cm-table
      ref="tableRef"
      :get-page="listPage"
      :filters="filters"
      :columns="columns"
      @handleEdit="handleEdit"
      @handleDelete="handleDelete"
    />
  </div>

  <el-dialog
    :title="isEdit ? t('action.edit') : t('action.add')"
    v-model="dialogVisible"
    draggable
    width="50%"
    :close-on-click-modal="false"
    @close="doClose"
  >
    <el-form
      ref="formRef"
      :model="form"
      label-width="80px"
      :rules="rules"
      label-position="right"
    >
      <el-form-item :label="t('form.username')" prop="name">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item :label="t('form.department')" prop="departmentId">
        <el-cascader
          v-model="form.departmentId"
          :props="{
            label: 'name',
            value: 'id',
            checkStrictly: true,
            emitPath: false
          }"
          :options="departmentData"
          clearable
          filterable
          class="w100p"
        ></el-cascader>
      </el-form-item>
      <el-form-item :label="t('form.email')" prop="email">
        <el-input v-model="form.email"></el-input>
      </el-form-item>
      <el-form-item :label="t('form.mobile')" prop="mobile">
        <el-input v-model="form.mobile"></el-input>
      </el-form-item>
      <el-form-item :label="t('form.role')" prop="roleIds">
        <el-select
          v-model="form.roleIds"
          multiple
          :placeholder="t('form.choose')"
          style="width: 100%"
        >
          <el-option
            v-for="item in roles"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item :label="t('status.on')">
        <el-switch v-model="form.status"></el-switch>
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="doClose">{{ t('action.cancel') }}</el-button>
      <el-button type="primary" @click="handleSubmit" :loading="formLoading">{{
        t('action.submit')
      }}</el-button>
    </template>
  </el-dialog>
</template>
