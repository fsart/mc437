/*global angular:false*/
angular.module('app.placeChange', []).config(function ($routeProvider) {
    'use strict';

    $routeProvider.when('/patrimonio/:id/alterar-localizacao', {
        templateUrl : '/client-views/place-change.tpl.html',
        controller : function ($rootScope, $scope, $http, $location, $routeParams) {
            if (!$rootScope.user) {
                return $location.path('/entrar');
            }

            $http.get('/api/patrimonies/' + $routeParams.id).success(function (data) {
                $scope.patrimony = data;
                $scope.form = {'patrimonyId' : $routeParams.id, building : data.building, floor : data.floor, complement : data.complement};
            });

            $scope.save = function () {
                $http.post('/api/place-change', $scope.form).success(function () {
                    $rootScope.message = 'requisição de alteração enviada';
                    $location.path('/consultar-patrimonio');
                });
            };
        }
    }).
    when('/notificacoes', {
        templateUrl : '/client-views/place-change-list.tpl.html',
        controller : function ($rootScope, $scope, $http, $location) {
            if (!$rootScope.user) {
                return $location.path('/entrar');
            }

            $http.get('/api/place-change').success(function (data) {
                $scope.placeChanges = data;
                for (var i = 0; i < $scope.placeChanges.length; i+=1) {
                    switch ($scope.placeChanges[i].building) {
                    case '74' :
                        $scope.placeChanges[i].building = 'IC1';
                        break;
                    case '75' :
                        $scope.placeChanges[i].building = 'IC2';
                        break;
                    case '711' :
                        $scope.placeChanges[i].building = 'IC3';
                        break;
                    case '1366' :
                        $scope.placeChanges[i].building = 'IC3.5';
                        break;
                    case '724' :
                        $scope.placeChanges[i].building = 'INOVA';
                        break;
                    }
                }
            });
        }
    });

});
