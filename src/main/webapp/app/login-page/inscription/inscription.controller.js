/**
 * Created by Baptiste on 28/03/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('InscriptionCtrl', inscriptionCtrl);

    inscriptionCtrl.$inject = ['$state', 'Users'];

    function inscriptionCtrl($state, Users) {
        var _this = this;
        _this.user = {
            pseudo: "",
            fullName: "",
            email: "",
            password: ""
        };

        _this.inscription = function inscription() {
            Users.user.save(_this.user,
                function success() {
                    _this.user  = {
                        pseudo: "",
                        fullName: "",
                        email: "",
                        password: ""
                    };
                },
                function error(err) {

                }
            );
        };

        _this.onFocus = function onFocus() {
            _this.isFocus = true;
        };

        _this.onBlur = function onBlur() {
            if (!_this.user.pseudo && !_this.user.fullName && !_this.user.email && !_this.user.password) {
                _this.isFocus = false;
            }
        };

        _this.getClass = function getClass() {
            return _this.isFocus ? 'inscription-form-card' : 'inscription-form-card-small';
        };
    }
})();