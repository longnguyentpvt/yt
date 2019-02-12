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

});

var app = angular.module('youtripper', [])
        .config(['$qProvider', function ($qProvider) {
                $qProvider.errorOnUnhandledRejections(false);
            }])
        .config(['$httpProvider', function ($httpProvider) {
                $httpProvider.interceptors.push(function () {
                    return {
                        responseError: function (rejection) {
                            if (rejection.status === 403) {
                                //show error dialog                     
                                $(location).attr('href', "/status/timeout");
                            } else if (rejection.status >= 400) {
                                $(location).attr('href', '/error');
                            }
                            return rejection;
                        }
                    };
                });
            }
        ]);

app.controller('YoutripperController', function ($scope, $http, $q, $document, $timeout) {
    /* COMMON */
    $scope.POPUP = {
        "funcs": {
            "pop": null,
            "upop": null
        }
    };

    function enablePing() {
        $("html").addClass("pop");
    }

    function disablePing() {
        // check
        var l = $(".yt-popup.pop").length + $("#yt-header .menu.op").length;
        if (l <= 1) {
            $("html.pop").removeClass("pop");
        }
    }

    $scope.POPUP.funcs.pop = function (pop) {
        pop.pop = true;
        enablePing();
    };

    $scope.POPUP.funcs.upop = function (pop) {
        disablePing();
        pop.pop = false;
    };
    /* END COMMON */

    /* HEADER */
    $scope.ytheader = {
        "vars": {
            "ming": false,
            "searching": false,
            "txts": null,
            "lpop": {
                "cl": {
                    "pop": false
                }
            },
            "spop": {
                "cl": {
                    "pop": false
                }
            },
            "tspop": {
                "cl": {
                    "pop": false
                }
            }
        },
        "funcs": {
            "fc": null,
            "ufc": null,
            "ct": null,
            "tgm": null,
            "ologin": null,
            "clogin": null,
            "osignup": null,
            "csignup": null,
            "ots": null,
            "cts": null
        }
    };

    $scope.ytheader.funcs.fc = function () {
        $scope.ytheader.vars.searching = true;
    };

    $scope.ytheader.funcs.ufc = function () {
        var vars = $scope.ytheader.vars;
        var txt = vars.txts;
        if (!angular.isString(txt) || txt.length === 0) {
            vars.searching = false;
        }
    };

    $scope.ytheader.funcs.ct = function () {
        var vars = $scope.ytheader.vars;
        vars.txts = null;
        vars.searching = false;
    };

    $scope.ytheader.funcs.tgm = function () {
        var vars = $scope.ytheader.vars;
        vars.ming = !vars.ming;
        if (vars.ming) {
            enablePing();
        } else {
            disablePing();
        }
    };

    $scope.ytheader.funcs.ologin = function () {
        var vars = $scope.ytheader.vars;
        $scope.POPUP.funcs.pop(vars.lpop.cl);
    };

    $scope.ytheader.funcs.clogin = function () {
        var vars = $scope.ytheader.vars;
        $scope.POPUP.funcs.upop(vars.lpop.cl);
    };

    $scope.ytheader.funcs.osignup = function () {
        var vars = $scope.ytheader.vars;
        $scope.POPUP.funcs.pop(vars.spop.cl);
    };

    $scope.ytheader.funcs.csignup = function () {
        var vars = $scope.ytheader.vars;
        $scope.POPUP.funcs.upop(vars.spop.cl);
    };

    $scope.ytheader.funcs.ots = function () {
        var vars = $scope.ytheader.vars;
        $scope.POPUP.funcs.pop(vars.tspop.cl);
    };

    $scope.ytheader.funcs.cts = function () {
        var vars = $scope.ytheader.vars;
        $scope.POPUP.funcs.upop(vars.tspop.cl);
    };
    /* END HEADER */
});