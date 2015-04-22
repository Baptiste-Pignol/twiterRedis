/**
 * Created by Baptiste on 27/03/2015.
 */

(function () {
    angular
        .module('twitterApp')
        .factory('Users', userFct);

    userFct.$inject = ['$resource'];

    function userFct($resource) {
        return {
            user: $resource('rest/users/:pseudo'),
            follower: $resource('rest/users/followers/:pseudo'),
            nbFollowers: $resource('rest/users/:pseudo/followers/size'),
            following: $resource('rest/users/following/:pseudo'),
            nbFollowing: $resource('rest/users/:pseudo/following/size')
        };
    }


})();