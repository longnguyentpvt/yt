/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
app.controller('BookingHistory', function ($scope, $http, $interval) {
    $scope.bookingM = {
        "vars": {
            "PACKAGE_LIST": initData.ytps,
            "up": {// upcoming today
                "lding": false, // loading
                "dt": null, // data
                "filter": {
                    "currentPage": 1,
                    "limit": limit,
                    "reset": true,
                    "spkg": "regular", //selected package
                    "channel": null,
                    "packageID": "all",
                    "key_search": null,
                    "time": "all",
                    "startDate": {
                        "milli": null,
                        "ddMMyyyy": ""
                    },
                    "endDate": {
                        "milli": null,
                        "ddMMyyyy": ""
                    },
                    "reviewed": false,
                    "hsk": null,
                    "type": "regular"
                },
                "paging": {
                    "totalPage": 1,
                    "total": 0,
                    "pageRange": null,
                },
                "DATA": {
                    "dmin": "minutes",
                    "dd": "days",
                    "dw": "weeks",
                    "dmonth": "months"
                },
                "TIME_DATA": {
                    "ALL": "all",
                    "YESTERDAY": "yesterday",
                    "PREV_MONTH": "prevMonth",
                    "SPECIFICED": "specificed"
                },
                "PACKAGE_TYPE": {
                    "OPENED": "open-timed",
                    "REGULAR": "regular"
                },
                "FIRST_PAGE": $scope.youtripper.vars.lastPage,
                "REGULAR_BASE_URL": "/partner/booking-history",
                "PARAMS": {
                    "SEARCH_TEXT_PARAM": "searchText",
                    "START_PARAM": "startDate",
                    "END_PARAM": "endDate",
                    "CHANNEL_PARAM": "channel",
                    "PACKAGEID_PARAM": "packageID",
                    "TYPE_PARAM": "type",
                    "NOEX_PARAM": "isNoExpire",
                    "TIME_PARAM": "time",
                    "HASHKEY_PARAM": "hsk",
                    "CURRENTPAGE_PARAM": "crp",
                    "RESET_PARAM": "rs",
                    "REVIEWED_PARAM": "rv"
                }
            },
            "cm": {
                "cd": $scope.CALENDAR.funcs.newYTime(initData.currentDate)
            }
        },
        "funcs": {
            "nextU": null,
            "prevU": null,
            "ccn": null, // CHANGE CHANEL
            "sbbk": null, // SEARCH BY BOOKING CODE,
            "cpkg": null,
            "tfc": null,
            "csd": null, //change startDate
            "ced": null, //change startDate
            "cbkn": null,
            "crv": null

        }
    };
    // LOAD BOOKING
    function loadRBK(reset) {
        let up = $scope.bookingM.vars.up;
        var paging = up.paging;

        up.lding = true;
        // reset to search
        if (reset) {
            up.filter.currentPage = 1;
        } else {
            reset = false;
        }
        $http.post("/partner/booking-history/get-regular-booking", up.filter).then(function successCallback(response) {
            var responseData = response.data;
            if (angular.isObject(responseData)) {
                up.dt = responseData;
                if (up.dt.length) {
                    paging.total = up.dt[0].totalResults;
                    paging.totalPage = Math.ceil(paging.total / up.filter.limit);
                    if (paging.totalPage === 0) {
                        paging.totalPage = 1;
                    }
                    // calculate page range
                    pageRange();
                }
            }
            up.lding = false;
        });
    }
    //
    function loadOBK(reset) {
        let up = $scope.bookingM.vars.up;
        var paging = up.paging;

        up.lding = true;
        // reset to search
        if (reset) {
            up.filter.currentPage = 1;
        } else {
            reset = false;
        }
        $http.post("/partner/booking-history/get-open-booking", up.filter).then(function successCallback(response) {
            var responseData = response.data;
            if (angular.isObject(responseData)) {
                up.dt = responseData;
                if (up.dt.length) {
                    paging.total = up.dt[0].totalResults;
                    paging.totalPage = Math.ceil(paging.total / up.filter.limit);
                    if (paging.totalPage === 0) {
                        paging.totalPage = 1;
                    }
                    // calculate page range
                    pageRange();
                }
            }
            up.lding = false;
        });
    }//

    // PAGE RANGE 1-10, 21-30 ...
    function pageRange() {
        var up = $scope.bookingM.vars.up;
        var paging = up.paging;
        //
        var l = (up.filter.currentPage * up.filter.limit - up.filter.limit + 1);
        var r = up.filter.currentPage * up.filter.limit;
        // IF greater than total
        if (r > paging.total) {
            r = paging.total;
        }
        paging.pageRange = l + " - " + r;
    }

    // next page
    $scope.bookingM.funcs.nextU = function () {
        var up = $scope.bookingM.vars.up;
        var paging = up.paging;

        if (up.filter.currentPage > 0 && up.filter.currentPage < paging.totalPage) {
            up.filter.currentPage++;
            up.filter.reset = false;
            changeState();
        }
    };

    // previous page
    $scope.bookingM.funcs.prevU = function () {
        var up = $scope.bookingM.vars.up;
        if (up.filter.currentPage > 1) {
            up.filter.currentPage--;
            up.filter.reset = false;
            changeState();
        }
    };
    // change channel
    $scope.bookingM.funcs.ccn = function () {
        var up = $scope.bookingM.vars.up;
        up.filter.reset = true;
        changeState();
    };
    // SET UP DATE RANGE
    function setupDateRange() {
        var setup = $scope.bookingM.vars;
        var timeFilter = setup.up.filter.time;
        var filterData = setup.up.TIME_DATA;
        var startDate = setup.up.filter.startDate;
        var endDate = setup.up.filter.endDate;
        var toDateObject = new Date(setup.cm.cd);
        toDateObject.setHours(0, 0, 0, 0);
        if (timeFilter === filterData.ALL) {
            startDate.milli = null;
            endDate.milli = null;
        } else if (timeFilter === filterData.YESTERDAY) {
            //
            var yesterday = new Date(toDateObject);
            yesterday.setDate(yesterday.getDate() - 1);
            startDate.milli = yesterday.getTime();
            endDate.milli = yesterday.getTime();
        } else if (timeFilter === filterData.PREV_MONTH) {
            // get start date and end adate of previous month
            var startDateOfMonth = new Date(toDateObject);
            startDateOfMonth.setMonth(startDateOfMonth.getMonth() - 1);
            startDateOfMonth.setDate(1);
            startDate.milli = startDateOfMonth.getTime();

            var endDateOfMonth = new Date(toDateObject);
            endDateOfMonth.setDate(0);
            endDate.milli = endDateOfMonth.getTime();
        } else {
            var yesterday = new Date(toDateObject);
            yesterday.setDate(yesterday.getDate() - 1);
            startDate.milli = yesterday.getTime();
            endDate.milli = yesterday.getTime();
        }
        // then convert milli to str
        startDate.ddMMyyyy = $scope.CALENDAR.funcs.cMiToDDMMyyyy(startDate.milli);
        endDate.ddMMyyyy = $scope.CALENDAR.funcs.cMiToDDMMyyyy(endDate.milli);
    }

    // blank text search
    $scope.bookingM.funcs.bts = function () {
        var up = $scope.bookingM.vars.up;
        if (!up.filter.key_search.length) {
            up.filter.reset = true;
            changeState();
        }
    };

    // search BY BOOKING NO
    $scope.bookingM.funcs.sbbk = function (event) {
        var up = $scope.bookingM.vars.up;
        if (event.keyCode === 13 || event.keyCode === 10) {
            up.filter.reset = true;
            changeState();
        }
    };

    // search clear booking NO
    $scope.bookingM.funcs.cbkn = function () {
        var setup = $scope.bookingM.vars.up.filter;
        setup.key_search = null;
        setup.reset = true;
        changeState();
    };

    // change package
    $scope.bookingM.funcs.cpkg = function () {
        var setup = $scope.bookingM.vars.up.filter;
        //reset time to Show All
        setup.time = "all";
        setupDateRange();
        // get model
        var selectedPkg = setup.spkg;
        if (!angular.isString(selectedPkg)) { // model is PACKAGE
            var packageID = selectedPkg.packageID;
            setup.packageID = packageID;
            var servingType = selectedPkg.servingType;
            var hashkey = selectedPkg.$$hashKey.split(":");
            setup.hsk = hashkey[1];
            if (servingType === $scope.bookingM.vars.up.PACKAGE_TYPE.OPENED) { // serving type is OPEN-TIMED
                setup.type = $scope.bookingM.vars.up.PACKAGE_TYPE.OPENED;
            } else { // OTHER SERVING (public,private,group). Set type REGULAR
                setup.type = $scope.bookingM.vars.up.PACKAGE_TYPE.REGULAR;
            }
        } else { // model is a STRING
            setup.packageID = "all";
            setup.type = selectedPkg;
        }
        setup.reset = true;
        changeState();
    };
    //time filter change
    $scope.bookingM.funcs.tfc = function () {
        var setup = $scope.bookingM.vars.up.filter;
        setupDateRange();
        //load result
        setup.reset = true;
        changeState();
    };
    // CHANGE START DATE
    $scope.bookingM.funcs.csd = function (dNo) {
        var up = $scope.bookingM.vars.up;
        var aD = $scope.startDate.rO.dates[dNo];
        if (aD.ty === 'no' || aD.ty === 'to') {
            var mi = aD.mi;
            up.filter.startDate.milli = mi;
            up.filter.startDate.ddMMyyyy = $scope.CALENDAR.funcs.cMiToDDMMyyyy(mi);
            $scope.CALENDAR.funcs.clC();
            //
            up.filter.reset = true;
            changeState();
        }
    };
    // CHANGE END DATE
    $scope.bookingM.funcs.ced = function (dNo) {
        var up = $scope.bookingM.vars.up;
        var aD = $scope.startDate.rO.dates[dNo];
        if (aD.ty === 'no' || aD.ty === 'to') {
            var mi = aD.mi;
            up.filter.endDate.milli = mi;
            up.filter.endDate.ddMMyyyy = $scope.CALENDAR.funcs.cMiToDDMMyyyy(mi);
            $scope.CALENDAR.funcs.clC();
            //
            up.filter.reset = true;
            changeState();
        }
    };
    // CHANGE REVIEWED
    $scope.bookingM.funcs.crv = function () {
        var up = $scope.bookingM.vars.up;
        up.filter.reset = true;
        changeState();
    };
    //
    // init
    function init(reset) {
        var setup = $scope.bookingM.vars.up;
        if (setup.filter.type === setup.PACKAGE_TYPE.OPENED) {
            loadOBK(reset);
        } else {
            loadRBK(reset);
        }

    }
    //
    //KEEP SEARCHING
    // override history push state
    (function (history) {
        var pushState = history.pushState;
        history.pushState = function (state) {
            if (typeof history.onpushstate === "function") {
                history.onpushstate({state: state});
            }
            // whatever else you want to do
            // maybe call onhashchange e.handler
            return pushState.apply(history, arguments);
        };
    })(window.history);

    function buildURL() {
        var vars = $scope.bookingM.vars.up;
        var filterVars = vars.filter;
        // build param
        var paramURL = new URLSearchParams();
        // build url
        var url = vars.REGULAR_BASE_URL;
        // search txt 1
        var searchText = filterVars.key_search;
        if (angular.isString(searchText)) {
            paramURL.append(vars.PARAMS.SEARCH_TEXT_PARAM, searchText);
        }
        //start date and endDate 2
        if (filterVars.startDate.milli > 0) {
            paramURL.append(vars.PARAMS.START_PARAM, filterVars.startDate.milli);
        }
        // 3
        if (filterVars.endDate.milli > 0) {
            paramURL.append(vars.PARAMS.END_PARAM, filterVars.endDate.milli);
        }
        // channel filter 4
        var channel = filterVars.channel;
        if (channel !== null) {
            paramURL.append(vars.PARAMS.CHANNEL_PARAM, channel);
        }
        // packageID filter 5
        var packageID = filterVars.packageID;
        if (angular.isString(packageID)) {
            paramURL.append(vars.PARAMS.PACKAGEID_PARAM, packageID);
        }
        // time filter 6
        var time = filterVars.time;
        if (angular.isString(time)) {
            paramURL.append(vars.PARAMS.TIME_PARAM, time);
        }
        // type filter 8
        var type = filterVars.type;
        if (angular.isString(type)) {
            paramURL.append(vars.PARAMS.TYPE_PARAM, type);
        }
        // hashkey
        var hsk = filterVars.hsk;
        if (angular.isString(hsk) || angular.isNumber(hsk)) {
            paramURL.append(vars.PARAMS.HASHKEY_PARAM, hsk);
        }
        // current page
        var crp = filterVars.currentPage;
        if (angular.isNumber(crp)) {
            paramURL.append(vars.PARAMS.CURRENTPAGE_PARAM, crp);
        }
        // reset
        var rs = filterVars.reset;
        if (angular.isDefined(rs)) {
            paramURL.append(vars.PARAMS.RESET_PARAM, rs);
        }
        //review
        var rv = filterVars.reviewed;
        if (angular.isDefined(rv)) {
            paramURL.append(vars.PARAMS.REVIEWED_PARAM, rv);
        }
        //
        url += "?" + paramURL.toString();
        return url;
    }

    function changeState() {
        // update state
        var url = buildURL();
        $scope.youtripper.vars.lastPage = url;
        window.history.pushState(null, null, url);
    }

    history.onpushstate = function (e) {
        init($scope.bookingM.vars.up.filter.reset);
    };

    window.onpopstate = function (e) {
        $(location).attr('href', $scope.bookingM.vars.up.FIRST_PAGE);
    };
    // END KEEP SEARCHING

    // get value from URL
    var urlParams = new URLSearchParams(window.location.search);
    var PARAMS = $scope.bookingM.vars.up.PARAMS;
    var searchText = urlParams.getAll(PARAMS.SEARCH_TEXT_PARAM);
    var startDate = urlParams.getAll(PARAMS.START_PARAM);
    var endDate = urlParams.getAll(PARAMS.END_PARAM);
    var channel = urlParams.getAll(PARAMS.CHANNEL_PARAM);
    var packageID = urlParams.getAll(PARAMS.PACKAGEID_PARAM);
    var time = urlParams.getAll(PARAMS.TIME_PARAM);
    var type = urlParams.getAll(PARAMS.TYPE_PARAM);
    var hske = urlParams.getAll(PARAMS.HASHKEY_PARAM);
    var crpg = urlParams.getAll(PARAMS.CURRENTPAGE_PARAM);
    var rsp = urlParams.getAll(PARAMS.RESET_PARAM);
    var rvp = urlParams.getAll(PARAMS.REVIEWED_PARAM);
    //
    var filterVars = $scope.bookingM.vars.up.filter;
    //tripperID
    //startDate
    if (rvp[0] !== undefined) {
        if (startDate[0] !== undefined) {
            filterVars.startDate.milli = Number(startDate[0]);
            filterVars.startDate.ddMMyyyy = $scope.CALENDAR.funcs.cMiToDDMMyyyy(Number(startDate[0]));
        }
        //end Date
        if (endDate[0] !== undefined) {
            filterVars.endDate.milli = Number(endDate[0]);
            filterVars.endDate.ddMMyyyy = $scope.CALENDAR.funcs.cMiToDDMMyyyy(Number(endDate[0]));
        }
        //search text
        if (searchText[0] !== undefined) {
            filterVars.key_search = (searchText[0]);
        }
        //status
        if (channel[0] !== undefined) {
            filterVars.channel = (channel[0].toLowerCase().trim() === 'true' ? true : false);
        }
        //hashkey
        if (hske[0] !== undefined) {
            filterVars.hsk = Number(hske[0]);
        }
        //packageID
        if (type[0] !== undefined) {
            //type
            filterVars.type = (type[0]);
            if (packageID[0] !== undefined) {
                filterVars.packageID = (packageID[0]);
                if (filterVars.packageID === "all") {
                    filterVars.spkg = filterVars.type;
                } else {
                    filterVars.spkg = {"packageID": filterVars.packageID, "servingType": filterVars.type, "$$hashKey": "object:" + filterVars.hsk};
                }
            }
        }
        //packageID
        if (packageID[0] !== undefined) {
            filterVars.packageID = (packageID[0]);
        }
        //time
        if (time[0] !== undefined) {
            filterVars.time = (time[0]);
        }
        // crp
        filterVars.currentPage = Number(crpg[0]);
        // reset page
        filterVars.reset = (rsp[0].toLowerCase().trim() === 'true' ? true : false);
        //
        filterVars.reviewed = (rvp[0].toLowerCase().trim() === 'true' ? true : false);
        // init
        init(filterVars.reset);
    } else {
        setupDateRange();
        init(true);
    }
    //
    // REFRESH EVERY 60s
//    $interval(function () {
//        init(false);
//    }, 60000);

});


