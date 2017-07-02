'use strict';

angular.module('home')

.controller('DevToolController',
		['$scope', '$rootScope', '$location', 'DevToolService','ConfigFactory','$filter',
		 function($scope, $rootScope, $location, DevToolService,ConfigFactory,$filter) {

			$scope.hostnames = ConfigFactory.getHostnames().hostnames;
			
			$scope.tailLogs = function() {
				$scope.dataLoading = true;
				
				var machineName = $scope.hostname
				
				if($rootScope.authenticated){
					DevToolService.tailLogs(machineName, function(response) {

						if (response) {
							$scope.tickets = response;
							$rootScope.tickets = response;
							$scope.dataLoading = false;
						}
						else {
							$scope.error = response.message;
							$scope.dataLoading = false;
							$location.path('/login');
						}
					});
				}
				else{
					$location.path('/login');
				}
			};
		}]);


