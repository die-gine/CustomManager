/**
 * 
 */

var serviceURI = "/CustomManagerWeb/api/v1/customers/list";
	
var app = angular.module('cmApp', []);


app.controller('cmStartViewController', function($scope, $http) {
    $http.get(serviceURI).
    success(function(data) {
        $scope.customers = data;
    });

    
  /*  $scope.applySearchFilter = function(){
    	$scope.searchFilter = $scope.search;
    }
    
    $scope.getRealtionship = function(relationship){
    	if(relationship == 'Friend'){
    			return "Freund";
    	}
    	return "Unbekannt";
    	
    }
    
    $scope.getAllCustomers($scope,$http);*/
    
    
    
});

