/**
 * Created by Baptiste on 28/03/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('FormTweetController', formTweetController);

    formTweetController.$inject = ['Tweets'];

    function formTweetController(Tweets) {
        var me = this;
        me.tweet = {
            message: ""
        };
        me.sendTweet = function () {
            Tweets.save(me.tweet);
        }
    }
})();