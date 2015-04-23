/**
 * Created by Baptiste on 11/04/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('HomeCtrl', homeCtrl);

    homeCtrl.$inject = ['$stateParams', 'Tweets'];

    function homeCtrl($stateParams, Tweets) {
        var _this =this;
        this.currentUserPseudo = $stateParams.pseudo;
        this.newTweet = {
            message: ''
        };

        this.sendTweet = function sendTweet() {
            Tweets.currentUserTweets.save(_this.newTweet,
                function success() {
                    _this.loadWallTweet();
                    _this.newTweet = {
                        message: ''
                    };
                },
                function error(err) {
                    console.log(err);
                }
            );
        };

        // favorite
        this.addFavorite = function addFavorite(tweet) {
            Tweets.favorite.save(tweet,
                function success() {
                    _this.loadTweet();
                },
                function error(err) {
                    console.log(err);
                }
            );
        };

        this.retweet = function retweet(tweet) {
            Tweets.currentUserTweets.save(tweet,
                function success() {
                    _this.loadWallTweet();
                },
                function error(err) {
                    console.log(err);
                }
            );
        };

        // remove a tweet
        this.removeTweet = function removeTweet(tweet) {
            Tweets.currentUserTweets.remove({id: tweet.id},
                function success() {
                    _this.loadWallTweet();
                },
                function error(err) {
                    console.log(err);
                }
            );
        };

        this.loadWallTweet = function loadWallTweet() {
            Tweets.wallUserTweets.query({'pseudo': _this.currentUserPseudo},
                function success(dbTweets) {
                    _this.tweets = dbTweets;
                },
                function error(err) {
                    console.log(err);
                }
            );
        };
        this.loadWallTweet();
    }
})();