/**
 * Created by Baptiste on 27/03/2015.
 */

(function () {
    angular
        .module('twitterApp')
        .factory('Users', userFct);

    userFct.$inject = ['$resource'];

    function userFct($resource) {
        return $resource('rest/users/:pseudo');
    }
})();