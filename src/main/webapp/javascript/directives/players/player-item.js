'use strict';

angular.module("snippetShare")
.directive("playerItem", function () {
        return {
            restrict: "E",
            templateUrl: "templates/directives/players/player-item.html",
            controller: function ($scope) {
                $scope.player_id = "1";
                $scope.firstname = "xiaoli";
                $scope.lastname = "jiang";

            }
        }
    });
