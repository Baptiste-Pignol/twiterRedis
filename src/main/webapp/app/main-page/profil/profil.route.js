/**
 * Created by Baptiste on 06/04/2015.
 */

(function () {
    angular
        .module('twitterApp')
        .config(config);

    config.$inject = ['$stateProvider'];

    function config($stateProvider) {
        $stateProvider.state('connection', {
            url: '/connection',
            templateUrl: 'app/connection/connection.html',
            controller: 'ConnectionCtrl',
            controllerAs: 'connection'
        });
    }
})();