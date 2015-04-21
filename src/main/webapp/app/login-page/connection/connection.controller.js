/**
 * Created by Baptiste on 27/03/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('ConnectionCtrl', connectionCtrl);

    connectionCtrl.$inject = ['$state', 'AppConnexion', '$rootScope'];

    function connectionCtrl($state, AppConnexion, $rootScope) {
        var _this = this;

        _this.user = {
            pseudo: "",
            password: ""
        };

        _this.connection = function connection() {
            AppConnexion.connection(_this.user)
                .success(function () {
                    $rootScope.connectedUserPseudo = _this.user.pseudo;
                    $rootScope.currentUserPseudo = _this.user.pseudo;
                    $state.go("twitter.home", {pseudo: _this.user.pseudo});
                });
        }
    }
})();