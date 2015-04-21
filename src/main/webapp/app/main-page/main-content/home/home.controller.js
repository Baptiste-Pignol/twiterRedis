/**
 * Created by Baptiste on 11/04/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('HomeCtrl', homeCtrl);

    homeCtrl.$inject = ['$stateParams', 'Tweets', 'UserHome'];

    function homeCtrl($stateParams, Tweets, UserHome) {
        var _this =this;
        this.currentUserPseudo = $stateParams.pseudo;
        this.newTweet = {
            message: ''
        };

        this.sendTweet = function sendTweet() {
            Tweets.save(_this.newTweet);
        };

        this.loadWallTweet = function loadWallTweet() {
            UserHome.query({'pseudo': _this.currentUserPseudo},
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