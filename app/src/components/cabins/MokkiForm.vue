<script>
const url = import.meta.env.VITE_API_URL.concat("/cabins");
export default {
  data() {
    return {
      form: {
      cabinName: '',
      areaId: '',
      description: '',
      equipment: '',
      postalCode: '',
      streetAddress: '',
      personCount: '',
      price: '',
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
        this.$router.push("/cabins/".concat(response.data.cabinId))
      })
    }
  }
}
</script>

<template>
  <h1>Lisää uusi mökki</h1>
  <section class="form">
    <div class="control">
      <label class="label">Nimi</label>
      <input v-model="form.cabinName" class="input" type="text" placeholder="Mökin nimi">
    </div>
    <div class="control">
      <label class="label">Alue ID</label>
      <input v-model="form.areaId" class="input" type="text" placeholder="Alueen id">
    </div>
    <div class="control">
      <label class="label">Osoite</label>
      <input v-model="form.streetAddress" class="input" type="text" placeholder="Mökin osoite">
    </div>
    <div class="control">
      <label class="label">Postinumero</label>
      <input v-model="form.postalCode" class="input" type="text" placeholder="Mökin postinumero">
    </div>
    <div class="control">
      <label class="label">Kuvaus</label>
      <input v-model="form.description" class="input" type="text" placeholder="Mökin kuvaus">
    </div>
    <div class="control">
      <label class="label">Varustelu</label>
      <input v-model="form.equipment" class="input" type="text" placeholder="Mökin varustelu">
    </div>
    <div class="control">
      <label class="label">Henkilömäärä</label>
      <input v-model="form.personCount" class="input" type="text" placeholder="Mökin henkilömäärä">
    </div>
    <div class="control">
      <label class="label">Hinta</label>
      <input v-model="form.price" class="input" type="text" placeholder="Mökin hinta">
    </div>
    <button @click="(event) => sendForm(event)">Lisää</button>
  </section>
</template>