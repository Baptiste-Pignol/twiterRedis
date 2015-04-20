/**
 * Created by Baptiste on 11/04/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('TweetCtrl', tweetCtrl);

    tweetCtrl.$inject = ['$rootScope', '$stateParams', 'Tweets', 'UserTweet'];

    function tweetCtrl($rootScope, $stateParams, Tweets, UserTweet) {
        var _this =this;
        _this.currentUserPseudo = $stateParams.pseudo || 'me';
        if (_this.currentUserPseudo == 'me') {
            this.tweets = Tweets.query()
        } else {
            this.tweets = UserTweet.query({'pseudo': _this.currentUserPseudo});
        }

        _this.sendTweet = function sendTweet() {
            Tweets.save(_this.newTweet);
        }
    }
})();