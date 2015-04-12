'use strict';

//specify routes
angular.module("snippetShare")
    .config(function ($routeProvider) {
        $routeProvider
            .when("/",{
                templateUrl: "templates/pages/players/index.html"
            })
            .when("/players",{
                templateUrl: "templates/pages/players/index.html",
                controller: "PlayersIndexController",
                controllerAs: "indexCtrl"
            })
            .when("/players/:id",{
                templateUrl: "templates/pages/players/show.html",
                controller: "PlayersShowController",
                controllerAs: "showCtrl"
            })
            .when("/newplayer",{
                templateUrl: "templates/pages/players/new.html",
                controller: "PlayersCreateController",
                controllerAs: "createCtrl"
            })
            .when("/sponsors",{
                templateUrl: "templates/pages/sponsors/index.html",
                controller: "SponsorsIndexController",
                controllerAs: "indexCtrl"
            })
            .when("/sponsors/:id",{
                templateUrl: "templates/pages/sponsors/show.html",
                controller: "SponsorsShowController",
                controllerAs: "showCtrl"
            })
            .otherwise({ redirectTo:'/'});

    });