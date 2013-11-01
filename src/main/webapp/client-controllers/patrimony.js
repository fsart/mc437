angular.module('app.patrimony', []).config(function ($routeProvider) {

    $routeProvider.
    when('/enviar-arquivo', {
        templateUrl : '/client-views/send-file.tpl.html',
        controller : function ($rootScope, $scope, $http, $location) {
            var uploader;

            if (!$rootScope.user) {
                return $location.path('/entrar');
            }

            $scope.upload = function () {
                var fr = new FileReader();
                fr.onload = function(e) {
                    $http.post('api/patrimonies/', 'file='+encodeURIComponent(e.target.result), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).success(function () {
                        $location.path('/consultar-patrimonio');
                    });
                };

                fr.readAsText(document.getElementById('inputFile').files[0]);
            }
        }
    }).
    when('/consultar-patrimonio', {
        templateUrl : '/client-views/patrimony-list.tpl.html',
        controller : function ($rootScope, $scope, $http, $location) {
        	var patrimonies;
            if (!$rootScope.user) {
                return $location.path('/entrar');
            }

            $scope.sort = function (param) {
                $scope.patrimonies.sort(function (a,b) {
                    if (a[param] > b[param]) return  1;
                    if (a[param] < b[param]) return -1;
                    return 0;
                })
            };

            $scope.filter = function (form) {
                form = form || {};
                $scope.patrimonies = patrimonies.filter(function (patrimony) {
                    return (!form.id || patrimony.id.toLowerCase().search(form.id.toLowerCase()) > -1) &&
                           (!form.mark || patrimony.mark.toLowerCase().search(form.mark.toLowerCase()) > -1) &&
                           (!form.model || patrimony.model.toLowerCase().search(form.model.toLowerCase()) > -1) &&
                           (!form.color || patrimony.color.toLowerCase().search(form.color.toLowerCase()) > -1) &&
                           (!form.description || patrimony.description.toLowerCase().search(form.description.toLowerCase()) > -1);
                }).slice(0,500);
            };


            $http.get('api/patrimonies').success(function (data) {
            	patrimonies = data;
                $scope.filter();
            });
        }
    });

});
