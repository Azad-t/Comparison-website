<script>
import TheWelcome from '../components/TheWelcome.vue'
import HelloWorld from '../components/HelloWorld.vue'

export default {
  name: 'HomeView',
  components: {
    TheWelcome,
    HelloWorld
  }
}
</script>

<template>
  <main>
    <TheWelcome />
    <HelloWorld />
  </main>
</template>
