<template>
    <div style="overflow-y: visible">
        <nav-header></nav-header>
        <router-view></router-view>
        <nav-footer></nav-footer>
    </div>
</template>

<script>
    import NavHeader from './NavHeader';
    import NavFooter from './NavFooter';
    export default {
        name: 'Index',
        components: { NavFooter, NavHeader }
    };
</script>


