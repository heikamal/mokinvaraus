<script>
export default {
  data() {
    return {
      listItems: []
    }
  },
  methods: {
    async getData() {
      const res = await fetch(import.meta.env.VITE_API_URL.concat("/cabins"));
      const finalRes = await res.json();
      this.listItems = finalRes;
      console.log(finalRes);
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