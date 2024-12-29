<script setup>
import { reactive, computed } from 'vue';
import { listPage, save, update, remove } from '@/api/sys-notice';
import CmTable from '@/components/CmTable.vue';
import useTableHandlers from '@/utils/use-table-handlers';

const filters = reactive({
  title: ''
});
const form = reactive({
  id: '',
  title: '',
  content: '',
  isPublish: false
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
    disabled: (row) => !!row.publishTime
  },
  {
    type: 'delete'
  }
];

const columns = computed(() => [
  { type: 'selection' },
  { prop: 'id', label: t('tableHeader.ID'), minWidth: 50 },
  {
    prop: 'title',
    label: t('tableHeader.title'),
    minWidth: 120,
    showOverflowTooltip: true
  },
  {
    prop: 'content',
    label: t('tableHeader.content'),
    minWidth: 120,
    showOverflowTooltip: true
  },
  { prop: 'publishTime', label: t('tableHeader.publishTime'), minWidth: 120 },
  { prop: 'createdTime', label: t('tableHeader.createdTime'), minWidth: 120 },
  { prop: 'createdBy', label: t('tableHeader.createdBy'), minWidth: 120 }
]);
const rules = computed(() => {
  return {
    title: [
      {
        required: true,
        message: t('form.titleRequired'),
        trigger: ['blur', 'change']
      },
      {
        min: 2,
        max: 60,
        message: t('form.titleError'),
        trigger: ['blur', 'change']
      }
    ],
    content: [
      {
        required: true,
        message: t('form.contentRequired'),
        trigger: ['blur', 'change']
      },
      {
        min: 2,
        max: 300,
        message: t('form.contentError'),
        trigger: ['blur', 'change']
      }
    ]
  };
});

function handleSubmit() {
  doSubmit({ save, update });
}

function handleDelete(ids, callback) {
  doRemove(remove, ids, callback);
}
</script>

<template>
  <div class="main-body">
    <div class="toolbar">
      <el-form :inline="true" :model="filters">
        <el-form-item>
          <el-input
            v-model="filters.title"
            :placeholder="t('tableHeader.title')"
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
      ref="formRef"
      :model="form"
      :rules="rules"
      label-width="80px"
      label-position="right"
    >
      <el-form-item :label="t('tableHeader.title')" prop="title">
        <el-input v-model="form.title"></el-input>
      </el-form-item>
      <el-form-item :label="t('tableHeader.content')" prop="content">
        <el-input
          type="textarea"
          v-model="form.content"
          :words-limit="300"
        ></el-input>
      </el-form-item>
      <el-form-item :label="t('form.publish')">
        <el-switch v-model="form.isPublish"></el-switch>
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="doClose">{{ t('action.cancel') }}</el-button>
      <el-button type="primary" :loading="formLoading" @click="handleSubmit">{{
        t('action.submit')
      }}</el-button>
    </template>
  </el-dialog>
</template>
