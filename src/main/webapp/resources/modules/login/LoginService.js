'use strict';

angular.module('login')

        .factory('LoginService',
                ['$http', '$cookieStore', '$rootScope', '$timeout',
                    function($http, $cookieStore, $rootScope, $timeout) {
                        var service = {};

                        service.Login = function(username, password, callback) {
                        	$http.post('http://localhost:8080/security/j_spring_security_check?j_username='+username+'&j_password='+password)
                            .success(function(response) {
                                callback(response);
                            });
                             
                        };

                        service.ClearCredentials = function() {
                            $rootScope.globals = {};
                            $cookieStore.remove('globals');
                            $http.defaults.headers.common.Authorization = 'Basic ';
                        };

                        return service;
                    }]);