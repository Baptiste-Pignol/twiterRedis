/**
 * Created by Baptiste on 28/03/2015.
 */

(function () {
    angular
        .module('twitterApp')
        .factory('TweeterAppConnexion', tweeterAppConnexionFct);

    tweeterAppConnexionFct.$inject = ['$http', '$rootScope', '$location'];

    function tweeterAppConnexionFct($http, $rootScope, $location) {
        return {
            connection: function (user) {
                $rootScope.$emit('connected');
                return $http.post('rest/connect', user);
            },
            verifConnection: function() {
                $http.get('rest/connect')
                    .success(function() {
                        $rootScope.$emit('connected');
                    })
                    .error(function() {
                        $rootScope.$emit('disconnected');
                        $location.url('/connection');
                    });
            }
        };
    }
})();
