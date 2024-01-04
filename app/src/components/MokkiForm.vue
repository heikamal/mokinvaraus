<script>
export default {
  data() {
    return {
      form: {
      name: '',
      },
    }
  }
}
</script>

<template>
  <h1>Lisää uusi mökki</h1>
  <section class="form">
    <label class="label">Nimi</label>
    <div class="control">
      <input v-model="form.name" class="input" type="text" placeholder="Mökin nimi">
    </div>
  </section>
</template>