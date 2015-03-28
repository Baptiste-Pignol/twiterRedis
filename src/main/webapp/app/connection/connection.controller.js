/**
 * Created by Baptiste on 27/03/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('ConnectionCtrl', connectionCtrl);

    connectionCtrl.$inject = ['$rootScope', '$location', 'TweeterAppConnexion'];

    function connectionCtrl($rootScope, $location, TweeterAppConnexion) {
        var me = this;

        me.user = {
            pseudo: "",
            password: ""
        };

        me.connection = function () {
            TweeterAppConnexion.connection(me.user)
                .success(function () {
                    $rootScope.$emit("connected");
                    $location.url("/myTweet");
                });
        }
    }
})();