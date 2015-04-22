/**
 * Created by Baptiste on 12/04/2015.
 */

(function () {

    'use strict';

    angular
        .module('twitterApp')
        .controller('SearchCtrl', searchCtrl);

    searchCtrl.$inject = ['Users'];

    function searchCtrl(Users) {
        var _this =this;

        // current type of research
        this.searchType = 1;

        // definition of possible type of research
        this.searchTypeOptions = [
            {
                val: 1,
                name: 'user'
            },
            {
                val: 2,
                name: 'hashtag'
            }
        ];

        // function which search user by pseudo
        this.searchUser = function searchUser(userPseudo) {
            Users.user.get({pseudo: userPseudo},
                function success(data) {
                    _this.error = undefined;
                    _this.result = data;
                },
                function error(err) {
                    _this.result = undefined;
                    _this.error = err;
                }
            );
        };

        // function which search tweet by hashtag
        this.searchHashtag = function(hashtag) {
            //todo implement hashtag research and add call to tweet service
        }

        this.searchVisitor = {
            1: _this.searchUser,
            2: _this.searchHashtag
        };

        // result of research
        this.result = undefined;

        this.search = function search() {
            _this.searchVisitor[_this.searchType](_this.pseudo);
        };

        this.addFollowing = function addFollowing() {
            Users.following.save( _this.result.pseudo,
                function success(data) {
                    console.log("success");
                },
                function error(err) {
                    console.log(err);
                }
            );
        };
    }
})();