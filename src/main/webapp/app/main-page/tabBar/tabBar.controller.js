/**
 * Created by Baptiste on 06/04/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('TabBarCtrl', tabBarCtrl);

    tabBarCtrl.$inject = ['$rootScope', '$state'];

    function tabBarCtrl($rootScope, $state) {
        var _this = this;
        this.selectedIndex = 0;
        /*_this.user = 'me';
        $rootScope.$on('currentUser', function(event, userPseudo) {
            _this.user = userPseudo;
        });*/
        this.tabs = [
            {
                title: 'home',
                state: 'twitter.home',
                index: 0
            },
            {
                title: 'tweet',
                state: 'twitter.tweet',
                index: 1
            },
            {
                title: 'follower',
                state: 'twitter.follower',
                index: 2
            },
            {
                title: 'following',
                state: 'twitter.following',
                index: 3
            },
            {
                title: 'favorite',
                state: 'twitter.favorite',
                index: 4
            },
            {
                title: 'search',
                state: 'twitter.search',
                index: 5
            }
        ];
        this.clickTab = function clickTab(tab) {
            $state.go(tab.state);
            _this.selectedIndex = tab.index;
        };
    }
})();