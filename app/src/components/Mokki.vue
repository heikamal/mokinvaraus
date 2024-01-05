<script>
const url = import.meta.env.VITE_API_URL.concat("/cabins/");
export default {
  data() {
    return{
      object: {},
    }
  },
  methods: {
    async getData() {
      const res = await fetch(url.concat(this.$route.params.id))
      const finalRes = await res.json();
      this.object = finalRes;
      console.log(finalRes)
      console.log(this.$route.params.id)
    }
  },
  created() {
    this.getData()
    this.$watch(
      () => this.$route.params,
      (toParams, previousParams) => {
        this.getData()
      }
    )
  }
}
</script>

<template>
  <h1>{{ object.cabinName }}</h1>
  <p>
    Mökin id: {{ object.cabinId }}<br>
    Alue: {{ object.areaId }}<br>
    Osoite: {{ object.streetAddress }}<br>
    Postiosoite: {{ object.postalCode }}<br>
    Hinta: {{ object.price }}<br>
    Kuvaus: {{ object.description }}<br>
    Henkilömäärä: {{ object.personCount }}<br>
    Varustelu: {{ object.equipment }}<br>
  </p>
</template>