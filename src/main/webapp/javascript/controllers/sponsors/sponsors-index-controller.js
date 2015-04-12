'use strict';

angular.module("snippetShare")
    .controller("SponsorsIndexController", function ($http) {
        var controller = this;

        //request GET all sponsors from server
        $http({method: "GET", url:"/sponsors"})
            .success(function (data) {
                console.log("get sponsors success");
                console.log(data);
                controller.sponsors = data;
            });

    });
