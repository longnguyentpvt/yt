/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

app.controller('PackageRegistration', function ($scope, $http, $document, $interval) {
    $scope.registration = {
        "vars": {
            "loaded": false,
            "noDones": 0,
            "steps": {
                "category": {
                    "active": false,
                    "done": false,
                    "load": null,
                    "checkURL": "/partner/package-registration/category/check",
                    "loaded": false,
                    "saving": false
                },
                "description": {
                    "active": false,
                    "done": false,
                    "load": null,
                    "checkURL": "/partner/package-registration/description/check",
                    "loaded": false,
                    "saving": false
                },
                "photo": {
                    "active": false,
                    "done": false,
                    "load": null,
                    "checkURL": "/partner/package-registration/photo/check",
                    "loaded": false,
                    "saving": false
                },
                "price": {
                    "active": false,
                    "done": false,
                    "loaded": false,
                    "saving": false
                },
                "availability": {
                    "active": false,
                    "done": false,
                    "loaded": false,
                    "saving": false
                }
            },
            "currentStep": null,
            "popups": {
                "stepSkipping": {
                    "creating": {
                        "active": false,
                        "complete": null
                    },
                    "active": false,
                    "step": null
                }
            },
            "done": false,
            "packageID": packageID
        },
        "funcs": {
            "openStepSkippingConfirmation": null,
            "openEditingSkippingConfirmation": null,
            "changeStep": null,
            "skipStep": null,
            "checkA": null
        }
    };

    $scope.registration.funcs.openStepSkippingConfirmation = function (step) {
        var popup = $scope.registration.vars.popups.stepSkipping;

        popup.step = step;

        // open popup
        $scope.POPUP.openPopup(popup.creating);
    };

    $scope.registration.funcs.openEditingSkippingConfirmation = function (step) {
        var popup = $scope.registration.vars.popups.stepSkipping;

        popup.step = step;

        // open popup
        $scope.POPUP.openPopup(popup.editing);
    };


    $scope.registration.funcs.changeStep = function (selectedStep) {
        var vars = $scope.registration.vars;
        var steps = vars.steps;
        var popup = vars.popups.stepSkipping;

        if (!selectedStep) {
            selectedStep = popup.step;
        }

        var aSelectedStep = steps[selectedStep];
        aSelectedStep.active = true;
        aSelectedStep.load();

        if (vars.currentStep !== null) {
            steps[vars.currentStep].active = false;
        }
        vars.currentStep = selectedStep;

        // close popup
        $scope.POPUP.closePopup(popup.creating);

        $(window).scrollTop(0);
    };

    var stT;
    $scope.registration.funcs.skipStep = function (selectedStep, lastStep) {
        var vars = $scope.registration.vars;
        var steps = vars.steps;

        var currentStep = vars.currentStep;
        if (selectedStep !== currentStep) {
            vars.loaded = false;

            stT = $interval(function () {
                var aStep = steps[currentStep];
                if (!aStep.saving) {
                    $interval.cancel(stT);
                    var checkURL = aStep.checkURL;
                    var data = {"packageID": vars.packageID};
                    $http.post(checkURL, data)
                            .then(function successCallback(response) {
                                var responseData = response.data;
                                if (angular.isObject(responseData)) {
                                    aStep.done = responseData.success;
                                    if (aStep.done) {
                                        $scope.registration.funcs.changeStep(selectedStep);
                                    } else {
                                        $scope.registration.funcs.openStepSkippingConfirmation(selectedStep);
                                    }

                                    vars.loaded = true;
                                }
                            });
                }
            }, 100);
        }
    };

    $scope.registration.vars.popups.stepSkipping.creating.complete = function () {
        var vars = $scope.registration.vars;
        var step = vars.steps[vars.currentStep];

        step.load();
        $(window).scrollTop(0);
        $scope.POPUP.closePopup(vars.popups.stepSkipping.creating);
    };


    $scope.registration.funcs.checkA = function () {
        var vars = $scope.registration.vars;
        var steps = vars.steps;
        var pI = vars.packageID;
        var data = {"packageID": pI};
        var to = steps.length;
        var i = 0;
        angular.forEach(steps, function (aStep, sN) {
            let checkURL = aStep.checkURL;
            if (angular.isString(checkURL) && checkURL.length) {
                $http.post(checkURL, data)
                        .then(function successCallback(response) {
                            var responseData = response.data;
                            if (angular.isObject(responseData)) {
                                aStep.done = responseData.success;
                                i++;
                                if (i === to) {
                                    console.log("done");
                                }
                            }
                        });
            }
        });
    };

    $document.ready(function () {
        $scope.registration.funcs.checkA();
        $scope.registration.funcs.changeStep("photo");
    });
});

