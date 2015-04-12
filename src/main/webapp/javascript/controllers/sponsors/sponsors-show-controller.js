'use strict';

angular.module("snippetShare")
    .controller("SponsorsShowController", function ($http, $routeParams) {
        var controller = this;

        //request GET all players from server
        $http({method: "GET", url:"/sponsors/" + $routeParams.id})
            .success(function (data) {
                console.log("get sponsor "+ $routeParams.id+ " success");
                console.log(data);
                controller.sponsor = data;
            });

    });

