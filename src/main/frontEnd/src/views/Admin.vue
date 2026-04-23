<script lang="ts" setup>
import axios from 'axios'
import { ref } from 'vue'
import { useCounterStore } from '@/stores/counter'
const loginUser = useCounterStore()

</script>

<template>
  <div class="admin">
    <h1>This is an about page</h1>
  </div>
</template>

<style>
</style>
