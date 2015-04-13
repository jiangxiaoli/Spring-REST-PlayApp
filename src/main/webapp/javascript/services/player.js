'use strict';

angular.module("snippetShare")
    .factory("Player", function PlayerFactory($http){
        return {
            all: function(){
                return $http({method: "GET", url:"/players"});
            },
            create: function(player){
                return $http.post("/players", player, {
                    //transfer $http send data format to request param
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
                    transformRequest: function(player){
                        return $.param(player);
                    }
                });
            },
            find: function (id) {
                return $http({method: "GET", url:"/players/" + id});
            },

            //test????
            update: function (player) {
                return $http.put("/players", player, {
                    //transfer $http send data format to request param
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
                    transformRequest: function(player){
                        return $.param(player);
                    }
                });
            },
            remove: function (id) {
                return $http({method: "DELETE", url:"/players/" + id});
            }

        }


    });