'use strict';

angular.module("snippetShare")
    .controller("PlayersEditController", function ($scope, Player, Sponsor, $routeParams, $location) {

        //request GET the current player from server
        Player.find($routeParams.id)
            .success(function (data) {
                console.log("get player "+ $routeParams.id+ " success");
                console.log(data);
                $scope.player = data;
            });


        //find all sponsor for list
        Sponsor.all()
            .success(function (data) {
                console.log("get sponsors success");
                console.log(data);
                $scope.sponsors = data;
            });

        $scope.isSubmitting = false;

        $scope.updatePlayer = function (player) {
            $scope.isSubmitting = true;

            Player.update(player, $routeParams.id)
                .success(function(data, status, headers, config) {
                    console.log("in update success");
                    console.log(data);
                    console.log(status);
                })
                .error(function(data, status, headers, config) {
                    console.log("in error");
                    console.log(data);
                    console.log(status);
                }).finally(function () {
                    $scope.isSubmitting = false;
                    $location.path("/players/" + $routeParams.id);
                });
        };


    });
