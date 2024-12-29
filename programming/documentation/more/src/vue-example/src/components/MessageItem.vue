<script setup>
import { defineProps, ref, toRef, reactive, onMounted } from 'vue';
import { useI18n } from 'vue-i18n';
import { ElMessage, ElMessageBox } from 'element-plus';
import { list, read, readAll, remove } from '@/api/message';

const props = defineProps({
  type: String
});

const { t } = useI18n();
const messages = ref([]);
const pageRequest = reactive({
  pageNum: 1,
  pageSize: 20
});
const totalSize = ref(0);
const type = toRef(props, 'type');

function getMessage() {
  const { pageNum, pageSize } = pageRequest;
  list({ type: type.value, pageNum, pageSize })
    .then((response) => {
      messages.value = response.data.content || [];
      totalSize.value = response.data.totalSize || 0;
    })
    .catch(() => {
      messages.value = [];
    });
}

function readMessage(id) {
  read({ type: type.value, id }).then(() => {
    ElMessage.success(t('tips.success'));
    getMessage();
  });
}

function readAllMessage() {
  readAll({ type: type.value }).then(() => {
    ElMessage.success(t('tips.success'));
    getMessage();
  });
}

function deleteMessage(id) {
  ElMessageBox.confirm(t('tips.deleteConfirm'), t('tips.deleteTitle'), {
    confirmButtonText: t('action.confirm'),
    cancelButtonText: t('action.cancel'),
    type: 'warning',
    draggable: true
  }).then(() => {
    remove({ type: type.value, id }).then(() => {
      ElMessage.success(t('tips.success'));
      getMessage();
    });
  });
}

function handleSizeChange(size) {
  pageRequest.pageSize = size;
  pageRequest.pageNum = 1;
  getMessage();
}

function handlePageChange(num) {
  pageRequest.pageNum = num;
  getMessage();
}

onMounted(() => {
  getMessage();
});
</script>

<template>
  <div v-if="messages.length">
    <div class="txt-r">
      <el-button plain type="primary" @click="readAllMessage">{{
        t('action.markedAllRead')
      }}</el-button>
    </div>
    <div class="events">
      <div v-for="(item, i) in messages" class="event" :key="i">
        <div class="label">
          <el-badge :is-dot="!item.isRead">
            <el-icon>
              <i-ep-comment />
            </el-icon>
          </el-badge>
        </div>
        <div class="content">
          <div class="summary">
            {{ item.title }}
            <div class="date">{{ item.date }}</div>
          </div>
          <div class="extra text">{{ item.content }}</div>
          <div class="actions">
            <el-button
              link
              class="action a-read"
              :disabled="item.isRead"
              @click="readMessage(item.id)"
            >
              <i class="el-icon-check"></i>
              {{ t('action.markedRead') }}
            </el-button>
            <el-button
              link
              class="action a-delete"
              @click="deleteMessage(item.id)"
            >
              <i class="el-icon-delete"></i>
              {{ t('action.delete') }}
            </el-button>
          </div>
        </div>
      </div>
    </div>
    <div class="flex-center">
      <el-pagination
        v-model:currentPage="pageRequest.pageNum"
        v-mode:page-size="pageRequest.pageSize"
        :total="totalSize || 0"
        layout="prev, pager, next"
        @size-change="handleSizeChange"
        @current-change="handlePageChange"
      ></el-pagination>
    </div>
  </div>
  <div v-else>
    <div class="nodata txt-c">{{ t('nodata') }}</div>
  </div>
</template>

<style lang="scss" scoped>
.nodata {
  line-height: 250px;
  color: #666;
}

.txt-r {
  text-align: right;
}

.events {
  .event {
    display: table;
    padding: 10px 0;

    .label {
      display: table-cell;
      vertical-align: top;
      padding: 5px;

      i {
        background-color: #24cde4;
        color: #fff;
        display: block;
        width: 32px;
        height: 32px;
        line-height: 32px;
        text-align: center;
        border-radius: 50%;
      }
    }

    .content {
      display: table-cell;
      vertical-align: top;
      padding: 0 10px;

      .summary {
        margin: 0 0 10px;
        font-size: 14px;
        font-weight: 700;
        color: rgba(0, 0, 0, 0.8);

        .date {
          display: inline-block;
          font-weight: 400;
          font-size: 12px;
          font-style: normal;
          margin: 0 0 0 0.5em;
          padding: 0;
          color: rgba(0, 0, 0, 0.4);
        }
      }

      .extra.text {
        padding: 7px 14px;
        border-left: 3px solid rgba(0, 0, 0, 0.2);
        font-size: 14px;
        max-width: 500px;
        line-height: 1.33;
        color: #666;
      }

      .actions {
        display: flex;
        color: #999;

        .action {
          margin-right: 10px;
          font-size: 12px;
          cursor: pointer;

          i {
            margin-right: 5px;
          }
        }
      }
    }
  }
}
</style>
