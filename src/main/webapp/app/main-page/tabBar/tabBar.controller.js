/**
 * Created by Baptiste on 06/04/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('TabBarCtrl', tabBarCtrl);

    tabBarCtrl.$inject = ['$rootScope', '$state', '$stateParams'];

    function tabBarCtrl($rootScope, $state, $stateParams) {
        var _this = this;
        this.selectedIndex = 0;
        this.connectedUserPseudo = $rootScope.connectedUserPseudo;

        $rootScope.$on('$stateChangeStart', handleStateChange);
        function handleStateChange(event, toState, toParam, fromState, fromParam) {
            var pseudo = toParam.pseudo;
            if (pseudo) {
                $rootScope.currentUserPseudo = pseudo;
            }
        }


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
            $state.go(tab.state, {pseudo: $rootScope.currentUserPseudo || $rootScope.connectedUserPseudo});
            _this.selectedIndex = tab.index;
        };
        //ui-sref='{{tab.state}}({pseudo: "{{tabBar.currentUserPseudo}}"})'
        //{{tab.state}}({pseudo: '{{tabBar.currentUserPseudo}}'})
    }
})();