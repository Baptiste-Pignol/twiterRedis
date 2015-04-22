/**
 * Created by Baptiste on 11/04/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('FollowingCtrl', followingCtrl);

    followingCtrl.$inject = ['$stateParams', 'Users'];

    function followingCtrl($stateParams, Users) {
        var _this =this;

        // users following
        this.users = [];

        // get current user pseudo
        this.currentUserPseudo = $stateParams.pseudo;

        // function which load following users
        this.loadFollowing = function loadFollowing() {
            Users.following.query({'pseudo': _this.currentUserPseudo},
                function success(dbUsers) {
                    _this.users = dbUsers;
                },
                function error(err) {
                    console.log(err);
                }
            );
        };

        // load users
        this.loadFollowing();

    }
})();