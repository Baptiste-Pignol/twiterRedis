/**
 * Created by Baptiste on 28/03/2015.
 */

(function () {
    angular
        .module('twitterApp')
        .config(config);

    config.$inject = ['$routeProvider'];

    function config($routeProvider) {
        $routeProvider.
            when('/search', {
                templateUrl: 'app/search/search.html',
                controller: 'SearchCtrl',
                controllerAs: 'search'
            });
    }
})();