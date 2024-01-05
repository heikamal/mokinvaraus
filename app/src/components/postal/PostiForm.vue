<script>
const url = import.meta.env.VITE_API_URL.concat("/postal");
export default {
  data() {
    return {
      form: {
      postalCode: '',
      city: ''
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
        this.$router.push("/postal")
      })
    }
  }
}
</script>

<template>
  <h1>Lisää uusi mökki</h1>
  <section class="form">
    <div class="control">
      <label class="label">Postinumero</label>
      <input v-model="form.postalCode" class="input" type="text" placeholder="Postinumero">
    </div>
    <div class="control">
      <label class="label">Toimipaikka</label>
      <input v-model="form.city" class="input" type="text" placeholder="Toimipaikka">
    </div>
    <button @click="(event) => sendForm(event)">Lisää</button>
  </section>
</template>