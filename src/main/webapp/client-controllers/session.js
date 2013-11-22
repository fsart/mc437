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
                        $location.path('/');
                    } else {
                        $scope.alert = 'Login ou senha invalidos';
                    }
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
                    $location.path('/');
                });
            };
        }
    });

});
