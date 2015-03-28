/**
 * Created by Baptiste on 17/03/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp', [
            'ngRoute',
            'ngResource'
        ])
        .config(routeConfig)

    routeConfig.$inject = ['$routeProvider'];

    function routeConfig($routeProvider) {
        $routeProvider.
            otherwise({
                redirectTo: '/connection'
            });
    }
})();