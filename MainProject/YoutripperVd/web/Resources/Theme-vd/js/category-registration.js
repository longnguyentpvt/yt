/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


app.controller('CategoryRegistration', function ($scope, $q, $http, $timeout) {
    /* COMMON */
    $scope.category = {
        "vars": {
            "questions": {
                "category": {
                    "subs": [],
                    "defer": $q.defer(),
                    "updating": false,
                    "mandatory": true,
                    "done": false,
                    "otherPromise": null,
                    "save": null,
                    "validate": null,
                    "init": null,
                    "categoryChanged": null,
                    "subCategoryChanged": null,
                    "othersCategoryChanged": null,
                    "changed": null,
                    "getSub": null
                },
                "suitability": {
                    "defer": $q.defer(),
                    "promise": null,
                    "updating": false,
                    "mandatory": true,
                    "done": false,
                    "save": null,
                    "validate": null,
                    "init": null,
                    "options": {
                        "couples": false,
                        "elderly": false,
                        "family": false,
                        "idvd": false,
                        "uni": false
                    },
                    "changed": null
                },
                "gender": {
                    "defer": $q.defer(),
                    "promise": null,
                    "updating": false,
                    "mandatory": true,
                    "done": false,
                    "save": null,
                    "validate": null,
                    "init": null,
                    "changed": null
                },
                "target": {
                    "defer": $q.defer(),
                    "promise": null,
                    "updating": false,
                    "mandatory": false,
                    "done": true,
                    "save": null,
                    "validate": null,
                    "init": null,
                    "changed": null
                },
                "color": {
                    "defer": $q.defer(),
                    "promise": null,
                    "updating": false,
                    "mandatory": true,
                    "done": false,
                    "save": null,
                    "validate": null,
                    "init": null,
                    "ddl": {
                        "active": null,
                        "color": null,
                        "open": null,
                        "close": null
                    },
                    "changed": null
                }
            },
            "ytPackage": null,
            "colors": null,
            "locations": null,
            "data": {
                "CATEGORIES": {
                    "attractions": "attractions",
                    "learning": "learning",
                    "indoor": "indoor",
                    "outdoor": "outdoor",
                    "extreme": "extreme-sport",
                    "water": "water-sport",
                    "spa": "spa-wellness"
                },
                "SUB_OTHER_ID": "others"
            }
        },
        "funcs": {
            "load": null,
            "initAll": null
        }
    };


    $scope.category.funcs.initAll = function () {
        var questions = $scope.category.vars.questions;
        angular.forEach(questions, function (question, name) {

            if (question.init !== null) {
                question.init();
            }

            if (question.validate !== null) {
                question.validate();
            }
        });

    };

    $scope.registration.vars.steps.category.load = function () {
        var registrationVars = $scope.registration.vars;
        registrationVars.loaded = false;

        var vars = $scope.category.vars;
        vars.ytPackage = null;
        vars.questions.category.subs.length = 0;

        var packageID = registrationVars.packageID;
        var data = {"packageID": packageID, "localeCode": $scope.youtripper.vars.data.currentLan};

        $http.post("/partner/package-registration/category/data", data)
                .then(function successCallback(response) {
                    var responseData = response.data;
                    if (angular.isObject(responseData)) {
                        vars.ytPackage = responseData.ytPackage;

                        vars.colors = responseData.pkgColors;
                        vars.locations = responseData.targetLocations;

                        // then validate
                        $scope.category.funcs.initAll();

                        registrationVars.loaded = true;
                    }
                });
    };
    /* END COMMON */

    /* CATEGORY QUESTION */
    var gettingSubPromise = $q.defer(), gettingSubLoading = false;
    $scope.category.vars.questions.category.getSub = function (categoryID) {
        var vars = $scope.category.vars;

        if (gettingSubLoading) {
            gettingSubPromise.resolve();
            gettingSubPromise = $q.defer();
        }
        gettingSubLoading = true;
        var data = {"categoryID": categoryID, "localeCode": $scope.youtripper.vars.data.currentLan};
        $http.post("/partner/package-registration/category/sub-category-loading", data, {timeout: gettingSubPromise.promise})
                .then(function successCallback(response) {
                    var responseData = response.data;
                    if (angular.isObject(responseData)) {
//                        vars.questions.category.subs.length = 0;

//                        for (var i = 0; i < responseData.length; i++) {
//                            vars.questions.category.subs.push(responseData[i]);
//                        }
                        $scope.category.vars.questions.category.subs = responseData;
                        gettingSubPromise = $q.defer();
                        gettingSubLoading = false;
                    }
                });

    };

    $scope.category.vars.questions.category.changed = function () {
        $scope.category.vars.questions.category.validate();

        $scope.category.vars.questions.category.save();
    };

    $scope.category.vars.questions.category.categoryChanged = function (categoryID) {
        var vars = $scope.category.vars;
        var question = vars.questions.category;
        question.getSub(categoryID);

        var ytPackage = vars.ytPackage;
        ytPackage.subCategoryID = null;
        ytPackage.otherSubCategory = null;
        ytPackage.categoryID = categoryID;

        question.changed(true);
    };

    $scope.category.vars.questions.category.subCategoryChanged = function () {
        var vars = $scope.category.vars;

        var ytPackage = vars.ytPackage;
        ytPackage.otherSubCategory = null;

        // validate
        vars.questions.category.changed();
    };

    $scope.category.vars.questions.category.othersCategoryChanged = function () {
        $scope.category.vars.questions.category.changed(true);
    };

    $scope.category.vars.questions.category.init = function () {
        var vars = $scope.category.vars;
        var ytPackage = vars.ytPackage;
        vars.questions.category.getSub(ytPackage.categoryID);
    };

    $scope.category.vars.questions.category.validate = function () {
        var vars = $scope.category.vars;
        var ytPackage = vars.ytPackage;

        var done = false;
        if (ytPackage.categoryID !== null && ytPackage.subCategoryID !== null) {
            if (ytPackage.subCategoryID === vars.data.SUB_OTHER_ID) {
                if (angular.isString(ytPackage.otherSubCategory) &&
                        ytPackage.otherSubCategory.trim().length) {
                    done = true;
                }
            } else {
                done = true;
            }
        }
        vars.questions.category.done = done;
    };

    $scope.category.vars.questions.category.save = function () {
        var vars = $scope.category.vars;
        var question = vars.questions.category;
        $timeout.cancel(question.otherPromise);

        $scope.registration.vars.steps.category.saving = true;
        question.otherPromise = $timeout(function () {
            question.updating = true;

            var ytPackage = vars.ytPackage;
            var data = {
                "packageID": $scope.registration.vars.packageID,
                "categoryID": ytPackage.categoryID,
                "subCategoryID": ytPackage.subCategoryID,
                "otherSubCategory": ytPackage.otherSubCategory
            };

            $http.post("/partner/package-registration/category/category-updating", data)
                    .then(function successCallback(response) {
                        if (angular.isObject(response.data)) {
                            $scope.registration.vars.steps.category.saving = false;
                            question.updating = false;
                            console.log("Category Saved");
                        }
                    });
        }, 500);
    };
    /* END CATEGORY QUESTION */

    /* SUITABILITY */
    $scope.category.vars.questions.suitability.validate = function () {
        var vars = $scope.category.vars;
        var suitabilityQuestion = vars.questions.suitability;
        var ytPackage = vars.ytPackage;

        suitabilityQuestion.done = ytPackage.suitableCouples || ytPackage.suitableElderly || ytPackage.suitableFamily ||
                ytPackage.suitableIndividual || ytPackage.suitableUniversal;

        var gender = $scope.category.vars.questions.gender;

        gender.mandatory = ytPackage.suitableIndividual;
        if (gender.mandatory) {
            gender.done = angular.isString(ytPackage.genderSuitability);
        }
    };

    $scope.category.vars.questions.suitability.changed = function (singleSwitched, invidualSwitched) {
        var vars = $scope.category.vars;
        var suitabilityQuestion = vars.questions.suitability;

        var ytPackage = vars.ytPackage;
        if (singleSwitched) {
            ytPackage.suitableUniversal = false;
        } else {
            if (ytPackage.suitableUniversal) {
                ytPackage.suitableCouples = false;
                ytPackage.suitableElderly = false;
                ytPackage.suitableFamily = false;
                ytPackage.suitableIndividual = false;
            }
        }

        if (invidualSwitched) {
            ytPackage.genderSuitability = null;
        }

        suitabilityQuestion.save();
        // validate
        suitabilityQuestion.validate();
    };

    $scope.category.vars.questions.suitability.save = function () {
        var vars = $scope.category.vars;
        var question = vars.questions.suitability;
        $timeout.cancel(question.promise);
        $scope.registration.vars.steps.category.saving = true;

        question.promise = $timeout(function () {
            question.updating = true;

            var ytPackage = vars.ytPackage;
            var data = {
                "packageID": $scope.registration.vars.packageID,
                "suitableCouples": ytPackage.suitableCouples,
                "suitableElderly": ytPackage.suitableElderly,
                "suitableFamily": ytPackage.suitableFamily,
                "suitableIndividual": ytPackage.suitableIndividual,
                "suitableUniversal": ytPackage.suitableUniversal,
                "genderSuitability": ytPackage.genderSuitability
            };

            $http.post("/partner/package-registration/category/suitability-updating", data)
                    .then(function successCallback(response) {
                        if (angular.isObject(response.data)) {
                            $scope.registration.vars.steps.category.saving = false;
                            question.updating = false;
                            console.log("suitability saved");
                        }
                    });
        }, 500);
    };
    /* END SUITABILITY */

    /* TARGET */
    $scope.category.vars.questions.target.save = function () {
        var vars = $scope.category.vars;
        var question = vars.questions.target;
        $timeout.cancel(question.promise);

        $scope.registration.vars.steps.category.saving = true;

        question.promise = $timeout(function () {
            question.updating = true;

            var ytPackage = vars.ytPackage;
            var data = {
                "packageID": $scope.registration.vars.packageID,
                "targetLocationID": ytPackage.targetLocationID
            };

            $http.post("/partner/package-registration/category/target-location-updating", data)
                    .then(function successCallback(response) {
                        if (angular.isObject(response.data)) {
                            $scope.registration.vars.steps.category.saving = false;
                            question.updating = false;
                            console.log("Target Saved");
                        }
                    });
        }, 500);
    };

    $scope.category.vars.questions.target.changed = function () {
        var question = $scope.category.vars.questions.target;

        question.save();
    };
    /* END TARGET */

    /* COLOR */
    $scope.category.vars.questions.color.ddl.open = function () {
        var ddl = $scope.category.vars.questions.color.ddl;
        ddl.active = true;
    };
    $scope.category.vars.questions.color.ddl.close = function () {
        var ddl = $scope.category.vars.questions.color.ddl;
        ddl.active = false;
    };

    // close
    $(document).on('click', function (event) {
        if ($("#category-step .color").hasClass("active") && !$(event.target).is("#category-step .color.active .ddl") &&
                !$(event.target).closest("#category-step .color.active .ddl").length) {
            $scope.category.vars.questions.color.ddl.close();
            $scope.$digest();
        }
    });

    $scope.category.vars.questions.color.init = function () {
        var vars = $scope.category.vars;
        var ytPackage = vars.ytPackage;

        if (ytPackage.colorCode) {
            vars.questions.color.ddl.color = vars.colors[ytPackage.colorCode].hexCode;
        }
    };

    $scope.category.vars.questions.color.validate = function () {
        var vars = $scope.category.vars;
        var ytPackage = vars.ytPackage;
        var colorQuestion = vars.questions.color;
        if (ytPackage.colorCode) {
            colorQuestion.done = true;
        } else {
            colorQuestion.done = false;
        }
    };

    $scope.category.vars.questions.color.save = function () {
        var vars = $scope.category.vars;
        var question = vars.questions.color;

        $timeout.cancel(question.promise);
        $scope.registration.vars.steps.category.saving = true;

        question.promise = $timeout(function () {
            question.updating = true;

            var ytPackage = vars.ytPackage;
            var data = {
                "packageID": $scope.registration.vars.packageID,
                "colorCode": ytPackage.colorCode
            };

            $http.post("/partner/package-registration/category/color-updating", data)
                    .then(function successCallback(response) {
                        if (angular.isObject(response.data)) {
                            $scope.registration.vars.steps.category.saving = false;
                            question.updating = false;
                            console.log("Color Saved");
                        }
                    });
        }, 500);
    };

    $scope.category.vars.questions.color.changed = function (code, hexCode) {
        var vars = $scope.category.vars;
        var question = vars.questions.color;

        var colorDDL = question.ddl;
        var ytPackage = vars.ytPackage;
        ytPackage.colorCode = code;
        colorDDL.color = hexCode;

        question.save();
        question.validate();
    };
    /* END COLOR */
});