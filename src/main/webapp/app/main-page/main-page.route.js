/**
 * Created by Baptiste on 05/04/2015.
 */

(function () {
    angular
        .module('twitterApp')
        .config(config);

    config.$inject = ['$stateProvider'];

    function config($stateProvider) {
        $stateProvider.state('twitter', {
            url: '/twitter',
            views: {
                '': {
                    templateUrl: 'app/main-page/main-page.html'/*,
                     controller: 'MainPageCtrl',
                     controllerAs: 'mainPage'*/
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
                },
                'suggestion@twitter': {
                    templateUrl: 'app/main-page/suggestion/suggestion.html',
                    controller: 'SuggestionCtrl',
                    controllerAs: 'suggestion'
                },
                'trend@twitter': {
                    templateUrl: 'app/main-page/trend/trend.html',
                    controller: 'TrendCtrl',
                    controllerAs: 'trend'
                }
            }
        }).state('twitter.home', {
            url: '/home',
            templateUrl: 'app/main-page/main-content/tweet/tweet.html',
            controller: 'HomeCtrl',
            controllerAs: 'tweetCtrl'
        }).state('twitter.tweet', {
            url: '/tweet',
            templateUrl: 'app/main-page/main-content/tweet/tweet.html',
            controller: 'TweetCtrl',
            controllerAs: 'tweetCtrl'
        }).state('twitter.follower', {
            url: '/follower',
            templateUrl: 'app/main-page/main-content/follower/follower.html',
            controller: 'FollowerCtrl',
            controllerAs: 'followCtrl'
        }).state('twitter.following', {
            url: '/following',
            templateUrl: 'app/main-page/main-content/follower/follower.html',
            controller: 'FollowingCtrl',
            controllerAs: 'followCtrl'
        }).state('twitter.favorite', {
            url: '/favorite',
            templateUrl: 'app/main-page/main-content/tweet/tweet.html',
            controller: 'FavoriteCtrl',
            controllerAs: 'tweetCtrl'
        }).state('twitter.search', {
            url: '/search',
            templateUrl: 'app/main-page/main-content/search/search.html',
            controller: 'SearchCtrl',
            controllerAs: 'search'
        });
    }
})();