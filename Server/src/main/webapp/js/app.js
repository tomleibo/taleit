(function () {
    var app = angular.module('taleItApp', ['ui.bootstrap', 'ngRoute']);

    app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider

            // route for the home page
            .when('/', {
                templateUrl: '/html/pages/main.html'
            })

            // route for the about page
            .when('/categories/:categoryValue', {
                templateUrl: '/html/pages/category-page.html'
            })

            .when('/stories/:storyId', {
                templateUrl: '/html/pages/story-page.html'
            })
            .otherwise({templateUrl: '/html/pages/default.html'})
    }]);

    app.controller('CategoriesController', ['$http', function ($http) {
        var catList = this;
        catList.categoriesResult = [];

        $http.get('http://127.0.0.1:8080/rest/stories/categories').success(function (data) {
            catList.categoriesResult = data;
        });
    }]);

    app.controller('StoriesController', ['$http', '$routeParams', function ($http, $routeParams) {
        var storiesList = this;
        var browseUrl = 'http://127.0.0.1:8080/rest/stories/browse';

        storiesList.storiesResult = [];

        var searchResults = $routeParams.categoryValue;

        if (searchResults === null) {
            $http.get(browseUrl).success(function (data) {
                storiesList.storiesResult = data;
            });
        }
        else {
            $http.get(browseUrl + '?category=' + searchResults).success(function (data) {
                storiesList.storiesResult = data;
            });
        }

    }]);

    app.controller('StoryViewerCtrl', ['$http', '$routeParams', function ($http, $routeParams) {
        var storyInfo = this;
        var browseUrl = 'http://127.0.0.1:8080/rest/stories/view/';

        storyInfo = [];

        var searchResults = $routeParams.storyId;

        $http.get(browseUrl + '?storyId=' + searchResults).success(function (data) {
            storyInfo = data;
        });


    }]);

    app.directive("categoriesSection", function () {
        return {
            restrict: "E",
            templateUrl: "/html/categories-section.html"
        };
    });

    app.directive("storiesSection", ['$http', function () {
        return {
            restrict: "E",
            templateUrl: "/html/stories-section.html"
        };
    }]);

    app.directive("titleSection", function () {
        return {
            restrict: "E",
            templateUrl: "/html/title-section.html"
        };
    });

})();



