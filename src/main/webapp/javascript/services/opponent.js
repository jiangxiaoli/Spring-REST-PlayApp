'use strict';

angular.module("snippetShare")
    .factory("Opponent", function OpponentFactory($http){
        return {

            create: function(id1, id2){
                return $http({method: "PUT", url:"/opponent/" + id1+"/"+id2});
            },
            remove: function (id1, id2) {
                return $http({method: "DELETE", url:"/opponent/" + id1+"/"+id2});
            }

        }


    });