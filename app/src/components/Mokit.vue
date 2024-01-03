<script>
export default {
  data() {
    return {
      listItems: []
    }
  },
  methods: {
    async getData() {
      const res = await fetch("http://localhost:8080/api/mokit");
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
    {{item.cabinName}}
  </div>
</template>