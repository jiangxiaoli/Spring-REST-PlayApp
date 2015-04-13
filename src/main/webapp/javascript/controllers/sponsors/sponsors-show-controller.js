'use strict';

angular.module("snippetShare")
    .controller("SponsorsShowController", function ($http, $routeParams, $scope) {
        //request GET all players from server
        $http({method: "GET", url:"/sponsors/" + $routeParams.id})
            .success(function (data) {
                console.log("get sponsor "+ $routeParams.id+ " success");
                console.log(data);
                $scope.sponsor = data;
            });

    });

