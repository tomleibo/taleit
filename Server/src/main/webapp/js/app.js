(function () {
    var app = angular.module('taleItApp', ['ui.bootstrap']);

    app.controller('CategoriesController', ['$http', function ($http) {
        var catList = this;
        catList.categoriesResult = [];

        $http.get('http://127.0.0.1:8080/rest/stories/categories').success(function (data) {
            catList.categoriesResult = data;
        });
    }]);

    app.directive("categoriesSection", function () {
        return {
            restrict: "E",
            templateUrl: "/html/categories-section.html"
        };
    });

    app.controller('StoriesController', ['$http', '$location', function ($http, $location) {
        this.getURLParameter = function(sParam) {
            var sPageURL = window.location.search.substring(1);
            var sURLVariables = sPageURL.split('&');
            for (var i = 0; i < sURLVariables.length; i++) {
                var sParameterName = sURLVariables[i].split('=');
                if (sParameterName[0] == sParam) {
                    return sParameterName[1];
                }
            }
            return null;
        }

        var storiesList = this;
        var browseUrl = 'http://127.0.0.1:8080/rest/stories/browse';

        storiesList.storiesResult = [];

        var searchResults = this.getURLParameter('category');

        if (searchResults === null) {
            $http.get(browseUrl).success(function (data) {
                storiesList.storiesResult = data;
            });
        }
        else {
            var postFixUrl = '?category=' + searchResults;
            $http.get(browseUrl + postFixUrl).success(function (data) {
                storiesList.storiesResult = data;
            });
        }


    }]);

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



