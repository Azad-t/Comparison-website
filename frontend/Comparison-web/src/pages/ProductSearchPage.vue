<template>
    <div class="product-search-page">
        <ProductSearch />
    </div>
</template>

<script>
import ProductSearch from "@/components/ProductSearch.vue";

export default {
    name: "ProductSearchPage",
    components: {
        ProductSearch
    }
};
</script>

<style scoped>  
.product-search-page {
    padding: 20px;
}
</style>