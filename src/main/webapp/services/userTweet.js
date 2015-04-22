/**
 * Created by Baptiste on 27/03/2015.
 */

(function () {
    angular
        .module('twitterApp')
        .factory('UserTweet', userTweetFct);

    userTweetFct.$inject = ['$resource'];

    function userTweetFct($resource) {
        return {
            userTweet: $resource('rest/users/:pseudo/tweets'),
            userTweetSize: $resource('rest/users/:pseudo/tweets/size')
        }
    }
})();
