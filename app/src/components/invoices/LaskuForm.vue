<script>
const url = import.meta.env.VITE_API_URL.concat("/invoices");
export default {
  data() {
    return {
      form: {
      reservationId: '',
      amount: '',
      tax: '',
      },
    }
  },
  methods: {
    sendForm(event) {
      if (event) {
        event.preventDefault()
      }
      this.axios.post(url, this.form).then((response) => {
        //console.log(response.data)
        this.$router.push("/invoices")
      })
    }
  }
}
</script>

<template>
  <h1>Lisää uusi lasku</h1>
  <section class="form">
    <div class="control">
      <label class="label">Varauksen ID</label>
      <input v-model="form.reservationId" class="input" type="text" placeholder="ID">
    </div>
    <div class="control">
      <label class="label">Veroton hinta</label>
      <input v-model="form.amount" class="input" type="text" placeholder="Hinta">
    </div>
    <div class="control">
      <label class="label">Arvonlisävero</label>
      <input v-model="form.tax" class="input" type="text" placeholder="Hinta * alv%">
    </div>
    <button @click="(event) => sendForm(event)">Lisää</button>
  </section>
</template>