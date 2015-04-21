/**
 * Created by Baptiste on 11/04/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('FollowerCtrl', followerCtrl);

    followerCtrl.$inject = ['$stateParams', 'UserFollower'];

    function followerCtrl($stateParams, UserFollower) {
        var _this =this;

        // user followers
        this.users = [];

        // get current user pseudo
        this.currentUserPseudo = $stateParams.pseudo;

        // function which load followers users
        this.loadFollower = function loadFollower() {
            UserFollower.query({'pseudo': _this.currentUserPseudo},
                function success(dbUsers) {
                    _this.users = dbUsers;
                },
                function error(err) {
                    console.log(err);
                }
            );
        };

        // load users
        this.loadFollower();
    }
})();