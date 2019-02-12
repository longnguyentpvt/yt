/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function URLSearchParams(urlQuery) {
    this.paramsMap = {
    };
    if (angular.isString(urlQuery)) {
        urlQuery = urlQuery.substr(1, urlQuery.length - 1);
        var paramStrs = urlQuery.split("&");
        for (var i = 0, max = paramStrs.length; i < max; i++) {
            var paramStr = paramStrs[i];
            var paramPair = paramStr.split("=");
            var paramName = paramPair[0];

            var queryValues = this.paramsMap[paramName];
            if (!angular.isArray(queryValues)) {
                queryValues = [];
                this.paramsMap[paramName] = queryValues;
            }
            queryValues.push(paramPair[1]);
        }
    }
}

URLSearchParams.prototype.get = function (paramName) {
    var paramValues = this.paramsMap[paramName];
    if (angular.isArray(paramValues) && paramValues.length) {
        var result = unescape(paramValues[0]);
        result = decodeURIComponent(paramValues[0]);
        result = result.replace(/\+/g, " ");
        return result;
    }
    return null;
};

URLSearchParams.prototype.getAll = function (paramName) {
    var paramValues = this.paramsMap[paramName];
    if (angular.isArray(paramValues) && paramValues.length) {
        return paramValues;
    }
    return [];
};

URLSearchParams.prototype.set = function (paramName, paramValue) {
    var paramValues = this.paramsMap[paramName];
    if (angular.isArray(paramValues)) {
        paramValues.length = 0;
        paramValues = [];
    } else {
        paramValues = [];
        this.paramsMap[paramName] = paramValues;
    }
    paramValues.push(paramValue);
};

URLSearchParams.prototype.append = function (paramName, paramValue) {
    var paramValues = this.paramsMap[paramName];
    if (!angular.isArray(paramValues)) {
        paramValues = [];
        this.paramsMap[paramName] = paramValues;
    }
    paramValues.push(paramValue);
};

URLSearchParams.prototype.toString = function () {
    var str = "";
    angular.forEach(this.paramsMap, function (paramValues, paramName) {
        for (var i = 0, max = paramValues.length; i < max; i++) {
            str += paramName + "=" + paramValues[i] + "&";
        }
    });
    return str.substr(0, str.length - 1);
};

URLSearchParams.prototype.deleteParam = function (paramName) {
    delete  this.paramsMap[paramName];
};

$(function () {
    $("header .menu").click(function () {
        $("header .menu.active").removeClass("active");
        $(this).addClass("active");
    });

    $.getScript('//apis.google.com/js/api:client.js', function () {
        if (typeof gapi !== 'undefined') {
            gapi.load('auth2', function () {
                // Retrieve the singleton for the GoogleAuth library and set up the client.
                auth2 = gapi.auth2.init({
                    client_id: ggid,
                    cookiepolicy: 'single_host_origin',
                    ux_mode: 'popup'
                            // Request scopes in addition to 'profile' and 'email'
                            //scope: 'additional_scope'
                });

            });
        }
    });

    $.getScript('//connect.facebook.net/en_US/sdk.js', function () {
        if (typeof FB !== 'undefined') {
            FB.init({
                appId: fbid,
                version: 'v2.7'
            });
        }
    });

    $(document).on("click", ".yt-clock-input > input", function () {
        $(".yt-clock-input.active").removeClass("active");

        var picker = $(this).parent(".yt-clock-input");
        picker.addClass("active");
    });

    // CLOSE ALL
    $(document).on('click', function (event) {
        // close calendar input
        if (!$(event.target).is(".calendar-input .yt-calendar") &&
                !$(event.target).closest(".calendar-input .yt-calendar").length && !$(event.target).is(".calendar-input input")) {
            $(".calendar-input.active").removeClass("active");
        }

        // close clock input
        if (!$(event.target).is(".yt-clock-input .clock") &&
                !$(event.target).closest(".yt-clock-input .clock").length && !$(event.target).is(".yt-clock-input > input")) {
            $(".yt-clock-input.active").removeClass("active");
        }

        // close header popup
        if (!$(event.target).is("header .menu") &&
                !$(event.target).closest("header .menu").length) {
            $("header .menu").removeClass("active");
        }
    });
    // END CLOSE ALL
});

