var myApp = angular.module("AcademyApp", ['ngRoute']);

myApp.config(function($routeProvider){
  $routeProvider.when("/SpringSecurityDatabase/*",
    {
      templateUrl: "navbar.jsp",
      controller: "AcademyAppController"
    }
  );
});
	  
myApp.controller("AcademyAppController", function(){
    
});

