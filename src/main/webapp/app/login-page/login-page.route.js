/**
 * Created by Baptiste on 04/04/2015.
 */

(function () {
    angular
        .module('twitterApp')
        .config(config);

    config.$inject = ['$stateProvider'];

    function config($stateProvider) {
        $stateProvider.state('connection', {
            url: '/connection',
            views: {
                '': {
                    templateUrl: 'app/login-page/login-page.html',
                    controller: 'LoginPageCtrl',
                    controllerAs: 'loginPage'
                },
                'presentation@connection': {
                    templateUrl: 'app/login-page/presentation/presentation.html',
                    controller: 'PresentationCtrl',
                    controllerAs: 'presentation'
                },
                'connection@connection': {
                    templateUrl: 'app/login-page/connection/connection.html',
                    controller: 'ConnectionCtrl',
                    controllerAs: 'connection'
                },
                'inscription@connection': {
                    templateUrl: 'app/login-page/inscription/inscription.html',
                    controller: 'InscriptionCtrl',
                    controllerAs: 'inscription'
                }
            }
        });
    }
})();