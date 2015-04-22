/**
 * Created by Baptiste on 11/04/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('TweetCtrl', tweetCtrl);

    tweetCtrl.$inject = ['$stateParams', 'Tweets'];

    function tweetCtrl($stateParams, Tweets) {
        var _this =this;
        this.currentUserPseudo = $stateParams.pseudo;

        // send new tweet
        this.sendTweet = function sendTweet() {
            Tweets.currentUserTweets.save(_this.newTweet,
                function success() {
                    _this.loadTweet();
                },
                function error(err) {
                    console.log(err);
                }
            );
        };

        // load all tweets of the current user
        this.loadTweet = function loadTweet() {
            Tweets.userTweets.query({'pseudo': _this.currentUserPseudo},
                function success(dbTweets) {
                    _this.tweets = dbTweets;
                },
                function error(err) {
                    console.log(err);
                }
            );
        };

        this.loadTweet();
    }
})();