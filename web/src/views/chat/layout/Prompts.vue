<script lang="ts">
import {defineComponent, ref} from 'vue'
import {usePromptStore} from "@/store";
import {storeToRefs} from "pinia";

const promptStore = usePromptStore()
const {prompt} = storeToRefs<any>(promptStore)

function getOptions(depth = 2, iterator = 1, prefix = '') {
	const {prompts, tags} = prompt.value
	return Object.keys(tags).map(key => {
		const {label} = tags[key]
		const currentPrompts = prompts.filter(item => {
			return item.tags?.includes(key);
		});
		return {
			value: `${key}`,
			label: `${label}`,
			children: currentPrompts.map(item => {
				const {title} = item
				return {
					value: `${title}`,
					label: `${title}`,
				}
			})
		}
	})
}

export default defineComponent({
	setup() {
		return {
			value: ref(null),
			options: getOptions()
		}
	}
})
</script>

<template>
	<n-space vertical>
		<n-cascader
			v-model:value="value"
			placeholder="prompt select"
			:options="options"
			check-strategy="child"
			size="small"
		/>
	</n-space>
</template>
