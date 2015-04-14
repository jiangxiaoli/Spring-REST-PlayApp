
'use strict';

angular.module("snippetShare")
    .factory("Sponsor", function SponsorFactory($http){
        return {
            all: function(){
                return $http({method: "GET", url:"/sponsors"});
            },
            create: function(sponsor){
                return $http.post("/sponsors", sponsor, {
                    //transfer $http send data format to request param
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
                    transformRequest: function(sponsor){
                        return $.param(sponsor);
                    }
                });
            },
            find: function (id) {
                return $http({method: "GET", url:"/sponsors/" + id});
            },
            update: function (sponsor, id) {
                return $http.post("/sponsors/"+id, sponsor, {
                    //transfer $http send data format to request param
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
                    transformRequest: function(sponsor){
                        return $.param(sponsor);
                    }
                });
            },
            remove: function (id) {
                return $http({method: "DELETE", url:"/sponsors/" + id});
            }

        }


    });