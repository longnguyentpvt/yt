/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
app.controller('Payment', function ($scope, $http) {
    $scope.payment = {
        "vars": {
            "lding": false, // loading
            "dt": null, // data
            "filter": {
                "currentPage": 1,
                "limit": 5,
                "slm": null,
                "sly": 2018,
                "status": null
            },
            "paging": {
                "totalPage": 1,
                "total": 0,
                "pageRange": null
            },
            "DATA": {
                "YEARS": [2018, 2017],
                "BILL_STATUS": {
                    "paid": "paid",
                    "payable": "payable",
                    "pending": "pending",
                    "processing": "processing",
                    "requesting": "requesting"
                }
            }
        },
        "funcs": {
            "cm": null,
            "cy": null,
            "cstt": null,
            "prevU": null,
            "nextU": null

        }
    };

    //load payment list
    function loadPm(reset) {
        var payment = $scope.payment.vars;
        var paging = payment.paging;
        payment.lding = true;
        //reset to search
        if (reset) {
            payment.filter.currentPage = 1;
        } else {
            reset = false;
        }
        $http.post("/partner/partner-payment/loadPayment", payment.filter)
                .then(function successCallback(response) {
                    // set scroll stay at bottom
                    var responseData = response.data;
                    if (angular.isObject(responseData)) {
                        payment.dt = responseData.orders;
                        if (payment.dt.length) {
                            paging.total = responseData.totalResults;
                            paging.totalPage = Math.ceil(paging.total / payment.filter.limit);
                            if (paging.totalPage === 0) {
                                paging.totalPage = 1;
                            }
                            // calculate page range
                            pageRange();
                        }
                    }
                    payment.lding = false;
                });
    }
    // PAGE RANGE 1-10, 21-30 ...
    function pageRange() {
        var payment = $scope.payment.vars;
        var paging = payment.paging;
        //
        paging.pageRange = (payment.filter.currentPage * payment.filter.limit - payment.filter.limit + 1) + " - " + payment.filter.currentPage * payment.filter.limit;
        // IF greater than total
        if (payment.filter.currentPage * payment.filter.limit > paging.total) {
            paging.pageRange = (payment.filter.currentPage * payment.filter.limit - payment.filter.limit + 1) + " - " + paging.total;
        }
    }
    // next page
    $scope.payment.funcs.nextU = function () {
        var up = $scope.payment.vars;
        var paging = up.paging;
        if (angular.isNumber(up.filter.currentPage)) {
            if (up.filter.currentPage > 0 && up.filter.currentPage < paging.totalPage) {
                up.filter.currentPage++;
                loadPm(false);
            }
        }
    };
    // previous page
    $scope.payment.funcs.prevU = function () {
        var up = $scope.payment.vars;
        if (angular.isNumber(up.filter.currentPage)) {
            if (up.filter.currentPage > 1) {
                up.filter.currentPage--;
                loadPm(false);
            }
        }
    };
    // change status 
    $scope.payment.funcs.cstt = function () {
        loadPm(true);
    };
    // change month 
    $scope.payment.funcs.cm = function () {
        loadPm(true);
    };
    // change year 
    $scope.payment.funcs.cy = function () {
        loadPm(true);
    };
    //
    loadPm(true);
});


