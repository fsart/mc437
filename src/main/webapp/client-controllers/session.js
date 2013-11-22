/*global angular:false*/

angular.module('app.session', []).config(function ($routeProvider) {
    'use strict';

    $routeProvider.when('/entrar', {
        templateUrl : '/client-views/login.tpl.html',
        controller : function ($rootScope, $scope, $http, $location) {
            $scope.login = function (form) {
                $http.get('/api/users?username=' + form.user + '&password=' + form.password).success(function (user) {
                    if (user) {
                        $rootScope.user = user;
                        $location.path('/consultar-patrimonio');
                    } else {
                        $rootScope.alert = 'Login ou senha invalidos';
                    }
                }).error(function () {
                    $rootScope.alert = 'ocorreu um erro no servidor';
                });
            };
        }
    }).
    when('/sair', {
        templateUrl : '/client-views/login.tpl.html',
        controller : function ($rootScope, $scope, $http, $location) {
            delete $rootScope.user;
            $location.path('/');
        }
    }).
    when('/cadastrar-usuario', {
        templateUrl : '/client-views/user-register.tpl.html',
        controller : function ($rootScope, $scope, $http, $location) {
            $scope.form = {type : 'user'};

            $scope.save = function () {
                $http.post('/api/users', $scope.form).success(function () {
                    $rootScope.message = 'usuário criado com sucesso';
                    $location.path('/');
                }).error(function () {
                    $rootScope.alert = 'ocorreu um erro no servidor';
                });
            };
        }
    });

});
