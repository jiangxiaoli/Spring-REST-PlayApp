'use strict';

angular.module("snippetShare")
    .controller("PlayersShowController", function ($routeParams, $scope, Player, Opponent, $location) {

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
        };

        //opponent operation
        //get all players
        Player.all()
            .success(function (data) {
                console.log("get players success");
                console.log(data);
                $scope.players = data;
            });

        $scope.isOpponenting = false;

        $scope.addOpponent = function (player) {
            $scope.isOpponenting = true;
            Opponent.create($routeParams.id, player.player_id)
                .success(function (data) {
                    console.log("in new opponent success");
                    console.log(data);
                    $scope.isOpponenting = false;
                    //how to update view???


                });
        };

        $scope.deleteOpponent = function (player) {
            $scope.isOpponenting = true;
            Opponent.remove($routeParams.id, player.player_id)
                .success(function (data) {
                    console.log("in delete opponent success");
                    console.log(data);
                    $scope.isOpponenting = false;
                    //how to update view???

                });
        };


    });
