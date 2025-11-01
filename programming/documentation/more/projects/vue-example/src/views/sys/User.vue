<script setup>
import { reactive, computed } from 'vue';
import { ElMessageBox } from 'element-plus';
import { listPage, save, update, setPassword, remove } from '@/api/sys-user';
import CmTable from '@/components/CmTable.vue';
import { roles } from '@/mock/data';
import useTableHandlers from '@/utils/use-table-handlers';

const filters = reactive({
  name: ''
});
const form = reactive({
  id: '',
  name: '',
  roleId: ''
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
  doClose,
  doRemove
} = useTableHandlers(form);

const operations = [
  {
    type: 'edit',
    show: (row) => row.createdBy !== 'system'
  },
  {
    type: 'delete',
    show: (row) => row.createdBy !== 'system'
  }
];

const columns = computed(() => [
  { type: 'selection', selectable: isSelectable },
  { prop: 'id', label: t('tableHeader.ID'), minWidth: 50 },
  { prop: 'name', label: t('tableHeader.username'), minWidth: 120 },
  { prop: 'roleName', label: t('tableHeader.group'), minWidth: 120 },
  { prop: 'createdBy', label: t('tableHeader.createdBy'), minWidth: 120 },
  { prop: 'ip', label: t('tableHeader.latestIp'), minWidth: 100 },
  { prop: 'visitTime', label: t('tableHeader.latestVisit'), minWidth: 120 }
]);
const rules = computed(() => {
  return {
    name: [
      {
        required: true,
        message: t('form.usernameHolder'),
        trigger: ['blur', 'change']
      }
    ],
    roleId: [
      {
        required: true,
        message: t('form.roleRequired'),
        trigger: ['blur', 'change']
      }
    ]
  };
});

function isSelectable(row) {
  return row.createdBy !== 'system';
}

function handleDelete(ids, callback) {
  doRemove(remove, ids, callback);
}

function handleSubmit() {
  doSubmit({ save, update }, (response) => {
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

function handleSetPassword() {
  setPassword({ ...form }).then((response) => {
    ElMessageBox.alert(
      `${t('tips.success')}${t('form.username')}: ${response.data.name},${t(
        'form.password'
      )}: ${response.data.password}`,
      t('tips.title'),
      {
        confirmButtonText: t('action.confirm')
      }
    );
  });
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
          <el-button icon="plus" type="primary" @click="doAdd">{{
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
      :operations="operations"
      @handleEdit="doEdit"
      @handleDelete="handleDelete"
    />
  </div>

  <el-dialog
    :title="isEdit ? t('action.edit') : t('action.add')"
    v-model="dialogVisible"
    draggable
    width="40%"
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
      <el-form-item :label="t('form.group')" prop="roleId">
        <el-select
          v-model="form.roleId"
          :placeholder="t('form.choose')"
          style="width: 100%"
        >
          <el-option
            v-for="item in roles"
            :key="item.name"
            :label="item.label"
            :value="item.name"
          ></el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="doClose">{{ t('action.cancel') }}</el-button>
      <el-button v-if="isEdit" type="primary" @click="handleSetPassword">{{
        t('form.resetPassword')
      }}</el-button>
      <el-button type="primary" @click="handleSubmit" :loading="formLoading">{{
        t('action.submit')
      }}</el-button>
    </template>
  </el-dialog>
</template>
