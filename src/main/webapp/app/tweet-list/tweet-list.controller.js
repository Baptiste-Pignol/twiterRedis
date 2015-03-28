/**
 * Created by Baptiste on 28/03/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('TweetListCtrl', tweetListCtrl);

    tweetListCtrl.$inject = ['Tweets', 'TweeterAppConnexion'];

    function tweetListCtrl(Tweets, TweeterAppConnexion) {
        var me = this;
        TweeterAppConnexion.verifConnection();
        me.tweets = Tweets.query();
    }
})();