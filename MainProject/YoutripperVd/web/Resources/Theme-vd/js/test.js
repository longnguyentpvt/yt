/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

app.controller('Common', function ($scope) {
    $scope.common = {
        "fullSpinner": false,
        "time": {
            "hour": -1,
            "min": -1,
            "minutes": null,
            "HHmm": null,
            "change": null
        }
    };
    $scope.popup = {
        "common": {
            "active": false
        }
    };
    $scope.startDate = {
        "todayMilli": 1526295262077,
        "milli": 1526749200000,
        "txt": $scope.CALENDAR.funcs.cMiToDDMMyyyy(1526749200000),
        "limitRight": 1527181200000,
        "ddMMyyyy": null,
        "rO": null,
        "change": null
    };

    $scope.startDate.change = function (dNo) {
        var aD = $scope.startDate.rO.dates[dNo];
        if (aD.ty === 'no') {
            var mi = aD.mi;
            $scope.startDate.milli = mi;
            $scope.startDate.txt = $scope.CALENDAR.funcs.cMiToDDMMyyyy(mi);
            $scope.CALENDAR.funcs.clC();
        }
    };

    $scope.common.time.change = function () {
        var t = $scope.common.time;
        t.minutes = $scope.CLOCK.funcs.gM(t.hour, t.min);
        t.HHmm = $scope.CLOCK.funcs.HHmm(t.minutes);
    };
});