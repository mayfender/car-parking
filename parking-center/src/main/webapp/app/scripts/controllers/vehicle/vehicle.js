angular.module('sbAdminApp').controller('VehicleCtrl', function($rootScope, $scope, $stateParams, $http, $state, urlPrefix, loadVehicles, toaster) {
	console.log(loadVehicles);
	$scope.vehicles = loadVehicles.vehicles;
	$scope.format = "dd/MM/yyyy";
	$scope.formData = {currentPage : 1};
	$scope.totalItems = 100;
	$scope.itemsPerPage = 5;
	$scope.maxSize = 5;
	
	$scope.openStart = function($event) {
	    $event.preventDefault();
	    $event.stopPropagation();

	    $scope.formData.openedStart = true;
	}
	
	$scope.openEnd = function($event) {
	    $event.preventDefault();
	    $event.stopPropagation();

	    $scope.formData.openedEnd = true;
	}
	
	$scope.pageChanged = function() {
		
	}
	
});