<script>
const url = import.meta.env.VITE_API_URL.concat("/locations");
export default {
  data() {
    return {
      listItems: []
    }
  },
  methods: {
    async getData() {
      this.axios.get(url).then((response) => {
        //console.log(response.data)
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
  <h1>Tietokannassa olevat alueet:</h1>
  <div v-for="item in listItems" :key="item.areaId">
    {{item.areaId}}: {{item.name}}
  </div>
  <router-link to="/locations/new">Lisää uusi</router-link>
</template>