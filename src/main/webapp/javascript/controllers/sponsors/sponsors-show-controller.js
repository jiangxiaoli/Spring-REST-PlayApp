'use strict';

angular.module("snippetShare")
    .controller("SponsorsShowController", function ($routeParams, $scope, Sponsor) {
        //request GET all players from server
        Sponsor.find($routeParams.id)
            .success(function (data) {
                console.log("get sponsor "+ $routeParams.id+ " success");
                console.log(data);
                $scope.sponsor = data;
            });

    });

