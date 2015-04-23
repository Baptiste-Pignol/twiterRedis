/**
 * Created by Baptiste on 27/03/2015.
 */

(function () {
    angular
        .module('twitterApp')
        .factory('Tweets', tweetFct);

    tweetFct.$inject = ['$resource'];

    function tweetFct($resource) {
        return {
            currentUserTweets: $resource('rest/tweets/:id'),
            wallUserTweets: $resource('rest/users/:pseudo/wallTweets'),
            userTweets: $resource('rest/users/:pseudo/tweets'),
            userTweetSize: $resource('rest/users/:pseudo/tweets/size'),
            hashtag: $resource('rest/hashtag/:hashtag'),
            favorite: $resource('rest/user/:pseudo/favorite/:tweetId')
        };
    }
})();