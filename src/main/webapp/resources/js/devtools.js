'use strict';

/* App Module */
//declare modules
angular.module('login', []);
angular.module('home', []);
angular.module('register', []);
var devToolsApp = angular.module('devToolsApp', ['ngRoute', 'login','home','register','ngCookies']);

devToolsApp.config(['$routeProvider',
                     function($routeProvider) {
	$routeProvider.
	when('/login', {
		templateUrl: 'resources/modules/login/login.html',
		controller: 'LoginController'
	}).
	when('/home', {
		templateUrl: 'resources/modules/home/home.html',
		controller: 'DevToolController'
	}).
	when('/register', {
		templateUrl: 'resources/modules/register/register.html',
		controller: 'RegisterController'
	}).
	otherwise({
		redirectTo: '/login'
	});
}]);

devToolsApp.factory("ConfigFactory",function($rootScope){

	return{
		getHostnames: function(){
			var ref = JSON.parse('{"hostnames":["vcc_dev","vcce_dev","dev_int_qm"]}');
			return ref;
		}
	}
});