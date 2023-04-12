import type {Router} from 'vue-router'
import {useAuthStoreWithout} from '@/store/modules/auth'
import {usePromptStore} from "@/store";

export function setupPageGuard(router: Router) {
	router.beforeEach(async (from, to, next) => {
		const promptStore = usePromptStore()
		promptStore.getPromptList()
		next()
	})
}
