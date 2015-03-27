/**
 * Created by Baptiste on 17/03/2015.
 */

angular.module('tweeterControllers', ['tweeterServices']);

angular.module('tweeterControllers')
    .controller('ConnectionCtrl', ['$scope', '$location', 'TweeterAppConnexion', connectionCtrl])
    .controller('TweetListCtrl',  ['$scope', 'Tweets', 'Users', 'UserTweets', tweetListCtrl])
    .controller('InscriptionCtrl', ['$scope', '$location', 'Users', inscriptionCtrl])
    .controller('FormTweetController', ['$scope', 'Tweets', formTweetController])
    .controller('UserCtrl', ['$scope', 'Users', userCtrl])
    .controller('FollowerListCtrl', ['$scope', 'UsersFollowers', followerListCtrl])
    .controller('FollowedListCtrl', ['$scope', 'UsersFollowed', followedListCtrl])
    .controller('SearchUserCtrl', ['$scope', 'Users', 'UsersFollowed', searchUserCtrl]);



function connectionCtrl($scope, $location, TweeterAppConnexion) {
    $scope.user = {
        pseudo: "",
        password: ""
    };

    $scope.connection = function () {
        TweeterAppConnexion.connection($scope.user)
            .success(function () {
                window.location.href = "twitterRedis.html";
            });
    }
}

function formTweetController($scope, Tweets) {
    $scope.tweet = {
        message: ""
    };
    $scope.sendTweet = function () {
        Tweets.save($scope.tweet);
    }
}

function tweetListCtrl($scope, Tweets, Users, UserTweets) {
    $scope.tweets = Tweets.query();
}

function inscriptionCtrl($scope, $location, Users) {
    $scope.user = {
        pseudo: "",
        name: "",
        surname: "",
        password: ""
    };

    $scope.inscription = function () {
        Users.save($scope.user);
    }
}

function userCtrl($scope, Users) {
    $scope.user = Users.get();
}

function followerListCtrl($scope, UsersFollowers) {
    $scope.users = UsersFollowers.get();
}

function followedListCtrl($scope, UsersFollowed) {
    $scope.users = UsersFollowed.get();
}

function searchUserCtrl($scope, Users, UsersFollowed) {
    $scope.user = {
        pseudo: "",
        name: "",
        surname: "",
        password: ""
    };

    $scope.searchUser = function () {
        $scope.user = Users.get({id: $scope.user.pseudo});
        console.log($scope.user);
    }

    $scope.follow = function () {
        UsersFollowed.save($scope.user);
    }
}
