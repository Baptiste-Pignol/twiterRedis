/**
 * Created by Baptiste on 28/03/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('TweetListCtrl', tweetListCtrl);

    tweetListCtrl.$inject = ['Tweets', '$location'];

    function tweetListCtrl(Tweets) {
        var me = this;
        me.tweets = Tweets.query();
    }
})();