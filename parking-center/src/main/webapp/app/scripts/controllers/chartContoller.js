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
	
	$scope.donut = {
		labels: ['2006', '2007', '2008'],
    	data: [1000, 1235, 925]
    };
	
	 $scope.bar = {
		labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],

		data: [
	      [65, 59, 80, 81, 56, 55, 40]
	    ]
	};
	
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
    
   
    
    
    
    

   

    $scope.radar = {
    	labels:["Eating", "Drinking", "Sleeping", "Designing", "Coding", "Cycling", "Running"],

    	data:[
    	    [65, 59, 90, 81, 56, 55, 40],
    	    [28, 48, 40, 19, 96, 27, 100]
    	]
    };

    $scope.pie = {
    	labels : ["Download Sales", "In-Store Sales", "Mail-Order Sales"],
    	data : [300, 500, 100]
    };

    $scope.polar = {
    	labels : ["Download Sales", "In-Store Sales", "Mail-Order Sales", "Tele Sales", "Corporate Sales"],
    	data : [300, 500, 100, 40, 120]
    };

    $scope.dynamic = {
    	labels : ["Download Sales", "In-Store Sales", "Mail-Order Sales", "Tele Sales", "Corporate Sales"],
    	data : [300, 500, 100, 40, 120],
    	type : 'PolarArea',

    	toggle : function () 
    	{
    		this.type = this.type === 'PolarArea' ?
    	    'Pie' : 'PolarArea';
		}
    };
}]);