var app = angular.module('youtripper', [])
        .config(['$qProvider', function ($qProvider) {
                $qProvider.errorOnUnhandledRejections(false);
            }])
        .config(['$httpProvider', function ($httpProvider) {
                $httpProvider.interceptors.push(function ($rootScope) {
                    return {
                        responseError: function (rejection) {
                            if (rejection.status === 404) {
                                $rootScope.$broadcast('system-nf');
                            } else if (rejection.status >= 400) {
                                $rootScope.$broadcast('system-error');
                            }
                            return rejection;
                        }
                    };
                });
            }
        ])
        .config(function ($provide) {
            $provide.decorator("$exceptionHandler", ['$delegate', '$injector', function ($delegate, $injector, $scope) {
                    return function (exception, cause) {
                        //get browser
                        function get_browser() {
                            var ua = navigator.userAgent, tem, M = ua.match(/(opera|chrome|safari|firefox|msie|trident(?=\/))\/?\s*(\d+)/i) || [];
                            var isPopularBrowser = false;
                            if (ua.indexOf("Chrome") !== -1 || ua.indexOf("Safari") !== -1
                                    || ua.indexOf("Firefox") !== -1 || ua.indexOf("MSIE") !== -1
                                    || (!!document.documentMode === true) || ua.indexOf("Opera") !== -1
                                    || ua.indexOf("OPR") !== -1) {
                                isPopularBrowser = true;
                            }
                            //
                            if (isPopularBrowser) {
                                if (/trident/i.test(M[1])) {
                                    tem = /\brv[ :]+(\d+)/g.exec(ua) || [];
                                    return {name: 'IE', version: (tem[1] || '')};
                                }
                                var opera = (ua.match(/Opera|OPR\/(\d+)/));
                                if (opera !== 'undefined' && opera !== null) {
                                    return {name: opera[0].split("/")[0], version: opera[1]};
                                }
                                if (M[1] === 'Chrome') {
                                    tem = ua.match(/\bOPR|Edge\/(\d+)/);
                                    if (tem !== null) {
                                        return {name: tem[0].split("/")[0], version: tem[1]};
                                    }
                                }
                                M = M[2] ? [M[1], M[2]] : [navigator.appName, navigator.appVersion, '-?'];
                                if ((tem = ua.match(/version\/(\d+)/i)) !== null) {
                                    M.splice(1, 1, tem[1]);
                                }
                                return {
                                    name: M[0],
                                    version: M[1]
                                };
                            } else {
                                return {
                                    name: "Unknow",
                                    version: "Unknow"
                                };
                            }
                        }

                        // browser infor
                        var browser = get_browser();
                        var version = null;

                        //mobile version
                        var android = navigator.userAgent.match(/Android\s+([\d\._]+)/);
                        var ios = navigator.userAgent.match(/OS\s+([\d\._]+)/);
                        if (android !== null) {
                            version = android[1];
                        }
                        if (ios !== null) {
                            version = ios[1];
                        }

                        //page URL
                        var pageURL = $(location).attr('href');
                        //GA cut URL
                        var gclidPos = pageURL.indexOf("?gclid");
                        if (gclidPos !== -1) {
                            pageURL = pageURL.slice(0, gclidPos);
                        }
                        var uqPos = pageURL.indexOf("?utm_");
                        if (uqPos !== -1) {
                            pageURL = pageURL.slice(0, uqPos);
                        }
                        var ulPos = pageURL.indexOf("&utm_");
                        if (ulPos !== -1) {
                            pageURL = pageURL.slice(0, ulPos);
                        }

                        //save to DB
                        let $http = $injector.get("$http");

                        //page ID
                        var pageID = exception.pageID;
                        var data = {
                            "pageID": pageID,
                            "pageURL": pageURL,
                            "browserName": browser.name,
                            "browserVersion": browser.version,
                            "mobileVersion": version
                        };
                        $http.post("/log-javascript-exception", data)
                                .then(function successCallback(response) {
                                    // get result
                                    $delegate(exception, cause);
                                    $scope.youtripper.funcs.directToErrorPage();
                                });
                    };
                }]);
        })
        .directive("ytmaxlength", function () {
            return {
                require: "ngModel",
                link: function postLink(scope, elem, attrs, ngModel) {
                    var maxlength = parseInt(attrs["ytmaxlength"]);

                    elem.bind('input', function () {
                        var text = $(this).val();
                        if (angular.isString(text)) {
                            var txtLength = text.length;
                            if (txtLength > maxlength) {
                                text = text.substring(0, maxlength);
                                $(this).val(text);
                                ngModel.$setViewValue(text);
                            }
                        }
                    });
                }
            };
        }).directive('convertToNumber', function () {
    return {
        require: 'ngModel',
        link: function (scope, element, attrs, ngModel) {
            ngModel.$parsers.push(function (val) {
                return parseInt(val, 10);
            });
            ngModel.$formatters.push(function (val) {
                return '' + val;
            });
        }
    };
}).directive("numberStrInput", function () {
    return {
        scope: {
            // Reference the outer scope
            fn: "&numberStrInput"
        },
        require: "ngModel",
        link: function postLink(scope, elem, attrs, ngModel) {
            elem.on('paste', function (e) {
                e.preventDefault();
            });

            elem.on('keydown', function (e) {
                if (!e.shiftKey) {
                    var keyCode = e.which;
                    if (keyCode !== 9 && keyCode !== 8 && keyCode !== 46 && !(keyCode >= 37 && keyCode <= 40)) {
                        if (!(keyCode >= 48 && keyCode <= 57) && !(keyCode >= 96 && keyCode <= 105)) {
                            e.preventDefault();
                        }
                    }
                } else {
                    e.preventDefault();
                }
            });
        }
    };
}).directive("imageFile", function () {
    return {
        require: "ngModel",
        link: function postLink(scope, elem, attrs, ngModel) {
            elem.on("change", function (e) {
                var file = elem[0].files[0];
                if (file) {
                    var type = file.type;

                    if (type === "image/png" || type === "image/jpeg"
                            || type === "image/jpg") {
                        var valid = true;

                        if (valid) {
                            ngModel.$setViewValue(file);
                        }
                    }
                }

                $(this).wrap('<form>').closest('form').get(0).reset();
                $(this).unwrap();
            });
        }
    };
}).directive("imagePdfFile", function () {
    return {
        require: "ngModel",
        link: function postLink(scope, elem, attrs, ngModel) {
            elem.on("change", function (e) {
                var file = elem[0].files[0];
                if (file) {
                    var type = file.type;

                    if (type === "image/png" || type === "image/jpeg"
                            || type === "image/jpg" || type === "application/pdf") {
                        var valid = true;

                        if (valid) {
                            ngModel.$setViewValue(file);
                        }
                    }
                }

                $(this).wrap('<form>').closest('form').get(0).reset();
                $(this).unwrap();
            });
        }
    };
});


