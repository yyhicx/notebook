<script setup>
import { ref, reactive, computed } from 'vue';
import { ElMessage } from 'element-plus';
import { listTree } from '@/api/app-resource';
import { listPage, save, update, remove, bindResource } from '@/api/app-role';
import CmTable from '@/components/CmTable.vue';
import useTableHandlers from '@/utils/use-table-handlers';

const filters = reactive({
  name: ''
});
const form = reactive({
  id: '',
  name: '',
  remark: ''
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
const resourceTreeData = ref([]);
const bindDialogVisible = ref(false);
const defaultCheckedKeys = ref([]);
const bindLoading = ref(false);
const treeRef = ref();

const columns = computed(() => [
  { type: 'selection' },
  { prop: 'id', label: t('tableHeader.ID'), minWidth: 50 },
  { prop: 'name', label: t('tableHeader.roleName'), minWidth: 120 },
  {
    prop: 'remark',
    label: t('tableHeader.remark'),
    minWidth: 120,
    showOverflowTooltip: true
  },
  { prop: 'createdBy', label: t('tableHeader.createdBy'), minWidth: 120 },
  { prop: 'createdTime', label: t('tableHeader.createdTime'), minWidth: 160 },
  { prop: 'lastUpdatedBy', label: t('tableHeader.updatedBy'), minWidth: 120 },
  {
    prop: 'lastUpdatedTime',
    label: t('tableHeader.updatedTime'),
    minWidth: 160
  }
]);
const operations = computed(() => [
  {
    type: 'edit'
  },
  {
    label: t('action.bindResource'),
    onClick: handleBindResource
  },
  {
    type: 'delete'
  }
]);
const rules = computed(() => {
  return {
    name: [{ required: true, message: t('form.nameRequired'), trigger: 'blur' }]
  };
});

function handleDelete(ids, callback) {
  doRemove(remove, ids, callback);
}

function handleSubmit() {
  doSubmit({ save, update });
}

function handleBindResource(row) {
  bindDialogVisible.value = true;
  getResourceTree();
  defaultCheckedKeys.value = row.resourceIds ? row.resourceIds.split(',') : [];
}

function getResourceTree() {
  listTree().then((response) => {
    resourceTreeData.value = response.data;
  });
}

function onBindResourceConfirm() {
  bindLoading.value = true;
  bindResource({ ids: treeRef.value.getCheckedKeys().toString() })
    .then(() => {
      ElMessage({
        message: t('tips.success'),
        type: 'success',
        showClose: true
      });
      bindDialogVisible.value = false;
    })
    .finally(() => {
      bindLoading.value = false;
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
    <el-form ref="formRef" :model="form" label-width="80px" :rules="rules">
      <el-form-item :label="t('tableHeader.roleName')" prop="name">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item :label="t('tableHeader.remark')" prop="remark">
        <el-input v-model="form.remark" type="textarea"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="doClose">{{ t('action.cancel') }}</el-button>
        <el-button
          type="primary"
          @click="handleSubmit"
          :loading="formLoading"
          >{{ t('action.submit') }}</el-button
        >
      </div>
    </template>
  </el-dialog>

  <el-dialog
    :title="t('action.bindResource')"
    width="40%"
    draggable
    v-model="bindDialogVisible"
    :close-on-click-modal="false"
  >
    <el-tree
      ref="treeRef"
      :data="resourceTreeData"
      show-checkbox
      node-key="id"
      default-expand-all
      :default-checked-keys="defaultCheckedKeys"
      :props="{ label: 'displayName' }"
    />
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="bindDialogVisible = false">{{
          t('action.cancel')
        }}</el-button>
        <el-button
          type="primary"
          @click="onBindResourceConfirm"
          :loading="bindLoading"
          >{{ t('action.submit') }}</el-button
        >
      </div>
    </template>
  </el-dialog>
</template>
