/**
 * Created by Baptiste on 11/04/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('SuggestionCtrl', suggestionCtrl);

    //suggestionCtrl.$inject = [''];

    function suggestionCtrl() {
        var _this =this;
        this.users = [
            {
                pseudo: 'electonica',
                fullName: 'Eric E',
                email: 'eric@eric.fr',
                image: 'http://lorempixel.com/141/141/',
                nbrTweet: 0,
                nbrFollower: 0,
                nbrFollowing: 0,
                nbrFavoris: 0
            },
            {
                pseudo: 'somotolo',
                fullName: 'Bill S',
                email: 'swagSomo@bill.fr',
                image: 'http://lorempixel.com/142/142/',
                nbrTweet: 0,
                nbrFollower: 0,
                nbrFollowing: 0,
                nbrFavoris: 0
            },
            {
                pseudo: 'soron',
                fullName: 'soron T',
                email: 'soron@mordor.com',
                image: 'http://lorempixel.com/140/140/',
                nbrTweet: 0,
                nbrFollower: 0,
                nbrFollowing: 0,
                nbrFavoris: 0
            }
        ];
    }
})();