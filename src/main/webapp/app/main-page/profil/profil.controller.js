/**
 * Created by Baptiste on 06/04/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('ProfilCtrl', profilCtrl);

    //profilCtrl.$inject = [''];

    function profilCtrl() {
        var _this =this;
        this.connectedUser = {
            pseudo: 'Bobyaaaaaaa',
            fullName: 'Bob B',
            email: 'bob@bob.fr',
            image: 'http://www.mathcurve.com/fractals/arbre/arbre3d.gif',
            nbrTweet: 0,
            nbrFollower: 0,
            nbrFollowing: 0,
            nbrFavoris: 0
        };
    }
})();