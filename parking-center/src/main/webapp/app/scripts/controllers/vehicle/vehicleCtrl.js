angular.module('sbAdminApp').controller('VehicleCtrl', function($rootScope, $scope, $stateParams, $http, $state, $filter, $stomp, urlPrefix, loadVehicles, toaster) {
	
	var today = new Date();
	var subscription;
	$scope.vehicles = loadVehicles.vehicleParkings;
	$scope.totalItems = loadVehicles.totalItems;
	$scope.format = "dd-MM-yyyy";
	$scope.itemsPerPage = 10;
	$scope.maxSize = 5;
	$scope.formData = {currentPage : 1};
	
	$scope.isRealTimeChange = function() {
		if($scope.isRealTime) {
			$scope.clear();
			$scope.formData.currentPage = 1;
			$scope.pageChanged();
			subWebsocket();
		}else{
			unsubscribe();
		}
	}
	
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
	
	//------------------: Websocket :--------------------
	
	function initWebsocket() {
		console.log('initWebsocket');
		$stomp.connect(urlPrefix + '/websocketHandler')
	    .then(function (frame) {
	    	console.log('Websocket connect success');
	    	console.log(frame);
	    });
	}
	
	function subWebsocket() {
		console.log('subWebsocket');
    	subscription = $stomp.subscribe('/topic/checkIn', function (payload, headers, res) {
	       
			if(!payload || !$scope.vehicles || $scope.formData.currentPage != 1) return;
			
			$scope.vehicles.unshift(payload);			
			$scope.totalItems += 1;
			$scope.$apply();
	            
		});
	}
	
	function unsubscribe() {
		if(subscription) {
			console.log('unsubscribe');
			subscription.unsubscribe();
		}
	}
	
	function disconnWebsocket() {
		console.log('disconnWebsocket');
        $stomp.disconnect().then(function(data){
        	console.log('disconnection success');
        	console.log(data);
        }, function(response){
        	console.log('disconnection error');
        	console.log(response);        	
        });
	}
	
	//-----------------: Call method :--------------------
	$scope.clear();
	initWebsocket();
	
	
	//------------------: Event call back :------------------------
	$scope.$on('$destroy', function () { 
		console.log('Destroy');
		disconnWebsocket();
	});
	
});