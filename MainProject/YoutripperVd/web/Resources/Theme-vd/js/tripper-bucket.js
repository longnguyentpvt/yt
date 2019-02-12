/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

app.controller('TripperBucket', function ($scope, $http) {
    $scope.bucket = {
        "vars": {
            "lding": false,
            "dt": null
        },
        "funcs": {
            "rm": null,
        }
    };

    //load bucket list
    function loadbl() {
        let b = $scope.bucket.vars;
        b.lding = true;
        $http.post("/tripper/buckets/list", {}).then(function successCallback(response) {
            var responseData = response.data;
            if (angular.isObject(responseData)) {
                b.dt = response.data;
                b.lding = false;
            }
        });
    }

    //remove package from list
    $scope.bucket.funcs.rm = function (packageID) {
        let b = $scope.bucket.vars;
        var rqdt = {"packageID": packageID};
        b.lding = true;
        $http.post("/tripper/buckets/remove", rqdt).then(function successCallback(response) {
            var responseData = response.data;
            if (angular.isObject(responseData)) {
                if (responseData.result === true) {
                    loadbl();
                }
            }
            b.lding = false;
        });
    };

    //
    loadbl();

});

