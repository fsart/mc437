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
            });
        }
    });

});