app.controller('YoutripperController', function ($scope, $http, $document, $timeout, $q) {

    // COMMON POPUP
    $scope.POPUP = {
        "openPopup": null,
        "openPopupElement": null,
        "closePopup": null,
        "closePopupElement": null
    };

    $scope.POPUP.openPopup = function (popup) {
        $('body').css("overflow", "hidden");
        popup.active = true;
    };

    $scope.POPUP.openPopupElement = function (id) {
        id = "#" + id;
        $('body').css("overflow", "hidden");
        $(id).addClass("active");
    };

    $scope.POPUP.closePopupElement = function (id) {
        id = "#" + id;
        var activePopup = $(".yt-popup.active");
        if (activePopup !== null && activePopup.length === 1) {
            $('body').css("overflow", "auto");
        }
        $(id).removeClass("active");
        return false;
    };

    $scope.POPUP.closePopup = function (popup, event) {
        if (event !== undefined) {
            if ($(event.target).is(".center-content") || $(event.target).is(".header > .close-btn")
                    || $(event.target).is(".body > .close-btn")) {
                var activePopup = $(".yt-popup.active");
                if (activePopup !== null && activePopup.length === 1) {
                    $('body').css("overflow", "auto");
                }
                popup.active = false;
                return true;
            }
        } else {
            var activePopup = $(".yt-popup.active");
            if (activePopup !== null && activePopup.length === 1) {
                $('body').css("overflow", "auto");
            }
            popup.active = false;
        }
        return false;
    };
    // END POPUP

    // CALENDAR
    var DAYS = ["SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"];
    var MONTHS = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];

    $scope.CALENDAR = {
        "funcs": {
            "cMiToDDMMyyyy": null,
            "gDiffD": null,
            "getDaOfW": null,
            "cDaToHHmm": null,
            "newYDate": null,
            "newYTime": null,
            "reYTime": null,
            "getCa": null,
            "nxtM": null,
            "prvM": null,
            "clC": null
        }
    };

    $scope.CALENDAR.funcs.cMiToDDMMyyyy = function (milli) {
        if (milli > 0) {
            var dateObj = new Date(milli);
            var date = dateObj.getDate();
            var month = dateObj.getMonth() + 1;
            var year = dateObj.getFullYear();


            return ((date < 10) ? "0" + date : "" + date) + "/" +
                    ((month < 10) ? "0" + month : "" + month) + "/" + year;
        }
        return null;
    };

    $scope.CALENDAR.funcs.cDaToHHmm = function (date) {
        if (angular.isNumber(date) && date > 0) {
            var d = new Date(date);
            var hour = d.getHours();
            var minute = d.getMinutes();
            return (hour > 9 ? hour : "0" + hour) + " : " + (minute > 9 ? minute : "0" + minute);

        }
        return null;
    };

    $scope.CALENDAR.funcs.gDiffD = function (date1, date2) {
        var dt1 = new Date(date1);
        var dt2 = new Date(date2);
        return Math.floor((Date.UTC(dt2.getFullYear(), dt2.getMonth(),
                dt2.getDate()) - Date.UTC(dt1.getFullYear(), dt1.getMonth(), dt1.getDate())) / (1000 * 60 * 60 * 24));
    };

    $scope.CALENDAR.funcs.getDaOfW = function (date) {
        date = new Date(date);
        var day = date.getDay();
        if (day === 0) {
            day = 7;
        }
        day--;
        return day;
    };

    $scope.CALENDAR.funcs.newYDate = function (milli) {
        var crT = new Date();
        var oT = (crT.getTimezoneOffset() + 420) * 60 * 1000;
        if (angular.isNumber(milli)) {
            return new Date(milli + oT);
        } else if (angular.isObject(milli)) {
            return new Date(milli.getTime() + oT);
        } else {
            return new Date(crT.getTime() + oT);
        }
    };

    $scope.CALENDAR.funcs.newYTime = function (milli) {
        var crT = new Date();
        var oT = (crT.getTimezoneOffset() + 420) * 60 * 1000;
        if (angular.isNumber(milli)) {
            return milli + oT;
        } else if (angular.isObject(milli)) {
            return milli.getTime() + oT;
        } else {
            return crT.getTime() + oT;
        }
    };

    $scope.CALENDAR.funcs.reYTime = function (milli) {
        var crT = new Date();
        var oT = (crT.getTimezoneOffset() + 420) * 60 * 1000;
        if (angular.isNumber(milli)) {
            return milli - oT;
        } else if (angular.isObject(milli)) {
            return milli.getTime() - oT;
        }
        return null;
    };

    $scope.CALENDAR.funcs.getCa = function ($event, cuD, mDate, sDate, lDate, rDate) {
        $scope.CALENDAR.funcs.clC();

        var cdO = new Date(cuD);
        cdO.setHours(0);
        cdO.setMinutes(0);
        cdO.setSeconds(0);
        cdO.setMilliseconds(0);
        var cdM = cdO.getTime();

        var sdM = cdM;
        if (angular.isNumber(sDate)) {
            let mdO = new Date(sDate);
            cdO.setHours(0);
            cdO.setMinutes(0);
            cdO.setSeconds(0);
            cdO.setMilliseconds(0);

            sDate = mdO.getTime();

            sdM = sDate;
        } else if (angular.isNumber(mDate)) {
            let mdO = new Date(mDate);
            cdO.setHours(0);
            cdO.setMinutes(0);
            cdO.setSeconds(0);
            cdO.setMilliseconds(0);

            mDate = mdO.getTime();

            sdM = mDate;
        }
        var sdO = new Date(sdM);
        sdO.setDate(1);
        sdM = sdO.getTime();

        var ldM = null, rdM = null;
        if (angular.isNumber(lDate)) {
            var ldO = new Date(lDate);
            ldO.setHours(0);
            ldO.setMinutes(0);
            ldO.setSeconds(0);
            ldO.setMilliseconds(0);

            ldM = ldO.getTime();
        }
        if (angular.isNumber(rDate)) {
            var ldO = new Date(rDate);
            ldO.setHours(0);
            ldO.setMinutes(0);
            ldO.setSeconds(0);
            ldO.setMilliseconds(0);

            rdM = ldO.getTime();
        }

        var elem = angular.element($event.currentTarget);
        var picker = elem.parent(".calendar-input");
        // find picker to add class
        picker.addClass("active");

        return  bCal(cdM, mDate, sdM, ldM, rdM);
    };

    $scope.CALENDAR.funcs.prvM = function (caO) {
        if (caO.lav) {
            var cdM = caO.cdM, mdM = caO.mdM, sdM = caO.sdM, ldM = caO.ldM, rdM = caO.rdM;
            var sdO = new Date(sdM);
            sdO.setMonth(sdO.getMonth() - 1);
            sdM = sdO.getTime();

            return  bCal(cdM, mdM, sdM, ldM, rdM);
        }
        return caO;
    };

    $scope.CALENDAR.funcs.nxtM = function (caO) {
        if (caO.rav) {
            var cdM = caO.cdM, mdM = caO.mdM, sdM = caO.sdM, ldM = caO.ldM, rdM = caO.rdM;
            var sdO = new Date(sdM);
            sdO.setMonth(sdO.getMonth() + 1);
            sdM = sdO.getTime();
            return  bCal(cdM, mdM, sdM, ldM, rdM);
        }
        return caO;
    };


    function bCal(cdM, mdM, sdM, ldM, rdM) {
        var sDate = new Date(sdM);
        // header
        var mo = sDate.getMonth();
        var moT = MONTHS[mo];
        var ye = sDate.getFullYear();
        var yeT = ye;
        if ($scope.youtripper.vars.data.currentLan === $scope.youtripper.vars.LANGS.th.code) {
            yeT += 543;
        }

        // body
        var dates = [];
        var dow = $scope.CALENDAR.funcs.getDaOfW(sdM);
        var aDate = new Date(sDate);

        aDate.setDate(aDate.getDate() - dow);
        let to = 0;
        for (let i = 0; i < dow; i++) {
            let aD = {
                "ty": "out",
                "txt": aDate.getDate(),
                "mi": aDate.getTime()
            };

            dates.push(aD);
            aDate.setDate(aDate.getDate() + 1);
            to++;
        }

        var lav = true, rav = true;
        do {
            // check today or not
            let aMilli = aDate.getTime();
            let type = "no";
            if (ldM !== null && aMilli < ldM) {
                type = "in";
                lav = false;
            }
            if (rdM !== null && aMilli > rdM) {
                type = "in";
                rav = false;
            }

            if (type === "no") {
                if (aMilli === mdM) {
                    type = "mo";
                } else if (aMilli === cdM) {
                    type = "to";
                }
            }

            let aD = {
                "ty": type,
                "txt": aDate.getDate() + "",
                "mi": aDate.getTime()
            };

            dates.push(aD);
            aDate.setDate(aDate.getDate() + 1);
            var aMo = aDate.getMonth();
            to++;
        } while (aMo === mo);

        for (; to < 42; to++) {
            let aD = {
                "ty": "out",
                "txt": aDate.getDate(),
                "mi": aDate.getTime()
            };

            dates.push(aD);
            aDate.setDate(aDate.getDate() + 1);
        }

        var rO = {
            "cdM": cdM,
            "mdM": mdM,
            "sdM": sdM,
            "ldM": ldM,
            "rdM": rdM,
            "dates": dates,
            "lav": lav,
            "rav": rav,
            "mo": mo,
            "moT": moT,
            "ye": ye,
            "yeT": yeT
        };

        return rO;
    }

    $scope.CALENDAR.funcs.clC = function () {
        $(".calendar-input.active").removeClass("active");
    };
    // END CALENDAR

    // YT CLOCK
    $scope.CLOCK = {
        "funcs": {
            "convertToHHmm": null,
            "getTime": null,
            "getHM": null
        }
    };

    $scope.CLOCK.funcs.HHmm = function (total) {
        if (angular.isNumber(total) && total >= 0) {
            var hours = Math.floor(total / 60);
            var minutes = total - hours * 60;
            return (hours < 10 ? "0" + hours : "" + hours) + ":" + (minutes < 10 ? "0" + minutes : "" + minutes);
        }
        return null;
    };

    $scope.CLOCK.funcs.gM = function (h, m) {
        if (h >= 0 && m >= 0) {
            return h * 60 + m;
        }
        return null;
    };

    $scope.CLOCK.funcs.getHM = function (min) {
        let h = -1;
        let m = -1;
        if (angular.isNumber(min)) {
            h = Math.floor(min / 60);
            m = min - (h * 60);
        }
        return [h, m];
    };
    // END YT CLOCK

    // HEADER
    var signuprg = {"numericAlphabet": /^[a-zA-Z0-9\s]*$/, "numeric": /^[0-9]*$/,
        "alphabet": /^[a-zA-Z\s]*$/, "email": /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
        "password": /^[a-zA-Z0-9#?!@$%^&*-]*$/, "numericAlphabetSlash": /^[\/a-zA-Z0-9,.-\\-\s]*$/};

    $scope.ytheader = {
        "vars": {
            "me": true
        }
    };

    $scope.tripperSignup = {
        "vars": {
            "popup": {
                "active": false,
                "email": {
                    "loading": false,
                    "val": null,
                    "cVal": null,
                    "e": false,
                    "re": false,
                    "used": false,
                    "valid": false,
                    "confirmation": false,
                    "sc": false
                },
                "password": {
                    "val": null,
                    "cVal": null,
                    "e": false,
                    "re": false,
                    "leValid": false,
                    "foValid": false,
                    "confirmation": false,
                    "sc": false
                }
            },
            "loading": false
        },
        "funcs": {
            "openPopup": null,
            "signup": null,
            "emailChecking": null,
            "ech": null,
            "rech": null,
            "pch": null,
            "rpch": null
        }
    };

    $scope.logining = {
        "vars": {
            "rl": null,
            "popup": {
                "active": false,
                "loading": false,
                "data": {
                    "email": null,
                    "password": null,
                    "remember": false
                },
                "error": false
            }
        },
        "funcs": {
            "openPopup": null,
            "login": null,
            "sc": null
        }
    };

    $scope.ytSocial = {
        "vars": {
            "error": {
                "exists": false,
                "nonFBEmail": false
            }
        },
        "funcs": {
            "ggLogin": null,
            "fbLogin": null,
            "lpLogin": null
        }
    };

    $scope.tripperSignup.funcs.openPopup = function () {
        $scope.POPUP.closePopup($scope.logining.vars.popup);

        var error = $scope.ytSocial.vars.error;
        error.exists = false;
        error.nonFBEmail = false;

        var vars = $scope.tripperSignup.vars;
        var pop = vars.popup;
        var emS = pop.email;
        emS.val = null;
        emS.cVal = null;
        emS.e = false;
        emS.re = false;
        emS.valid = false;
        emS.confirmation = false;
        emS.used = false;
        emS.sc = false;

        var pwS = pop.password;
        pwS.val = null;
        pwS.cVal = null;
        pwS.e = false;
        pwS.re = false;
        pwS.leValid = false;
        pwS.foValid = false;
        pwS.confirmation = false;
        pwS.sc = false;

        $scope.POPUP.openPopup(pop);
    };

    $scope.tripperSignup.funcs.signup = function () {
        var vars = $scope.tripperSignup.vars;
        var popup = vars.popup;
        var emailS = popup.email;
        var pwS = popup.password;
        if (!vars.loading && !emailS.loading) {
            vars.loading = true;

            let sc = true;

            emailS.e = false;
            if (!emailS.valid || emailS.used) {
                emailS.e = true;
                sc = false;
            }

            pwS.e = false;
            if (!pwS.leValid || !pwS.foValid) {
                pwS.e = true;
                sc = false;
            }

            emailS.re = false;
            if (!emailS.confirmation) {
                emailS.re = true;
                sc = false;
            }

            pwS.re = false;
            if (!pwS.confirmation) {
                pwS.re = true;
                sc = false;
            }

            if (sc) {
                var data = {"em": emailS.val, "pw": pwS.val};

                $http.post("/tripper-registration/registration", data)
                        .then(function successCallback(response) {
                            var responseData = response.data;
                            if (angular.isObject(responseData)) {
                                if (responseData.success) {
                                    $scope.logining.funcs.sc();
                                }
                            }
                        });
            } else {
                vars.loading = false;
            }

        }
    };

    var ecto = null;
    $scope.tripperSignup.funcs.emailChecking = function () {
        $timeout.cancel(ecto);
        ecto = $timeout(function () {
            var pop = $scope.tripperSignup.vars.popup;
            var emailInput = pop.email;
            emailInput.loading = true;
            emailInput.used = false;

            var data = {"email": emailInput.val};

            $http.post("/tripper-registration/email-valid", data)
                    .then(function successCallback(response) {
                        var responseData = response.data;
                        if (angular.isObject(responseData)) {
                            emailInput.used = !responseData.valid;
                            if (!emailInput.used) {
                                emailInput.sc = true;
                            }
                            emailInput.loading = false;
                        }
                    });
        }, 300);
    };

    $scope.tripperSignup.funcs.ech = function () {
        var vars = $scope.tripperSignup.vars;
        var pop = vars.popup;
        var emailS = pop.email;
        var em = emailS.val;
        var cem = emailS.cVal;
        emailS.e = false;
        emailS.valid = false;
        emailS.used = false;
        emailS.confirmation = (em === cem);
        emailS.sc = false;

        if (angular.isString(em) && em.length) {
            emailS.valid = signuprg.email.test(em);
        }

        if (emailS.valid) {
            $scope.tripperSignup.funcs.emailChecking();
        }
    };

    $scope.tripperSignup.funcs.rech = function () {
        var vars = $scope.tripperSignup.vars;
        var pop = vars.popup;
        var emailS = pop.email;
        var em = emailS.val;
        var cem = emailS.cVal;
        emailS.confirmation = (em === cem);
    };

    $scope.tripperSignup.funcs.pch = function () {
        var vars = $scope.tripperSignup.vars;
        var pop = vars.popup;
        var pwS = pop.password;
        var pw = pwS.val;
        var cpw = pwS.cVal;
        pwS.e = false;
        pwS.confirmation = (pw === cpw);
        pwS.leValid = angular.isString(pw) && pw.length >= 8;
        pwS.foValid = signuprg.password.test(pw);
        pwS.sc = pwS.leValid && pwS.foValid;
    };

    $scope.tripperSignup.funcs.rpch = function () {
        var vars = $scope.tripperSignup.vars;
        var pop = vars.popup;
        var pwS = pop.password;
        var pw = pwS.val;
        var cpw = pwS.cVal;
        pwS.confirmation = (pw === cpw);
    };

    $scope.logining.funcs.openPopup = function () {
        $scope.POPUP.closePopup($scope.tripperSignup.vars.popup);

        var error = $scope.ytSocial.vars.error;
        error.exists = false;
        error.nonFBEmail = false;

        var vars = $scope.logining.vars;
        var popup = vars.popup;
        popup.error = false;
        $scope.POPUP.openPopup(vars.popup);
    };

    $scope.logining.funcs.sc = function () {
        var vars = $scope.logining.vars;
        if (angular.isString(vars.rl)) {
            $(location).attr('href', $scope.youtripper.funcs.getLink(vars.rl));
        } else {
            location.reload();
        }
    };

    $scope.logining.funcs.login = function () {
        var vars = $scope.logining.vars;
        var popup = vars.popup;
        popup.loading = true;
        var data = popup.data;
        if (angular.isString(data.email) && angular.isString(data.password) &&
                data.email.length && data.password.length) {
            popup.error = false;

            $http.post("/login", data)
                    .then(function successCallback(response) {
                        var responseData = response.data;
                        if (angular.isObject(responseData)) {
                            var success = responseData.success;
                            if (success) {
                                $scope.logining.funcs.sc();
                            } else {
                                popup.error = true;
                                popup.loading = false;
                            }
                        }
                    });
        } else {
            popup.error = true;
            popup.loading = false;
        }
    };

    $scope.ytSocial.funcs.ggLogin = function () {
        if (typeof auth2 !== 'undefined') {
            $scope.logining.vars.popup.loading = true;
            $scope.tripperSignup.vars.loading = true;
            auth2.signIn({
                clientid: ggid,
                scope: 'profile email',
                cookiepolicy: 'single_host_origin',
                prompt: 'select_account',
                ux_mode: 'popup'
            }).then(function (authResponse) {

                $timeout(function () {
                    var token = auth2.currentUser.get().getAuthResponse().id_token;

                    var data = {'idToken': token};

                    // then check gg account
                    $http.post("/gg-login", data)
                            .then(function successCallback(response) {
                                var result = response.data;
                                var registered = false;
                                if (angular.isObject(result)) {
                                    var registered = result.registered;
                                    if (registered) {
                                        $scope.logining.funcs.sc();
                                    } else {
                                        var error = $scope.ytSocial.vars.error;
                                        error.exists = result.errorExists;
                                        error.nonFBEmail = result.errorNotValidFB;
                                    }
                                }

                                if (!registered) {
                                    $scope.logining.vars.popup.loading = false;
                                    $scope.tripperSignup.vars.loading = false;
                                }
                            });

                }, 1);
            }, function (errorCode) {
                $timeout(function () {
                    $scope.logining.vars.popup.loading = false;
                    $scope.tripperSignup.vars.loading = false;
                }, 1);
            });
        }
    };

    $scope.ytSocial.funcs.fbLogin = function () {
        if (typeof FB !== 'undefined') {
            $scope.logining.vars.popup.loading = true;
            $scope.tripperSignup.vars.loading = true;

            FB.login(function (response) {
                $timeout(function () {
                    if (response.status === 'connected') {
                        var accessToken = response.authResponse.accessToken;
                        var data = {'idToken': accessToken};

                        // then check gg account
                        $http.post("/fb-login", data)
                                .then(function successCallback(response) {
                                    var result = response.data;
                                    var registered = false;
                                    if (angular.isObject(result)) {
                                        var registered = result.registered;
                                        if (registered) {
                                            $scope.logining.funcs.sc();
                                        } else {
                                            var error = $scope.ytSocial.vars.error;
                                            error.exists = result.errorExists;
                                            error.nonFBEmail = result.errorNotValidFB;
                                        }
                                    }

                                    if (!registered) {
                                        $scope.logining.vars.popup.loading = false;
                                        $scope.tripperSignup.vars.loading = false;
                                    }
                                });
                    } else {
                        $scope.logining.vars.popup.loading = false;
                        $scope.tripperSignup.vars.loading = false;
                    }
                }, 1);
            }, {scope: 'email'});
        }
    };

    $scope.ytSocial.funcs.lpLogin = function () {
        $scope.logining.vars.popup.loading = true;
        $scope.tripperSignup.vars.loading = true;
        
        let rurl = $scope.logining.vars.rl;
        if (!angular.isString(rurl) || rurl.length === 0) {
            rurl = window.location.pathname + window.location.search;
        }
        
        let rqData = {"rurl": rurl};
        $http.post("/lp-login-rq", rqData)
                .then(function successCallback(response) {
                    var result = response.data;
                    if (angular.isObject(result)) {
                        $(location).attr('href', result.rs);
                    }
                });
    };
    // END HEADER

    // SEARCH
    $scope.search = {
        "vars": {
            "hkw": null,
            "hkwa": false,
            "hdkw": null,
            "hdkwa": false,
            "ctld": false,
            "catld": false,
            "subld": false,
            "kwld": false,
            "lts": [],
            "cats": [],
            "subs": [],
            "kws": []
        },
        "funcs": {
            "kwc": null
        }
    };

    var spm, lsgp = $q.defer(), catsgp = $q.defer(), scatsgp = $q.defer(), kwsgp = $q.defer();
    function loadlsg(kw) {
        $http.post("/lt-search", {"kw": kw}, {timeout: lsgp.promise})
                .then(function successCallback(response) {
                    var result = response.data;
                    if (angular.isObject(result)) {
                        $scope.search.vars.lts = result;
                        $scope.search.vars.ctld = false;
                    }
                });
    }
    function loadcatsg(kw) {
        $http.post("/cat-search", {"kw": kw}, {timeout: catsgp.promise})
                .then(function successCallback(response) {
                    var result = response.data;
                    if (angular.isObject(result)) {
                        $scope.search.vars.cats = result;
                        $scope.search.vars.catld = false;
                    }
                });
    }
    function loadsubsg(kw) {
        $http.post("/sub-search", {"kw": kw, "lc": $scope.youtripper.vars.data.currentLan}, {timeout: scatsgp.promise})
                .then(function successCallback(response) {
                    var result = response.data;
                    if (angular.isObject(result)) {
                        $scope.search.vars.subs = result;
                        $scope.search.vars.subld = false;
                    }
                });
    }
    function loadkwsg(kw) {
        $http.post("/keyword-search", {"kw": kw, "lc": $scope.youtripper.vars.data.currentLan}, {timeout: kwsgp.promise})
                .then(function successCallback(response) {
                    var result = response.data;
                    if (angular.isObject(result)) {
                        $scope.search.vars.kws = result;
                        $scope.search.vars.kwld = false;
                    }
                });
    }

    $scope.search.funcs.kwc = function (hd) {
        var vs = $scope.search.vars;
        vs.lts.length = 0;
        vs.cats.length = 0;
        vs.subs.length = 0;
        vs.kws.length = 0;

        let kw = (hd ? vs.hdkw : vs.hkw);
        if (kw !== null && kw.length > 1) {
            vs.ctld = true;
            vs.catld = true;
            vs.subld = true;
            vs.kwld = true;

            lsgp.resolve();
            catsgp.resolve();
            scatsgp.resolve();
            kwsgp.resolve();

            lsgp = $q.defer();
            catsgp = $q.defer();
            scatsgp = $q.defer();
            kwsgp = $q.defer();
            $timeout.cancel(spm);

            spm = $timeout(function () {
                loadlsg(kw);
                loadcatsg(kw);
                loadsubsg(kw);
                loadkwsg(kw);
            }, 300);
        } else {
            vs.ctld = false;
            vs.catld = false;
            vs.subld = false;
            vs.kwld = false;
        }
    };
    // END SEARCH

    // YOUTRIPPER 
    $scope.youtripper = {
        "vars": {
            "LANGS": {
                "en": {
                    "code": "en",
                    "name": "English",
                    "engName": "English"
                },
                "th": {
                    "code": "th",
                    "name": "ไทย",
                    "engName": "Thai"
                }
            },
            "CURS": {
                "THB": {
                    "name": "THB",
                    "sym": "฿",
                    "rounded": true
                },
                "CNY": {
                    "name": "CNY",
                    "sym": "¥",
                    "rounded": false
                },
                "EUR": {
                    "name": "EUR",
                    "sym": "€",
                    "rounded": false
                },
                "USD": {
                    "name": "USD",
                    "sym": "$",
                    "rounded": false
                }
            },
            "data": {
                "currentCur": $("html").attr("currency"),
                "currentLan": $("html").attr("lang"),
                "tempCur": "THB",
                "tempLan": null
            },
            "lastPage": location.pathname + location.search,
            "lanCurChanging": false
        },
        "funcs": {
            "getLink": null,
            "changeLanCur": null,
            "initLanCur": null,
            "directToErrorPage": null,
            "notf": null
        }
    };

    $scope.youtripper.funcs.getLink = function (pURL) {
        var vars = $scope.youtripper.vars;
        var currentCur = vars.data.currentLan;

        var currentCode = vars.LANGS[currentCur].code;
        var currentPath = "";
        if (currentCode !== "en") {
            currentPath = "/" + currentCode;
        }

        return currentPath + pURL;
    };

    $scope.youtripper.funcs.directToErrorPage = function () {
        $(location).attr('href', $scope.youtripper.funcs.getLink('/error'));
    };

    $scope.youtripper.funcs.notf = function () {
        $(location).attr('href', $scope.youtripper.funcs.getLink('/page-not-found'));
    };

    $scope.$on('system-error', function () {
        $scope.youtripper.funcs.directToErrorPage();
    });

    $scope.$on('system-nf', function () {
        $scope.youtripper.funcs.notf();
    });

    $scope.youtripper.funcs.initLanCur = function () {
        var data = $scope.youtripper.vars.data;
        var currentLan = data.currentLan;
        var currentCur = data.currentCur;
        if (angular.isString(currentLan) && angular.isString(currentCur) &&
                currentLan.length && currentCur.length) {
            data.tempLan = currentLan;
            data.tempCur = currentCur;
        } else {
            $scope.youtripper.funcs.directToErrorPage();
        }
    };

    $scope.youtripper.funcs.changeLanCur = function () {
        var vars = $scope.youtripper.vars;
        vars.lanCurChanging = true;

        var data = vars.data;
        $http.post("/currency-setting", {"currencyCode": data.tempCur, "localeCode": data.tempLan})
                .then(function successCallback(response) {
                    var result = response.data;
                    if (angular.isObject(result)) {
//                        data.currentCur = data.tempCur;
                        data.currentLan = data.tempLan;

                        var lastPage = location.pathname + location.search;

                        var LANGS = vars.LANGS;
                        var existPath = "";
                        angular.forEach(LANGS, function (aLan, aCode) {
                            var aPath = "/" + aCode + "/";
                            if (lastPage.startsWith(aPath)) {
                                existPath = aPath;
                            }
                        });

                        if (existPath.length) {
                            lastPage = lastPage.replace(existPath, "/");
                        }

                        $(location).attr('href', $scope.youtripper.funcs.getLink(lastPage));
                    }
                });
    };

    $document.ready(function () {
        var youtripper = $scope.youtripper;
        youtripper.funcs.initLanCur();
        $scope.$apply();
    });
    // END YOUTRIPPER
});

app.controller('CalendarInputController', function ($scope) {
    $scope.calendar = {"MONTHS": [
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "July", "Aug", "Sep", "Oct", "Nov", "Dec"
        ],
        "TOTAL_CALENDAR_DAYS": 42, "MAX_DAYS_PER_MONTH": 31, "currentMonth": 1,
        "currentYear": 2016,
        "selectedDate": {}, "currentDate": 0,
        "modelDate": 0,
        "limitLeftDate": 0,
        "limitRightDate": 0,
        "dates": [
            {
                "outside": false, "today": false, "able": true,
                "value": 1,
                "milli": 0
            }
        ],
        "active": false
    };
    // this function is to oen calendar when user click on input
    $scope.openCalendarPicker = function ($event, currentDate, modelDate, limitLeftDate, limitRightDate) {
        // check currentDate with outTime
        var currentDateObj = new Date(currentDate);
        currentDateObj.setHours(0);
        currentDateObj.setMinutes(0);
        currentDateObj.setSeconds(0);
        currentDateObj.setMilliseconds(0);
        currentDate = currentDateObj.getTime();
        // close all current picker
        $(".calendar-input").removeClass("active");
        // build calendar dates basing on current date or modelDate
        // if modelDate is null then base on current date
        var selectedDateInMilli = modelDate;
        if (!angular.isNumber(selectedDateInMilli) || selectedDateInMilli < 1) {
            selectedDateInMilli = currentDate;
        }

        var selectedDate = new Date(selectedDateInMilli);
        // move to the first date of month
        selectedDate.setDate(1);
        // then define needed data to build
        $scope.calendar.selectedDate = new Date(selectedDate);
        $scope.calendar.currentDate = currentDate;
        $scope.calendar.modelDate = modelDate;
        if (!angular.isNumber(limitLeftDate) || limitLeftDate < 1) {
            $scope.calendar.limitLeftDate = 0;
        } else {
            var limitLeftDateObj = new Date(limitLeftDate);
            limitLeftDateObj.setHours(0);
            limitLeftDateObj.setMinutes(0);
            limitLeftDateObj.setSeconds(0);
            limitLeftDateObj.setMilliseconds(0);
            $scope.calendar.limitLeftDate = limitLeftDateObj.getTime();
        }

        if (!angular.isNumber(limitRightDate) || limitRightDate < 1) {
            $scope.calendar.limitRightDate = 0;
        } else {
            $scope.calendar.limitRightDate = limitRightDate;
        }

        // then build
        buildCalendar();
        // then open current
        var elem = angular.element($event.currentTarget);
        var picker = elem.parent(".calendar-input");
        // find picker to add class
        picker.addClass("active");
    };
    // this function to build calendar data
    function buildCalendar() {
        // get data to build
        var selectedDate = new Date($scope.calendar.selectedDate);
        var currentDate = $scope.calendar.currentDate;
        var modelDate = $scope.calendar.modelDate;
        // build header of calendar
        $scope.calendar.currentMonth = selectedDate.getMonth();
        $scope.calendar.currentYear = selectedDate.getFullYear();
        // then build dates
        $scope.calendar.dates.length = 0;
        // detect outside gaps before this month
        var dayOfFirstDate = selectedDate.getDay();
        // convert sunday to be last day of week
        if (dayOfFirstDate === 0) {
            dayOfFirstDate = 6;
        } else {             // and monday to be first day of week
            dayOfFirstDate--;
        }

        // build before gaps
        var beforeGaps = dayOfFirstDate;
        selectedDate.setDate(selectedDate.getDate() - beforeGaps);
        for (var i = 0; i < beforeGaps; i++) {
            var date = {"outside": true,
                "today": false, "able": false, "value": selectedDate.getDate(),
                "milli": selectedDate.getTime()
            };
            // add to the list of dates
            $scope.calendar.dates.push(date);
            // move to next day
            selectedDate.setDate(selectedDate.getDate() + 1);
        }
        var limitLeft = $scope.calendar.limitLeftDate;
        var limitRight = $scope.calendar.limitRightDate;
        //set selected date in week
        var dateObject = new Date(modelDate);
        var startDateOfWeek = 0;
        var endDateOfWeek = 0;
        if (dateObject.getDay() === 0) {//selected date is sunday
            endDateOfWeek = dateObject.getTime();
            dateObject.setDate(dateObject.getDate() - 6);
            startDateOfWeek = dateObject.getTime();
        } else if (dateObject.getDay() === 1) {//selected date is monday
            startDateOfWeek = dateObject.getTime();
            dateObject.setDate(dateObject.getDate() + 6);
            endDateOfWeek = dateObject.getTime();
        } else {//selected date is not sunday or monday
            var dateOfWeek = dateObject.getDay();
            dateObject.setDate(dateObject.getDate() - (dateOfWeek - 1));
            startDateOfWeek = dateObject.getTime();
            dateObject.setDate(dateObject.getDate() + 6);
            endDateOfWeek = dateObject.getTime();
        }

        // build actual dates of month
        do {
            // check today or not
            var singleMilli = selectedDate.getTime();
            var isToDay = singleMilli === currentDate ? true : false;
            var isModelDate = singleMilli === modelDate ? true : false;             //check within a week or not
            var withinWeek = (singleMilli >= startDateOfWeek && singleMilli <= endDateOfWeek) ? true : false;             // if in rangethen able is true
            var able = true, outside = false;
            if ((limitLeft > 0 && singleMilli < limitLeft) ||
                    (limitRight > 0 && singleMilli > limitRight)) {
                able = false;
                outside = false;
            }

            var date = {"outside": outside,
                "today": isToDay,
                "modelDate": isModelDate,
                "able": able,
                "value": selectedDate.getDate(),
                "milli": singleMilli,
                "withinWeek": withinWeek
            };
            // add to the list of dates
            $scope.calendar.dates.push(date);
            //move to next day
            selectedDate.setDate(selectedDate.getDate() + 1);             // variable to detect end of this month
            var currentMonth = selectedDate.getMonth();
        } while (currentMonth === $scope.calendar.currentMonth);
        // build after gaps
//        selectedDate.setDate(selectedDate.getDate() - 1);
        var getFirstDayOfAfterGap = selectedDate.getDay();
        // convert sunday to be last day of week
        if (getFirstDayOfAfterGap === 0) {
            getFirstDayOfAfterGap = 6;
        } else {             // and monday to be first day of week
            getFirstDayOfAfterGap--;
        }

        if (getFirstDayOfAfterGap !== 0) {
            // if not the first day of next week
            var afterGaps = 6 - getFirstDayOfAfterGap;
            for (var i = 0; i <= afterGaps; i++) {
                var date = {"outside": true,
                    "today": false, "able": false, "value": selectedDate.getDate(),
                    "milli": selectedDate.getTime()
                };
                // add to the list of dates
                $scope.calendar.dates.push(date);
                //move to next day
                selectedDate.setDate(selectedDate.getDate() + 1);
            }
        }

        // loop to fill all days in a week
        var currentDay = $scope.calendar.dates.length;
        for (var i = currentDay, max = $scope.calendar.TOTAL_CALENDAR_DAYS; i < max; i++) {
            var date = {"outside": true,
                "today": false, "able": false, "value": selectedDate.getDate(),
                "milli": selectedDate.getTime()
            };
            // add to the list of dates
            $scope.calendar.dates.push(date);
            //move to next day
            selectedDate.setDate(selectedDate.getDate() + 1);
        }
    }

    //hover to select week
    $scope.selectWeek = function (selectedDate, outside) {
        if (outside === false) {
            //set selected date in week
            var dateObject = new Date(selectedDate);
            var startDateOfWeek = 0;
            var endDateOfWeek = 0;
            if (dateObject.getDay() === 0) {//selected date is sunday
                endDateOfWeek = dateObject.getTime();
                dateObject.setDate(dateObject.getDate() - 6);
                startDateOfWeek = dateObject.getTime();
            } else if (dateObject.getDay() === 1) {//selected date is monday
                startDateOfWeek = dateObject.getTime();
                dateObject.setDate(dateObject.getDate() + 6);
                endDateOfWeek = dateObject.getTime();
            } else {//selected date is not sunday or monday
                var dateOfWeek = dateObject.getDay();
                dateObject.setDate(dateObject.getDate() - (dateOfWeek - 1));
                startDateOfWeek = dateObject.getTime();
                dateObject.setDate(dateObject.getDate() + 6);
                endDateOfWeek = dateObject.getTime();
            }
            angular.forEach($scope.calendar.dates, function (child) {
                //check within a week or not
                var withinWeek = (child.milli >= startDateOfWeek && child.milli <= endDateOfWeek) ? true : false;
                child.withinWeek = withinWeek;
            });
        }
    };
    //reset hover to selected value when mouse leave
    $scope.resetToSelectedDate = function (outside) {

        if (outside === false) {
            //set selected date in week
            var dateObject = new Date($scope.calendar.modelDate);
            var startDateOfWeek = 0;
            var endDateOfWeek = 0;
            if (dateObject.getDay() === 0) {//selected date is sunday
                endDateOfWeek = dateObject.getTime();
                dateObject.setDate(dateObject.getDate() - 6);
                startDateOfWeek = dateObject.getTime();
            } else if (dateObject.getDay() === 1) {//selected date is monday
                startDateOfWeek = dateObject.getTime();
                dateObject.setDate(dateObject.getDate() + 6);
                endDateOfWeek = dateObject.getTime();
            } else {//selected date is not sunday or monday
                var dateOfWeek = dateObject.getDay();
                dateObject.setDate(dateObject.getDate() - (dateOfWeek - 1));
                startDateOfWeek = dateObject.getTime();
                dateObject.setDate(dateObject.getDate() + 6);
                endDateOfWeek = dateObject.getTime();
            }
            angular.forEach($scope.calendar.dates, function (child) {
                //check within a week or not
                var withinWeek = (child.milli >= startDateOfWeek && child.milli <= endDateOfWeek) ? true : false;
                child.withinWeek = withinWeek;
            });
        }
    };
    $scope.previousMonth = function () {
        // move to prev month
        $scope.calendar.selectedDate.setMonth($scope.calendar.selectedDate.getMonth() - 1);         // then build data again
        buildCalendar();
    };
    $scope.nextMonth = function () {
        // move to next month
        $scope.calendar.selectedDate.setMonth($scope.calendar.selectedDate.getMonth() + 1);         // then build data again
        buildCalendar();
    };
    $scope.previousYear = function () {
        // move to prev year
        $scope.calendar.selectedDate.setYear($scope.calendar.selectedDate.getYear() - 1 + 1900);         // then build data again
        buildCalendar();
    };
    $scope.nextYear = function () {
        // move to next year
        $scope.calendar.selectedDate.setYear($scope.calendar.selectedDate.getYear() + 1 + 1900);         // then build data again
        buildCalendar();
    };
});

app.controller('PageController', function ($scope, $interval) {
    $scope.scs = 5;
    var ct = new Date().getTime();
    var it = $interval(function () {
        let ds = Math.floor((new Date().getTime() - ct) / 1000);
        let lf = 5 - ds;
        $scope.scs = lf;
        if (lf <= 0) {
            $interval.cancel(it);
            $(location).attr('href', $scope.youtripper.funcs.getLink("/"));
        }
    }, 1000);
});