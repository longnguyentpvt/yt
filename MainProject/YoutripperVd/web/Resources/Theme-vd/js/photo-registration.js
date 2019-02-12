/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    $(document).on("mousedown", "#cover-popup, #portrait-popup", function (event) {
        return false;
    });
});

app.controller('PhotoRegistration', function ($scope, $http, $timeout) {
    $scope.photo = {
        "vars": {
            "atLink": null,
            "cTy": null,
            "cvs": [],
            "cVi": null,
            "ptI": null,
            "tps": [],
            "aps": [],
            "cFile": null,
            "cPop": {
                "active": false,
                "uploading": false,
                "imgI": -1,
                "imgS": null,
                "maxW": 0,
                "maxH": 0,
                "imgW": 0,
                "imgH": 0,
                "cstyle": {
                    "width": "0px",
                    "left": "0px",
                    "top": "0px"
                },
                "resizer": {
                    "w": 100,
                    "s": {
                        "width": "10%"
                    },
                    "max": 0,
                    "current": 0
                },
                "pos": {
                    "left": 0,
                    "top": 0
                },
                "cover": null
            }
        },
        "funcs": {
            "cFileChanged": null,
            "ocBrowser": null,
            "cCoPop": null,
            "smCover": null,
            "mCover": null,
            "emCover": null,
            "srCover": null,
            "rCover": null,
            "erCover": null,
            "cropCover": null
        }
    };

    /* COVERS */
    function initCovers() {
        var vars = $scope.photo.vars;
        var cvs = vars.cvs;
        var l = vars.atLink;
        for (let i = 0, max = cvs.length; i < max; i++) {
            let aC = cvs[i];
            if (angular.isString(aC) && aC.length) {
                cvs[i] = l + aC;
            }
        }
    }

    $scope.photo.funcs.ocBrowser = function (ci) {
        $('#cover-file').trigger('click');
        if (angular.isNumber(ci)) {
            $scope.photo.vars.cPop.cover = ci;
        }
    };

    $scope.photo.funcs.cFileChanged = function () {
        var vars = $scope.photo.vars;
        var pop = vars.cPop;
        pop.uploading = true;

        $http({method: 'POST',
            url: "/partner/package-registration/photo/cover-uploading",
            headers: {
                'Content-Type': undefined
            },
            data: {
                "cover": vars.cFile
            },
            transformRequest: function (data, headersGetter) {
                var formData = new FormData();
                angular.forEach(data, function (value, key) {
                    formData.append(key, value);
                });
                return formData;
            }
        }).then(function successCallback(response) {
            var responseData = response.data;
            if (angular.isObject(responseData)) {
                console.log(responseData);
                if (responseData.success) {
                    pop.maxW = responseData.width;
                    pop.maxH = responseData.height;
                    pop.imgI = responseData.coverIndex;
                    pop.imgS = $scope.youtripper.funcs.getLink("/partner/package-registration/photo/uploaded?registration=" + pop.imgI + "&t=" + new Date().getTime());
                    pop.resizer.max = responseData.range;
                    rcStyle();
                } else {
                    pop.imgI = -1;
                    pop.imgS = null;
                }
                pop.uploading = false;
            }
        });

        $scope.POPUP.openPopup(pop);
    };

    $scope.photo.funcs.cCoPop = function () {
        var vars = $scope.photo.vars;
        var pop = vars.cPop;

        $scope.POPUP.closePopup(pop);
    };

    function rcStyle() {
        var vars = $scope.photo.vars;
        var pop = vars.cPop;

        pop.imgW = pop.maxW;
        pop.imgH = pop.maxH;

        pop.pos.left = 0;
        pop.pos.top = 0;

        var max = pop.resizer.max;
        pop.resizer.current = max;
        pop.resizer.w = 100;

        ucStyle();
        ucSize();
    }

    function ucStyle() {
        var vars = $scope.photo.vars;
        var pop = vars.cPop;

        pop.cstyle.left = pop.pos.left + "px";
        pop.cstyle.top = pop.pos.top + "px";
    }

    function ucSize() {
        var vars = $scope.photo.vars;
        var pop = vars.cPop;
        var resizer = pop.resizer;

        var w = resizer.w;
        resizer.s.width = w + "%";

        pop.cstyle.width = pop.imgW + "px";
    }

    var coverM = false, coverR = false, cmI = {
        "ol": 0,
        "ot": 0,
        "ox": 0,
        "oy": 0,
        "ml": 0,
        "mt": 0,
        "lb": 0,
        "bw": 0,
        "ow": 0,
        "oh": 0,
        "ovl": 0,
        "ovt": 0,
        "bgw": 0,
        "bgh": 0
    };

    $scope.photo.funcs.smCover = function ($event) {
        var vars = $scope.photo.vars;
        var pop = vars.cPop;

        cmI.ol = pop.pos.left;
        cmI.ot = pop.pos.top;
        cmI.ox = $event.pageX;
        cmI.oy = $event.pageY;

        var img = $("#cover-popup .aCover .img");
        var bg = $("#cover-popup .aCover");
        cmI.ml = -(Math.ceil(img.width()) - Math.ceil(bg.width()));
        cmI.mt = -(Math.ceil(img.height()) - Math.ceil(bg.height()));
        coverM = true;
    };

    $scope.photo.funcs.emCover = function () {
        coverM = false;
    };

    $scope.photo.funcs.mCover = function ($event) {
        if (coverM) {
            var vars = $scope.photo.vars;
            var pop = vars.cPop;

            var newX = cmI.ol + ($event.pageX - cmI.ox);
            var newY = cmI.ot + ($event.pageY - cmI.oy);
            if (newX > 0) {
                newX = 0;
            } else if (newX < cmI.ml) {
                newX = cmI.ml;
            }

            if (newY > 0) {
                newY = 0;
            } else if (newY < cmI.mt) {
                newY = cmI.mt;
            }

            pop.pos.left = newX;
            pop.pos.top = newY;

            ucStyle();
        }
    };

    $scope.photo.funcs.srCover = function ($event) {
        var vars = $scope.photo.vars;
        var pop = vars.cPop;

        cmI.ox = $event.pageX;

        var reb = $("#cover-popup .resizer .origin-bar");
        cmI.lb = Math.ceil(reb.offset().left);
        cmI.bw = Math.ceil(reb.width());

        cmI.ol = pop.pos.left;
        cmI.ot = pop.pos.top;

        cmI.ow = pop.imgW;
        cmI.oh = pop.imgH;

        var bg = $("#cover-popup .aCover");
        cmI.bgw = Math.ceil(bg.width());
        cmI.bgh = Math.ceil(bg.height());

        coverR = true;
    };

    $scope.photo.funcs.erCover = function () {
        coverR = false;
    };

    $scope.photo.funcs.rCover = function ($event) {
        if (coverR) {
            var vars = $scope.photo.vars;
            var pop = vars.cPop;

            var mx = $event.pageX;
            var cw = mx - cmI.lb;

            var wp = Math.ceil((cw / cmI.bw) * 100);
            if (wp < 0) {
                wp = 0;
            } else if (wp > 100) {
                wp = 100;
            }

            pop.resizer.w = wp;

            var maxW = pop.maxW, range = pop.resizer.max;
            var diW = Math.ceil(range * ((100 - wp) / 100));
            var imgW = maxW - diW;

            pop.imgW = imgW;

            var maxH = pop.maxH;
            var imgH = Math.ceil(imgW * (maxH) / maxW);
            pop.imgH = imgH;

            var wD = cmI.ow - imgW;
            var wH = cmI.oh - imgH;

            var wrange = Math.ceil(wD / 2);
            var hrange = Math.ceil(wH / 2);

            var newLeft = (cmI.ol + wrange);
            var newTop = (cmI.ot + hrange);

            var ovl = (-newLeft + cmI.bgw) - imgW;
            if (ovl > 0) {
                newLeft += ovl;
            }

            var ovt = (-newTop + cmI.bgh) - imgH;
            if (ovt > 0) {
                newTop += ovt;
            }

            if (newLeft > 0) {
                newLeft = 0;
            }
            if (newTop > 0) {
                newTop = 0;
            }

            pop.pos.left = newLeft;
            pop.pos.top = newTop;

            ucStyle();
            ucSize();
        }
    };

    $scope.photo.funcs.cropCover = function () {
        var vars = $scope.photo.vars;
        var pop = vars.cPop;

        pop.uploading = true;

        let ci = pop.cover;
        var data = {
            "packageID": $scope.registration.vars.packageID,
            "coverI": pop.imgI,
            "resizedW": pop.imgW,
            "left": pop.pos.left,
            "top": pop.pos.top,
            "cover": ci
        };

        $http.post("/partner/package-registration/photo/cover-editing", data)
                .then(function successCallback(response) {
                    var responseData = response.data;
                    if (angular.isObject(responseData)) {
                        vars.cvs[ci] = vars.atLink + responseData.result;

                        $scope.photo.funcs.cCoPop();
                        pop.uploading = false;
                    }
                });
    };
    /* END COVERS */

    /* PORTRAIT */
    function initPortrait() {
        var vars = $scope.photo.vars;
        var pt = vars.ptI;
        var l = vars.atLink;
        if (angular.isString(pt) && pt.length) {
            vars.ptI = l + pt;
        }
    }
    /* END PORTRAIT */

    /* THUMBS */
    function initThumbs() {
        var vars = $scope.photo.vars;
        var tps = vars.tps;
        var l = vars.atLink;
        for (let i = 0, max = tps.length; i < max; i++) {
            let aC = tps[i];
            if (angular.isString(aC) && aC.length) {
                tps[i] = l + aC;
            }
        }

        var aps = vars.aps;
        for (let i = 0, max = aps.length; i < max; i++) {
            let aC = aps[i];
            let n = aC.pictureName;
            aC.pictureName = l + n;
        }

    }
    /* END THUMBS */

    /* COMMON */
    function validateAll() {
        initCovers();
        initPortrait();
        initThumbs();
    }

    $scope.registration.vars.steps.photo.load = function () {
        var registrationVars = $scope.registration.vars;
        registrationVars.loaded = false;

        var packageID = registrationVars.packageID;
        var data = {"packageID": packageID};

        $http.post("/partner/package-registration/photo/data", data)
                .then(function successCallback(response) {
                    var responseData = response.data;
                    if (angular.isObject(responseData)) {
                        console.log(responseData);
                        var vars = $scope.photo.vars;

                        vars.cTy = responseData.coverType;
                        vars.atLink = responseData.urlLink;

                        let cvs = vars.cvs;
                        cvs.length = 0;
                        cvs.push(responseData.fCover);
                        cvs.push(responseData.sCover);
                        cvs.push(responseData.tCover);

                        vars.cVi = responseData.vCover;

                        vars.ptI = responseData.portrait;

                        let tps = vars.tps;
                        tps.length = 0;
                        tps.push(responseData.fThumb);
                        tps.push(responseData.sThumb);
                        tps.push(responseData.tThumb);
                        tps.push(responseData.foThumb);
                        tps.push(responseData.fThumb);

                        let aps = vars.aps;
                        aps.length = 0;
                        Array.prototype.push.apply(aps, responseData.pics);

                        validateAll();

                        registrationVars.loaded = true;
                    }
                });
    };
    /* END COMMON */
});