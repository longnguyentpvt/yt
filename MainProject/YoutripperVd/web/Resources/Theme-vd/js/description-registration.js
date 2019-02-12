/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


app.controller('DescriptionRegistration', function ($scope, $http, $timeout) {
    $scope.description = {
        "vars": {
            "selectedLanguage": "en",
            "contentHM": null,
            "keywordHM": null,
            "sLans": null,
            "sLanHM": {},
            "lAdding": false,
            "enableEditing": false,
            "DATA": {
                "mandatoryDescriptionLanguage": "en",
                "descriptionLanguages": null,
                "servingLanguages": null
            },
            "questions": {
                "pkgName": {
                    "mandatory": true,
                    "done": {}
                },
                "mDs": {
                    "mandatory": true,
                    "done": {}
                },
                "activityLocation": {
                    "mandatory": true,
                    "done": {}
                },
                "kw": {
                    "mandatory": true,
                    "done": {}
                },
                "seoDes": {
                    "mandatory": true,
                    "done": {}
                },
                "sLan": {
                    "mandatory": true,
                    "done": false
                }
            },
            "popup": {
                "activeEditing": {
                    "active": false,
                    "updating": false,
                    "editedPart": null,
                    "PARTS": {
                        "pName": "pkgName",
                        "mDes": "mDes",
                        "nLan": "nLan",
                        "rLan": "rLan"
                    }
                },
                "lAdding": {
                    "active": false,
                    "adding": false,
                    "mL": null
                },
                "lD": {
                    "active": false,
                    "dl": false,
                    "mL": null
                },
                "loc": {
                    "active": false,
                    "loading": false,
                    "name": null,
                    "addr": null,
                    "lat": null,
                    "lng": null,
                    "type": null,
                    "edit": false,
                    "ei": null,
                    "err": false
                }
            },
            "kwTemp": null
        },
        "funcs": {
            "changeDL": null,
            "pkgNameChanged": null,
            "addNMD": null,
            "removeMD": null,
            "mdChanged": null,
            "openEditingPopup": null,
            "cancelEditing": null,
            "acceptApproval": null,
            "openAddingLanguage": null,
            "cancelAddingP": null,
            "aNewL": null,
            "oDL": null,
            "raL": null,
            "lostC": null,
            "loetC": null,
            "clsT": null,
            "cleT": null,
            "alChanged": null,
            "dlChanged": null,
            "plChanged": null,
            "anLoc": null,
            "enLoc": null,
            "closeLoc": null,
            "seLoc": null,
            "daLoc": null,
            "anKW": null,
            "dlKW": null,
            "gdChanged": null,
            "slChanged": null
        }
    };

    function validateDescriptionLanguage() {
        var data = $scope.description.vars.DATA;

        var qs = $scope.description.vars.questions;
        var contentHM = $scope.description.vars.contentHM;
        var lans = data.descriptionLanguages;
        for (var i = 0, max = lans.length; i < max; i++) {
            var aL = lans[i];
            var aCode = aL[0];
            var registered = contentHM[aCode].registered;

            if (registered) {
                var done = true;
                angular.forEach(qs, function (aQ, qN) {
                    let dO = aQ.done;
                    if (angular.isObject(dO)) {
                        var qD = aQ.done[aCode];
                        if (!angular.isDefined(qD) || !qD) {
                            done = false;
                        }
                    } else {
                        if (!dO) {
                            done = false;
                        }
                    }
                });
                aL[2] = done;
            }
        }
    }

    $scope.description.funcs.changeDL = function (lC) {
        if (lC !== $scope.description.vars.selectedLanguage) {
            $scope.description.vars.selectedLanguage = lC;
        }
    };

    /* Package Name */
    function validatePackageName(pkgName, languageCode) {
        var valid = false;
        if (angular.isString(pkgName) && pkgName.length) {
            valid = true;
        }

        $scope.description.vars.questions.pkgName.done[languageCode] = valid;
    }

    var pnC = {};
    function savePackageName(pkgName, lC) {
        $timeout.cancel(pnC[lC]);
        $scope.registration.vars.steps.description.saving = true;
        pnC[lC] = $timeout(function () {
            var data = {
                "packageID": $scope.registration.vars.packageID,
                "languageCode": lC,
                "packageName": pkgName
            };

            $http.post("/partner/package-registration/description/packageName-updating", data)
                    .then(function successCallback(response) {
                        if (angular.isObject(response.data)) {
                            $scope.registration.vars.steps.description.saving = false;
                            console.log("Package Name Saved");
                        }
                    });
        }, 500);
    }

    $scope.description.funcs.pkgNameChanged = function () {
        var vars = $scope.description.vars;
        if (vars.enableEditing) {
            var lC = vars.selectedLanguage;
            var aContent = $scope.description.vars.contentHM[lC];
            savePackageName(aContent.packageName, lC);

            validatePackageName(aContent.packageName, lC);
            validateDescriptionLanguage();
        } else {
            var p = vars.popup.activeEditing;
            $scope.description.funcs.openEditingPopup();
        }
    };
    /* End Package Name */

    /* Multi Description */
    $scope.description.funcs.addNMD = function (pLC) {
        var vars = $scope.description.vars;
        var lC = !angular.isString(pLC) ? vars.selectedLanguage : pLC;

        var mDs = vars.contentHM[lC].multiDescriptions;
        if (!angular.isArray(mDs)) {
            mDs = [];
            vars.contentHM[lC].multiDescriptions = mDs;
        }

        var nD = {"title": null, "description": null};
        mDs.push(nD);

        if (!angular.isString(pLC)) {
            $scope.description.funcs.mdChanged();
        }
    };

    $scope.description.funcs.removeMD = function (id) {
        var lC = $scope.description.vars.selectedLanguage;
        var mDs = $scope.description.vars.contentHM[lC].multiDescriptions;
        mDs.splice(id, 1);

        $scope.description.funcs.mdChanged();
    };

    function validateMD(mDs, lC) {
        if (!angular.isArray(mDs) || mDs.length === 0) {
            $scope.description.funcs.addNMD(lC);
        } else {
            var valid = true;
            for (var i = 0, max = mDs.length; i < max; i++) {
                var aD = mDs[i];
                if (!angular.isString(aD.title) || !angular.isString(aD.description) ||
                        !aD.title.length || !aD.description.length) {
                    valid = false;
                }
            }
            $scope.description.vars.questions.mDs.done[lC] = valid;
        }
    }

    var mdC = {};
    function saveMD(mDs, lC) {
        $timeout.cancel(mdC[lC]);
        $scope.registration.vars.steps.description.saving = true;
        mdC[lC] = $timeout(function () {
            var data = {
                "packageID": $scope.registration.vars.packageID,
                "languageCode": lC,
                "multiDescription": mDs
            };

            $http.post("/partner/package-registration/description/multiDescription-updating", data)
                    .then(function successCallback(response) {
                        if (angular.isObject(response.data)) {
                            $scope.registration.vars.steps.description.saving = false;
                            console.log("Multi Description Saved");
                        }
                    });
        }, 500);
    }

    $scope.description.funcs.mdChanged = function () {
        var vars = $scope.description.vars;
        if (vars.enableEditing) {
            var lC = vars.selectedLanguage;
            var aContent = vars.contentHM[lC];
            saveMD(aContent.multiDescriptions, lC);

            validateMD(aContent.multiDescriptions, lC);
            validateDescriptionLanguage();
        } else {
            var p = vars.popup.activeEditing;
            $scope.description.funcs.openEditingPopup();
        }
    };
    /* End Multi Description */

    /* Active Editing */
    $scope.description.funcs.openEditingPopup = function () {
        $(":focus").blur();
        var vars = $scope.description.vars;
        var p = vars.popup.activeEditing;
        $scope.POPUP.openPopup(p);
    };

    $scope.description.funcs.cancelEditing = function () {
        $scope.registration.vars.steps.description.load();
        $scope.POPUP.closePopup($scope.description.vars.popup.activeEditing);
    };

    $scope.description.funcs.acceptApproval = function () {
        var vars = $scope.description.vars;
        var pu = vars.popup.activeEditing;
        pu.updating = true;

        var data = {"packageID": $scope.registration.vars.packageID};
        $http.post("/partner/package-registration/description/active-editing", data)
                .then(function successCallback(response) {
                    var responseData = response.data;
                    if (angular.isObject(responseData)) {
                        vars.enableEditing = true;
                        $scope.POPUP.closePopup(pu);
                        pu.updating = false;
                    }
                });
    };
    /* End Active Editing */

    /* Adding Language */
    $scope.description.funcs.openAddingLanguage = function () {
        var vars = $scope.description.vars;
        var pu = vars.popup.lAdding;
        $scope.POPUP.openPopup(pu);
    };

    $scope.description.funcs.cancelAddingP = function () {
        var vars = $scope.description.vars;
        var pu = vars.popup.lAdding;

        pu.mL = null;

        $scope.POPUP.closePopup(pu);
    };

    $scope.description.funcs.aNewL = function () {
        var vars = $scope.description.vars;
        var pu = vars.popup.lAdding;
        if (pu.mL !== null) {
            var sC = $scope.description.vars.contentHM[pu.mL];
            if (angular.isObject(sC)) {
                pu.adding = true;

                var data = {
                    "packageID": $scope.registration.vars.packageID,
                    "languageCode": pu.mL
                };
                $http.post("/partner/package-registration/description/language-activation", data)
                        .then(function successCallback(response) {
                            var responseData = response.data;
                            if (angular.isObject(responseData)) {
                                sC.registered = true;
                                $scope.description.vars.selectedLanguage = pu.mL;
                                validateAll();

                                $scope.POPUP.closePopup(pu);
                                pu.mL = null;
                                pu.adding = false;
                            }
                        });
            }
        }
    };

    $scope.description.funcs.oDL = function (l) {
        var vars = $scope.description.vars;
        var pu = vars.popup.lD;
        pu.mL = l;
        $scope.POPUP.openPopup(pu);
    };

    $scope.description.funcs.raL = function () {
        var vars = $scope.description.vars;
        var pu = vars.popup.lD;
        var sC = vars.contentHM[pu.mL];
        if (angular.isObject(sC)) {
            pu.dl = true;
            var data = {
                "packageID": $scope.registration.vars.packageID,
                "languageCode": pu.mL
            };
            $http.post("/partner/package-registration/description/language-unactivation", data)
                    .then(function successCallback(response) {
                        var responseData = response.data;
                        if (angular.isObject(responseData)) {
                            sC.registered = false;

                            if (vars.selectedLanguage === pu.mL) {
                                vars.selectedLanguage = vars.DATA.mandatoryDescriptionLanguage;
                            }

                            detectEnableAddingLanguage();
                            $scope.POPUP.closePopup(pu);
                            pu.mL = null;
                            pu.dl = false;
                        }
                    });

        }
    };
    /* End Adding Language */

    /* location */
    var prM, mLs = [], ull, aic, pic, dic;
    function inM() {
        if (!angular.isObject(prM)) {
            prM = new google.maps.Map($(".preview-map .map")[0], {
                center: {lat: 13.735893, lng: 100.6486693},
                zoom: 17
            });

            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function (position) {
                    ull = {
                        lat: position.coords.latitude,
                        lng: position.coords.longitude
                    };

                    prM.setCenter(ull);
                }, function () {
                    console.log("Geolocation Disabled");
                });
            }
            udM();
        }
    }

    function getML(lat, lng, ic, lName) {
        var aML = {
            "m": null,
            "w": null
        };

        aML.m = new google.maps.Marker({
            position: {lat: lat, lng: lng},
            map: prM,
            icon: ic
        });

        aML.w = new google.maps.InfoWindow({
            content: lName
        });
        aML.w.open(prM, aML.m);

        return aML;
    }

    function udM() {
        if (angular.isArray(mLs)) {
            for (let i = 0, max = mLs.length; i < max; i++) {
                let amL = mLs[i];
                amL.m.setMap(null);
                amL.w.close();
            }
            mLs.length = 0;
        }

        var vs = $scope.description.vars;
        var sct = vs.contentHM[vs.selectedLanguage];

        var ls = sct.activityLocations;
        if (angular.isArray(ls)) {
            for (let i = 0, max = ls.length; i < max; i++) {
                let aL = ls[i];
                let lat = aL.lat;
                let lng = aL.lng;
                let na = aL.name;
                mLs.push(getML(lat, lng, aic, na));
            }
        }

        ls = sct.departureLocations;
        if (angular.isArray(ls)) {
            for (let i = 0, max = ls.length; i < max; i++) {
                let aL = ls[i];
                let lat = aL.lat;
                let lng = aL.lng;
                let na = aL.name;
                mLs.push(getML(lat, lng, dic, na));
            }
        }

        ls = sct.pickupLocations;
        if (angular.isArray(ls)) {
            for (let i = 0, max = ls.length; i < max; i++) {
                let aL = ls[i];
                let lat = aL.lat;
                let lng = aL.lng;
                let na = aL.name;
                mLs.push(getML(lat, lng, pic, na));
            }
        }


        var loL = mLs.length;
        if (loL > 0) {
            var bounds = new google.maps.LatLngBounds();
            for (let i = 0; i < loL; i++) {
                bounds.extend(mLs[i].m.getPosition());
            }
            prM.fitBounds(bounds);

            let listener = google.maps.event.addListener(prM, "idle", function () {
                if (prM.getZoom() > 17)
                    prM.setZoom(17);
                google.maps.event.removeListener(listener);
            });
        }
    }

    function initLocation(aContent) {
        var ls = aContent.activityLocations;
        if (!angular.isArray(ls)) {
            aContent.activityLocations = [];
            ls = aContent.activityLocations;
        }

        for (let i = 0, max = ls.length; i < max; i++) {
            let aL = ls[i];
            let sT = aL["startTime"];
            let hm = $scope.CLOCK.funcs.getHM(sT);
            aL["sH"] = hm[0];
            aL["sM"] = hm[1];
            aL["stT"] = $scope.CLOCK.funcs.HHmm(sT);

            let eT = aL["endTime"];
            hm = $scope.CLOCK.funcs.getHM(eT);
            aL["eH"] = hm[0];
            aL["eM"] = hm[1];
            aL["etT"] = $scope.CLOCK.funcs.HHmm(eT);
        }

        ls = aContent.departureLocations;
        if (!angular.isArray(ls)) {
            aContent.departureLocations = [];
            ls = aContent.departureLocations;
        }

        for (let i = 0, max = ls.length; i < max; i++) {
            let aL = ls[i];
            let sT = aL["startTime"];
            let hm = $scope.CLOCK.funcs.getHM(sT);
            aL["sH"] = hm[0];
            aL["sM"] = hm[1];
            aL["stT"] = $scope.CLOCK.funcs.HHmm(sT);

            let eT = aL["endTime"];
            hm = $scope.CLOCK.funcs.getHM(eT);
            aL["eH"] = hm[0];
            aL["eM"] = hm[1];
            aL["etT"] = $scope.CLOCK.funcs.HHmm(eT);
        }

        ls = aContent.pickupLocations;
        if (!angular.isArray(ls)) {
            aContent.pickupLocations = [];
            ls = aContent.pickupLocations;
        }

        for (let i = 0, max = ls.length; i < max; i++) {
            let aL = ls[i];
            let sT = aL["startTime"];
            let hm = $scope.CLOCK.funcs.getHM(sT);
            aL["sH"] = hm[0];
            aL["sM"] = hm[1];
            aL["stT"] = $scope.CLOCK.funcs.HHmm(sT);

            let eT = aL["endTime"];
            hm = $scope.CLOCK.funcs.getHM(eT);
            aL["eH"] = hm[0];
            aL["eM"] = hm[1];
            aL["etT"] = $scope.CLOCK.funcs.HHmm(eT);
        }
    }

    function validateLocation(ls, lC) {
        var done = false;
        if (angular.isArray(ls) && ls.length > 0) {
            done = true;
        }
        $scope.description.vars.questions.activityLocation.done[lC] = done;
    }

    $scope.description.funcs.lostC = function (aL, activity, departure, pickup) {
        var h = aL["sH"];
        var m = aL["sM"];
        var min = $scope.CLOCK.funcs.gM(h, m);
        var txt = $scope.CLOCK.funcs.HHmm(min);
        aL["startTime"] = min;
        aL["stT"] = txt;

        if (activity) {
            $scope.description.funcs.alChanged();
        } else if (departure) {
            $scope.description.funcs.dlChanged();
        } else if (pickup) {
            $scope.description.funcs.plChanged();
        }
    };

    $scope.description.funcs.loetC = function (aL, activity, departure, pickup) {
        var h = aL["eH"];
        var m = aL["eM"];
        var min = $scope.CLOCK.funcs.gM(h, m);
        var txt = $scope.CLOCK.funcs.HHmm(min);
        aL["endTime"] = min;
        aL["etT"] = txt;

        if (activity) {
            $scope.description.funcs.alChanged();
        } else if (departure) {
            $scope.description.funcs.dlChanged();
        } else if (pickup) {
            $scope.description.funcs.plChanged();
        }
    };

    $scope.description.funcs.clsT = function (aL, activity, departure, pickup) {
        aL["sH"] = -1;
        aL["sM"] = -1;
        aL["startTime"] = null;
        aL["stT"] = null;

        if (activity) {
            $scope.description.funcs.alChanged();
        } else if (departure) {
            $scope.description.funcs.dlChanged();
        } else if (pickup) {
            $scope.description.funcs.plChanged();
        }
    };

    $scope.description.funcs.cleT = function (aL, activity, departure, pickup) {
        aL["eH"] = -1;
        aL["eM"] = -1;
        aL["endTime"] = null;
        aL["etT"] = null;

        if (activity) {
            $scope.description.funcs.alChanged();
        } else if (departure) {
            $scope.description.funcs.dlChanged();
        } else if (pickup) {
            $scope.description.funcs.plChanged();
        }
    };

    var edM, edMr, emI;
    function inEM() {
        if (!angular.isObject(edM)) {
            edM = new google.maps.Map($("#location-popup .map")[0], {
                center: {lat: 13.735893, lng: 100.6486693},
                zoom: 17
            });

            emI = new google.maps.places.SearchBox($("#location-popup .editing-map .input input")[0]);
            emI.bindTo('bounds', edM);

            emI.addListener('places_changed', function () {
                emlChange();
                $scope.$apply();
            });

            google.maps.event.addListener(edM, "click", function (event) {
                var pos = event.latLng;
                emClick(pos);

                $scope.$apply();
            });
        }
    }

    function emlChange() {
        var places = emI.getPlaces();
        if (places.length === 0) {
            return;
        }
        var place = places[0];

        var pos = place.geometry.location;
        if (angular.isObject(pos)) {
            edMr.setPosition(pos);
            edM.setCenter(pos);
            edM.setZoom(17);

            var vars = $scope.description.vars;
            var p = vars.popup.loc;
            p.lat = pos.lat();
            p.lng = pos.lng();
        }
    }

    function emClick(pos) {
        if (angular.isObject(pos)) {
            var vars = $scope.description.vars;
            var p = vars.popup.loc;

            p.lat = pos.lat();
            p.lng = pos.lng();

            edMr.setPosition(pos);
        }
    }

    function upEM() {
        var vars = $scope.description.vars;
        var p = vars.popup.loc;

        let c = {"lat": p.lat, "lng": p.lng};

        edMr = new google.maps.Marker({
            position: c,
            map: edM
        });
        edM.setCenter(c);
        edM.setZoom(17);
    }

    $scope.description.funcs.anLoc = function (type) {
        var vars = $scope.description.vars;
        var p = vars.popup.loc;

        p.name = null;
        p.addr = null;
        p.lat = 13.735893;
        p.lng = 100.6486693;
        p.type = type;
        p.edit = false;
        p.ei = null;
        p.err = false;

        if (angular.isObject(ull)) {
            p.lat = ull.lat;
            p.lng = ull.lng;
        }

        $("#location-popup .editing-map .input input").val("");
        $scope.POPUP.openPopup(p);
        upEM();
    };

    $scope.description.funcs.enLoc = function (type, aL, ei) {
        var vars = $scope.description.vars;
        var p = vars.popup.loc;

        p.name = aL.name;
        p.addr = aL.addr;
        p.lat = aL.lat;
        p.lng = aL.lng;
        p.type = type;
        p.edit = true;
        p.ei = ei;
        p.err = false;

        $("#location-popup .editing-map .input input").val("");
        $scope.POPUP.openPopup(p);
        upEM();
    };

    $scope.description.funcs.seLoc = function () {
        var vars = $scope.description.vars;
        var p = vars.popup.loc;
        var sCon = vars.contentHM[vars.selectedLanguage];
        let name = p.name;
        let addr = p.addr;

        let valid = false;
        if (angular.isString(name) && angular.isString(addr) && name.length && addr.length) {
            valid = true;
            var ty = p.type, locs;
            if (ty === "activity") {
                locs = sCon.activityLocations;
            } else if (ty === "departure") {
                locs = sCon.departureLocations;
            } else if (ty === "pickup") {
                locs = sCon.pickupLocations;
            }

            if (!p.edit) {
                let nL;

                nL = {
                    "name": p.name,
                    "addr": p.addr,
                    "desc": null,
                    "startTime": null,
                    "sH": -1,
                    "sM": -1,
                    "stT": null,
                    "endTime": null,
                    "eH": -1,
                    "eM": -1,
                    "etT": null,
                    "lat": p.lat,
                    "lng": p.lng
                };

                locs.push(nL);
            } else {
                let  nL;

                var sL = locs[p.ei];
                sL["name"] = p.name;
                sL["addr"] = p.addr;
                sL["lat"] = p.lat;
                sL["lng"] = p.lng;
            }

            if (ty === "activity") {
                $scope.description.funcs.alChanged();
            } else if (ty === "departure") {
                $scope.description.funcs.dlChanged();
            } else if (ty === "pickup") {
                $scope.description.funcs.plChanged();
            }
            $scope.description.funcs.closeLoc();
        }
        p.err = !valid;
    };

    $scope.description.funcs.closeLoc = function () {
        var vars = $scope.description.vars;
        var p = vars.popup.loc;
        $scope.POPUP.closePopup(p);
    };

    $scope.description.funcs.daLoc = function (ty, di) {
        var vars = $scope.description.vars;
        var sCon = vars.contentHM[vars.selectedLanguage];

        var locs;
        if (ty === "activity") {
            locs = sCon.activityLocations;
        } else if (ty === "departure") {
            locs = sCon.departureLocations;
        } else if (ty === "pickup") {
            locs = sCon.pickupLocations;
        }

        locs.splice(di, 1);

        if (ty === "activity") {
            $scope.description.funcs.alChanged();
        } else if (ty === "departure") {
            $scope.description.funcs.dlChanged();
        } else if (ty === "pickup") {
            $scope.description.funcs.plChanged();
        }
    };

    var alC = {};
    function saveALocation(ls, lC) {
        $timeout.cancel(alC[lC]);
        $scope.registration.vars.steps.description.saving = true;
        alC[lC] = $timeout(function () {
            var locations = [];
            for (let i = 0, max = ls.length; i < max; i++) {
                let l = ls[i];
                let aL = {
                    "name": l.name,
                    "addr": l.addr,
                    "desc": l.desc,
                    "startTime": l.startTime,
                    "endTime": l.endTime,
                    "lat": l.lat,
                    "lng": l.lng
                };

                locations.push(aL);
            }

            var data = {
                "packageID": $scope.registration.vars.packageID,
                "languageCode": lC,
                "locations": locations
            };

            $http.post("/partner/package-registration/description/activityLocation-updating", data)
                    .then(function successCallback(response) {
                        if (angular.isObject(response.data)) {
                            $scope.registration.vars.steps.description.saving = false;
                            console.log("Activity Saved");
                        }
                    });
        }, 500);
    }

    var dlC = {};
    function saveDLocation(ls, lC) {
        $timeout.cancel(dlC[lC]);
        $scope.registration.vars.steps.description.saving = true;
        dlC[lC] = $timeout(function () {
            var locations = [];
            for (let i = 0, max = ls.length; i < max; i++) {
                let l = ls[i];
                let aL = {
                    "name": l.name,
                    "addr": l.addr,
                    "desc": l.desc,
                    "startTime": l.startTime,
                    "endTime": l.endTime,
                    "lat": l.lat,
                    "lng": l.lng
                };

                locations.push(aL);
            }

            var data = {
                "packageID": $scope.registration.vars.packageID,
                "languageCode": lC,
                "locations": locations
            };

            $http.post("/partner/package-registration/description/departureLocation-updating", data)
                    .then(function successCallback(response) {
                        if (angular.isObject(response.data)) {
                            $scope.registration.vars.steps.description.saving = false;
                            console.log("Departure Saved");
                        }
                    });
        }, 500);
    }

    var plC = {};
    function savePLocation(ls, lC) {
        $timeout.cancel(plC[lC]);
        $scope.registration.vars.steps.description.saving = true;
        plC[lC] = $timeout(function () {
            var locations = [];
            for (let i = 0, max = ls.length; i < max; i++) {
                let l = ls[i];
                let aL = {
                    "name": l.name,
                    "addr": l.addr,
                    "desc": l.desc,
                    "startTime": l.startTime,
                    "endTime": l.endTime,
                    "lat": l.lat,
                    "lng": l.lng
                };

                locations.push(aL);
            }

            var data = {
                "packageID": $scope.registration.vars.packageID,
                "languageCode": lC,
                "locations": locations
            };

            $http.post("/partner/package-registration/description/pickupLocation-updating", data)
                    .then(function successCallback(response) {
                        if (angular.isObject(response.data)) {
                            $scope.registration.vars.steps.description.saving = false;
                            console.log("Pickup Saved");
                        }
                    });
        }, 500);
    }

    $scope.description.funcs.alChanged = function () {
        var vars = $scope.description.vars;
        var lC = vars.selectedLanguage;
        var ls = vars.contentHM[lC].activityLocations;

        saveALocation(ls, lC);

        validateLocation(ls, lC);
        validateDescriptionLanguage();
    };

    $scope.description.funcs.dlChanged = function () {
        var vars = $scope.description.vars;
        var lC = vars.selectedLanguage;
        var ls = vars.contentHM[lC].departureLocations;

        saveDLocation(ls, lC);
    };

    $scope.description.funcs.plChanged = function () {
        var vars = $scope.description.vars;
        var lC = vars.selectedLanguage;
        var ls = vars.contentHM[lC].pickupLocations;

        savePLocation(ls, lC);
    };
    /* end location */

    /* keyword */
    function initKW(aContent) {
        if (!angular.isArray(aContent.keywords)) {
            aContent.keywords = [];
        }
    }

    function validateKW(kws, lC) {
        let valid = false;
        if (angular.isArray(kws) && kws.length > 0) {
            valid = true;
        }
        $scope.description.vars.questions.kw.done[lC] = valid;
    }

    $scope.description.funcs.anKW = function ($event) {
        var keyCode = $event.which;
        if (keyCode === 13) {
            var vars = $scope.description.vars;
            var ckw = vars.kwTemp;
            if (angular.isString(ckw)) {
                ckw = ckw.trim().replace(/ +(?= )/g, '');
                if (ckw.length) {
                    var kws = vars.contentHM[vars.selectedLanguage].keywords;
                    let max = kws.length;
                    if (max < 9) {
                        let exist = false;
                        for (let i = 0; i < max; i++) {
                            if (kws[i] === ckw) {
                                exist = true;
                            }
                        }
                        if (!exist) {
                            vars.kwTemp = null;
                            kws.push(ckw);

                            kwsChanged();
                        }
                    }
                }
            }

        }
    };

    $scope.description.funcs.dlKW = function (di) {
        var vars = $scope.description.vars;
        var kws = vars.contentHM[vars.selectedLanguage].keywords;

        kws.splice(di, 1);

        kwsChanged();
    };

    var kwC = {};
    function saveKW(kws, lC) {
        $timeout.cancel(kwC[lC]);
        $scope.registration.vars.steps.description.saving = true;
        kwC[lC] = $timeout(function () {
            var data = {
                "packageID": $scope.registration.vars.packageID,
                "languageCode": lC,
                "kws": kws
            };

            $http.post("/partner/package-registration/description/keyword-updating", data)
                    .then(function successCallback(response) {
                        if (angular.isObject(response.data)) {
                            $scope.registration.vars.steps.description.saving = false;
                            console.log("Keyword Saved");
                        }
                    });
        }, 500);
    }

    function kwsChanged() {
        var vars = $scope.description.vars;

        var lC = vars.selectedLanguage;
        var kws = vars.contentHM[lC].keywords;

        saveKW(kws, lC);

        validateKW(kws, lC);
        validateDescriptionLanguage();
    }
    /* end keyword */

    /* seo description */
    function validatesDes(des, lC) {
        let valid = false;
        if (angular.isString(des) && des.length > 0) {
            valid = true;
        }
        $scope.description.vars.questions.seoDes.done[lC] = valid;
    }

    var gdC = {};
    function saveDesc(desc, lC) {
        $timeout.cancel(gdC[lC]);
        $scope.registration.vars.steps.description.saving = true;
        gdC[lC] = $timeout(function () {
            var data = {
                "packageID": $scope.registration.vars.packageID,
                "languageCode": lC,
                "desc": desc
            };

            $http.post("/partner/package-registration/description/seo-updating", data)
                    .then(function successCallback(response) {
                        if (angular.isObject(response.data)) {
                            $scope.registration.vars.steps.description.saving = false;
                            console.log("Google Description Saved");
                        }
                    });
        }, 500);
    }

    $scope.description.funcs.gdChanged = function () {
        var vars = $scope.description.vars;

        var lC = vars.selectedLanguage;
        var desc = vars.contentHM[lC].googleDescription;

        saveDesc(desc, lC);

        validatesDes(desc, lC);
        validateDescriptionLanguage();
    };
    /* end seo description */

    /* serving language */
    function initSerLan() {
        var vars = $scope.description.vars;
        var sLanHM = vars.sLanHM;
        var ysLans = vars.DATA.servingLanguages;
        for (let i = 0, max = ysLans.length; i < max; i++) {
            let aL = ysLans[i];
            sLanHM[aL[0]] = {
                "n": aL[1],
                "s": false
            };
        }

        var sLans = vars.sLans;
        if (!angular.isArray(sLans)) {
            sLans = [];
            vars.sLans = sLans;
        }
        for (let i = 0, max = sLans.length; i < max; i++) {
            sLanHM[sLans[i]]["s"] = true;
        }
    }

    function validateSerLan(lans) {
        let valid = false;
        if (angular.isArray(lans) && lans.length > 0) {
            valid = true;
        }
        $scope.description.vars.questions.sLan.done = valid;
    }

    var svC;
    function saveServingLanguage(sls) {
        $timeout.cancel(svC);
        $scope.registration.vars.steps.description.saving = true;
        svC = $timeout(function () {
            var data = {
                "packageID": $scope.registration.vars.packageID,
                "servingLanguages": sls
            };

            $http.post("/partner/package-registration/description/servingLanguage-updating", data)
                    .then(function successCallback(response) {
                        if (angular.isObject(response.data)) {
                            $scope.registration.vars.steps.description.saving = false;
                            console.log("Serving Language Saved");
                        }
                    });
        }, 500);
    }

    $scope.description.funcs.slChanged = function (sC) {
        var vars = $scope.description.vars;
        var sLanHM = vars.sLanHM;
        var cLan = sLanHM[sC];
        var sLans = vars.sLans;
        if (cLan.s) {
            sLans.push(sC);
        } else {
            let di = 0;
            for (let i = 0, max = sLans.length; i < max; i++) {
                if (sLans[i] === sC) {
                    di = i;
                    break;
                }
            }

            sLans.splice(di, 1);
        }
        saveServingLanguage(sLans);

        validateSerLan(sLans);
        validateDescriptionLanguage();
    };
    /* end serving language */


    /* COMMON */
    function detectEnableAddingLanguage() {
        var enable = false;
        var contentHM = $scope.description.vars.contentHM;
        angular.forEach(contentHM, function (aContent, cCode) {
            if (!aContent.registered) {
                enable = true;
            }
        });
        $scope.description.vars.lAdding = enable;
    }

    function validateAll() {
        var contentHM = $scope.description.vars.contentHM;
        angular.forEach(contentHM, function (aContent, cCode) {
            if (aContent.registered) {
                validatePackageName(aContent.packageName, cCode);
                validateMD(aContent.multiDescriptions, cCode);

                initLocation(aContent);
                validateLocation(aContent.activityLocations, cCode);

                initKW(aContent);
                validateKW(aContent.keywords, cCode);

                validatesDes(aContent.googleDescription, cCode);
            }
        });

        initSerLan();
        validateSerLan($scope.description.vars.sLans);

        validateDescriptionLanguage();
        detectEnableAddingLanguage();
    }

    $scope.registration.vars.steps.description.load = function () {
        var registrationVars = $scope.registration.vars;
        registrationVars.loaded = false;

        var vars = $scope.description.vars;
        vars.contentHM = null;

        var packageID = registrationVars.packageID;
        var data = {"packageID": packageID, "localeCode": $scope.youtripper.vars.data.currentLan};

        $http.post("/partner/package-registration/description/data", data)
                .then(function successCallback(response) {
                    var responseData = response.data;
                    if (angular.isObject(responseData)) {
                        console.log(responseData);

                        var desData = vars.DATA;
                        desData.descriptionLanguages = responseData.descriptionLanguages;
                        desData.servingLanguages = responseData.ytServingLanguages;

                        vars.contentHM = responseData.contentHM;
                        vars.keywordHM = responseData.keywordHM;
                        vars.sLans = responseData.servingLanguages;
                        vars.enableEditing = !responseData.disableEditing;

                        aic = responseData.activityMarker;
                        dic = responseData.departureMarker;
                        pic = responseData.pickupMarker;

                        validateAll();
                        inM();
                        inEM();
                        registrationVars.loaded = true;
                    }
                });
    };
    /* END COMMON */
});