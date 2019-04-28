var routerApp = angular.module('routerApp', ['ui.router']);

routerApp.config(function($stateProvider, $urlRouterProvider) {
    
    $urlRouterProvider.otherwise('/home');
    
    $stateProvider
        
        // HOME STATES AND NESTED VIEWS ========================================
        .state('home', {
            url: '/home',
            templateUrl: 'Templates/home.html'
        })
        // ABOUT PAGE AND MULTIPLE NAMED VIEWS =================================
        .state('service', {
            url: '/service',
            templateUrl: 'Templates/service.html', 
            controller: 'serviceController'            
        });
        
});

routerApp.controller('serviceController', function($scope,$http) {
    
    $scope.employees=undefined;
    $scope.red = "red";
    $scope.yellow = "yellow";
    $scope.green = "green";
    var fetchData = function(){
        $http.get("http://localhost:8081/employee/").then(function(response){
        $scope.employees = response.data;
                });
        };
    $scope.fetchData = fetchData;

    $scope.ledToggleOperation = function(colour){
    	console.log("Printing Colour:"+colour);
    	var c = colour;
    	var url = "http://192.168.43.248:8989/"+c+"/toggle";
    	$http.get(url).then(function(response){
    	     console.log(response.data);
    	});
	};
	
    $scope.ledBlinkOperation = function(colour){
    	var url = "http://192.168.43.248:8989/"+colour +"/blink";
    	$http.get(url).then(function(response){
    	     console.log(response.data);
    	});	
	};

    $scope.ledPulseOperation = function(colour){
    	var url = "http://192.168.43.248:8989/"+colour +"/pulse";
    	$http.get(url).then(function(response){
    	     console.log(response.data);
    	});	
	};
    /*var ledOnOffOperation = function(){
        $http.get("http://192.168.0.103:8989/red/on").then(function(response){
        console.log(response.data);
        });
    }
    $scope.ledOnOffOperation = ledOnOffOperation;

    var ledToggleOperation = function(){
        $http.get("http://192.168.0.103:8989/yellow/toggle").then(function(response){
        console.log(response.data);
        });
    }
    $scope.ledToggleOperation = ledToggleOperation;

    var ledOnBlinkOperation = function(){
        $http.get("http://192.168.0.103:8989/green/blink").then(function(response){
        console.log(response.data);
        });
    }
    $scope.ledOnBlinkOperation = ledOnBlinkOperation;*/
});