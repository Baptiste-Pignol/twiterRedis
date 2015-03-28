/**
 * Created by Baptiste on 27/03/2015.
 */

(function () {
    angular
        .module('twitterApp')
        .factory('UserFollowed', userFollowedFct);

    userFollowedFct.$inject = ['$resource'];

    function userFollowedFct($resource) {
        return $resource('rest/users/followed/:pseudo');
    }
})();