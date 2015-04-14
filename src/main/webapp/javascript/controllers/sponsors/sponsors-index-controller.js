'use strict';

angular.module("snippetShare")
    .controller("SponsorsIndexController", function ($scope, Sponsor) {

        //request GET all sponsors from server
        Sponsor.all()
            .success(function (data) {
                console.log("get sponsors success");
                console.log(data);
                $scope.sponsors = data;
            });

    });
