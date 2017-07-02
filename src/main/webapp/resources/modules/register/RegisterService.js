'use strict';

angular.module('register')

.factory('RegisterService',
		['$http', '$cookieStore', '$rootScope', '$timeout',
		 function($http, $cookieStore, $rootScope, $timeout) {
			var service = {};

			service.register = function(user,callback) {
				//Server call

				//$http.defaults.headers.common['Accept'] = 'application/json';
				//$http.defaults.headers.common['Content-Type'] = 'application/json;charset=utf-8';
				
				var userData = JSON.stringify(user);
				$http.post('http://localhost:8080/security/register'
						,JSON.parse(userData),
						{
							hearders : 
							{
								'Content-Type' : 'application/json;charset=utf-8',
								'Accept' : 'application/json'
							}
						})
				.success(function(response) {
					callback(response);
				});

			};
			return service;
		}]);