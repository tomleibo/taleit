app.controller('MainController', ['$scope', 'categories', function($scope, categories) {
    categories.success(function(data) {
        $scope.hello = data;
    });
}]);