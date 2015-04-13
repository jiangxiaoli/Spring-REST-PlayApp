'use strict';

angular.module("snippetShare")
    .controller("PlayersCreateController", function ($scope, Player, $location) {
        $scope.isSubmitting = false;

        $scope.savePlayer = function (player) {
            $scope.isSubmitting = true;

            Player.create(player)
                .success(function(data, status, headers, config) {
                    console.log("in create success");
                    console.log(data);
                    console.log(status);
                    $location.path("/players");
                })
                .error(function(data, status, headers, config) {
                    console.log("in error");
                    console.log(data);
                    console.log(status);
                }).finally(function () {
                    $scope.isSubmitting = false;
                });

        }
    });