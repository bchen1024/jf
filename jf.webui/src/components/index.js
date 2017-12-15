import JFForm from './form/JFForm.vue';
import JFGrid from './grid/JFGrid.vue';

const jfviews={
    JFForm,
    JFGrid
};

const install=function(Vue){
    Object.keys(jfviews).forEach(key => {
        Vue.component(key, jfviews[key]);
    });
};
// auto install
if (typeof window !== 'undefined' && window.Vue) {
    install(window.Vue);
}
export default {jfviews,install};