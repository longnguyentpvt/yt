/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

app.controller('TripperAccountProfile', function ($scope, $http, $exceptionHandler) {
    var pageID = "T-Profile";
    try {
        $scope.profile = {
            "vars": {
                "data": {
                    "countries": pData.countries,
                    "phoneCodes": pData.phoneCodes,
                    "pls": {},
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
                    "tax": null,
                    "gd": null, //gender
                    "act": null,
                    "eFn": false,
                    "eLn": false,
                    "eDn": false,
                    "eGd": false,
                    "eCon": false,
                    "eSta": false,
                    "eAddr": false,
                    "eCt": false,
                    "eZip": false,
                    "ePc": false,
                    "ePn": false,
                    "eComp": false,
                    "eTax": false
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
                    "cper": false,
                    "nem": null,
                    "cfem": null,
                    "nemE": false,
                    "nemFE": false,
                    "cfemFE": false,
                    "cfemE": false,
                    "eE": false
                },

                "cdc": {
                    "lding": false,
                    "cards": null,
                    "dlting": false,
                    "dpop": {
                        "active": false
                    },
                    "cID": null
                },
                "billing": {
                    "lding": false,
                    "billings": null,
                    "bID": null,
                    "dlting": false,
                    "dpop": {
                        "active": false
                    },
                    "detail": {
                        "loading": false,
                        "dt": null,
                        "active": false
                    }

                },
                "op": {
                    "eding": false,
                    "lding": false,
                    "bg": null, /// BACK GROUND
                    "pl": null // PREFER LANGUAGE
                }
            },
            "funcs": {
                "rmcd": null, // remove credit cards
                "edpsn": null,
                "edai": null,
                "svpsn": null,
                "svai": null,
                "ccpsn": null,
                "cai": null,
                "countryChange": null,
                "odcd": null,
                "cdcd": null,
                "odb": null,
                "cdb": null,
                "rmb": null,
                "obd": null,
                "cop": null,
                "ccpw": null,
                "cnpw": null,
                "ccfpw": null,
                "cgd": null,
                "cSta": null,
                "caddr": null,
                "cct": null,
                "czip": null,
                "cpn": null,
                "ccomp": null,
                "ctax": null,
                "edemai": null,
                "svemai": null,
                "cemai": null,
                "ccfem": null,
                "cnem": null
            }
        };
        // change country
        $scope.profile.funcs.countryChange = function () {
            let psn = $scope.profile.vars.psn;
            if (angular.isString(psn.con) && psn.con.length) {
                psn.eCon = false;
                loadst(psn.con);
            } else {
                psn.eCon = true;
            }

        };
        // load state
        function loadst(countryID) {
            let sData = $scope.profile.vars.data;
            sData.cities.length = 0;
            $http.post("/tripper/account-profile/state-loading", {"countryID": countryID}).then(function successCallback(response) {
                var responseData = response.data;
                if (angular.isObject(responseData)) {
                    sData.cities = responseData;
                }
            });
        }

        // load credit cards
        function loadCre() {
            let cdc = $scope.profile.vars.cdc;
            cdc.lding = true;
            $http.post("/tripper/account-profile/get-credit", {}).then(function successCallback(response) {
                var responseData = response.data;
                if (angular.isObject(responseData)) {
                    cdc.cards = responseData;
                    for (let i = 0, max = cdc.cards.length; i < max; i++) {
                        let aC = cdc.cards[i];
                        let ed = $scope.CALENDAR.funcs.newYTime(aC.expirationDate);

                        aC.ed = $scope.CALENDAR.funcs.cMiToDDMMyyyy(ed);
                        aC.sn = "XXXX-XXXX-XXXX-" + aC.shownNumber;
                    }
                }
                cdc.lding = false;
            });
        }

        // OPEN POPUP REMOVE CREDIT CARD
        $scope.profile.funcs.odcd = function (cardID) {
            var cdc = $scope.profile.vars.cdc;
            cdc.cID = cardID;
            $scope.POPUP.openPopup(cdc.dpop);
        };

        //CLOSE DELETE CREDIT
        $scope.profile.funcs.cdcd = function () {
            var cdc = $scope.profile.vars.cdc;
            cdc.cID = null;
            $scope.POPUP.closePopup(cdc.dpop);
            loadCre();
        };

        // remove credit cards
        $scope.profile.funcs.rmcd = function () {
            let cdc = $scope.profile.vars.cdc;
            var rqdt = {"cardID": cdc.cID};
            cdc.dlting = true;
            $http.post("/tripper/account-profile/delete-credit", rqdt).then(function successCallback(response) {
                var responseData = response.data;
                if (angular.isObject(responseData)) {
                    $scope.profile.funcs.cdcd();
                }
                cdc.dlting = false;
            });
        };

        // load billing
        function loadBilling() {
            let billing = $scope.profile.vars.billing;
            $http.post("/tripper/account-profile/get-billing", {}).then(function successCallback(response) {
                var responseData = response.data;
                if (angular.isObject(responseData)) {
                    billing.billings = responseData;
                }
            });
        }

        // OPEN POPUP REMOVE BILLING
        $scope.profile.funcs.odb = function (cardID) {
            var billing = $scope.profile.vars.billing;
            billing.bID = cardID;
            $scope.POPUP.openPopup(billing.dpop);
        };

        //CLOSE REMOVE BILLING
        $scope.profile.funcs.cdb = function () {
            var billing = $scope.profile.vars.billing;
            billing.bID = null;
            $scope.POPUP.closePopup(billing.dpop);
            loadBilling();
        };

        // remove REMOVE BILLING
        $scope.profile.funcs.rmb = function () {
            let billing = $scope.profile.vars.billing;
            billing.dlting = true;
            var rqdt = {"billingID": billing.bID};
            $http.post("/tripper/account-profile/delete-billing", rqdt).then(function successCallback(response) {
                var responseData = response.data;
                if (angular.isObject(responseData)) {
                    $scope.profile.funcs.cdb();
                }
                billing.dlting = false;
            });
        };

        //  OPEN BILLING DEITAL
        $scope.profile.funcs.obd = function (bID) {
            let billing = $scope.profile.vars.billing;
            var detail = billing.detail;
            $scope.POPUP.openPopup(detail);
            detail.lding = true;
            var rqdt = {"billingID": bID};
            $http.post("/tripper/account-profile/detail-billing", rqdt).then(function successCallback(response) {
                var responseData = response.data;
                if (angular.isObject(responseData)) {
                    detail.dt = responseData;
                }
                detail.lding = false;
            });
        };

        // load personal information
        function loadpsn(ed) {
            let psn = $scope.profile.vars.psn;
            psn.eFn = false;
            psn.eLn = false;
            psn.eDn = false;
            psn.eGd = false;
            psn.eCon = false;
            psn.eSta = false;
            psn.eAddr = false;
            psn.eCt = false;
            psn.eZip = false;
            psn.ePn = false;
            psn.eComp = false;
            psn.eTax = false;
            $scope.profile.vars.data.cities.length = 0;
            psn.lding = true;
            $http.post("/tripper/account-profile/personal-loading", {}).then(function successCallback(response) {
                var responseData = response.data;
                if (angular.isObject(responseData)) {
                    $scope.profile.vars.data.cities = responseData.cities;
                    psn.fn = responseData.firstName;
                    psn.ln = responseData.lastName;
                    psn.dn = responseData.displayName;
                    psn.gd = responseData.isMale;
                    psn.em = responseData.email;
                    psn.addr = responseData.personalAddress;
                    psn.ct = responseData.personalCity;
                    psn.sta = responseData.stateID;
                    psn.sn = responseData.stateName;
                    psn.con = responseData.countryID;
                    psn.cn = responseData.countryName;
                    psn.pc = responseData.phoneCode;
                    psn.pn = responseData.phoneNumber;
                    psn.zip = responseData.postalCode;
                    psn.tax = responseData.taxNumber;
                    psn.comp = responseData.company;
                    psn.em = responseData.email;
                    psn.act = responseData.accountType;
                    // $scope.profile.funcs.cSta();
                    psn.eding = ed;
                    psn.lding = false;
                }
            });
        }

        // click edit personal
        $scope.profile.funcs.edpsn = function () {
            loadpsn(true);
        };

        // click save personal
        $scope.profile.funcs.svpsn = function () {
            let psn = $scope.profile.vars.psn;
            // validate
            let valid = false;
            psn.eFn = false;
            psn.eLn = false;
            psn.eGd = false;
            psn.eCon = false;
            psn.eSta = false;
            psn.eAddr = false;
            psn.eCt = false;
            psn.eZip = false;
            psn.ePn = false;
            psn.eComp = false;
            psn.eTax = false;
            //ERROR
            if (!angular.isString(psn.fn) || !psn.fn.length) {
                psn.eFn = true;
            }
            if (!angular.isString(psn.ln) || !psn.ln.length) {
                psn.eLn = true;
            }
            if (psn.gd === null) {
                psn.eGd = true;
            }
            if (!angular.isString(psn.con) || !psn.con.length) {
                psn.eCon = true;
            }
            if (!angular.isNumber(psn.sta)) {
                psn.eSta = true;
            }
            if (!angular.isString(psn.addr) || !psn.addr.length) {
                psn.eAddr = true;
            }
            if (!angular.isString(psn.ct) || !psn.ct.length) {
                psn.eCt = true;
            }
            if (!angular.isString(psn.zip) || !psn.zip.length) {
                psn.eZip = true;
            }
            if (!angular.isString(psn.pn) || !psn.pn.length) {
                psn.ePn = true;
            }
            if (!angular.isString(psn.comp) || !psn.comp.length) {
                psn.eComp = true;
            }
            if (!angular.isString(psn.tax) || !psn.tax.length) {
                psn.eTax = true;
            }
            if (!angular.isString(psn.dn) || !psn.dn.length) {
                psn.eDn = true;
            }
            //VALID WHEN
            if (!psn.eFn && !psn.eLn && !psn.eGd && !psn.eCon && !psn.eSta && !psn.eAddr && !psn.eCt && !psn.eZip && !psn.ePn && !psn.eComp
                    && !psn.eTax && !psn.eDn) {
                valid = true;
            }
            //contine saving
            if (valid) {
                psn.lding = true;
                let rqd = {"firstName": psn.fn, "lastName": psn.ln, "gender": psn.gd,
                    "countryID": psn.con, "stateID": psn.sta,
                    "personalAddress": psn.addr, "personalCity": psn.ct,
                    "postalCode": psn.zip, "phoneCode": psn.pc, "phoneNumber": psn.pn
                    , "companyName": psn.comp, "taxNumber": psn.tax, "displayName": psn.dn
                };
                $http.post("/tripper/account-profile/personal-save", rqd).then(function successCallback(response) {
                    var responseData = response.data;
                    if (angular.isObject(responseData)) {
                        if (responseData.result === true) {
                            $('#yt-header .name .display-name').text(psn.dn);
                            loadpsn(false);
                        }
                    }
                    psn.lding = false;
                });
            }
        };
        // click cancel personal
        $scope.profile.funcs.ccpsn = function () {
            loadpsn(false);
        };
        // CHANGE FIRST NAME
        $scope.profile.funcs.cfn = function () {
            let psn = $scope.profile.vars.psn;
            if (angular.isString(psn.fn) && psn.fn.length) {
                psn.eFn = false;
            } else {
                psn.eFn = true;
            }
        };
        // CHANGE LAST NAME
        $scope.profile.funcs.cln = function () {
            let psn = $scope.profile.vars.psn;
            if (angular.isString(psn.ln) && psn.ln.length) {
                psn.eLn = false;
            } else {
                psn.eLn = true;
            }
        };
        // CHANGE LAST NAME
        $scope.profile.funcs.cdn = function () {
            let psn = $scope.profile.vars.psn;
            if (angular.isString(psn.dn) && psn.dn.length) {
                psn.eDn = false;
            } else {
                psn.eDn = true;
            }
        };
        // CHANGE GENDER
        $scope.profile.funcs.cgd = function () {
            let psn = $scope.profile.vars.psn;
            if (psn.gd === null) {
                psn.eGd = true;
            } else {
                psn.eGd = false;
            }
        };
        // CHANGE STATE
        $scope.profile.funcs.cSta = function () {
            if ($scope.profile.vars.data.cities.length) {
                let psn = $scope.profile.vars.psn;
                if (angular.isNumber(psn.sta)) {
                    psn.eSta = false;
                } else {
                    psn.eSta = true;
                }
            }

        };
        //CHANGE ADDR
        $scope.profile.funcs.caddr = function () {
            var psn = $scope.profile.vars.psn;
            if (angular.isString(psn.addr) && psn.addr.length) {
                psn.eAddr = false;
            } else {
                psn.eAddr = true;
            }
        };
        //CHANGE CITY
        $scope.profile.funcs.cct = function () {
            var psn = $scope.profile.vars.psn;
            if (angular.isString(psn.ct) && psn.ct.length) {
                psn.eCt = false;
            } else {
                psn.eCt = true;
            }
        };
        //CHANGE ZIP
        $scope.profile.funcs.czip = function () {
            var psn = $scope.profile.vars.psn;
            if (angular.isString(psn.zip) && psn.zip.length) {
                psn.eZip = false;
            } else {
                psn.eZip = true;
            }
        };
        //CHANGE PHONE NUMBER
        $scope.profile.funcs.cpn = function () {
            var psn = $scope.profile.vars.psn;
            if (angular.isString(psn.pn) && psn.pn.length) {
                psn.ePn = false;
            } else {
                psn.ePn = true;
            }
        };
        //CHANGE COMPANY
        $scope.profile.funcs.ccomp = function () {
            var psn = $scope.profile.vars.psn;
            if (angular.isString(psn.comp) && psn.comp.length) {
                psn.eComp = false;
            } else {
                psn.eComp = true;
            }
        };

        //CHANGE TAX ID
        $scope.profile.funcs.ctax = function () {
            var psn = $scope.profile.vars.psn;
            if (angular.isString(psn.tax) && psn.tax.length) {
                psn.eTax = false;
            } else {
                psn.eTax = true;
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
                ai.lding = true;
                let rqd = {"currentPassword": ai.crpw,
                    "newPassword": ai.npw,
                    "confirmPassword": ai.cfpw
                };
                $http.post("/tripper/account-profile/ai-save", rqd).then(function successCallback(response) {
                    var responseData = response.data;
                    if (angular.isObject(responseData)) {
                        if (responseData.result === true) {
                            $scope.profile.funcs.cai();
                        } else if (responseData.result === null) {
                            ai.crpwnc = true;
                        }
                    }
                    ai.lding = false;
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

        //click edit email
        $scope.profile.funcs.edemai = function () {
            let ai = $scope.profile.vars.ai;
            ai.nemE = false;
            ai.nemFE = false;
            ai.cfemE = false;
            ai.cfemFE = false;
            ai.eE = true;
        };

        // save email
        $scope.profile.funcs.svemai = function () {
            let ai = $scope.profile.vars.ai;
            // validate
            let valid = false;
            var validNEM = false;
            var validCFEM = false;
            ai.nemE = false;
            ai.nemFE = false;
            ai.cfemE = false;
            ai.cfemFE = false;

            // NEW EMAIL AND CONFRIM EMAIL
            if (angular.isString(ai.nem) && ai.nem.length) {
                if ($scope.profile.vars.data.regex.email.test(ai.nem)) {
                    ai.nemE = false;
                    // confirm password
                    if (ai.nem === ai.cfem) {
                        ai.cfemE = false;
                        validCFEM = true;
                    } else {
                        ai.cfemE = true;
                        validCFEM = false;
                    }
                    //
                    validNEM = true;
                } else {
                    ai.nemFE = true;
                }
            } else {
                ai.nemE = true;
                validNEM = false;
            }

            // PASS VALIDATION
            if (validCFEM && validNEM) {
                valid = true;
            }
            //SAVE
            if (valid) {
                ai.lding = true;
                let rqd = {"newEmail": ai.nem,
                    "confirmEmail": ai.cfem
                };
                $http.post("/tripper/account-profile/aiem-save", rqd).then(function successCallback(response) {
                    var responseData = response.data;
                    if (angular.isObject(responseData)) {
                        if (responseData.result === true) {
                            $scope.profile.vars.psn.em = ai.nem;
                            $scope.profile.funcs.cemai();
                        }
                    }
                    ai.lding = false;
                });
            }
        };

        // change new email
        $scope.profile.funcs.cnem = function () {
            let ai = $scope.profile.vars.ai;
            if (angular.isString(ai.nem) && ai.nem.length) {
                ai.nemE = false;
                ai.nemFE = false;
            } else {
                ai.nemE = true;
            }
        }

        // change confirm email
        $scope.profile.funcs.ccfem = function () {
            let ai = $scope.profile.vars.ai;
            if (angular.isString(ai.cfem) && ai.cfem.length) {
                ai.cfemE = false;
            } else {
                ai.cfemE = true;
            }
        }

        // click cancel email saving
        $scope.profile.funcs.cemai = function () {
            let ai = $scope.profile.vars.ai;
            ai.nem = null;
            ai.cfem = null;
            ai.nemE = false;
            ai.nemFE = false;
            ai.cfemE = false;
            ai.cfemFE = false;
            ai.eE = false;
        };

        // LOAD OPTIONAL
        function loadop(ed) {
            let op = $scope.profile.vars.op;
            op.lding = true;
            var rqdt = {"locale": $scope.youtripper.vars.data.currentLan};
            $http.post("/tripper/account-profile/optional-loading", rqdt).then(function successCallback(response) {
                var responseData = response.data;
                if (angular.isObject(responseData)) {
                    op.bg = responseData.background;
                    op.pl = responseData.preferedLanguageID;
//                var a = document.getElementById("selectL");
//                op.pln = (a.options[a.selectedIndex].text);
                    op.eding = ed;
                    op.lding = false;
                }
            });
        }

        // click edit personal
        $scope.profile.funcs.edop = function () {
            loadop(true);
        };
        // click save personal

        $scope.profile.funcs.svop = function () {
            let op = $scope.profile.vars.op;
            op.lding = true;
            let rqd = {"backGround": op.bg, "preferedLanguageID": op.pl};
            $http.post("/tripper/account-profile/optional-save", rqd).then(function successCallback(response) {
                var responseData = response.data;
                if (angular.isObject(responseData)) {
                    if (responseData.result === true) {
                        loadop(false);
                    }
                }
                op.lding = false;
            });

        };
        // click cancel optional
        $scope.profile.funcs.cop = function () {
            loadop(false);
        };

        // function init
        function init() {
            loadpsn(false);
            loadCre();
            loadBilling();
            loadop(false);
            //     $scope.profile.funcs.loadbs(false);
        }
        init();
    } catch (e) {
        console.log(e.message);
        $exceptionHandler({"pageID": pageID}, "");
    }
});