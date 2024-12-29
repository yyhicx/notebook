<script setup>
import { ref, reactive, computed } from 'vue';
import {
  listTree,
  listOtherTreeById,
  save,
  update,
  remove
} from '@/api/app-department';
import CmTable from '@/components/CmTable.vue';
import useTableHandlers from '@/utils/use-table-handlers';

const filters = reactive({
  name: ''
});
const form = reactive({
  id: '',
  name: '',
  parentId: null
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
  doRemove,
  doSubmit,
  doClose
} = useTableHandlers(form);
const departmentData = ref([]);

const columns = computed(() => [
  { prop: 'id', label: t('tableHeader.ID') },
  { prop: 'name', label: t('tableHeader.name') },
  { prop: 'createdBy', label: t('tableHeader.createdBy') },
  { prop: 'createdTime', label: t('tableHeader.createdTime'), minWidth: 160 },
  { prop: 'lastUpdatedBy', label: t('tableHeader.updatedBy') },
  {
    prop: 'lastUpdatedTime',
    label: t('tableHeader.updatedTime'),
    minWidth: 160
  }
]);

const rules = computed(() => {
  return {
    name: [
      {
        required: true,
        message: t('form.usernameHolder'),
        trigger: ['change', 'blur']
      }
    ]
  };
});

function initFormRequest(row) {
  listOtherTreeById(row ? { id: row.id } : null).then((response) => {
    departmentData.value = response.data;
  });
}

function handleAdd() {
  initFormRequest();
  doAdd();
}

function handleEdit(row) {
  initFormRequest(row);
  doEdit(row);
}

function handleDelete(ids, callback) {
  doRemove(remove, ids, callback);
}

function handleSubmit() {
  doSubmit({ save, update });
}
</script>

<template>
  <div class="main-body">
    <div class="toolbar">
      <el-form :inline="true" :model="filters">
        <el-form-item>
          <el-input
            v-model="filters.name"
            :placeholder="t('tableHeader.name')"
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
      rowKey="id"
      ref="tableRef"
      :get-page="listTree"
      :filters="filters"
      :columns="columns"
      :showBatchDelete="false"
      :showPagination="false"
      @handleEdit="handleEdit"
      @handleDelete="handleDelete"
    ></cm-table>
  </div>
  <el-dialog
    :title="isEdit ? t('action.edit') : t('action.add')"
    width="40%"
    draggable
    v-model="dialogVisible"
    :close-on-click-modal="false"
    @close="doClose"
  >
    <el-form
      :model="form"
      :rules="rules"
      ref="formRef"
      @keyup.enter="handleSubmit"
      label-width="80px"
    >
      <el-form-item :label="t('tableHeader.name')" prop="name">
        <el-input
          v-model="form.name"
          :placeholder="t('tableHeader.name')"
        ></el-input>
      </el-form-item>
      <el-form-item :label="t('form.parent')" prop="parentId">
        <el-cascader
          v-model="form.parentId"
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
    </el-form>
    <template #footer>
      <el-button @click="doClose">{{ t('action.cancel') }}</el-button>
      <el-button type="primary" @click="handleSubmit">{{
        t('action.confirm')
      }}</el-button>
    </template>
  </el-dialog>
</template>
