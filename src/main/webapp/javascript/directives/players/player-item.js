'use strict';

angular.module("snippetShare")
.directive("playerItem", function () {
        return {
            restrict: "E",
            templateUrl: "templates/directives/players/player-item.html",
            scope: {
                player_id: "=",
                firstname: "=",
                lastname: "=",
                email: "="
            },
            controller: function ($scope) {
            },
            link: function (scope, element, attrs) {

                element("h5").on("click", function () {
                    alert("click on " + scope.firstname);
                    element.find("h5").css("color", "#000");
                });

            }

        }
    });
