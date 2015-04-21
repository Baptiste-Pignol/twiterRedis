/**
 * controller of profil ui-view
 * show current user information
 */
(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('ProfilCtrl', profilCtrl);

    profilCtrl.$inject = ['$rootScope', 'Users'];

    function profilCtrl($rootScope, Users) {
        var _this =this;

        // temporary value of connectedUser
        this.connectedUser = {
            pseudo: '...',
            fullName: '...',
            email: '...',
            image: 'http://www.mathcurve.com/fractals/arbre/arbre3d.gif',
            nbrTweet: 0,
            nbrFollower: 0,
            nbrFollowing: 0,
            nbrFavoris: 0
        };


        this.loadProfil = function loadProfil() {
            // get current user data
            Users.get({pseudo: $rootScope.currentUserPseudo || $rootScope.connectedUserPseudo},
                function success(bdUser){
                    _this.connectedUser.pseudo = bdUser.pseudo;
                    _this.connectedUser.fullName = bdUser.fullName;
                    _this.connectedUser.email = bdUser.email;
                },
                function error(err){
                    console.log(err);
                }
            );
        };

        $rootScope.$on('$stateChangeStart', handleStateChange);
        function handleStateChange(event, toState, toParam, fromState, fromParam) {
            if (fromParam.pseudo != toParam.pseudo) {
                _this.loadProfil();
            }
        }

        this.loadProfil();
    }
})();