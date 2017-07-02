<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html ng-app="devToolsApp">

  <head>
    <meta charset="utf-8" />

    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" />
  </head>

  <body>
  
    <div class="jumbotron">
      <div class="container">
        <div class="col-xs-offset-2 col-xs-8">
          <div ng-view></div>
        </div>
      </div>
    </div>



    <script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
    <script src="http://code.angularjs.org/1.2.20/angular.js"></script>
    <script src="http://code.angularjs.org/1.2.20/angular-route.js"></script>
    <script src="http://code.angularjs.org/1.2.13/angular-cookies.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="resources/js/devtools.js"></script>
    <script src="resources/modules/login/LoginService.js"></script>
    <script src="resources/modules/login/LoginController.js"></script>
    <script src="resources/modules/register/RegisterService.js"></script>
    <script src="resources/modules/register/RegisterController.js"></script>
    <script src="resources/modules/home/DevToolService.js"></script>
    <script src="resources/modules/home/DevToolController.js"></script>
  </body>

</html>