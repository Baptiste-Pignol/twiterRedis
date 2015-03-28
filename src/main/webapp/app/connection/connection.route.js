/**
 * Created by Baptiste on 27/03/2015.
 */

(function () {
    angular
        .module('twitterApp')
        .config(config);

    config.$inject = ['$routeProvider'];

    function config($routeProvider) {
        $routeProvider.
            when('/connection', {
                templateUrl: 'app/connection/connection.html',
                controller: 'ConnectionCtrl',
                controllerAs: 'connection'
            });
    }
})();
