
(function() {
    var app = angular.module('taleItApp', ['ui.bootstrap']);

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

    app.controller('StoriesController', ['$http', function($http){
        var storiesList = this;

        var browseUrl = 'http://127.0.0.1:8080/rest/stories/browse';
        storiesList.storiesResult = [];
        storiesList.storyByCategoryList = [];

        $http.get(browseUrl).success(function(data){
            storiesList.storiesResult = data;
        });

        this.getStoriesFromCategory = function (categoryStr){
            var postFixUrl = '?category='+categoryStr;
            $http.get(browseUrl+postFixUrl).success(function(data){
                storiesList.storiesResult = data;
            });
        }
    }]);

    app.directive("storiesSection", ['$http', function() {
        return {
            restrict: "E",
            templateUrl: "/html/stories-section.html"
        };
    }]);

    app.directive("titleSection", function() {
        return {
            restrict: "E",
            templateUrl: "/html/title-section.html"
        };
    });

})();



