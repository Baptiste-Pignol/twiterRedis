/**
 * Created by Baptiste on 05/04/2015.
 */

(function () {
    angular
        .module('twitterApp')
        .config(config);

    config.$inject = ['$stateProvider', '$urlRouterProvider'];

    function config($stateProvider, $urlRouterProvider) {
        $stateProvider.state('twitter', {
            url: '/twitter',
            views: {
                '': {
                    templateUrl: 'app/main-page/main-page.html'
                },
                'profil@twitter': {
                    templateUrl: 'app/main-page/profil/profil.html',
                    controller: 'ProfilCtrl',
                    controllerAs: 'profil'
                },
                'tabBar@twitter': {
                    templateUrl: 'app/main-page/tabBar/tabBar.html',
                    controller: 'TabBarCtrl',
                    controllerAs: 'tabBar'
                }
            }
        }).state('twitter.home', {
            url: '/home/:pseudo',
            templateUrl: 'app/main-page/main-content/tweet/tweet.html',
            controller: 'HomeCtrl',
            controllerAs: 'tweetCtrl'
        }).state('twitter.tweet', {
            url: '/tweet/:pseudo',
            templateUrl: 'app/main-page/main-content/tweet/tweet.html',
            controller: 'TweetCtrl',
            controllerAs: 'tweetCtrl'
        }).state('twitter.follower', {
            url: '/follower/:pseudo',
            templateUrl: 'app/main-page/main-content/follower/follower.html',
            controller: 'FollowerCtrl',
            controllerAs: 'followCtrl'
        }).state('twitter.following', {
            url: '/following/:pseudo',
            templateUrl: 'app/main-page/main-content/follower/follower.html',
            controller: 'FollowingCtrl',
            controllerAs: 'followCtrl'
        }).state('twitter.favorite', {
            url: '/favorite/:pseudo',
            templateUrl: 'app/main-page/main-content/tweet/tweet.html',
            controller: 'FavoriteCtrl',
            controllerAs: 'tweetCtrl'
        }).state('twitter.search', {
            url: '/search/:pseudo',
            templateUrl: 'app/main-page/main-content/search/search.html',
            controller: 'SearchCtrl',
            controllerAs: 'search'
        });
    }
})();