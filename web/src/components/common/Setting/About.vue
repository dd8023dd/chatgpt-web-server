<script setup lang='ts'>
import { onMounted, ref } from 'vue'
import { NSpin } from 'naive-ui'
import { fetchChatConfig } from '@/api'
import pkg from '@/../package.json'

interface ConfigState {
  remainingCount?:number
  expireDate?:number
  message?: string
}

const loading = ref(false)

const config = ref<ConfigState>()

async function fetchConfig() {
  try {
    loading.value = true;
    const { data } = await fetchChatConfig<ConfigState>();
    config.value = data;
  }
  catch (error) {
    console.error(error);
  }
  finally {
    loading.value = false;
  }
}

onMounted(() => {
  fetchConfig()
})
</script>

<template>
  <NSpin :show="loading">
    <div class="p-4 space-y-4">
      <h2 class="text-xl font-bold">
        配置说明：
      </h2>
      <div class="p-2 space-y-2 rounded-md bg-neutral-100 dark:bg-neutral-700">
        <p>
          配置User key即可正常使用，正常对话一次消耗1次。
        </p>
                <p>
        图片对话一次消耗3次余额
        </p>
        <p>
         开启连续对话会加倍消耗次数，请谨慎开启。
        </p>
        <p>
          任何疑问可以添加：
        </p>
        <div class="flex items-center justify-center text-center">
        <div class="w-[300px]">
          <img src="../../../wx.png">
        </div>
      </div>
      </div>
      <p>余额：{{ config?.remainingCount ?? '-' }}</p>

    </div>
  </NSpin>
</template>
