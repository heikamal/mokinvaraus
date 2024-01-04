<script>
import { toRaw } from 'vue'
export default {
  data() {
    return {
      form: {
      cabinName: '',
      },
    }
  },
  methods: {
    sendForm(event) {
      if (event) {
        event.preventDefault()
      }
      console.log(toRaw(this.form))
    }
  }
}
</script>

<template>
  <h1>Lisää uusi mökki</h1>
  <section class="form">
    <label class="label">Nimi</label>
    <div class="control">
      <input v-model="form.cabinName" class="input" type="text" placeholder="Mökin nimi">
    </div>
    <button @click="(event) => sendForm(event)">Lisää</button>
  </section>
</template>