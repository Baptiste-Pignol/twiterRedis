/**
 * controller of profil ui-view
 * show current user information
 */
(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('ProfilCtrl', profilCtrl);

    profilCtrl.$inject = ['$rootScope', 'Users', 'Tweets', '$state'];

    function profilCtrl($rootScope, Users, Tweets, $state) {
        var _this =this;

        var pseudo = $state.params.pseudo;
        if (pseudo)
            $rootScope.currentUserPseudo = pseudo;
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
            var user = $rootScope.currentUserPseudo || $rootScope.connectedUserPseudo;
            // get current user data
            Users.user.get({pseudo: user},
                function success(bdUser){
                    _this.connectedUser.pseudo = bdUser.pseudo;
                    _this.connectedUser.fullName = bdUser.fullName;
                    _this.connectedUser.email = bdUser.email;
                },
                function error(err){
                    console.log(err);
                }
            );
            Tweets.userTweetSize.get({pseudo: user},
                function success(dbNbTweets) {
                    _this.connectedUser.nbrTweet = dbNbTweets.nbTweet;
                },
                function error(err) {
                    console.log(err);
                }
            );
            Users.nbFollowers.get({pseudo: user},
                function success(dbNbFollowers) {
                    _this.connectedUser.nbrFollower = dbNbFollowers.nbFollowers;
                },
                function error(err) {
                    console.log(err);
                }
            );
            Users.nbFollowing.get({pseudo: user},
                function success(dbNbFollowing) {
                    _this.connectedUser.nbrFollowing = dbNbFollowing.nbFollowing;
                },
                function error(err) {
                    console.log(err);
                }
            );
        };

        $rootScope.$on('$stateChangeStart', handleStateChange);
        function handleStateChange(event, toState, toParam, fromState, fromParam) {
            if (toParam.pseudo)
                $rootScope.currentUserPseudo = toParam.pseudo;
            if (toParam.pseudo != fromParam.pseudo)
                _this.loadProfil();
        }

        this.loadProfil();
    }
})();