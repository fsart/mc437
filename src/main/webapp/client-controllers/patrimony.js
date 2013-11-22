/*global angular:false*/
angular.module('app.patrimony', []).config(function ($routeProvider) {
    'use strict';

    $routeProvider.when('/enviar-arquivo', {
        templateUrl : '/client-views/send-file.tpl.html',
        controller : function ($rootScope, $scope, $http, $location) {
            if (!$rootScope.user) {
                return $location.path('/entrar');
            }
        }
    }).
    when('/patrimonio/:id', {
        templateUrl : '/client-views/patrimony-details.tpl.html',
        controller : function ($rootScope, $scope, $http, $location, $routeParams) {
            if (!$rootScope.user) {
                return $location.path('/entrar');
            }

            $http.get('/api/patrimonies/' + $routeParams.id).success(function (data) {
                $scope.patrimony = data;
                switch (data.building) {
                case '74' :
                    $scope.patrimony.building = 'IC1';
                    break;
                case '75' :
                    $scope.patrimony.building = 'IC2';
                    break;
                case '711' :
                    $scope.patrimony.building = 'IC3';
                    break;
                case '1366' :
                    $scope.patrimony.building = 'IC3.5';
                    break;
                case '724' :
                    $scope.patrimony.building = 'INOVA';
                    break;
                }
            }).error(function () {
                $rootScope.alert = 'ocorreu um erro no servidor';
            });
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
                    if (a[param] > b[param]) {return  1;}
                    if (a[param] < b[param]) {return -1;}
                    return 0;
                });
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
            }).error(function () {
                $rootScope.alert = 'ocorreu um erro no servidor';
            });
        }
    });

});
