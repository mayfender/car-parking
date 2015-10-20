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
	   '#4D5360',  // dark grey
       '#F7464A', // red
       '#97BBCD', // blue
       '#FDB45C', // yellow
       '#949FB1', // grey
       '#DCDCDC' // light grey	
    ];
	
	var titles = {vehicle: 'สรุปจำนวนรถที่เข้าจอด', money: 'สรุปจำนวนเงินที่ได้รับ'};
	var units = {vehicle: 'จำนวนคัน', money: 'จำนวนเงิน'};
	$scope.title = titles.vehicle;
	$scope.unit = units.vehicle;
	
	$scope.line = {
    	labels: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', 
   	             '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', 
   	             '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31'],
	    
   	    data: [
   	           [0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
   	    		0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
   	    		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
   	    ],
	   	
   	    onClick: function (points, evt) {}
    };
	
	$scope.bar = {
		labels: ['ม.ค.', 'ก.พ.', 'มี.ค.', 'เม.ย.', 'พ.ค.', 'มิ.ย.', 'ก.ค.', 'ส.ค.', 'ก.ย.', 'ต.ค.', 'พ.ย.', 'ธ.ค.'],

		data: [
	      [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
	    ],
	    
	    onClick: function (points, evt) {
	    	var point = points[0];
	    	
	    	$scope.line.colours = [{
	    		fillColor: point.fillColor, 
	    		strokeColor: point.strokeColor,
	    		pointColor: point.strokeColor,
	            pointStrokeColor: "#fff",
	            pointHighlightFill: "#fff",
	            pointHighlightStroke: point.strokeColor,
	    		
	    	}];
	    	
	    	if(points[0].label == 'January') {
	    		$scope.line.labels = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', 
	    		                      '11', '12', '13', '14', '15', '16', '17', '18', '19', '20']
	    		$scope.line.data = [[100, 150, 150, 110, 120, 130, 111, 132, 80, 70,
	    		        	    		211, 111, 123, 121, 142, 122, 122, 111, 150, 57]];
	    	}else if(points[0].label == 'February') {
	    		$scope.line.labels = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', 
	    		                      '11', '12', '13', '14', '15', '16', '17', '18', '19', '20',
	    		                      '21', '22', '23', '24', '25']
	    		$scope.line.data = [[100, 150, 150, 110, 120, 130, 111, 132, 80, 70,
	    		        	    		211, 111, 123, 121, 142, 122, 122, 111, 150, 57,
	    		        	    		132, 104, 102, 87, 98]];
	    	}else{
	    		$scope.line.labels = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', 
	    		                      '11', '12', '13', '14', '15', '16', '17', '18', '19', '20',
	    		                      '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31']
	    		$scope.line.data = [[100, 150, 150, 110, 120, 130, 111, 132, 80, 70,
	    		        	    		211, 111, 123, 121, 142, 122, 122, 111, 150, 57,
	    		        	    		132, 104, 102, 87, 98, 54, 89, 99, 114, 145, 214]];
	    	}
	    }
	};
	
	$scope.donut = {	
		labels: ['2006', '2007', '2008', '2009', '2010', '2011', '2012'],
    	data: [1000, 1235, 925, 925, 922, 911, 910],
    	
    	onClick: function (points, evt) {
    		var fillColor = points[0].fillColor;
    		var indexOf = fillColor.lastIndexOf(',') + 1;
    		var fillColorResult = fillColor.substring(0, indexOf) + '0.2)';
    		
    		$scope.bar.colours = [{ fillColor: fillColorResult, strokeColor: fillColor }];
    		
    		if(points[0].label == '2006') {
    			$scope.bar.labels = ['January', 'February', 'March', 'April', 'May'];
    			$scope.bar.data = [[65, 59, 80, 81, 56]];
    		}else if(points[0].label == '2007') {
    			$scope.bar.labels = ['January', 'February', 'March', 'April', 'May', 'June'];
    			$scope.bar.data = [[65, 59, 80, 81, 56, 55]];
    		}else{
    			$scope.bar.labels = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
    			$scope.bar.data = [[65, 59, 80, 81, 56, 55, 40]];
    		}
  	    }
    };
	
	$scope.chartVehicle = function() {
		$scope.title = titles.vehicle;
		$scope.unit = units.vehicle;
	}
	
	$scope.chartMoney = function() {
		$scope.title = titles.money;
		$scope.unit = units.money;
	}
    
}]);