/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


app.controller('CheckoutReview', function ($scope, $http, $timeout) {
    $scope.ytheader.vars.me = false;

    $scope.cr = {
        "vars": {
            "tid": tid,
            "if": {
                "pid": null,
                "pname": null,
                "ppr": null,
                "qty": null,
                "stt": null,
                "tt": null,
                "cc": null
            },
            "tem": null,
            "bl": {
                "fn": null,
                "ln": null
            },
            "dc": {
                "code": "testcode02",
                "ep": false,
                "wc": false,
                "tt": 0
            },
            "on": null,
            "mt": "vm",
            "loading": true
        },
        "funcs": {
            "co": null
        }
    };
    
    // load booking info
    function lrv() {
        var vs = $scope.cr.vars;

        $http.post("/checkout-review/loading", {"tid": vs.tid, "dc" : vs.dc.code})
                .then(function successCallback(response) {
                    var responseData = response.data;
                    if (angular.isObject(responseData)) {
                        console.log(responseData);
                        var rp = responseData.info;

                        var info = vs.if;
                        info.pid = rp.pkgID;
                        info.ppr = rp.pkgPrice;
                        info.qty = rp.qty;
                        info.stt = rp.subTotal;
                        info.tt = rp.total;
                        info.cc = rp.currencyCode;

                        vs.loading = false;
                    }
                });
    }

    // checkout function
    $scope.cr.funcs.co = function () {
        var vs = $scope.cr.vars;
        var mt = vs.mt;
        var rpd = {
            "tid": vs.tid,
            "tem": vs.tem,
            "bfn": vs.bl.fn,
            "bln": vs.bl.ln,
            "dc" : vs.dc.code
        };

        if (mt === 'vm') {
            $http.post("/checkout-review/vm-request", rpd)
                    .then(function successCallback(response) {
                        var responseData = response.data;
                        if (angular.isObject(responseData)) {
                            vs.on = responseData.rp;
                            $timeout(function () {
                                $("#co-f").submit();
                            }, 100);
                        }
                    });
        } else if (mt === 'pp') {
            $http.post("/checkout-review/paypal-request", rpd)
                    .then(function successCallback(response) {
                        var responseData = response.data;
                        if (angular.isObject(responseData)) {
                            let rl = responseData.rp;
                            $(location).attr('href', rl);
                        }
                    });
        } else if (mt === 'lp') {
            $http.post("/checkout-review/linepay-request", rpd)
                    .then(function successCallback(response) {
                        var responseData = response.data;
                        if (angular.isObject(responseData)) {
                            console.log(responseData);
                            let rl = responseData.rp;
                            $(location).attr('href', rl);
                        }
                    });
        }
    };

    lrv();
});