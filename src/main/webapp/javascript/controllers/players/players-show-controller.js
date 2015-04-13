'use strict';

angular.module("snippetShare")
    .controller("PlayersShowController", function ($routeParams, $scope, Player, $location) {

        //request GET all players from server
        Player.find($routeParams.id)
            .success(function (data) {
                console.log("get player "+ $routeParams.id+ " success");
                console.log(data);
                $scope.player = data;
            });

        $scope.deletePlayer = function(player){
            Player.remove($routeParams.id)
                .success(function (data) {
                    console.log("delete player "+ $routeParams.id+ " success");
                    console.log(data);
                    $location.path('/players');
                });
                //.then(function(){
                //    $location.path('/players');
                //});
        }



    });
