'use strict';

angular.module('register')

        .controller('RegisterController',
                ['$scope', '$rootScope', '$location', 'RegisterService',
                    function($scope, $rootScope, $location, RegisterService) {

                        $scope.register = function() {

                            $scope.dataLoading = true;
                            RegisterService.register($scope.user, function(response) {

                                if (response) {
                                    $location.path('/login');
                                }
                                else {
                                    $scope.error = "Registration Failed!";
                                    $scope.dataLoading = false;
                                    $location.path('/register');
                                }
                            });
                        };
                    }]);


