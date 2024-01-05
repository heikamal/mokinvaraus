<script>
const url = import.meta.env.VITE_API_URL.concat("/customers");
export default {
  data() {
    return {
      form: {
      email: '',
      firstName: '',
      lastName: '',
      phoneNumber: '',
      postalCode: '',
      streetAddress: '',
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
        this.$router.push("/customers/".concat(response.data.customerId))
      })
    }
  }
}
</script>

<template>
  <h1>Lisää uusi asiakas</h1>
  <section class="form">
    <div class="control">
      <label class="label">Etunimi</label>
      <input v-model="form.firstName" class="input" type="text" placeholder="Etunimi">
    </div>
    <div class="control">
      <label class="label">Sukunimi</label>
      <input v-model="form.lastName" class="input" type="text" placeholder="Sukunimi">
    </div>
    <div class="control">
      <label class="label">Katuosoite</label>
      <input v-model="form.streetAddress" class="input" type="text" placeholder="Osoite">
    </div>
    <div class="control">
      <label class="label">Postinumero</label>
      <input v-model="form.postalCode" class="input" type="text" placeholder="Postinumero">
    </div>
    <div class="control">
      <label class="label">Puhelinnumero</label>
      <input v-model="form.phoneNumber" class="input" type="text" placeholder="Numero">
    </div>
    <div class="control">
      <label class="label">Sähköpostiosoite</label>
      <input v-model="form.email" class="input" type="text" placeholder="etunimi.sukunimi@email.com">
    </div>
    <button @click="(event) => sendForm(event)">Lisää</button>
  </section>
</template>