import Vue from 'vue'
import 'api/resource'
import router from 'router/router'
import '@babel/polyfill'
import store from 'store/store'
import i18n from "i18n/i18n";

import App from 'viewes/App.vue'
import jwt_decode from "jwt-decode";
import axios from "axios";


Vue.http.interceptors.push(function (request, next) {
    console.log("Interceptor")
    console.log(request)
    if (!request.headers.get('Authorization')) {
        const accessToken = jwt_decode(localStorage.getItem('accessToken'))
console.log(accessToken)
        // if (accessToken.exp * 1000 < new Date().getTime()) {
        //     const promise = refresh()
        //     promise.then((response) => {
        //         localStorage.setItem('accessToken', response.data.access_token)
        //         localStorage.setItem('refreshToken', response.data.refresh_token)
        //         request.headers.set('Authorization', `Bearer ` + localStorage.getItem('accessToken'))
        //         next()
        //     })
        // } else {
            request.headers.set('Authorization', `Bearer ` + localStorage.getItem('accessToken'))

        // request.headers.set('Access-Control-Allow-Origin','true')
        // request.headers.set('Access-Control-Allow-Origin','*')
           console.log(request)

            next()
        // }
    }

}.bind(this));

new Vue({
    render: a => a(App),
    store,
    router,
    i18n
}).$mount('#app');

function refresh() {

    return axios.post('/oauth/token',
        'grant_type=refresh_token' +
        '&refresh_token=' + localStorage.getItem('refreshToken') +
        '&scope=read write'
        , {
            auth: {
                username: 'client',
                password: 'secret'
            }
        });
}