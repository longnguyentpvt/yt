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

$(function () {
    $(document).on('click', function (event) {
        var ce = $(event.target);
        if (!ce.is("#bks .abk .h-r .ops .hbg") && !ce.closest("#bks .abk .h-r .ops .hbg").length) {
            $("#bks .abk .h-r .ops.op").removeClass("op");
        }
    });
    $(document).on('click', "#bks .abk .h-r .ops .hbg", function () {
        let pr = $(this).parent(".ops");
        pr.addClass("clicked").toggleClass("op");
        $("#bks .abk .h-r .ops.op:not(.clicked)").removeClass("op");
        pr.removeClass("clicked");
    });
});

app.controller('MyBooking', function ($scope, $http, $sce) {
    $scope.bking = {
        "vars": {
            "lding": false,
            "dt": null,
            "filter": {
                "page": 1,
                "limit": limit,
                "bookingCode": null,
                "reset": true
            },
            "paging": {
                "totalPage": 1,
                "total": 0,
                "pageRange": null
            },
            "popup": {
                "active": false,
                "invoiceUrl": null
            }
        },
        "funcs": {
            "nextU": null,
            "prevU": null,
            "openInv": null
        }
    };
    //load booking list
    function loadbk(reset) {
        let b = $scope.bking.vars;
        var paging = b.paging;
        b.lding = true;
        //reset when filter
        if (reset) {
            b.filter.page = 1;
        }
        $http.post("/tripper/my-booking/list", b.filter).then(function successCallback(response) {
            var responseData = response.data;
            if (angular.isObject(responseData)) {
                b.dt = response.data;
                //get total orders
                paging.total = b.dt[0].totalResults;
                paging.totalPage = Math.ceil(paging.total / b.filter.limit);
                if (paging.totalPage === 0) {
                    paging.totalPage = 1;
                }
                //calculate
                pageRange();
            }
            b.lding = false;
        });
    }
    // load booking list
    loadbk(false);

    // calculate page range
    function pageRange() {
        var up = $scope.bking.vars;
        var paging = up.paging;
        //
        var l = (up.filter.page * up.filter.limit - up.filter.limit + 1);
        var r = up.filter.page * up.filter.limit;
        // IF greater than total
        if (r > paging.total) {
            r = paging.total;
        }
        paging.pageRange = l + " - " + r;
    }

    // next page
    $scope.bking.funcs.nextU = function () {
        var up = $scope.bking.vars;
        var paging = up.paging;

        if (up.filter.page > 0 && up.filter.page < paging.totalPage) {
            up.filter.page++;
            loadbk(false);
        }
    };

    // previous page
    $scope.bking.funcs.prevU = function () {
        var up = $scope.bking.vars;

        if (up.filter.page > 1) {
            up.filter.page--;
            loadbk(false);
        }
    };

    //open
    $scope.bking.funcs.openInv = function (link) {
        var up = $scope.bking.vars;
        up.popup.invoiceUrl = link;
        //open PDF popup
        $scope.POPUP.openPopup(up.popup);
    };
    //config link for showing PDF
    $scope.trustSrc = function (src) {
        return $sce.trustAsResourceUrl(src);
    };

    // booking code is blank
    $scope.bking.funcs.bts = function () {
        var up = $scope.bking.vars;
        if (!up.filter.bookingCode.length) {
            loadbk(true);
        }
    };

    // search BY BOOKING NO
    $scope.bking.funcs.sbbk = function (event) {
        var up = $scope.bking.vars;
        if (event.keyCode === 13 || event.keyCode === 10) {
            loadbk(true);
        }
    };

    // search clear booking NO
    $scope.bking.funcs.cbkn = function () {
        var setup = $scope.bking.vars.filter;
        setup.bookingCode = null;
        loadbk(true);
    };
});

