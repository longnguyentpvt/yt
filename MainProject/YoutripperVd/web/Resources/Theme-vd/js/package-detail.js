/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


app.controller('PackageDetail', function ($scope, $http, $timeout) {
    var cd = $scope.CALENDAR.funcs.newYTime(1531242000000);
    $scope.dt = {
        "vars": {
            "pid": pid,
            "cd": cd,
            "td": {
                "ml": null,
                "txt": null,
                "lml": cd,
                "cld": null
            },
            "npkgs": null,
            "tid": null
        },
        "funcs": {
            "slsd": null, // select new serving date
            "book": null, // book function,
            "atwl": null// add to wish list
        }
    };

    $scope.dt.funcs.slsd = function (id) {
        var td = $scope.dt.vars.td;
        var ds = td.cld.dates;
        var sm = ds[id];
        if (sm.ty === 'no' || sm.ty === 'to') {
            td.ml = sm.mi;
            td.txt = $scope.CALENDAR.funcs.cMiToDDMMyyyy(sm.mi);
            $scope.CALENDAR.funcs.clC();
        }
    };

    $scope.dt.funcs.book = function () {
        var vs = $scope.dt.vars;
        var rqd = {
            "pkgID": vs.pid,
            "cc": $scope.youtripper.vars.data.currentCur,
            "np": vs.npkgs,
            "sd": $scope.CALENDAR.funcs.reYTime(vs.td.ml)
        };
        $http.post("/booking-process", rqd)
                .then(function successCallback(response) {
                    var responseData = response.data;
                    if (angular.isObject(responseData)) {
                        vs.tid = responseData.rs;
                        $timeout(function () {
                            $("#co-f").submit();
                        }, 100);
                    }
                });
    };

    $scope.dt.funcs.atwl = function () {
        var vs = $scope.dt.vars;
        var rqd = {
            "packageID": vs.pid
        };
        $http.post("/tripper/add-wishlist", rqd)
                .then(function successCallback(response) {
                    var responseData = response.data;
                    if (angular.isObject(responseData)) {
                        alert(responseData.result);
                    }
                });
    };
});