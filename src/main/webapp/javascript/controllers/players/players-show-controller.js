'use strict';

angular.module("snippetShare")
    .controller("PlayersShowController", function ($http, $routeParams, $scope) {

        //request GET all players from server
        $http({method: "GET", url:"/players/" + $routeParams.id})
            .success(function (data) {
                console.log("get player "+ $routeParams.id+ " success");
                console.log(data);
                $scope.player = data;
            });

    });
