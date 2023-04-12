import { ref } from 'vue'
import { useMessage } from 'naive-ui'
import { t } from '@/locales'

export function useTypeContext() {
  const ms = useMessage()
  const usingTypeContext = ref<string>('TEXT')

  function toggleTypeContext() {
    usingTypeContext.value = usingTypeContext.value === 'TEXT' ? 'IMAGE' : 'TEXT'
    if (usingTypeContext.value === 'IMAGE')
      ms.success(t('chat.turnOnImage'))
  }

  return {
    usingTypeContext,
    toggleTypeContext,
  }
}
