/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

app.controller('PartnerAccountProfile', function ($scope, $http, $exceptionHandler) {
    var pageID = "P-Profile";
    try {
        $scope.profile = {
            "vars": {
                "data": {
                    "job": {
                        "bo": responseData.positionBusinessOwner,
                        "gm": responseData.positionGeneralManager,
                        "go": responseData.positionGeneralOfficer,
                        "mm": responseData.positionMarketingManager,
                        "sm": responseData.positionSaleManager,
                        "o": responseData.positionOthers
                    },
                    "partnerType": {
                        "i": responseData.businessTypePersonal,
                        "c": responseData.businessTypeCompany
                    },
                    "countries": responseData.partnerCountries,
                    "phoneCodes": responseData.phoneCodes,
                    "cities": [],
                    "regex": {"numericAlphabet": /^[a-zA-Z0-9\s]*$/,
                        "numeric": /^[0-9]*$/,
                        "postcode": /^[a-zA-Z0-9\-\s]*$/,
                        "alphabet": /^[a-zA-Z\s]*$/,
                        "email": /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
                        "password": /^[a-zA-Z0-9#?!@$%^&*-]*$/,
                        "numericAlphabetSlash": /^[\/a-zA-Z0-9,.-\\-\s]*$/},
                },
                "psn": {
                    "eding": false,
                    "lding": false,
                    "fn": null,
                    "ln": null,
                    "pos": null,
                    "em": null,
                    "fnsc": true,
                    "lnsc": true,
                    "possc": true
                },
                "ai": {
                    "crpw": null,
                    "npw": null,
                    "cfpw": null,
                    "pwe": false,
                    "crpwsc": false,
                    "npwsc": false,
                    "cfpwsc": false,
                    "crpwnc": false,
                    "nper": false,
                    "cper": false
                },
                "bs": {
                    "eding": false,
                    "lding": false,
                    "comp": null,
                    "ty": null,
                    "con": null,
                    "cn": null,
                    "sta": null,
                    "sn": null,
                    "ct": null,
                    "addr": null,
                    "zip": null,
                    "pc": null,
                    "pn": null,
                    "ab": null,
                    "eCon": false,
                    "eSta": false,
                    "eCt": false,
                    "eAddr": false,
                    "eZip": false,
                    "ePc": false,
                    "ePn": false
                }
            },
            "funcs": {
                "loadpsn": null,
                "loadbs": null,
                "edpsn": null,
                "edai": null,
                "edbs": null,
                "svpsn": null,
                "svbs": null,
                "svai": null,
                "ccpsn": null,
                "ccbs": null,
                "cai": null,
                "countryChange": null,
                "cfn": null,
                "cln": null,
                "ccpw": null,
                "cnpw": null,
                "ccfpw": null,
                "csta": null,
                "caddr": null,
                "cct": null,
                "czip": null,
                "cpn": null,
            }
        };
        // change country
        $scope.profile.funcs.countryChange = function () {
            let bs = $scope.profile.vars.bs;
            loadst(bs.con);
        };
        // load state
        function loadst(countryID) {
            let sData = $scope.profile.vars.data;
            $http.post("/partner/account-profile/state-loading", {"countryID": countryID}).then(function successCallback(response) {
                var responseData = response.data;
                if (angular.isObject(responseData)) {
                    sData.cities = responseData;
                }
            });
        }

        // load personal information
        $scope.profile.funcs.loadpsn = function (ed) {
            let psn = $scope.profile.vars.psn;
            psn.fnsc = true;
            psn.lnsc = true;
            psn.loading = true;
            $http.post("/partner/account-profile/personal-loading", {}).then(function successCallback(response) {
                var responseData = response.data;
                if (angular.isObject(responseData)) {
                    psn.fn = responseData.firstName;
                    psn.ln = responseData.lastName;
                    psn.pos = responseData.position;
                    psn.em = responseData.email;
                    psn.eding = ed;
                    psn.loading = false;
                }
            });
        };
        // click edit personal
        $scope.profile.funcs.edpsn = function () {
            $scope.profile.funcs.loadpsn(true);
        };
        // click save personal
        $scope.profile.funcs.svpsn = function () {
            let psn = $scope.profile.vars.psn;
            // validate
            psn.fnsc = true;
            psn.lnsc = true;
            let valid = false;

            //ERRROR
            if (!angular.isString(psn.fn) || !psn.fn.length) {
                psn.fnsc = false;
            }
            if (!angular.isString(psn.ln) || !psn.ln.length) {
                psn.lnsc = false;
            }

            if (psn.fnsc && psn.lnsc) {
                valid = true;
            }

            //
            if (valid) {
                psn.loading = true;
                let rqd = {"firstName": psn.fn,
                    "lastName": psn.ln,
                    "position": psn.pos};
                //  "currentPassword": psn.crpw,
                //  "newPassword": psn.npw};
                $http.post("/partner/account-profile/personal-save", rqd).then(function successCallback(response) {
                    var responseData = response.data;
                    if (angular.isObject(responseData)) {
                        if (responseData.result === true) {
                            psn.eding = false;
                        }
                        psn.loading = false;
                    }
                });
            }
        };
        // click cancel personal
        $scope.profile.funcs.ccpsn = function () {
            $scope.profile.funcs.loadpsn(false);
        };
        // CHANGE FIRST NAME
        $scope.profile.funcs.cfn = function () {
            let psn = $scope.profile.vars.psn;
            if (angular.isString(psn.fn) && psn.fn.length) {
                psn.fnsc = true;
            } else {
                psn.fnsc = false;
            }
        };
        // CHANGE LAST NAME
        $scope.profile.funcs.cln = function () {
            let psn = $scope.profile.vars.psn;
            if (angular.isString(psn.ln) && psn.ln.length) {
                psn.lnsc = true;
            } else {
                psn.lnsc = false;
            }
        };


        //click edit account information
        $scope.profile.funcs.edai = function () {
            let ai = $scope.profile.vars.ai;
            ai.crpwsc = false;
            ai.cfpwsc = false;
            ai.npwsc = false;
            ai.nper = false;
            ai.cper = false;
            ai.pwe = true;
        };

        // save account information
        $scope.profile.funcs.svai = function () {
            let ai = $scope.profile.vars.ai;
            // validate
            let valid = false;
            var validCp = false;
            var validNp = false;
            var validCf = false;
            ai.crpwsc = false;
            ai.cfpwsc = false;
            ai.npwsc = false;
            ai.nper = false;
            ai.cper = false;
            //check validate
            //CURRENT PASSWORD
            if (angular.isString(ai.crpw) && ai.crpw.length) {
                if ($scope.profile.vars.data.regex.password.test(ai.crpw)) {
                    ai.crpwsc = false;
                    validCp = true;
                } else {
                    ai.cper = true;
                }
            } else {
                ai.crpwsc = true;
                validCp = false;
            }
            // NEW PASSWORD
            if (angular.isString(ai.npw) && ai.npw.length) {
                if ($scope.profile.vars.data.regex.password.test(ai.npw)) {
                    ai.npwsc = false;
                    // confirm password
                    if (ai.npw === ai.cfpw) {
                        ai.cfpwsc = false;
                        validCf = true;
                    } else {
                        ai.cfpwsc = true;
                        validCf = false;
                    }
                    //
                    validNp = true;
                } else {
                    ai.nper = true;
                }
            } else {
                ai.npwsc = true;
                validNp = false;
            }
            // CONFIRM PASSWORD

            // PASS VALIDATION
            if (validCf && validNp && validCp) {
                valid = true;
            }
            //SAVE
            if (valid) {
                ai.loading = true;
                let rqd = {"currentPassword": ai.crpw,
                    "newPassword": ai.npw,
                    "confirmPassword": ai.cfpw
                };
                $http.post("/partner/account-profile/ai-save", rqd).then(function successCallback(response) {
                    var responseData = response.data;
                    if (angular.isObject(responseData)) {
                        if (responseData.result === true) {
                            $scope.profile.funcs.cai();
                        } else if (responseData.result === null) {
                            ai.crpwnc = true;
                        }
                    }
                    ai.loading = false;
                });
            }
        };

        // click cancel account information
        $scope.profile.funcs.cai = function () {
            let ai = $scope.profile.vars.ai;
            ai.crpw = null;
            ai.npw = null;
            ai.cfpw = null;
            ai.pwe = false;
            ai.crpwsc = false;
            ai.cfpwsc = false;
            ai.npwsc = false;
            ai.nper = false;
            ai.cper = false;
        };
        // CHANGE CURRENT PASSWORD
        $scope.profile.funcs.ccpw = function () {
            let ai = $scope.profile.vars.ai;
            if (angular.isString(ai.crpw) && ai.crpw.length) {
                ai.crpwsc = false;
                ai.crpwnc = false;
                ai.cper = false;
            } else {
                ai.crpwsc = true;
            }
        }
        // CHANGE NEW PASSWORD
        $scope.profile.funcs.cnpw = function () {
            let ai = $scope.profile.vars.ai;
            if (angular.isString(ai.npw) && ai.npw.length) {
                ai.npwsc = false;
                ai.nper = false;
            } else {
                ai.npwsc = true;
            }
        }
        // CHANGE CONFIRM PASSWORD
        $scope.profile.funcs.ccfpw = function () {
            let ai = $scope.profile.vars.ai;
            if (angular.isString(ai.cfpw) && ai.cfpw.length) {
                ai.cfpwsc = false;
            } else {
                ai.cfpwsc = true;
            }
        }

        // load business information
        $scope.profile.funcs.loadbs = function (ed) {
            let bs = $scope.profile.vars.bs;
            bs.eCon = false;
            bs.eSta = false;
            bs.eCt = false;
            bs.eZip = false;
            bs.ePc = false;
            bs.ePn = false;
            bs.eAddr = false;
            $scope.profile.vars.data.cities.length = 0;
            bs.loading = true;
            $http.post("/partner/account-profile/business-loading", {}).then(function successCallback(response) {
                var responseData = response.data;
                if (angular.isObject(responseData)) {
                    bs.comp = responseData.businessName;
                    bs.ty = responseData.businessType;
                    bs.con = responseData.countryID;
                    bs.cn = responseData.countryName;
                    bs.sta = responseData.stateID;
                    bs.sn = responseData.cityName;
                    bs.addr = responseData.businessAddress;
                    bs.ct = responseData.businessCity;
                    bs.zip = responseData.postalCode;
                    bs.pc = responseData.phoneCode;
                    bs.pn = responseData.phoneNumber;
                    bs.ab = responseData.businessBackGround;
                    $scope.profile.vars.data.cities = responseData.cities;
                    bs.eding = ed;
                    $scope.profile.funcs.csta();
                    bs.loading = false;
                }
            });
        };

        // click edit business
        $scope.profile.funcs.edbs = function () {
            $scope.profile.funcs.loadbs(true);
        };

        // click cancel business
        $scope.profile.funcs.ccbs = function () {
            //  $scope.profile.funcs.countryChange();
            $scope.profile.funcs.loadbs(false);
        };

        //save business
        $scope.profile.funcs.svbs = function () {
            let bs = $scope.profile.vars.bs;
            // validate
            let valid = false;
            bs.eCon = false;
            bs.eSta = false;
            bs.eCt = false;
            bs.eZip = false;
            bs.ePc = false;
            bs.ePn = false;
            bs.eAddr = false;
            //ERROR
            if (!angular.isString(bs.con) || !bs.con.length) {
                bs.eCon = true;
            }

            if (!angular.isNumber(bs.sta)) {
                bs.eSta = true;
            }

            if (!angular.isString(bs.addr) || !bs.addr.length) {
                bs.eAddr = true;
            }

            if (!angular.isString(bs.ct) || !bs.ct.length) {
                bs.eCt = true;
            }

            if (!angular.isString(bs.zip) || !bs.zip.length) {
                bs.eZip = true;
            }

            if (!angular.isString(bs.pc) || !bs.pc.length) {
                bs.ePc = true;
            }

            if (!angular.isString(bs.pn) || !bs.pn.length) {
                bs.ePn = true;
            }

            //VALID WHEN
            if (!bs.eCon && !bs.eSta && !bs.eAddr && !bs.eCt && !bs.eZip && !bs.ePc && !bs.ePn) {
                valid = true;
            }
            //contine saving
            if (valid) {
                bs.loading = true;
                let rqd = {"countryID": bs.con, "stateID": bs.sta, "businessAddress": bs.addr,
                    "businessCity": bs.ct, "postalCode": bs.zip, "phoneCode": bs.pc, "phoneNumber": bs.pn
                    , "businessBackGround": bs.ab
                };
                $http.post("/partner/account-profile/bs-save", rqd).then(function successCallback(response) {
                    var responseData = response.data;
                    if (angular.isObject(responseData)) {
                        if (responseData.result === true) {
                            $scope.profile.funcs.loadbs(false);
                        }
                    }
                    bs.loading = false;
                });
            }
        };

        //CHANGE STATE
        $scope.profile.funcs.csta = function () {
            var bs = $scope.profile.vars.bs;
            if (angular.isNumber(bs.sta)) {
                bs.eSta = false;
            } else {
                bs.eSta = true;
            }
        };
        //CHANGE ADDR
        $scope.profile.funcs.caddr = function () {
            var bs = $scope.profile.vars.bs;
            if (angular.isString(bs.addr) && bs.addr.length) {
                bs.eAddr = false;
            } else {
                bs.eAddr = true;
            }
        };
        //CHANGE CITY
        $scope.profile.funcs.cct = function () {
            var bs = $scope.profile.vars.bs;
            if (angular.isString(bs.ct) && bs.ct.length) {
                bs.eCt = false;
            } else {
                bs.eCt = true;
            }
        };
        //CHANGE ZIP
        $scope.profile.funcs.czip = function () {
            var bs = $scope.profile.vars.bs;
            if (angular.isString(bs.zip) && bs.zip.length) {
                bs.eZip = false;
            } else {
                bs.eZip = true;
            }
        };
        //CHANGE PHONE NUMBER
        $scope.profile.funcs.cpn = function () {
            var bs = $scope.profile.vars.bs;
            if (angular.isString(bs.pn) && bs.pn.length) {
                bs.ePn = false;
            } else {
                bs.ePn = true;
            }
        };

        // function init
        function init() {
            $scope.profile.funcs.loadpsn(false);
            $scope.profile.funcs.loadbs(false);
        }

        init();
    } catch (e) {
        console.log(e.message);
        $exceptionHandler({"pageID": pageID}, "");
    }
});