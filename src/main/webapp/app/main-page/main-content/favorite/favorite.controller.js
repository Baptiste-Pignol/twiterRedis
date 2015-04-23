/**
 * Created by Baptiste on 11/04/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('FavoriteCtrl', favoriteCtrl);

    favoriteCtrl.$inject = ['$stateParams', 'Tweets'];

    function favoriteCtrl($stateParams, Tweets) {
        var _this =this;
        this.currentUserPseudo = $stateParams.pseudo;
        this.tweets = [];

        this.isFavorite = true;

        // getfavorite
        this.loadFavorite = function loadFavorite() {
            Tweets.favorite.query({pseudo: this.currentUserPseudo},
                function success(data) {
                    _this.tweets = data;
                },
                function error(err) {
                    _this.tweets = [];
                }
            );
        };

        this.removeFavorite = function removeFavorite(tweet) {
            Tweets.favorite.remove({tweetId: tweet.id},
                function success(data) {
                    _this.loadFavorite();
                },
                function error(err) {
                    console.log(err);
                }
            );
        };

        this.loadFavorite();
    }
})();