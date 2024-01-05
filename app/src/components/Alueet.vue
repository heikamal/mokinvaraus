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
      const res = await fetch(url);
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
  <h1>Tietokannassa olevat alueet:</h1>
  <div v-for="item in listItems" :key="item.areaId">
    {{item.areaId}}: {{item.name}}
  </div>
  <router-link to="">Lisää uusi</router-link>
</template>