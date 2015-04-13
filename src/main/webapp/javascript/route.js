'use strict';

//specify routes
angular.module("snippetShare")
    .config(function ($routeProvider) {
        $routeProvider
            .when("/",{
                redirectTo:'/players'
            })
            .when("/players",{
                templateUrl: "templates/pages/players/index.html",
                controller: "PlayersIndexController"
            })
            .when("/players/:id",{
                templateUrl: "templates/pages/players/show.html",
                controller: "PlayersShowController"
            })
            .when("/newplayer",{
                templateUrl: "templates/pages/players/new.html",
                controller: "PlayersCreateController"
            })
            .when("/editplayer",{
                templateUrl: "templates/pages/players/edit.html",
                controller: "PlayersEditController"
            })
            .when("/sponsors",{
                templateUrl: "templates/pages/sponsors/index.html",
                controller: "SponsorsIndexController"
            })
            .when("/sponsors/:id",{
                templateUrl: "templates/pages/sponsors/show.html",
                controller: "SponsorsShowController"
            })
            .otherwise({ redirectTo:'/'});

    });