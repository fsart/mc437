angular.module('app.session', []).config(function ($routeProvider) {

    $routeProvider.
    when('/entrar', {
        templateUrl : 'resources/client-views/login.tpl.html',
        controller : function ($rootScope, $scope, $http, $location) {
            $scope.login = function (form) {
            	if (form.user === 'geronimo' && form.password === '1234') {
                    $rootScope.user = {token : '1234567'};
                    $location.path('/');
            	} else {
            		$scope.alert = 'Login ou senha invalidos';
            	}
            };
        }
    }).
    when('/sair', {
        templateUrl : 'resources/client-views/login.tpl.html',
        controller : function ($rootScope, $scope, $http, $location) {
            delete $rootScope.user;
            $location.path('/');
        }
    });

});
