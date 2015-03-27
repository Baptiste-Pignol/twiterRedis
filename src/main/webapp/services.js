/**
 * Created by Baptiste on 17/03/2015.
 */

angular.module('tweeterServices', ['ngResource']);

angular
    .module('tweeterServices')
    .factory('TweeterAppConnexion', tweeterAppConnexionFct)
    .factory('Tweets', ['$resource', tweetFct])
    .factory('Users', ['$resource', userFct])
    .factory('UserTweets', ['$resource', userTweetsFct])
    .factory('UsersFollowers', ['$resource', usersFollowersFct])
    .factory('UsersFollowed', ['$resource', usersFollowedFct]);

function tweeterAppConnexionFct($http) {
    return {
        connection: function (user) {
            return $http.post('rest/connect', user);
        }
    };
}

function tweetFct($resource) {
    return $resource('rest/tweets/:id');
}

function userFct($resource) {
    return $resource('rest/users/:id');
}

function userTweetsFct($resource) {
    return $resource('rest/users/:id/tweets');
}

function usersFollowersFct($resource) {
    return $resource('rest/users/followers/:pseudo');
}

function usersFollowedFct($resource) {
    return $resource('rest/users/followed/:pseudo');
}