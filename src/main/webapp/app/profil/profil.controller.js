/**
 * Created by Baptiste on 28/03/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('ProfilCtrl', profilCtrl);

    profilCtrl.$inject = ['Users', 'TweeterAppConnexion'];

    function profilCtrl(Users, TweeterAppConnexion) {
        var me = this;
        TweeterAppConnexion.verifConnection();
        me.user = Users.get();
    }
})();