import { createI18n } from 'vue-i18n';
import zhCn from 'element-plus/dist/locale/zh-cn.mjs';
import en from 'element-plus/dist/locale/en.mjs';
import localZhCn from '@/i18n/language/zh-cn';
import localEn from '@/i18n/language/en';

export const elementLocales = {
  'zh-cn': zhCn,
  en: en
};

const i18n = createI18n({
  legacy: false,
  locale: 'zh-cn',
  fallbackLocale: 'en',
  messages: {
    'zh-cn': localZhCn,
    en: localEn
  }
});

export default i18n;
