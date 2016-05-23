
(function() {
    var app = angular.module('taleItApp', []);

    app.controller('CategoriesController', ['$http', function($http){
        var catList = this;
        catList.categoriesResult = [];

        $http.get('http://127.0.0.1:8080/rest/stories/categories').success(function(data){
            catList.categoriesResult = data;
        });
    }]);

    app.directive("categoriesSection", function() {
        return {
            restrict: "E",
            templateUrl: "/html/categories-section.html"
        };
    });
})();
