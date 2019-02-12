/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
app.controller('PartnerRegulars', function ($scope, $http, $q, $document) {
    /* COMMON */
    $scope.trippdashSection = servingType;
    $scope.regulars = {
        "vars": {
            "filterURL": "/partner/filterPartnerRegularPackage",
            "deleteURL": "/partner/delete-package",
            "turnOffURL": "/partner/turningoff",
            "turnOnURL": "/partner/turningon",
            "editURL": "/partner/edit-package",
            "cancelDeletingPackageURL": "/partner/cancelDeletingPackage",
            "cloneURL": "/partner/clone-package",
            "cancelEditingURL": "/partner/cancel-package-editing",
            "ytPackages": null,
            "deletingPopup": {
                "active": false,
                "selectedPackage": null
            },
            "editingPopup": {
                "active": false,
                "selectedPackage": null,
                "editPrimaryPackage": null,
                "editingOption": false
            },
            "clonePopup": {
                "active": false,
                "selectedPackage": null,
                "all": false,
                "category": false,
                "description": false,
                "photos": false,
                "price": false,
                "availability": false,
                "selectAllOptionFunc": null,
                "selectOtherOptionFunc": null

            }
        },
        "funcs": {
            "filter": null,
            "edit": null,
            "clone": null,
            "turnOn": null,
            "turnOff": null,
            "deleteFunc": null,
            "cancelEditing": null,
            "cancelDeleting": null,
            "getPrice": null
        }
    };

    //define Math 
    $scope.Math = window.Math;

    // open option
    $(document).on('click', ".aPackage .status .options .icon", function () {
        // close another options first
        $(".aPackage .status .options.active").removeClass("active");
        // open option
        $(this).parent().addClass("active");
    });

    // close option
    $(document).on('click', function (event) {
        if (!$(event.target).is(".aPackage .status .options .icon") &&
                !$(event.target).closest(".aPackage .status .options .icon").length) {
            // close another options first
            $(".aPackage .status .options.active").removeClass("active");
        }
    });

    /* END COMMON */

    /* FILTER */
    $scope.filter = {
        "vars": {
            "searchTxt": "",
            "sortCri": "youngest",
            "statusCri": "",
            "servingType": servingType,
            "defer": $q.defer(),
            "loading": false
        },
        "funcs": {
        }
    };
    /* END FILTER */

    //Filter Package
    $scope.regulars.funcs.filter = function () {
        var filter = $scope.filter.vars;
        if (filter.loading) {
            filter.defer.resolve();
            filter.defer = $q.defer();
        }
        filter.loading = true;

        var data = {
            "packageName": filter.searchTxt,
            "sortCri": filter.sortCri,
            "statusCri": filter.statusCri,
            "servingType": filter.servingType
        };

        $http.post("/partner/regular-packages/filter", data, {timeout: filter.defer.promise})
                .then(function successCallback(response) {
                    // get result
                    var result = response.data;
                    if (angular.isObject(result)) {
                        console.log(result);
                        $scope.regulars.vars.ytPackages = result;
                        filter.defer = $q.defer();
                        filter.loading = false;
                    }
                });
    };

    $scope.regulars.funcs.getPrice = function (prices, currencyCode) {
        var aPrice = prices[currencyCode];
        if (!angular.isDefined(aPrice)) {
            return {
                "promotionPercent": 0,
                "pkgPrice": 0,
                "paidPrice": 0
            };
        }
        return aPrice;
    };

    //request delete primary package
    $scope.regulars.funcs.deleteFunc = function ($event) {
        var vars = $scope.regulars.vars;
        var filter = $scope.filter.vars;
        $scope.POPUP.closePopup(vars.deletingPopup, $event);
        filter.loading = true;
        var data = {"packageID": vars.deletingPopup.selectedPackage};
        $http.post(vars.deleteURL, data)
                .then(function successCallback(response) {
                    //reload data
                    $scope.regulars.funcs.filter();
                });
    };
    //turn off package
    $scope.regulars.funcs.turnOff = function (packageID) {
        var vars = $scope.regulars.vars;
        $scope.filter.vars.loading = true;
        var data = {"packageID": packageID};
        $http.post(vars.turnOffURL, data)
                .then(function successCallback(response) {
                    //reload data
                    $scope.regulars.funcs.filter();
                });
    };

    //turn on package
    $scope.regulars.funcs.turnOn = function (packageID) {
        var vars = $scope.regulars.vars;
        $scope.filter.vars.loading = true;
        var data = {"packageID": packageID};
        $http.post(vars.turnOnURL, data)
                .then(function successCallback(response) {
                    //reload data
                    $scope.regulars.funcs.filter();
                });
    };

    //package editing 
    $scope.regulars.funcs.edit = function (aPackage) {
        if (aPackage.status === 'approved') {
            $scope.regulars.vars.editingPopup.selectedPackage = aPackage;
            $scope.POPUP.openPopup($scope.regulars.vars.editingPopup);
        } else {
            var servingT = $scope.filter.vars.servingType;
            if (servingT === 'regular') {
                $(location).attr('href', "/partner/package-registration/regular/" + aPackage.tempID);
            } else if (servingT === '') {
//                            $(location).attr('href', "/package-registration/regular/" + returnID);
            }
        }
    };

    //edit primary package
    $scope.regulars.vars.editingPopup.editPrimaryPackage = function () {

        var vars = $scope.regulars.vars;
        //close popup
        $scope.POPUP.closePopup(vars.editingPopup);
        //loading
        $scope.filter.vars.loading = true;
        var data = {"packageID": vars.editingPopup.selectedPackage.packageID, "deletePackage": vars.editingPopup.editingOption};
        $http.post(vars.editURL, data)
                .then(function successCallback(response) {
                    var result = response.data;
                    //redirect to editing package page
                    window.location.href = "/partner/registration/regular/" + result.returnURL;
                });
    };

    //cancel package editing package
    $scope.regulars.funcs.cancelEditing = function (packageID) {
        var vars = $scope.regulars.vars;
        $scope.filter.vars.loading = true;
        var data = {"temppackageID": packageID, "packageType": "regular"};
        $http.post(vars.cancelEditingURL, data)
                .then(function successCallback(response) {
                    $scope.filter.vars.loading = false;
                    //reload data
                    $scope.regulars.funcs.filter();
                });
    };

    //cancel deleting package
    $scope.regulars.funcs.cancelDeleting = function (packageID) {
        var vars = $scope.regulars.vars;
        $scope.filter.vars.loading = true;
        var data = {"packageID": packageID};
        $http.post(vars.cancelDeletingPackageURL, data)
                .then(function successCallback(response) {
                    //reload data
                    $scope.regulars.funcs.filter();
                });
    };

    $scope.regulars.funcs.clone = function ($event) {

        var vars = $scope.regulars.vars;

        //check if data is valid
        if (vars.clonePopup.all || vars.clonePopup.category || vars.clonePopup.description || vars.clonePopup.photos || vars.clonePopup.price
                || vars.clonePopup.availability) {
            $scope.filter.vars.loading = true;
            $scope.POPUP.closePopup(vars.clonePopup);
            var data = {"packageID": vars.clonePopup.selectedPackage.packageID, "tempPackageID": vars.clonePopup.selectedPackage.tempPackageID,
                "cloneAll": vars.clonePopup.all, "cloneCategory": vars.clonePopup.category, "cloneDescription":
                        vars.clonePopup.description, "clonePhotos": vars.clonePopup.photos, "clonePrice": vars.clonePopup.price, "cloneAvailability":
                        vars.clonePopup.availability};
            $http.post(vars.cloneURL, data)
                    .then(function successCallback(response) {
                        var data = response.data;
                        if (data.result === 'success') {
                            //reload data
                            window.location.href = data.returnURL;
                        } else { // package exceed maximum number                         
                            $scope.POPUP.openPopup($scope.registration.vars.overRegistration);
                            vars.editingPopup.active = false;
                            $scope.filter.vars.loading = false;

                        }
                    });
        }

    };

    $scope.regulars.vars.clonePopup.selectAllOptionFunc = function () {
        var cloneOption = $scope.regulars.vars.clonePopup;
        if (cloneOption.all === true) { //if all option selected then turn off other options
            cloneOption.all = true;
            cloneOption.category = false;
            cloneOption.description = false;
            cloneOption.photos = false;
            cloneOption.price = false;
            cloneOption.availability = false;
        } else { //turn off all option also turn off other options
            cloneOption.all = false;
            cloneOption.category = false;
            cloneOption.description = false;
            cloneOption.photos = false;
            cloneOption.price = false;
            cloneOption.availability = false;
        }
    };

    $scope.regulars.vars.clonePopup.selectOtherOptionFunc = function () {
        var cloneOption = $scope.regulars.vars.clonePopup;
        //turn off all option
        cloneOption.all = false;
    };

    /* PACKAGE CREATING */
    $scope.registration = {
        "vars": {
            "overRegistration": {
                "active": false
            },
            "loading": false
        },
        "funcs": {
            "registration": null
        }
    };

    $scope.registration.funcs.registration = function () {
        var vars = $scope.registration.vars;
        vars.loading = true;
        $http.post("/partner/package-registration", {"servingType": $scope.filter.vars.servingType})
                .then(function successCallback(response) {
                    // get result
                    var responseData = response.data;
                    if (angular.isObject(responseData)) {
                        var returnID = responseData.packageID;
                        var servingT = $scope.filter.vars.servingType;

                        if (servingT === 'regular') {
                            $(location).attr('href', "/partner/package-registration/regular/" + returnID);
                        } else if (servingT === '') {
//                            $(location).attr('href', "/package-registration/regular/" + returnID);
                        }
                    }
//                    else {
//                        $scope.POPUP.openPopup(vars.overRegistration);
//                        vars.loading = false;
//                    }
                });
    };
    /* END PACKAGE CREATING */

    $document.ready(function () {
        $scope.regulars.funcs.filter();
    });
});