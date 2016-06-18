(function () {
    var app = angular.module('taleItApp', ['ui.bootstrap', 'ngRoute', 'ngCookies']);

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

            .when('/stories/:storyId/:paragraphId', {
                templateUrl: '/html/pages/story-page.html'
            })

            .when('/create', {
                templateUrl: '/html/pages/create.html'
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

    app.controller('StoriesController', ['$http', '$routeParams', '$scope', '$cookies', '$rootScope', function ($http, $routeParams, $scope, $cookies, $rootScope) {
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

        $scope.saveInfo = function (story) {
            $cookies.put("title", story.title);
            $cookies.put("id", story.id);

        };
    }]);

    app.controller('StoryViewerCtrl', ['$http', '$routeParams', '$scope', '$cookies', function ($http, $routeParams, $scope, $cookies) {
        var browseUrl = 'http://127.0.0.1:8080/rest/stories/view';
        $scope.storyTitle = $cookies.get("title");
        $scope.storyId = $cookies.get("id");
        $scope.storyInfo = [];
        $scope.amountOfParagraphsToShow = 4;

        var storyIdResults = $routeParams.storyId;
        var paragraphIdResults = $routeParams.paragraphId;

        if(paragraphIdResults){
            $http.get(browseUrl + '?storyId=' + storyIdResults + '&paragraphId=' + paragraphIdResults).success(function (data) {
                $scope.storyInfo = data;
            });
        }
        else{
            $http.get(browseUrl + '?storyId=' + storyIdResults).success(function (data) {
                $scope.storyInfo = data;
            });
        }



    }]);

    app.directive("titleSection", function () {
        return {
            restrict: "E",
            templateUrl: "/html/title-section.html"
        };
    });

    app.directive("menuBar", function () {
        return {
            restrict: "E",
            templateUrl: "/html/menu-bar.html"
        };
    });

    app.directive("facebookButton", function () {
        return {
            restrict: "E",
            templateUrl: "/html/facebook-button.html"
        };
    });

    app.directive("displayElement", function () {
        return {
            restrict: "E",
            scope: {
                title: '@',
                linkToUrl: '@',
                changeText: '@',
                imgSrc: '@',
                optionalTitle: '@'
            },
            templateUrl: "/html/display-element.html"

        };
    });

    app.directive("createButton", function () {
        return {
            restrict: "E",
            scope: {
                text: '@',
                linkToUrl: '@'
            },
            templateUrl: "/html/create-button.html"

        };
    });

    app.directive("storyForm", function () {
        return {
            restrict: "E",
            templateUrl: "/html/story-form.html"
        };
    });

})();



