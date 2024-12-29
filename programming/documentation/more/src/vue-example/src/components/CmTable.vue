<script setup>
import {
  defineProps,
  defineEmits,
  defineExpose,
  ref,
  reactive,
  onMounted
} from 'vue';
import { useI18n } from 'vue-i18n';
import { ElMessage, ElMessageBox } from 'element-plus';

const props = defineProps({
  getPage: Function,
  filters: Object,
  showPagination: {
    type: Boolean,
    default: true
  },
  columns: Array,
  showOperation: {
    type: Boolean,
    default: true
  },
  operations: {
    type: Array,
    default: () => {
      return [
        {
          type: 'edit'
        },
        {
          type: 'delete'
        }
      ];
    }
  },
  oprWidth: {
    type: Number,
    default: 185
  },
  showBatchDelete: {
    type: Boolean,
    default: true
  }
});

const emit = defineEmits(['handleEdit', 'handleDelete']);

const { t } = useI18n();
const loading = ref(false);
const pageRequest = reactive({
  pageNum: 1,
  pageSize: 10
});
const data = ref({});
const selections = ref([]);

function findPage() {
  if (!props.getPage) return;

  loading.value = true;
  const request = props.getPage({
    ...pageRequest,
    ...(props.filters || {})
  });
  if (Object(request).constructor === Promise) {
    request
      .then((response) => {
        if (response.data instanceof Array) {
          data.value = {
            content: response.data,
            totalSize: response.data.length
          };
        } else {
          data.value = response.data;
        }
      })
      .catch(() => {
        data.value = {};
      })
      .finally(() => {
        loading.value = false;
      });
  }
}

function reload() {
  handlePageChange(1);
}

function handleSizeChange(pageSize) {
  pageRequest.pageSize = pageSize;
  pageRequest.pageNum = 1;
  findPage();
}

function handlePageChange(pageNum) {
  pageRequest.pageNum = pageNum;
  findPage();
}

function isShow(showFn, row) {
  if (showFn && typeof showFn === 'function') {
    return showFn(row);
  }
  return true;
}

function isDisabled(disableFn, row) {
  if (disableFn && typeof disableFn === 'function') {
    return disableFn(row);
  }
  return false;
}

function handleEdit(row) {
  emit('handleEdit', row);
}

function handleDelete(row) {
  onDelete(row.id);
}

function selectionChange(slts) {
  selections.value = slts;
}

function handleBatchDelete() {
  let ids = selections.value.map((item) => item.id).toString();
  onDelete(ids);
}

function onDelete(ids) {
  ElMessageBox.confirm(t('tips.deleteConfirm'), t('tips.deleteTitle'), {
    confirmButtonText: t('action.confirm'),
    cancelButtonText: t('action.cancel'),
    type: 'warning',
    draggable: true
  })
    .then(() => {
      const callback = () => {
        ElMessage({ message: t('tips.success'), type: 'success' });
        reload();
      };
      emit('handleDelete', ids, callback);
    })
    .catch(() => {});
}

defineExpose({
  refresh: findPage,
  reload
});

onMounted(() => {
  reload();
});
</script>

<template>
  <div v-loading="loading" class="cm-table">
    <el-table
      :data="data.content"
      class="cm-table__tb"
      v-bind="$attrs"
      @selection-change="selectionChange"
    >
      <el-table-column
        v-for="column in columns"
        :key="column.prop"
        v-bind="column"
      >
        <template v-if="$slots[`${column.prop}Slot`]" #default="scope">
          <slot :name="`${column.prop}Slot`" :scope="scope"></slot>
        </template>
      </el-table-column>
      <el-table-column
        v-if="showOperation"
        fixed="right"
        :label="t('action.operation')"
        :width="oprWidth"
      >
        <template #default="{ row }">
          <template v-for="(opr, i) in operations" :key="i">
            <template v-if="isShow(opr.show, row)">
              <el-button
                v-if="opr.type === 'edit'"
                link
                class="primary"
                :disabled="isDisabled(opr.disabled, row)"
                @click="handleEdit(row)"
                >{{ t('action.edit') }}</el-button
              >
              <el-button
                v-else-if="opr.type === 'delete'"
                link
                class="danger"
                :disabled="isDisabled(opr.disabled, row)"
                @click="handleDelete(row)"
                >{{ t('action.delete') }}</el-button
              >
              <el-button
                v-else
                link
                :disabled="isDisabled(opr.disabled, row)"
                @click="opr.onClick(row)"
                >{{ opr.label }}</el-button
              >
            </template>
          </template>
        </template>
      </el-table-column>
    </el-table>
    <div class="cm-table__toolbar">
      <el-button
        v-if="showBatchDelete"
        type="danger"
        :disabled="selections.length === 0"
        @click="handleBatchDelete()"
        >{{ t('action.batchDelete') }}</el-button
      >
      <el-pagination
        v-if="showPagination"
        class="cm-table__pagination"
        v-model:currentPage="pageRequest.pageNum"
        v-model:page-size="pageRequest.pageSize"
        :total="data.totalSize || 0"
        :page-sizes="[10, 20, 50, 100, 200]"
        layout="total, prev, pager, next, sizes, jumper"
        @size-change="handleSizeChange"
        @current-change="handlePageChange"
      ></el-pagination>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.cm-table__tb {
  border: 1px solid #eee;
  border-bottom: none;
  min-width: 100%;
}

.cm-table__toolbar {
  padding: 10px 5px;

  &:after {
    content: '';
    display: table;
    clear: both;
  }
}

.cm-table__pagination {
  float: right;
  padding-right: 0;
}

.primary {
  color: var(--el-color-primary) !important;
}

.danger {
  color: var(--el-color-danger) !important;
}
</style>
