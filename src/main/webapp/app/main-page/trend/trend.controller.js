/**
 * Created by Baptiste on 11/04/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('TrendCtrl', trendCtrl);

    //trendCtrl.$inject = [''];

    function trendCtrl() {
        var _this =this;
        this.trends = [
            '#sddvhvsk',
            '#fnfnpofpj',
            '#sdvdsvdsv',
            '#spdovis',
            '#qshdvm',
            '#wxvwwvv',
            '#vihlbiue',
            '#djdjz',
            '#svlknq',
            '#lkqcjg'
        ];
    }
})();