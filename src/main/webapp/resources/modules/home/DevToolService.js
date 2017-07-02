'use strict';

angular.module('home')

.factory('DevToolService',
		['$http', '$cookieStore', '$rootScope', '$timeout',
		 function($http, $cookieStore, $rootScope, $timeout) {
			var service = {};
			var prevResult;
			service.tailLogs = function(machineName,callback) {

				$http.defaults.headers.common['Accept'] = 'application/json';
				$http.defaults.headers.common['Content-Type'] = 'application/json';
				//Server call
				$http.post('http://localhost:8080/security/tailLog',machineName)
				.success(function(result) {
					if(result && (prevResult != result)){
						//formattedResult = formattedResult.concat(result.replace(/\n/g,'<br/>'));
						$('#logDisplay').append(result.replace(/\n/g,'<br/>'))
						prevResult = result;
					}
					service.tailLogs(machineName, callback);
					//callback(result);
				});

			};
			return service;
		}]);