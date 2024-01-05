<script setup>
</script>

<template>
  <section id="navibar">
    <router-link to="/">Home</router-link>
    <router-link to="/cabins">Mökit</router-link>
    <router-link to="/locations">Alueet</router-link>
    <router-link to="/postal">Postinumerot</router-link>
    <router-link to="/customers">Asiakkaat</router-link>
    <router-link to="/invoices">Laskut</router-link>
    <router-link to="/reservations">Varaukset</router-link>
  </section>
  <router-view></router-view>
</template>
