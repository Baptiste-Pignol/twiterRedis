/**
 * Created by Baptiste on 11/04/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('FavoriteCtrl', favoriteCtrl);

    //favoriteCtrl.$inject = [''];

    function favoriteCtrl() {
        var _this =this;
        this.tweets = [
            {
                message: "Bonjour je tweet parcequ j'aime bien tweeter !!!! yahahaha",
                date: "5 minutes",
                sender: "bob",
                senderImage: "http://lorempixel.com/140/140/"
            },
            {
                message: "Salut je tweet parcequ j'aime bien tweeter !!!! yahahaha",
                date: "15 minutes",
                sender: "jksjsj",
                senderImage: "http://lorempixel.com/141/141/"
            },
            {
                message: "Salut je tweet parcequ j'aime bien tweeter !!!! yahahaha",
                date: "25 minutes",
                sender: "hqddsg",
                senderImage: "http://lorempixel.com/142/142/"
            },
            {
                message: "Salut je tweet parcequ j'aime bien tweeter !!!! yahahaha",
                date: "35 minutes",
                sender: "sgjgj",
                senderImage: "http://lorempixel.com/143/143/"
            },
            {
                message: "Salut je tweet parcequ j'aime bien tweeter !!!! yahahaha",
                date: "45 minutes",
                sender: "qhfqhfqh",
                senderImage: "http://lorempixel.com/144/144/"
            },
            {
                message: "Salut je tweet parcequ j'aime bien tweeter !!!! yahahaha",
                date: "2 jours",
                sender: "bob",
                senderImage: "http://lorempixel.com/145/145/"
            },
            {
                message: "Salut je tweet parcequ j'aime bien tweeter !!!! yahahaha",
                date: "4 jours",
                sender: "bob",
                senderImage: "http://lorempixel.com/146/146/"
            },
            {
                message: "Salut je tweet parcequ j'aime bien tweeter !!!! yahahaha Salut je tweet parcequ j'aime bien tweeter !!!! yahahaha",
                date: "20 jours",
                sender: "bob",
                senderImage: "http://lorempixel.com/147/147/"
            },
            {
                message: "Salut je tweet parcequ j'aime bien tweeter !!!! yahahaha",
                date: "2 mois",
                sender: "bob",
                senderImage: "http://lorempixel.com/148/148/"
            },
            {
                message: "Salut je tweet parcequ j'aime bien tweeter !!!! yahahaha",
                date: "5 mois",
                sender: "bfggfgfob",
                senderImage: "http://lorempixel.com/149/149/"
            },
            {
                message: "Salut je tweet parcequ j'aime bien tweeter !!!! yahahaha",
                date: "1 ans",
                sender: "bdfdffdob",
                senderImage: "http://lorempixel.com/141/141/"
            }
        ];
    }
})();