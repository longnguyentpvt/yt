app.controller('SearchModule', function ($scope) {
    $scope.search = {
        "vars": {
            "filter": {
                "cl": {
                    "op": false
                },
                "t": null
            }
        },
        "funcs": {
            "oFilter": null,
            "cFilter": null
        }
    };

    $scope.search.funcs.oFilter = function (f) {
        var filter = $scope.search.vars.filter;
        if (f !== filter.t) {
            filter.t = f;
            filter.cl.op = true;
        } else {
            $scope.search.funcs.cFilter();
        }
    };

    $scope.search.funcs.cFilter = function () {
        var filter = $scope.search.vars.filter;
        filter.cl.op = false;

        filter.t = null;
    };
});