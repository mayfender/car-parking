angular.module('sbAdminApp').controller('VehicleCtrl', function($rootScope, $scope, $stateParams, $http, $state, $filter, urlPrefix, loadVehicles, toaster) {
	
	var today = new Date();
	$scope.vehicles = loadVehicles.vehicleParkings;
	$scope.totalItems = loadVehicles.totalItems;
	$scope.format = "dd-MM-yyyy";
	$scope.itemsPerPage = 10;
	$scope.maxSize = 5;
	$scope.formData = {currentPage : 1};
	
	$scope.search = function() {
		$http.post(urlPrefix + '/restAct/vehicle/searchVehicleParking', {
			licenseNo: $scope.formData.licenseNo,
			startDate: $filter('date')($scope.formData.dateTimeStart, 'dd-MM-yyyy'),
			endDate: $filter('date')($scope.formData.dateTimeEnd, 'dd-MM-yyyy'),
			status: $scope.formData.status,
			currentPage: $scope.formData.currentPage,
	    	itemsPerPage: $scope.itemsPerPage
		}).then(function(data) {
			if(data.data.statusCode != 9999) {				
				$rootScope.systemAlert(data.data.statusCode);
				return;
			}
			
			$scope.vehicles = data.data.vehicleParkings;
			$scope.totalItems = data.data.totalItems;
		}, function(response) {
			$rootScope.systemAlert(response.status);
		});
	}
	
	$scope.clear = function() {
		$scope.formData.licenseNo = "";
		$scope.formData.dateTimeStart = "";
		$scope.formData.dateTimeEnd = "";
		$scope.formData.status = null;
		$scope.formData.dateTimeStart = today;
		$scope.formData.dateTimeEnd = today;
	}
	
	$scope.changeItemPerPage = function() {
		console.log('ok');
		$scope.formData.currentPage = 1;
		$scope.search();
	}
	
	$scope.timeDiff = function(v) {
		if(v == null) return '';
		var result = '';
		
		if(v.days != 0) {
			result += v.days + 'd ';
		}
		if(v.hours != 0) {
			result += v.hours + 'h ';
		}
		if(v.minutes != 0) {
			result += v.minutes + 'm ';
		}
		if(v.seconds != 0) {
			result += v.seconds + 's ';
		}
		
		return result;
	}
	
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
		$scope.search();
	}
	
	$scope.clear();
	
});