/**
 * Created by Baptiste on 27/03/2015.
 */

(function () {
    angular
        .module('twitterApp')
        .factory('Tweets', tweetFct);

    tweetFct.$inject = ['$resource'];

    function tweetFct($resource) {
        return $resource('rest/tweets/:id');
    }
})();