'use strict';

angular.module('login')
 
.controller('LoginController',
    ['$scope', '$rootScope', '$location', 'LoginService',
    function ($scope, $rootScope, $location, LoginService) {
        // reset login status
        LoginService.ClearCredentials();
 
        $scope.login = function () {
            $scope.dataLoading = true;
            LoginService.Login($scope.username, $scope.password, function(response) {
                if(response) {
                    //LoginService.SetCredentials($scope.username, $scope.password);
                	$rootScope.authenticated = true;
                    $location.path('/home');
                } else {
                    $scope.error = "Login Failed!";
                    $location.path('/login');
                    $scope.dataLoading = false;
                }
            });
        };
    }]);