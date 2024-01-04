<script>
export default {
  data() {
    return{
      listItems: [],
    }
  },
  methods: {
    async getData() {
      const res = await fetch("http://localhost:8080/api/cabins/"+ this.$route.params.id)
      const finalRes = await res.json();
      this.listItems = finalRes;
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
  <h1>Pöö</h1>
</template>