/**
 * Created by Baptiste on 11/04/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('FollowingCtrl', followingCtrl);

    //followingCtrl.$inject = [''];

    function followingCtrl() {
        var _this =this;
        this.users = [
            {
                pseudo: "bob",
                image: "http://lorempixel.com/148/148/"
            },
            {
                pseudo: "jksjsj",
                image: "http://lorempixel.com/143/143/"
            },
            {
                pseudo: "hqddsg",
                image: "http://lorempixel.com/145/145/"
            },
            {
                pseudo: "sgjgj",
                image: "http://lorempixel.com/147/147/"
            },
            {
                pseudo: "qhfqhfqh",
                image: "http://lorempixel.com/138/138/"
            },
            {
                pseudo: "dvffddddbbd",
                image: "http://lorempixel.com/135/135/"
            },
            {
                pseudo: "nntntkkgj",
                image: "http://lorempixel.com/146/146/"
            },
            {
                pseudo: "kukukk",
                image: "http://lorempixel.com/147/147/"
            },
            {
                pseudo: "mimimim",
                image: "http://lorempixel.com/148/148/"
            },
            {
                pseudo: "bfggfgfob",
                image: "http://lorempixel.com/149/149/"
            },
            {
                pseudo: "bdfdffdob",
                image: "http://lorempixel.com/141/141/"
            }
        ];
    }
})();