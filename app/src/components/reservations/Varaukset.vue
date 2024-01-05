<script>
const url = import.meta.env.VITE_API_URL.concat("/reservations");
export default {
  data() {
    return {
      listItems: [],
      columns: ['reservationId', 'customerId', 'cabinId', 'reservationDate', 'reservationConfirmedDate', 'reservationStartDate', 'reservationEndDate']
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
  <h1>Tietokannassa olevat varaukset:</h1>
  <table class="table">
    <thead>
      <tr>
        <th v-for="(column, index) in columns" :key="index">{{ column }}</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(item, index) in listItems" :key="item.reservationId">
        <td v-for="(column, indexColumn) in columns" :key="indexColumn">{{ item[column] }}</td>
      </tr>
    </tbody>
  </table>
  <router-link to="/cabins/new">Lisää uusi</router-link>
</template>