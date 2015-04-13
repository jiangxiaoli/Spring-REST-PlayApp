'use strict';

angular.module("snippetShare")
    .controller("PlayersCreateController", function ($scope, Player) {

        $scope.savePlayer = function (player) {
            Player.create(player)
                .success(function(data, status, headers, config) {
                    console.log("in success");
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