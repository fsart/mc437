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

            $scope.confirm = function (placeChange) {
                $http.put('/api/place-change/' + placeChange._id + '/confirm').success($scope.load);
            };

            $scope.cancel = function (placeChange) {
                $http.put('/api/place-change/' + placeChange._id + '/cancel').success($scope.load);
            };

            $scope.load = function () {
                $http.get('/api/place-change').success(function (data) {
                    $scope.placeChanges = [];
                    for (var i = 0; i < data.length; i+=1) {
                        switch (data[i].building) {
                        case '74' :
                            data[i].building = 'IC1';
                            break;
                        case '75' :
                            data[i].building = 'IC2';
                            break;
                        case '711' :
                            data[i].building = 'IC3';
                            break;
                        case '1366' :
                            data[i].building = 'IC3.5';
                            break;
                        case '724' :
                            data[i].building = 'INOVA';
                            break;
                        }
                        if (data[i].status === 'pending') {
                            $scope.placeChanges.push(data[i]);
                        }
                    }
                });
            };
            $scope.load();
        }
    });

});
