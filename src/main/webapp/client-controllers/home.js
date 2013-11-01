angular.module('app.home', []).config(function ($routeProvider) {

    $routeProvider.
    when('/', {
        templateUrl : '/client-views/home.tpl.html',
        controller : function ($rootScope, $scope, $http, $location) {
            if (!$rootScope.user) {
                return $location.path('/entrar');
            }
        }
    }).
    when('/contato', {
        templateUrl : '/client-views/contact.tpl.html',
        controller : function ($rootScope, $scope, $http, $location) {}
    });

});
