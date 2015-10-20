'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the sbAdminApp
 */
angular.module('sbAdminApp').controller('ChartCtrl', ['$scope', '$timeout', function ($scope, $timeout) {
	
	Chart.defaults.global.colours = [
	   '#46BFBD', // green
       '#F7464A', // red
       '#DCDCDC', // light grey	
       '#97BBCD', // blue
       '#FDB45C', // yellow
       '#949FB1', // grey
       '#4D5360'  // dark grey
    ];
	
	$scope.titles = {vehicle: 'สรุปจำนวนรถที่เข้าจอด', money: 'สรุปจำนวนเงินที่ได้รับ'};
	$scope.title = $scope.titles.vehicle;
	
	$scope.line = {
    	labels: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', 
   	             '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', 
   	             '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31'],
	    
   	    data: [
   	           [100, 150, 150, 110, 120, 130, 111, 132, 80, 70,
   	    		211, 111, 123, 121, 142, 122, 122, 111, 150, 57,
   	    		132, 104, 102, 87, 98, 54, 89, 99, 114, 145, 214]
   	    ],
	    onClick: function (points, evt) {
	      console.log(points, evt);
	    }
    };
	
	$scope.bar = {
		labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],

		data: [
	      [65, 59, 80, 81, 56, 55, 40]
	    ],
	    
	    onClick: function (points, evt) {
	    	if(points[0].label == 'January') {
	    		$scope.line.labels = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', 
	    		                      '11', '12', '13', '14', '15', '16', '17', '18', '19', '20']
	    		$scope.line.data = [[100, 150, 150, 110, 120, 130, 111, 132, 80, 70,
	    		        	    		211, 111, 123, 121, 142, 122, 122, 111, 150, 57]];
    			$scope.line.colours = ['#46BFBD'];
	    	}else if(points[0].label == 'February') {
	    		$scope.line.labels = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', 
	    		                      '11', '12', '13', '14', '15', '16', '17', '18', '19', '20',
	    		                      '21', '22', '23', '24', '25']
	    		$scope.line.data = [[100, 150, 150, 110, 120, 130, 111, 132, 80, 70,
	    		        	    		211, 111, 123, 121, 142, 122, 122, 111, 150, 57,
	    		        	    		132, 104, 102, 87, 98]];
    			$scope.line.colours = ['#F7464A'];
	    	}else{
	    		$scope.line.labels = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', 
	    		                      '11', '12', '13', '14', '15', '16', '17', '18', '19', '20',
	    		                      '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31']
	    		$scope.line.data = [[100, 150, 150, 110, 120, 130, 111, 132, 80, 70,
	    		        	    		211, 111, 123, 121, 142, 122, 122, 111, 150, 57,
	    		        	    		132, 104, 102, 87, 98, 54, 89, 99, 114, 145, 214]];
    			$scope.line.colours = ['#DCDCDC'];
	    	}
	    }
	};
	
	$scope.donut = {	
		labels: ['2006', '2007', '2008'],
		
    	data: [1000, 1235, 925],
    	
    	onClick: function (points, evt) {
    		console.log(points);
    		
    		if(points[0].label == '2006') {
    			$scope.bar.labels = ['January', 'February', 'March', 'April', 'May'];
    			$scope.bar.data = [[65, 59, 80, 81, 56]];
    			$scope.bar.colours = ['#46BFBD'];
    		}else if(points[0].label == '2007') {
    			console.log('b');
    			$scope.bar.labels = ['January', 'February', 'March', 'April', 'May', 'June'];
    			$scope.bar.data = [[65, 59, 80, 81, 56, 55]];
    			$scope.bar.colours = ['#F7464A'];
    		}else{
    			console.log('c');
    			$scope.bar.labels = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
    			$scope.bar.data = [[65, 59, 80, 81, 56, 55, 40]];
    			$scope.bar.colours = ['#DCDCDC'];
    		}
  	    }
    };
	
	
	
	$scope.chartVehicle = function() {
		console.log('vehicle');
		$scope.title = $scope.titles.vehicle;
	}
	
	$scope.chartMoney = function() {
		console.log('money');		
		$scope.title = $scope.titles.money;
	}
	
    
    
}]);