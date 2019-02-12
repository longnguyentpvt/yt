/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    // COVERS
    var cvs = $("#cover .cs > ul > li"), cvc = 0, cva = false, cvi;

    function ncv() {
        cvs.eq(cvc).removeClass("active");
        cvc++;
        if (cvc > (cvs.length - 1)) {
            cvc = 0;
        }
        cvs.eq(cvc).addClass("active");
    }

    function pcv() {
        cvs.eq(cvc).removeClass("active");
        cvc--;
        if (cvc < 0) {
            cvc = cvs.length - 1;
        }
        cvs.eq(cvc).addClass("active");
    }

    function ecv() {
        if (!cva) {
            cva = true;
            cvi = setInterval(function () {
                ncv();
            }, 8000);
        }
    }

    function dcv() {
        if (cva) {
            clearInterval(cvi);
            cva = false;
        }
    }

    $("#cover").mouseenter(function () {
        dcv();
    }).mouseleave(function () {
        ecv();
    });

    $("#cover .ctrls .ac.nxt").click(function () {
        ncv();
    });

    $("#cover .ctrls .ac.prv").click(function () {
        pcv();
    });

    ecv();
    // END COVERS

    // SLIDER
    $(".hp-sd .ctrl.nxt").click(function () {
        let ctrls = $(this).parent(".sd-ctrl"), sd = ctrls.parent(".hp-sd"), sliding = sd.children(".sd-bd").children(".sliding");
        let cs = parseInt(sd.attr("c-st")), spkgs = parseInt(sd.attr("s-pkg")), tt = sliding.children("li").length;
        let ms = Math.ceil(tt / spkgs);
        if (cs < ms) {
            cs++;

            let np = (cs - 1) * spkgs;
            let over = np + spkgs - tt;
            if (over > 0) {
                np = np - over;
            }
            let percent = "translateX(" + (-((100 / spkgs) * np)) + "%)";
            sliding.css({"transform": percent, "-ms-transform": percent, "-moz-transform": percent});

            if (cs === ms) {
                $(this).removeClass("active");
            }

            if (cs > 1) {
                ctrls.children(".prv").addClass("active");
            }

            sd.attr("c-st", cs);
        }
    });

    $(".hp-sd .ctrl.prv").click(function () {
        let ctrls = $(this).parent(".sd-ctrl"), sd = ctrls.parent(".hp-sd"), sliding = sd.children(".sd-bd").children(".sliding");
        let cs = parseInt(sd.attr("c-st")), spkgs = parseInt(sd.attr("s-pkg")), tt = sliding.children("li").length;
        if (cs > 1) {
            cs--;
            let np = (cs - 1) * spkgs;

            let percent = "translateX(" + (-((100 / spkgs) * np)) + "%)";
            sliding.css({"transform": percent, "-ms-transform": percent, "-moz-transform": percent});

            if (cs === 1) {
                $(this).removeClass("active");
            }

            ctrls.children(".nxt").addClass("active");

            sd.attr("c-st", cs);
        }
    });
    // END SLIDER

    // WHY ANIMATION
    $("#why-s .why-btn").click(function () {
        $(this).find("svg").css("display", "none");
        $("#why-s .awhy-s").addClass("ep");
    });
    // END WHY ANIMATION

    // SEARCH
    let hss = false;
    $("#yt-header .s-i").css("display", "none");

    $(window).scroll(function () {
        let wp = $(window).scrollTop() + $("#yt-header").height();
        let sp = $("#hp-bd").offset().top;
        if (wp >= sp) {
            if (!hss) {
                hss = true;
                $("#yt-header .s-i").css("display", "block");
            }
        } else {
            if (hss) {
                hss = false;
                $("#yt-header .s-i").css("display", "none");
            }
        }
    });
    // END SEARCH
});

app.controller('HomePage', function ($scope, $http, $window, $exceptionHandler) {
    var pageID = "homepage";
    try {
        $scope.hp = {
            "vars": {
                "htd": {// HOSTEST DEAL
                    "dt": null,
                    "lding": false,
                    "done": false
                },
                "jb": {// JUST BOOKED
                    "dt": null,
                    "lding": false,
                    "done": false
                },
                "epm": {// EXPLORE MORE
                    "dt": null,
                    "lding": false,
                    "done": false
                },
                "languageCode": $scope.youtripper.vars.data.currentLan,
                "currencyCode": $scope.youtripper.vars.data.currentCur
            },
            "funcs": {

            }
        };

        // LOAD HOSTEST DEAL
        function loadHtd(locale, currencyCode) {
            let ht = $scope.hp.vars.htd;
            ht.lding = true;
            var rqdt = {"locale": locale, "currencyCode": currencyCode};
            $http.post("/hot-loading", rqdt).then(function successCallback(response) {
                var responseData = response.data;
                if (angular.isObject(responseData)) {
                    ht.dt = response.data;
                    ht.lding = false;
                }
            });
        }

        //LOAD JUST BOOKED PACKAGE
        function loadJbp(locale, currencyCode) {
            let jb = $scope.hp.vars.jb;
            jb.lding = true;
            var rqdt = {"locale": locale, "currencyCode": currencyCode};
            $http.post("/just-book-loading", rqdt).then(function successCallback(response) {
                var responseData = response.data;
                if (angular.isObject(responseData)) {
                    jb.dt = response.data;
                    jb.lding = false;
                }
            });
        }

        //LOAD EXPLORE MORE
        function loadEmp(locale, currencyCode) {
            let epm = $scope.hp.vars.epm;
            epm.lding = true;
            var rqdt = {"locale": locale, "currencyCode": currencyCode};
            $http.post("/explore-more-loading", rqdt).then(function successCallback(response) {
                var responseData = response.data;
                if (angular.isObject(responseData)) {
                    epm.dt = response.data;
                    epm.lding = false;
                }
            });
        }

        //scroll
        angular.element($window).bind("scroll", function () {
            var current = $(this).scrollTop();
            var bottom = current + $(this).height();
            var sections = $scope.hp.vars;
            var hs = sections.htd;
            if (!hs.done) {
                let hotP = $("#hp-hot").offset().top;
                if (bottom > hotP) {
                    hs.done = true;
                    loadHtd(sections.languageCode, sections.currencyCode);
                }
            }

            var jbSection = sections.jb;
            if (!jbSection.done) {
                let jbP = $("#hp-j").offset().top;
                if (bottom > jbP) {
                    jbSection.done = true;
                    loadJbp(sections.languageCode, sections.currencyCode);
                }
            }

            var epmSection = sections.epm;
            if (!epmSection.done) {
                let jbP = $("#hp-epm").offset().top;
                if (bottom > jbP) {
                    epmSection.done = true;
                    loadEmp(sections.languageCode, sections.currencyCode);
                }
            }
            $scope.$apply();
        });
    } catch (e) {
        console.log(e.message);
        $exceptionHandler({"pageID": pageID}, "");
    }
});