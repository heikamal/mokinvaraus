<script>
const url = import.meta.env.VITE_API_URL.concat("/cabins");
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
  <h1>Tietokannassa olevat mökit:</h1>
  <div v-for="item in listItems" :key="item.cabinId">
    <router-link :to="'/cabins/' + item.cabinId">{{item.cabinName}}</router-link>
  </div>
  <router-link to="/cabins/new">Lisää uusi</router-link>
</template>