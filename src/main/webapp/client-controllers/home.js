/*global angular:false*/

angular.module('app.home', []).config(function ($routeProvider) {
    'use strict';

    $routeProvider.when('/contato', {
        templateUrl : '/client-views/contact.tpl.html',
        controller : function () {}
    }).when('/', {
        templateUrl : '/client-views/contact.tpl.html',
        controller : function () {}
    }).otherwise(function ($rootScope, $location) {
        if (!$rootScope.user) {
            return $location.path('/entrar');
        } else {
            return $location.path('/consultar-patrimonio');
        }
    });

});
