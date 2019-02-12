/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
app.controller('PartnerAccountCertification', function ($scope, $http) {
    $scope.certification = {
        "vars": {
            "cal": {
                "docs": null,
                "loading": true,
                "img": null,
                "error": false,
                "uerror": false //              
            },
            "pops": {
                "dcf": {// delete confirm popup
                    "active": false,
                    "img": null, // file id
                    "lding": false
                },
                "upop": {// uploading popup
                    "MIN_WIDTH": 400,
                    "MIN_HEIGHT": 400,
                    "MAX_SIZE": 2100000,
                    "FILE_TYPE": {
                        "jpg": "image/jpeg",
                        "png": "image/png",
                    },
                    "na": null,
                    "active": false,
                    "fn": null,
                    "img": null,
                    "lding": false,
                    "fnError": false, // file name
                    "sError": false, // size of file < 2mb
                    "dError": false, // demension error 400 400
                    "uError": false, // not upload
                    "bError": false,
                    "burl": "initial"
                }
            }
        },
        "funcs": {
            "bi": null, // browse image
            "oup": null, // open popup uploading
            "sud": null, // save uploadig popup
            "odc": null, // open deleting confirm,
            "cdp": null, // continue delete in deleting popup confirm
            "giu": null, // get image upload
            "cup": null, // close upload popup
            "cdlp": null // close upload delete popup
        }
    };

    // function to load certificate
    function loadc() {
        var cal = $scope.certification.vars.cal;
        cal.loading = true;
        $http.post("/partner/account-certificate/loadCertificate", {})
                .then(function successCallback(response) {
                    cal.docs = response.data;
                    cal.loading = false;
                });
    }

    // function to open popup uploading
    $scope.certification.funcs.oup = function () {
        var setup = $scope.certification.vars.pops;
        $scope.POPUP.openPopup(setup.upop);
    };

    // get image from machine
    $scope.certification.funcs.bi = function () {
        $('#certificationImage').trigger('click');
    };

    // close upload popup
    $scope.certification.funcs.cup = function () {
        var popup = $scope.certification.vars.pops.upop;
        //personal
        popup.fn = null;
        popup.na = null;
        popup.img = null;
        popup.sError = false;
        popup.dError = false;
        popup.fnError = false;
        popup.uError = false;
        popup.bError = false;
        popup.burl = "initial";
        popup.tn = null;
        init();
        $scope.POPUP.closePopup(popup);
    };


    //get image upload
    $scope.certification.funcs.giu = function () {
        var popup = $scope.certification.vars.pops.upop;
        popup.sError = false;
        popup.dError = false;
        popup.fnError = false;
        popup.uError = false;
        popup.bError = false;
        popup.img = null;
        popup.burl = "initial";
        popup.tn = null;
        //
        var file = popup.na;
        popup.fty = file.type;
        if (file) {
            if (popup.fty === popup.FILE_TYPE.jpg || popup.fty === popup.FILE_TYPE.png) {
                var img = new Image();
                img.onload = function () {
                    // get dimension
                    popup.lding = true;
                    $scope.$digest();
                    var width = this.width;
                    var height = this.height;
                    var size = file.size;
                    // ERROR SIZE
                    if (Number(size) >= popup.MAX_SIZE) {
                        popup.sError = true;
                        popup.lding = false;
                        $scope.$digest();
                    }
                    // DEMESION ERROR
                    if (width < popup.MIN_WIDTH || height < popup.MIN_HEIGHT) {
                        popup.dError = true;
                        popup.lding = false;
                        $scope.$digest();
                    }
                    if (!popup.sError && !popup.dError) {
                        $http({method: 'POST',
                            url: "/partner/account-certificate/uploadImage",
                            headers: {
                                'Content-Type': undefined
                            },
                            data: {
                                "certificationImage": file
                            },
                            transformRequest: function (data, headersGetter) {
                                var formData = new FormData();
                                angular.forEach(data, function (value, key) {
                                    formData.append(key, value);
                                });
                                return formData;
                            }
                        }).then(function successCallback(response) {
                            var rData = response.data.result;
                            if (rData !== null) {
                                popup.img = rData[0];
                                popup.burl = ('url(' + rData[1] + ')');
                            } else {
                                popup.bError = true;
                            }
                            popup.lding = false;

                        });
                    }
                };
                img.src = (window.URL || window.webkitURL).createObjectURL(file);
            }
        }
        $("#certificationImage").val("");
    };

    // function to save uploading popup
    $scope.certification.funcs.sud = function () {
        // check type
        // if type is personal call function upsn
        // if type is bank call function ubank
        var popup = $scope.certification.vars.pops.upop;
        popup.sError = false;
        popup.dError = false;
        popup.fnError = false;
        popup.uError = false;
        // NO IMAGE
        if (!angular.isString(popup.img) || !popup.img.length) {
            popup.uError = true;
        }
        // NOT INPUT FILE NAME
        if (!angular.isString(popup.fn) || !popup.fn.length) {
            popup.fnError = true;
        }
        var rqdt = {"fileName": popup.fn, "imageFile": popup.img};
        if (!popup.uError && !popup.fnError) {
            var vars = $scope.certification.vars.pops.upop;
            vars.lding = true;
            $http.post("/partner/account-certificate/saveImg", rqdt)
                    .then(function successCallback(response) {
                        var result = response.data.result;
                        vars.lding = false;
                        $scope.certification.funcs.cup();
                    });
        }


    };
    //open delte POPUP
    $scope.certification.funcs.odc = function (imageID) {
        // set type and img id
        // open popu
        var setup = $scope.certification.vars.pops;
        // set type
        setup.dcf.img = imageID;
        // open popup
        $scope.POPUP.openPopup(setup.dcf);
    };

    // close delete popup
    $scope.certification.funcs.cdlp = function () {
        var popup = $scope.certification.vars.pops.dcf;
        //personal
        popup.ty = null;
        popup.img = null;
        init();
        $scope.POPUP.closePopup(popup);
    };

    // function delete certìicate
    function dpsn(data) {
        var vars = $scope.certification.vars.pops.dcf;
        vars.lding = true;
        $http.post("/partner/account-certificate/deleteImg", data)
                .then(function successCallback(response) {
                    var result = response.data.result;
                    vars.lding = false;
                    $scope.certification.funcs.cdlp();
                });
    }

    // function to continue delete
    $scope.certification.funcs.cdp = function () {
        var setup = $scope.certification.vars.pops.dcf;
        var rqdt = {"fileID": setup.img};
        dpsn(rqdt);
    };

    // function init
    function init() {
        loadc();
    }

    init();
});

