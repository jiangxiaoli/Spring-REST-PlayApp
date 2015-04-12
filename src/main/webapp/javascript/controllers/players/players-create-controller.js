'use strict';

angular.module("snippetShare")
    .controller("PlayersCreateController", function ($http) {
        console.log("player create ctrl");
        var controller = this;
        this.savePlayer = function (player) {
            //handel errors
            //controller.errors = null;
            //$http({ method:"POST", url: "/players", data: player })
            //    .success(function (data) {
            //        console.log("in success");
            //        console.log(data);
            //    })
            //    .catch(function (player) {
            //        console.log("in error");
            //        console.log(player);
            //        controller.errors.player.error;
            //    });


            //transfer $http send data format to request param
            var transform = function(player){
                return $.param(player);
            };

            $http.post("/players", player, {
                headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
                transformRequest: transform
            })
                .success(function(data, status, headers, config) {
                    console.log("in success");
                    console.log(data);
                    console.log(status);
                })
                .error(function(data, status, headers, config) {
                    console.log("in error");
                    console.log(data);
                    console.log(status);
                });


        }
    });