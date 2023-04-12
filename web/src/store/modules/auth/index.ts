import { defineStore } from 'pinia'
import {getToken, removeToken, setContent, setToken} from './helper'
import { store } from '@/store'
import { fetchSession, getConfigs } from '@/api'

export interface AuthState {
  token: string | undefined
  session: { auth: boolean } | null
  prompts: []
  content: string | undefined
}

export const useAuthStore = defineStore('auth-store', {
  state: (): AuthState => ({
    token: getToken(),
    session: null,
    prompts: [],
    content: undefined
  }),

  actions: {
    async getConfig() {
      try {
        const data =await getConfigs<{ auth: boolean }>()
				setContent(data)
        this.content = data
        return data
      }
      catch (error) {
        return error
      }
    },
    async getSession() {
      try {
        const { data } = await fetchSession<{ auth: boolean }>()
        this.session = { ...data }
        return Promise.resolve(data)
      }
      catch (error) {
        return Promise.reject(error)
      }
    },
    setToken(token: string) {
      this.token = token
      setToken(token)
    },

    removeToken() {
      this.token = undefined
      removeToken()
    },
  },
})

export function useAuthStoreWithout() {
  return useAuthStore(store)
}
