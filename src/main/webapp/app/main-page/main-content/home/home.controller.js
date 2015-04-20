/**
 * Created by Baptiste on 11/04/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('HomeCtrl', homeCtrl);

    homeCtrl.$inject = ['Tweets'];

    function homeCtrl(Tweets) {
        var _this =this;
        this.tweets = Tweets.query();
        this.newTweet = {
            message: ''
        }

        _this.sendTweet = function sendTweet() {
            Tweets.save(_this.newTweet);
        }
    }
})();