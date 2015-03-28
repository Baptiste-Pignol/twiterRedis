/**
 * Created by Baptiste on 28/03/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('InscriptionCtrl', inscriptionCtrl);

    inscriptionCtrl.$inject = ['$location', 'Users'];

    function inscriptionCtrl($location, Users) {
        var me = this;
        me.user = {
            pseudo: "",
            name: "",
            surname: "",
            password: ""
        };

        me.inscription = function () {
            Users.save(me.user);
        }
    }
})();