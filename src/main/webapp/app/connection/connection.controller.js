/**
 * Created by Baptiste on 27/03/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('ConnectionCtrl', connectionCtrl);

    connectionCtrl.$inject = ['$location', 'TweeterAppConnexion'];

    function connectionCtrl($location, TweeterAppConnexion) {
        var me = this;
        me.user = {
            pseudo: "",
            password: ""
        };

        me.connection = function () {
            TweeterAppConnexion.connection(me.user)
                .success(function () {
                    $location.url("/myTweet");
                });
        }
    }
})();