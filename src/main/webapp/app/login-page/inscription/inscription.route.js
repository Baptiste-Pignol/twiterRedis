/**
 * Created by Baptiste on 28/03/2015.
 */


(function () {
    angular
        .module('twitterApp')
        .config(config);

    config.$inject = ['$stateProvider'];

    function config($stateProvider) {
        $stateProvider.state('inscription', {
            url: '/inscription',
            templateUrl: 'app/inscription/inscription.html',
            controller: 'InscriptionCtrl',
            controllerAs: 'inscription'
        });
    }
})();