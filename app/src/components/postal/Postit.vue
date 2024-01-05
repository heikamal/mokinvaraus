<script>
const url = import.meta.env.VITE_API_URL.concat("/postal");
export default {
  data() {
    return {
      listItems: []
    }
  },
  methods: {
    async getData() {
      this.axios.get(url).then((response) => {
        console.log(response.data)
        this.listItems = response.data
      })
    }
  },
  mounted() {
    this.getData()
  }
}
</script>

<template>
  <h1>Tietokannassa olevat postinumerot:</h1>
  <div v-for="item in listItems" :key="item.postalCode">
    {{item.postalCode}}: {{item.city}}
  </div>
  <router-link to="">Lisää uusi</router-link>
</template>