/**
 * Created by Baptiste on 28/03/2015.
 */


(function () {
    angular
        .module('twitterApp')
        .config(config);

    config.$inject = ['$stateProvider'];

    function config($stateProvider) {
        $stateProvider.state('presentation', {
            url: '/presentation',
            templateUrl: 'app/presentation/presentation.html',
            controller: 'PresentationCtrl',
            controllerAs: 'presentation'
        });
    }
})();