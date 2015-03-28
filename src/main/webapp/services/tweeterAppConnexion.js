/**
 * Created by Baptiste on 28/03/2015.
 */

(function () {
    angular
        .module('twitterApp')
        .factory('TweeterAppConnexion', tweeterAppConnexionFct);

    tweeterAppConnexionFct.$inject = ['$http'];

    function tweeterAppConnexionFct($http) {
        return {
            connection: function (user) {
                return $http.post('rest/connect', user);
            }
        };
    }
})();
