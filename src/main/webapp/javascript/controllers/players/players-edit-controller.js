'use strict';

angular.module("snippetShare")
    .controller("PlayersEditController", function ($scope, Player) {

        $scope.updatePlayer = function (player) {
            Player.updatePlayer(player)
                .success(function(data, status, headers, config) {
                    console.log("in update success");
                    console.log(data);
                    console.log(status);
                })
                .error(function(data, status, headers, config) {
                    console.log("in error");
                    console.log(data);
                    console.log(status);
                });
        };

        $scope.deletePlayer = function (id) {
            Player.remove(id)
                .success(function(data, status, headers, config) {
                    console.log("in delete success");
                    console.log(data);
                    console.log(status);
                })
                .error(function(data, status, headers, config) {
                    console.log("in error");
                    console.log(data);
                    console.log(status);
                });

        }


    });
