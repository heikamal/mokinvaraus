<script>
const url = import.meta.env.VITE_API_URL.concat("/customers/");
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
  <h1>{{ object.firstName }} {{ object.lastName }}</h1>
  <p>
    Asiakas-ID: {{ object.customerId }}<br>
    Sähköpostiosoite: {{ object.email }}<br>
    Puhelinnumero: {{ object.phoneNumber }}<br>
    Katuosoite: {{ object.streetAddress }}<br>
    Postinumero: {{ object.postalCode }}<br>
  </p>
</template>