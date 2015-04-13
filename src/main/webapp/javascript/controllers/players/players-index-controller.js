'use strict';

angular.module("snippetShare")
    .controller("PlayersIndexController", function ($scope, Player) {

        //request GET all players from server
        Player.all()
            .success(function (data) {
                console.log("get players success");
                console.log(data);
                $scope.players = data;
            });

    });