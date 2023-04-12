import {defineStore} from 'pinia'
import type {PromptStore} from './helper'
import {getLocalPromptList, setLocalPromptList} from './helper'
import {fetchPrompts} from "@/api";

export const usePromptStore = defineStore('prompt-store', {
	state: () => {
		return {prompt: {tags: {}, prompts: []}}
	},

	actions: {
		async getPromptList() {
			// let data = []
			// try {
			// 	data = await fetchPrompts()
			// 	this.$state.prompt = data
			// } catch (error) {
			// 	this.$state.prompt = error
			// }

		},
		getPrompts() {
			return this.$state.prompt
		}
	},
})
