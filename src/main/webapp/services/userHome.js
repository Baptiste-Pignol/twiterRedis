/**
 * Created by Baptiste on 21/04/2015.
 */
(function () {
    angular
        .module('twitterApp')
        .factory('UserHome', UserHomeFct);

    UserHomeFct.$inject = ['$resource'];

    function UserHomeFct($resource) {
        return $resource('rest/users/:pseudo/wallTweets');
    }
})();