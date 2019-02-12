/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

app.controller('VisaMasterPayment', function ($scope) {
    $scope.ytheader.vars.me = false;

    var reg = {
        "en": /^[a-zA-Z\s]*$/
    };

    $scope.pm = {
        "vars": {
            "on" : rdt.on,
            "op": "new",
            "new": {
                "rno": null,
                "sno": null,
                "hn": null,
                "sec": null,
                "em": null,
                "ey": null
            },
            "tt": rdt.tt,
            "cc": rdt.cc
        },
        "funcs": {
            "snoc": null,
            "pay": null
        }
    };

    $scope.pm.funcs.snoc = function () {
        var n = $scope.pm.vars.new;
        var no = n.sno;
        let t = no.replace(/-/g, "");
        let ln = t.length;
        let nt = "";
        for (let i = 0; i < ln; i++) {
            nt += t[i];

            var position = i + 1;
            if (i > 0 && position < ln && position % 4 === 0) {
                nt += "-";
            }
        }

        n.sno = nt;
        n.rno = t;
    };


    $scope.pm.funcs.pay = function () {
        $("#2c2p-payment-form").submit();
    };

    My2c2p.onSubmitForm("2c2p-payment-form", function (errCode, errDesc) {
//                    var errorCode = null;
//                    if (errCode === 1 || errCode === 2) {
//                        errorCode = errors.INVALID_NUMBER;
//                    } else if (errCode === 3 || errCode === 4
//                            || errCode === 5 || errCode === 6 || errCode === 9) {
//                        errorCode = errors.INVALID_EXPIRATATION;
//                    } else if (errCode === 7 || errCode === 8) {
//                        errorCode = errors.EXPIRED;
//                    } else if (errCode === 10) {
//                        errorCode = errors.INVALID_CVV;
//                    }
        alert(errDesc);
    });
});