/**
 * Created by Baptiste on 01/04/2015.
 */

(function () {
    angular
        .module('twitterApp')
        .config(config);

    config.$inject = ['$urlRouterProvider'];

    function config($urlRouterProvider) {
        $urlRouterProvider.otherwise('/connection');
    }
})();