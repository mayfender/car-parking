'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the sbAdminApp
 */
angular.module('sbAdminApp').controller('ChartCtrl', function ($rootScope, $scope, $timeout, $http, urlPrefix, findAllYears) {
	console.log(findAllYears);
	
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
	var year;
	$scope.title = titles.vehicle;
	$scope.unit = units.vehicle;
	var labels = {months: {'ม.ค.':'01', 'ก.พ.':'02', 'มี.ค.':'03', 'เม.ย.':'04', 'พ.ค.':'05', 'มิ.ย.':'06', 
        				   'ก.ค.':'07', 'ส.ค.':'08', 'ก.ย.':'09', 'ต.ค.':'10', 'พ.ย.':'11', 'ธ.ค.':'12'}};
	
	var keys = [];
	for(var k in labels.months) keys.push(k);
	
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
	
	
	
	
	console.log(keys);
	
	$scope.bar = {
		labels: keys,
		data: [
	      [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
	    ],
	    
	    onClick: function (points, evt) {
	    	var point = points[0];
	    	if(point == null || point.value == null || point.value == 0) return;
	    	
	    	$scope.line.colours = [{
	    		fillColor: point.fillColor, 
	    		strokeColor: point.strokeColor,
	    		pointColor: point.strokeColor,
	            pointStrokeColor: "#fff",
	            pointHighlightFill: "#fff",
	            pointHighlightStroke: point.strokeColor,
	    		
	    	}];
	    	
	    	
	    	$http.get(urlPrefix + '/restAct/report/reportDay?year=' + year + '&month=' + labels.months[point.label])
    		.then(function(data) {
        		if(data.data.statusCode != 9999) {
        			$rootScope.systemAlert(data.data.statusCode);
        			return;
        		}	    		
        		
        		$scope.line.data = [data.data.result.values];
    	    }, function(response) {
    	    	$rootScope.systemAlert(response.status);
    	    });
	    }
	};
	
	$scope.donut = {
		labels: findAllYears.result.years,
		data: findAllYears.result.values,
    	
    	onClick: function (points, evt) {
    		var point = points[0];
    		var fillColor = point.fillColor;
    		var indexOf = fillColor.lastIndexOf(',') + 1;
    		var fillColorResult = fillColor.substring(0, indexOf) + '0.2)';
    		year = point.label;
    		$scope.bar.colours = [{ fillColor: fillColorResult, strokeColor: fillColor }];
    		
    		$http.get(urlPrefix + '/restAct/report/reportMonth?year=' + year)
    		.then(function(data) {
        		if(data.data.statusCode != 9999) {
        			$rootScope.systemAlert(data.data.statusCode);
        			return;
        		}	    		
        		
        		$scope.bar.data = [data.data.result.values];
    	    }, function(response) {
    	    	$rootScope.systemAlert(response.status);
    	    });
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
    
});