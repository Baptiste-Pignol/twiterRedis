/**
 * Created by Baptiste on 12/04/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('SearchCtrl', searchCtrl);

    //searchCtrl.$inject = [''];

    function searchCtrl() {
        var _this =this;
        this.result = [
            {
                pseudo: "bob",
                image: "http://lorempixel.com/142/142/"
            },
            {
                pseudo: "jksjsj",
                image: "http://lorempixel.com/149/149/"
            },
            {
                pseudo: "hqddsg",
                image: "http://lorempixel.com/145/145/"
            },
            {
                pseudo: "sgjgj",
                image: "http://lorempixel.com/143/143/"
            },
            {
                pseudo: "qhfqhfqh",
                image: "http://lorempixel.com/144/144/"
            },
            {
                pseudo: "dvffddddbbd",
                image: "http://lorempixel.com/147/147/"
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