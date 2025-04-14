import { ref } from 'vue';
import { defineStore } from 'pinia';
import { findAllTypes } from '@/api';

export const useHeadlineTypesStore = defineStore('headlineTypes', () => {
  const headlineTypes = ref([
    {
      tid: null,
      tname: '',
    },
  ]);
  const isUpdate = ref(false);

  const getHeadlineTypesAction = async () => {
    // 更新过后不再更新
    if (isUpdate.value) return;

    const result = await findAllTypes();
    headlineTypes.value = result.types;
    isUpdate.value = true;
  };

  const resetHeadlineTypesAction = () => {
    headlineTypes.value = [
      {
        tid: null,
        tname: '',
      },
    ];
  };

  const getTidAction = (tname) => {
    const type = headlineTypes.value.find((item) => item.tname === tname);
    return type ? type.tid : null;
  };

  const getTnameAction = (tid) => {
    const type = headlineTypes.value.find((item) => item.tid === tid);
    return type ? type.tname : '';
  };

  return {
    headlineTypes,
    getHeadlineTypesAction,
    resetHeadlineTypesAction,
    getTidAction,
    getTnameAction,
  };
});
