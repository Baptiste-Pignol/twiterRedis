/**
 * Created by Baptiste on 27/03/2015.
 */

(function () {
    angular
        .module('twitterApp')
        .factory('UserFollower', userFollowerFct);

    userFollowerFct.$inject = ['$resource'];

    function userFollowerFct($resource) {
        return $resource('rest/users/follower/:pseudo');
    }
})();