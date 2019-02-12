/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
app.controller('PartnerAccountVerification', function ($scope, $http) {
    $scope.verification = {
        "vars": {
            "psn": {
                "docs": null,
                "vid": null, // Verfication ID
                "loading": true,
                "img": null,
                "cdnLink": null,
                "isS": false, // is submited?
                "error": false,
                "noDError": false,
                "popup": {
                    "active": false,
                    "loading": false
                },
                "type": null
            },
            "bank": {
                "docs": null,
                "bid": null, // Bank ID
                "bna": null, // Bank Name
                "bno": null, // Bank Number
                "loading": true,
                "cdnLink": null,
                "isS": false, // is submited?
                "errorBid": false,
                "errorBna": false,
                "errorBno": false,
                "noDError": false,
                "popup": {
                    "active": false,
                    "loading": false
                },
                "type": null

            },
            "pops": {
                "dcf": {// delete confirm popup
                    "active": false,
                    "ty": null, // type psn or bank
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
                        "pdf": "application/pdf"
                    },
                    "type": null, // psn or bank
                    "na": null,
                    "active": false,
                    "fn": null,
                    "tn": null, // TEMP NAME
                    "img": null,
                    "fty": null, // file type
                    "lding": false,
                    "linkPDF": null,
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
            "smpsn": null, // funciton submit personal
            "smbank": null, // function submit bank
            "giu": null, // get image upload
            "cup": null, // close upload popup
            "cdlp": null, // close upload delete popup,
            "ospp": null,
            "osbp": null
        }
    };
    // function to load psn
    function loadpsn(isLoadingDoc) {
        var psn = $scope.verification.vars.psn;
        psn.loading = true;
        $http.post("/partner/account-verification/loadpsndata", {})
                .then(function successCallback(response) {
                    var resultData = response.data;
                    psn.docs = resultData.files;
                    if (isLoadingDoc) {
                        psn.vid = resultData.verificationID;
                        psn.isS = resultData.isPSNSubmited;
                    }
                    psn.loading = false;
                });
    }
    // function to load bank
    function loadbank(isLoadingDoc) {
        // loadbankdata
        var bank = $scope.verification.vars.bank;
        bank.loading = true;
        $http.post("/partner/account-verification/loadbankdata", {})
                .then(function successCallback(response) {
                    var resultData = response.data;
                    //bank
                    bank.docs = resultData.files;
                    if (isLoadingDoc) {
                        bank.bid = resultData.bankID;
                        bank.bna = resultData.bankAccountName;
                        bank.bno = resultData.bankAccountNumber;
                        bank.isS = resultData.isBankSubmited;
                    }
                    bank.loading = false;
                });
    }

    // function to open popup uploading
    $scope.verification.funcs.oup = function (type) {
        var setup = $scope.verification.vars.pops;
        // set type
        setup.upop.type = type;
        // open popup
        $scope.POPUP.openPopup(setup.upop);
    };

    // get image from machine
    $scope.verification.funcs.bi = function () {
        $('#verificationImage').trigger('click');
    };

    // close upload popup
    $scope.verification.funcs.cup = function () {
        var popup = $scope.verification.vars.pops.upop;
        //personal
        popup.fn = null;
        popup.na = null;
        popup.img = null;
        popup.sError = false;
        popup.dError = false;
        popup.fnError = false;
        popup.uError = false;
        popup.burl = "initial";
        popup.tn = null;
        popup.linkPDF = null;
        if (popup.type === 'personal') {
            loadpsn(false);
        } else if (popup.type === 'bank') {
            loadbank(false);
        }
        $scope.POPUP.closePopup(popup);
    };

    // function save personal uploading
    function upsn(data) {
        var vars = $scope.verification.vars.pops.upop;
        vars.lding = true;
        $http.post("/partner/account-verification/savePersonalImg", data)
                .then(function successCallback(response) {
                    var result = response.data.result;
                    vars.lding = false;
                    $scope.verification.funcs.cup();
                });
    }

    // function save bank uploading 
    function ubank(data) {
        var vars = $scope.verification.vars.pops.upop;
        vars.lding = true;
        $http.post("/partner/account-verification/saveBankImg", data)
                .then(function successCallback(response) {
                    var result = response.data.result;
                    vars.lding = false;
                    $scope.verification.funcs.cup();
                });
    }

    $scope.verification.funcs.giu = function () {
        var popup = $scope.verification.vars.pops.upop;
        popup.sError = false;
        popup.dError = false;
        popup.fnError = false;
        popup.uError = false;
        popup.bError = false;
        popup.img = null;
        popup.burl = "initial";
        popup.tn = null;
        popup.linkPDF = null;
        //
        var file = popup.na;
        popup.fty = file.type;
        var size = file.size;
        if (file) {
            popup.lding = true;

            if (popup.fty === popup.FILE_TYPE.jpg || popup.fty === popup.FILE_TYPE.png) {
                var img = new Image();
                img.onload = function () {
                    // get dimension
                    var width = this.width;
                    var height = this.height;
                    var size = file.size;

                    // DEMESION ERROR
                    if (width < popup.MIN_WIDTH || height < popup.MIN_HEIGHT) {
                        popup.dError = true;
                    }

                    // ERROR SIZE
                    if (Number(size) >= popup.MAX_SIZE) {
                        popup.sError = true;
                    }

                    if (!popup.dError && !popup.sError) {
                        $http({method: 'POST',
                            url: "/partner/account-verification/uploadImage",
                            headers: {
                                'Content-Type': undefined
                            },
                            data: {
                                "verificationImage": file
                            },
                            transformRequest: function (data, headersGetter) {
                                var formData = new FormData();
                                angular.forEach(data, function (value, key) {
                                    formData.append(key, value);
                                });
                                return formData;
                            }
                        }).then(function successCallback(response) {
                            var responseData = response.data.result;
                            if (responseData !== null) {
                                popup.img = responseData[0];
                                popup.burl = 'url(' + responseData[1] + ')';
                            } else {
                                popup.bError = true;
                            }
                            popup.lding = false;
                        });
                    } else {
                        popup.lding = false;
                        $scope.$digest();
                    }
                };
                img.src = (window.URL || window.webkitURL).createObjectURL(file);
            } else if (popup.fty === popup.FILE_TYPE.pdf) {
                popup.img = null;
                popup.lding = true;
                if (Number(size) < popup.MAX_SIZE) {
                    popup.tn = file.name;
                    $http({method: 'POST',
                        url: "/partner/account-verification/uploadImage",
                        headers: {
                            'Content-Type': undefined
                        },
                        data: {
                            "verificationImage": file
                        },
                        transformRequest: function (data, headersGetter) {
                            var formData = new FormData();
                            angular.forEach(data, function (value, key) {
                                formData.append(key, value);
                            });
                            return formData;
                        }
                    }).then(function successCallback(response) {
                        var responseData = response.data.result;
                        if (responseData !== null) {
                            popup.img = responseData[0];
                            popup.linkPDF = responseData[1];
                        } else {
                            popup.bError = true;
                        }
                        popup.lding = false;
                    });
                }
                // ERROR SIZE
                if (Number(size) >= popup.MAX_SIZE) {
                    popup.sError = true;
                    popup.lding = false;
                }
            }
        }
        $("#verificationImage").val("");
    };

    // function to save uploading popup
    $scope.verification.funcs.sud = function () {
        // check type
        // if type is personal call function upsn
        // if type is bank call function ubank
        var popup = $scope.verification.vars.pops.upop;
        popup.sError = false;
        popup.dError = false;
        popup.fnError = false;
        popup.uError = false;
        var rqdt = {"fileName": popup.fn, "imageFile": popup.img, "fileType": popup.fty};
        // NO IMAGE
        if (popup.img === null || popup.img === "") {
            popup.uError = true;
        }

        // NOT INPUT FILE NAME
        if (popup.fn === null || popup.fn === "") {
            popup.fnError = true;
        }
        if (!popup.uError && !popup.fnError) {
            if (popup.type === 'personal') {
                upsn(rqdt);
            } else {
                ubank(rqdt);
            }
        }
    };

    //open delte POPUP
    $scope.verification.funcs.odc = function (imageID, type) {
        // set type and img id
        // open popu
        var setup = $scope.verification.vars.pops;
        // set type
        setup.dcf.ty = type;
        setup.dcf.img = imageID;
        // open popup
        $scope.POPUP.openPopup(setup.dcf);
    };

    // close delete popup
    $scope.verification.funcs.cdlp = function () {
        var popup = $scope.verification.vars.pops.dcf;
        //personal
        popup.img = null;
        if (popup.ty === 'personal') {
            loadpsn(false);
        } else {
            loadbank(false);
        }
        $scope.POPUP.closePopup(popup);
    };

    // function delete personal
    function dpsn(data) {
        var vars = $scope.verification.vars.pops.dcf;
        vars.lding = true;
        $http.post("/partner/account-verification/deletePersonalImg", data)
                .then(function successCallback(response) {
                    var result = response.data.result;
                    vars.lding = false;
                    $scope.verification.funcs.cdlp();
                });
    }

    // function deleting bank
    function dbank(data) {
        var vars = $scope.verification.vars.pops.dcf;
        vars.lding = true;
        $http.post("/partner/account-verification/deleteBankImg", data)
                .then(function successCallback(response) {
                    var result = response.data.result;
                    vars.lding = false;
                    $scope.verification.funcs.cdlp();
                });
    }

    // function to continue delete
    $scope.verification.funcs.cdp = function () {
        var setup = $scope.verification.vars.pops.dcf;
        var rqdt = {"fileID": setup.img};
        // check type
        // if personal call dpsn
        // if bank call dbank
        if (setup.ty === 'personal') {
            dpsn(rqdt);
        } else {
            dbank(rqdt);
        }
    };

    // function to submit personal for approval
    $scope.verification.funcs.ospp = function (type) {
        var psn = $scope.verification.vars.psn;
        psn.error = false;
        psn.uerror = false;
        psn.type = type;
        psn.noDError = false;
        if (!angular.isString(psn.vid) || !psn.vid.length) {
            psn.error = true;
        }
        if (!psn.docs.length) {
            psn.noDError = true;
        }
        //validate before open pop up
        if (!psn.error && !psn.noDError) {
            $scope.POPUP.openPopup(psn.popup);
        }
    };

    $scope.verification.funcs.osbp = function (type) {
        var bank = $scope.verification.vars.bank;
        bank.type = type;
        bank.errorBna = false;
        bank.errorBid = false;
        bank.errorBno = false;
        bank.noDError = false;
        if (!angular.isString(bank.bna) || !bank.bna.length) {
            bank.errorBna = true;
        }
        if (!angular.isString(bank.bno) || !bank.bno.length) {
            bank.errorBno = true;
        }
        if (!angular.isString(bank.bid) || !bank.bid.length) {
            bank.errorBid = true;
        }
        if (!bank.docs.length) {
            bank.noDError = true;
        }
        if (!bank.errorBna && !bank.errorBno && !bank.errorBid && !bank.noDError) {
            $scope.POPUP.openPopup(bank.popup);
        }
    };

    // submit personal
    $scope.verification.funcs.smpsn = function () {
        var psn = $scope.verification.vars.psn;
        var rqdt = {"taxID": psn.vid};
        //
        psn.popup.loading = true;
        $http.post("/partner/account-verification/submitPSN", rqdt)
                .then(function successCallback(response) {
                    var responseData = response.data;
                    if (angular.isObject(responseData)) {
                        if (responseData.result === true) {
                            loadpsn(true);
                        }
                    }
                    $scope.POPUP.closePopup(psn.popup);
                    psn.popup.loading = false;
                });
    };

    // function to submit bank for approval
    $scope.verification.funcs.smbank = function () {
        var bank = $scope.verification.vars.bank;
        bank.popup.loading = true;
        var rqdt = {"bankID": bank.bid, "bankAccountName": bank.bna, "bankAccountNumber": bank.bno};
        $http.post("/partner/account-verification/submitBank", rqdt)
                .then(function successCallback(response) {
                    var responseData = response.data;
                    if (angular.isObject(responseData)) {
                        if (responseData.result === true) {
                            loadbank(true);
                        }
                    }
                    $scope.POPUP.closePopup(bank.popup);
                    bank.popup.loading = false;
                });
    };
    // function init
    function init(isloadData) {
        loadpsn(isloadData);
        loadbank(isloadData);
    }

    init(true);
});

