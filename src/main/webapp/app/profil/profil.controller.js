/**
 * Created by Baptiste on 28/03/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('ProfilCtrl', profilCtrl);

    profilCtrl.$inject = ['Users'];

    function profilCtrl(Users) {
        var me = this;
        me.user = Users.get();
    }
})();