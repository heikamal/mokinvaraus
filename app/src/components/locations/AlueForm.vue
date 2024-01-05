<script>
const url = import.meta.env.VITE_API_URL.concat("/locations");
export default {
  data() {
    return {
      form: {
      name: '',
      },
    }
  },
  methods: {
    sendForm(event) {
      if (event) {
        event.preventDefault()
      }
      this.axios.post(url, this.form).then((response) => {
        console.log(response.data)
        this.$router.push('/locations')
      })
    }
  }
}
</script>

<template>
  <h1>Lisää uusi alue</h1>
  <section class="form">
    <div class="control">
      <label class="label">Nimi</label>
      <input v-model="form.name" class="input" type="text" placeholder="Alueen nimi">
    </div>
    <button @click="(event) => sendForm(event)">Lisää</button>
  </section>
</template>