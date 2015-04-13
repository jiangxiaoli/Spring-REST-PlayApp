'use strict';

angular.module("snippetShare")
    .controller("PlayersIndexController", function ($http, $scope) {

        //request GET all players from server
        $http({method: "GET", url:"/players"})
            .success(function (data) {
                console.log("get players success");
                console.log(data);
                $scope.players = data;
            });

    });