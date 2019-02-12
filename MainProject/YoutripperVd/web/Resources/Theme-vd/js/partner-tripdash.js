/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
app.controller('PartnerTripDash', function ($scope, $http, $interval) {
    $scope.tripDash = {
        "vars": {
            "po": {// PACKAGE OVERVIEW
                "lding": false, // loading
                "dt": null // data
            },
            "bs": {// BEST SELLING
                "lding": false, // loading
                "dt": null, // data
                "crd": null
            },
            "hg": {// HIGHEST GROSSING
                "lding": false, // loading
                "dt": null, // data
                "crd": null
            },
            "ts": {// TOTAL SALE
                "lding": false, // loading
                "dt": null, // data

            },
            "up": {// upcoming today
                "lding": false, // loading
                "dt": null, // data
                "currentPage": 1,
                "limit": limit,
                "totalPage": 1,
                "total": 0,
                "pageRange": null,
                "DATA": {
                    "dmin": "minutes",
                    "dd": "days",
                    "dw": "weeks",
                    "dmonth": "months",
                    "crd": null
                }
            }
        },
        "funcs": {
            "nextU": null,
            "prevU": null
        }
    };

    // LOAD PACKAGE OVERVIEW
    function loadPO(refresh) {
        let po = $scope.tripDash.vars.po;
        if (!refresh) {
            po.lding = true;
        }
        // SEND REQUEST
        $http.post("/partner/dashboard/package-overview", {}).then(function successCallback(response) {
            var responseData = response.data;
            if (angular.isObject(responseData)) {
                po.dt = responseData;
            }
            po.lding = false;
        });
    }

    // LOAD BEST SELLING
    function loadBS(refresh) {
        let bs = $scope.tripDash.vars.bs;
        if (!refresh) {
            bs.lding = true;
        }
        // SEND REQUEST
        $http.post("/partner/dashboard/package-selling", {}).then(function successCallback(response) {
            var responseData = response.data;
            bs.crd = responseData.currentDateMMYY;
            if (angular.isObject(responseData)) {
                bs.dt = responseData.top2;
                for (var i = 0; i < bs.dt.length; i++) {
                    let aP = bs.dt[i];
                    if (aP.packageID.length) {
                        aP.fnpercent = Math.round(aP.percent * 100);
                    } else {
                        aP.fnpercent = 0;
                    }
                }
            }
            bs.lding = false;
        });
    }
    // LOAD HIGHEST GROSSING
    function loadHG(refresh) {
        let hg = $scope.tripDash.vars.hg;
        if (!refresh) {
            hg.lding = true;
        }
        //
        $http.post("/partner/dashboard/highest-grossing", {}).then(function successCallback(response) {
            var responseData = response.data;
            hg.crd = responseData.currentDateMMYY;
            if (angular.isObject(responseData)) {
                hg.dt = responseData.top3;
            }
            hg.lding = false;
        });
    }
    // LOAD TOTAL SALE
    function loadTS(refresh) {
        let ts = $scope.tripDash.vars.ts;
        if (!refresh) {
            ts.lding = true;
        }
        //
        $http.post("/partner/dashboard/highest-sale", {}).then(function successCallback(response) {
            var responseData = response.data;
            if (angular.isObject(responseData)) {
                ts.dt = responseData;
            }
            ts.lding = false;
        });
    }
    // LOAD UPCOMING TODAY
    function loadUP(refresh) {
        let up = $scope.tripDash.vars.up;
        if (!refresh) {
            up.lding = true;
        }
        // REQUEST DATA
        var rqdt = {"page": up.currentPage, "limit": up.limit};
        //
        $http.post("/partner/dashboard/upcoming", rqdt).then(function successCallback(response) {
            var responseData = response.data;
            if (angular.isObject(responseData)) {
                up.dt = responseData.bookingOrders;
                up.DATA.crd = responseData.currentDateDDMMYYY;
                if (up.dt.length) {
                    up.total = up.dt[0].totalResults;
                    up.totalPage = Math.ceil(up.total / up.limit);
                    if (up.totalPage === 0) {
                        up.totalPage = 1;
                    }
                    pageRange();
                }
            }
            up.lding = false;
        });
    }//
    // PAGE RAGE 1-10, 21-30 ...
    function pageRange() {
        var up = $scope.tripDash.vars.up;
        //
        var l = (up.currentPage * up.limit - up.limit + 1);
        var r = up.currentPage * up.limit;
        if (r > up.total) {
            r = up.total;
        }
        up.pageRange = l + " - " + r;
    }
    // next page
    $scope.tripDash.funcs.nextU = function () {
        var up = $scope.tripDash.vars.up;
        if (!isNaN(up.currentPage)) {
            if (up.currentPage > 0 && up.currentPage < up.totalPage) {
                up.currentPage++;
                loadUP(false);
            }
        }
    };
    // previous page
    $scope.tripDash.funcs.prevU = function () {
        var up = $scope.tripDash.vars.up;
        if (!isNaN(up.currentPage)) {
            if (up.currentPage > 1) {
                up.currentPage--;
                loadUP(false);
            }
        }
    };

    function init(refresh) {
        loadPO(refresh);
        loadBS(refresh);
        loadHG(refresh);
        loadTS(refresh);
        loadUP(refresh);
    }
    // REFRESH EVERY 15s
    $interval(function () {
        init(true);
    }, 15000);

    init(false);
});


