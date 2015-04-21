/**
 * Created by Baptiste on 27/03/2015.
 */

(function () {
    angular
        .module('twitterApp')
        .factory('UserFollowing', userFollowingFct);

    userFollowingFct.$inject = ['$resource'];

    function userFollowingFct($resource) {
        return $resource('rest/users/following/:pseudo');
    }
})();