
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
            templateUrl: "/html/categories-section.html",
            controller: function($http) {
                this.regular = '1';

                this.isSet = function(checkRegular) {
                    return this.regular === checkRegular;
                };

                this.setTab = function(activeRegular) {
                    this.regular = activeRegular;
                };
            },
            controllerAs: "regular"
        };
    });

    app.controller('StoriesController', ['$http', function($http){
        var storiesList = this;
        storiesList.storiesResult = [];

        $http.get('http://127.0.0.1:8080/rest/stories/browse').success(function(data){
            storiesList.storiesResult = data;
        });
    }]);

    app.directive("storiesSection", ['$http', function() {
        return {
            restrict: "E",
            templateUrl: "/html/stories-section.html"
        };
    }]);
})();



