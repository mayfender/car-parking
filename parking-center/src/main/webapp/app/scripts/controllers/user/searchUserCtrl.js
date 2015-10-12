angular.module('sbAdminApp').controller('SearchUserCtrl', function($rootScope, $scope, $http, $state, loadUsers, urlPrefix) {	
	
	$scope.$parent.url = 'add';
	$scope.$parent.iconBtn = 'fa-plus-square';
	$scope.$parent.headerTitle = 'User Listing';
	$scope.data = {};
	$scope.data.users = loadUsers.users;
	
	$scope.deleteUser = function(userId) {
		
		var deleteUser = confirm('Are you sure you want to delete this USER?');
	    if(!deleteUser) return;
		
		$http.get(urlPrefix + '/restAct/user/deleteUser?userId=' + userId)
		.then(function(data) {
    		if(data.data.statusCode != 9999) {
    			$rootScope.systemAlert(data.data.statusCode);
    			return;
    		}	    		
    		
    		$rootScope.systemAlert(data.data.statusCode, 'Delete User Success');
    		$scope.data.users = data.data.users;
	    }, function(response) {
	    	$rootScope.systemAlert(response.status);
	    });
	}
	
	$scope.editUser = function(user) {
		$state.go('dashboard.user.add', {user: user});
	}
	
});
