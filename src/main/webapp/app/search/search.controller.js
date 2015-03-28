/**
 * Created by Baptiste on 28/03/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('SearchCtrl', searchCtrl);

    searchCtrl.$inject = ['Users', 'TweeterAppConnexion'];

    function searchCtrl(Users, TweeterAppConnexion) {
        var me = this;
        TweeterAppConnexion.verifConnection();
        me.user = {};

        me.search = function() {
            me.user = Users.get(me.user.pseudo);
        }
    }
})();