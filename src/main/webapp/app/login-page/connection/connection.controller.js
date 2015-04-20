/**
 * Created by Baptiste on 27/03/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('ConnectionCtrl', connectionCtrl);

    connectionCtrl.$inject = ['$state', 'AppConnexion'];

    function connectionCtrl($state, AppConnexion) {
        var _this = this;

        _this.user = {
            pseudo: "",
            password: ""
        };

        _this.connection = function connection() {
            AppConnexion.connection(_this.user)
                .success(function () {
                    $state.go("twitter.home");
                });
        }
    }
})();