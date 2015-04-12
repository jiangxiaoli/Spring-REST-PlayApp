'use strict';

angular.module("snippetShare")
    .controller("PlayersCreateController", function ($http) {
        console.log("player create ctrl");

        var controller = this;

        controller.player = null;

        this.savePlayer = function (player) {
            //handel errors
            controller.errors = null;
            $http({ method:"POST", url: "/players", data: player })
                .success(function (data) {
                    console.log("in success");
                    console.log(data);
                })
                .catch(function (player) {
                    console.log("in error");
                    console.log(player);
                    controller.errors.player.error;
                });
        }
    });