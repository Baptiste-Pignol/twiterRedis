/**
 * Created by Baptiste on 28/03/2015.
 */


(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('AppController', appController);

    appController.$inject = ['$rootScope'];

    function appController($rootScope) {
        var me = this;

        $rootScope.$on('connected', connected);
        $rootScope.$on('disconnected', disconnect);

        function connected() {
            me.connected = true;
        }

        function disconnect() {
            me.connected = false;
        }
    }
})();