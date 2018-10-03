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
    var fetchData = function(){
        $http.get("http://localhost:8081/employee/").then(function(response){
        $scope.employees = response.data;
                });
        };
    $scope.fetchData = fetchData;

    var ledOnOffOperation = function(){
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
    $scope.ledOnBlinkOperation = ledOnBlinkOperation;
});