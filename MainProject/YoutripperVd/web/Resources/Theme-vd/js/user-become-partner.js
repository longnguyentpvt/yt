/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(function () {
    $("#become-partner .cover .info input").on("click", function () {
        let pos = $("#registration-form").offset().top - $("#yt-header").height();
        $('html, body').animate({
            scrollTop: pos
        }, 500);
    });
});

app.controller('BecomePartner', function ($scope, $q, $http, $timeout, $document) {
    // REGISTRATION FORM
    $scope.registration = {
        "vars": {
            "formData": null,
            "regex": {"numericAlphabet": /^[a-zA-Z0-9\s]*$/,
                "numeric": /^[0-9]*$/,
                "postcode": /^[a-zA-Z0-9\-\s]*$/,
                "alphabet": /^[a-zA-Z\s]*$/,
                "email": /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
                "password": /^[a-zA-Z0-9#?!@$%^&*-]*$/,
                "numericAlphabetSlash": /^[\/a-zA-Z0-9,.-\\-\s]*$/},
            "partner": {
            },
            "email": {
                "loading": false,
                "exists": false,
                "confirm": false
            },
            "password": {
                "confirm": false
            },
            "captcha": {
                "success": false
            },
            "term": false,
            "error": false,
            "submit": false
        },
        "funcs": {
            "validate": null,
            "emailCheck": null,
            "emailConfirm": null,
            "passwordConfirm": null,
            "countryChange": null
        }
    };

    function loadData() {
        var vars = $scope.registration.vars;
        $http.post("/become-partner/data", {}).then(function successCallback(response) {
            var responseData = response.data;
            if (angular.isObject(responseData)) {
                vars.formData = responseData;
                console.log(vars.partner);
                console.log(vars.formData);
            }
        });
    }

    var stateDefer = $q.defer();
    $scope.registration.funcs.countryChange = function () {
        var vars = $scope.registration.vars;
        var partner = vars.partner;
        vars.formData["states"] = null;
        partner.city = null;

        var country = partner.country;
        if (angular.isString(partner.country)) {
            stateDefer.resolve();
            stateDefer = $q.defer();

            $http.post("/become-partner/state-loading", {"countryID": country}, {timeout: stateDefer.promise})
                    .then(function successCallback(response) {
                        var responseData = response.data;
                        if (angular.isObject(responseData)) {
                            vars.formData["states"] = responseData;
                        }
                    });
        }
    };

    $scope.registration.funcs.emailConfirm = function () {
        var vars = $scope.registration.vars;
        var partner = vars.partner;
        var confirm = false;
        if ($scope["signupForm"].email.$valid) {
            confirm = (partner.email === partner.reEmail);
        }
        vars.email.confirm = confirm;
    };

    $scope.registration.funcs.passwordConfirm = function () {
        var vars = $scope.registration.vars;
        var partner = vars.partner;
        vars.password.confirm = (partner.password === partner.rePassword);
    };

    var emp = null;
    $scope.registration.funcs.emailCheck = function () {
        $timeout.cancel(emp);
        $scope.registration.funcs.emailConfirm();
        var check = $scope.registration.vars.email;
        check.loading = true;
        check.exists = false;
        if ($scope["signupForm"].email.$valid) {
            emp = $timeout(function () {
                var data = {"email": $scope.registration.vars.partner.email};
                $http.post("/become-partner/email-valid", data).then(function successCallback(response) {
                    var responseData = response.data;
                    if (angular.isObject(responseData)) {
                        check.exists = !responseData.valid;
                        check.loading = false;
                    }
                });
            }, 300);
        } else {
            check.loading = false;
        }
    };

    $scope.registration.funcs.validate = function ($event) {
        var vars = $scope.registration.vars;
        if (!vars.submit) {
            vars.submit = true;
            // validate
            if (!vars.email.loading && $scope["signupForm"].$valid && vars.email.confirm && !vars.email.exists &&
                    vars.password.confirm && vars.captcha.success && vars.term) {
                vars.error = false;
            } else {
                vars.error = true;
            }

            if (vars.error) {
                $event.preventDefault();
                vars.submit = false;
            }
        } else {
            $event.preventDefault();
        }
    };
    // END REGISTRATION FORMfun


    // CAPTCHA
    $scope.captcha = {
        "vars": {
            "oldX": 0,
            "slider": $("#registration-form .slider-captcha"),
            "dragger": $("#registration-form .slider-captcha .drag"),
            "left": 0,
            "active": false,
            "done": false
        },
        "funcs": {
            "start": null,
            "stop": null,
            "drag": null
        }
    };

    $(document).on("mousedown", "#registration-form .slider-captcha", function (event) {
        return false;
    });


    $scope.captcha.funcs.start = function ($event) {
        var vars = $scope.captcha.vars;
        if (!vars.done) {
            vars.oldX = $event.pageX;

            vars.active = true;
        }
    };

    $scope.captcha.funcs.stop = function () {
        var vars = $scope.captcha.vars;
        if (vars.active) {
            if (!vars.done) {
                vars.left = 0;
            }

            vars.active = false;
        }
    };

    $scope.captcha.funcs.drag = function ($event) {
        var vars = $scope.captcha.vars;
        if (vars.active) {
            var newX = $event.pageX;
            var left = newX - vars.oldX;
            if (left < 0) {
                left = 0;
            }
            vars.left = left;

            // check range
            var max = vars.slider.width() - vars.dragger.width();
            if (left >= max) {
                vars.done = true;
                $scope.registration.vars.captcha.success = true;
                $scope.captcha.funcs.stop();
            }
        }
    };
    // END CAPTCHA

    // discount popup
    $scope.firstD = {
        "sc": false,
        "popup": {
            "active": false,
            "email": null,
            "ld": false,
            "e": false,
            "ex": false
        },
        "subcribe": null,
        "close": null
    };

    var firstDA = false;
    if (typeof pct !== 'undefined' && pct) {
        firstDA = true;
    }

    $(function () {
        $(document).on("mouseleave", function ($event) {
            if (!firstDA) {
                var mY = $event.clientY;
                if (mY <= 0) {
                    firstDA = true;
                    $scope.POPUP.openPopup($scope.firstD.popup);
                    $scope.$apply();
                }
            }
        });
    });

    $scope.firstD.subcribe = function () {
        var pu = $scope.firstD.popup;
        pu.ld = true;
        var rg = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        var e = pu.email;

        if (e !== null && e.length && rg.test(e)) {
            pu.e = false;
            $http.post("/become-partner/contact-registration", {"email": e})
                    .then(function successCallback(response) {
                        // get result from server
                        var responseData = response.data;
                        if (angular.isObject(responseData)) {
                            if (responseData.sc) {
                                pu.ex = false;
                                pu.sc = true;
                            } else {
                                pu.ex = true;
                            }
                            pu.ld = false;
                        }
                    });
        } else {
            pu.e = true;
            pu.ld = false;
        }
    };

    $scope.firstD.close = function ($event) {
        $http.post("/become-partner/contact-close", {})
                .then(function successCallback(response) {
                    // get result from server
                    var responseData = response.data;
                    if (angular.isObject(responseData)) {
                    }
                });
        var pu = $scope.firstD.popup;
        firstDA = true;
        $scope.POPUP.closePopup(pu, $event);
    };

    $document.ready(function () {
        loadData();
        $scope.$apply();
    });
});