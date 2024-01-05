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
      this.axios.get(url.concat(this.$route.params.id)).then((response) => {
        //console.log(response.data)
        this.object = response.data
      })
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