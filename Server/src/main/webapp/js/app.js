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
            .when('/continue/:storyId/:paragraphId', {
                templateUrl: '/html/pages/continue.html'
            })
            .otherwise({templateUrl: '/html/pages/default.html'});
    }]);

    app.controller('CategoriesController', ['$http', function ($http) {
        var catList = this;
        catList.categoriesResult = [];

        $http.get(window.urlApiCategories).success(function (data) {
            catList.categoriesResult = data;
        });
    }]);

    app.controller('StoriesController', ['$http', '$routeParams', function ($http, $routeParams) {
        var storiesList = this;
        var browseUrl = window.urlApiBrowse;

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

    app.controller('StoryViewerCtrl', ['$http', '$routeParams', '$scope', '$cookies', function ($http, $routeParams, $scope, $cookies) {
        var browseUrl =  window.urlApiView;
        $scope.storyInfo = [];
        $scope.amountOfParagraphsToShow = 4;

        var storyIdResults = $routeParams.storyId;
        var paragraphIdResults = $routeParams.paragraphId;

        if (paragraphIdResults) {
            $http.get(browseUrl + '?storyId=' + storyIdResults + '&paragraphId=' + paragraphIdResults).success(function (data) {
                $scope.storyInfo = data;
                $scope.authorId = $scope.storyInfo.data.userFacebookId;
            });
        }
        else {
            $http.get(browseUrl + '?storyId=' + storyIdResults).success(function (data) {
                console.log(browseUrl + '?storyId=' + storyIdResults)
                $scope.storyInfo = data;
                $scope.authorId = $scope.storyInfo.data.userFacebookId;
            });
        }




    }]);

    app.controller('createStoryFormCtrl', ['$scope', '$http', '$window', '$location', function ($scope, $http, $window, $location) {
        $scope.formData = {};

        $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8";

        $scope.go = function () {
            window.history.back();
        };

        $scope.sendPost = function () {
            if (window.userCookie == "") {
                alert("Only logged in members can create new stories, please log in via Facebook")
            }
            else {

                $http({
                    url:  window.urlApiCreate,
                    method: "POST",
                    data: {
                        'cookie': window.userCookie,
                        'title': $scope.formData.formStoryTitle,
                        'category': $scope.formData.formCategory,
                        'rootParagraph': {
                            'title': $scope.formData.formTitle,
                            'text': $scope.formData.formText
                        }
                    }
                }).then(function (response) {
                    console.log(response.data);
                    $scope.message = response.data;

                    $window.location.href="/#/stories/" + response.data.data.storyId;
                }, function (response) {
                    //fail case
                    console.log(response);
                    $scope.message = response;
                });
            }

        };
    }]);

    app.controller('continueStoryFormCtrl', ['$scope', '$http', '$routeParams', '$window', '$location', function ($scope, $http, $routeParams, $window, $location) {
        $scope.formData = {};

        $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8";


        $scope.go = function () {
            window.history.back();
        };

        var storyIdResults = $routeParams.storyId;
        var paragraphIdResults = $routeParams.paragraphId;

        console.log("cookie = " + window.userCookie);
        console.log("title = " + $scope.formData.formTitle);
        console.log("text = " + $scope.formData.formText);
        console.log("paragraphId = " + paragraphIdResults);
        console.log("storyId = " + storyIdResults);


        $scope.sendPost = function () {
            if (window.userCookie == "") {
                alert("Only logged in members can create new stories, please log in via Facebook")
            }
            else {
                $http({
                    url: window.urlApiContinue,
                    method: "POST",
                    data: {
                        'cookie': window.userCookie,
                        'title': $scope.formData.formTitle,
                        'text': $scope.formData.formText,
                        'paragraphId': paragraphIdResults,
                        'storyId': storyIdResults
                    }
                }).then(function (response) {
                    $window.location.href = '/#/stories/'+storyIdResults+'/'+response.data.data.paragraphId;
                }, function (response) {
                    //fail case
                });

            }
        };
    }]);

    app.controller('displayElementCtrl', ['$scope', function ($scope) {
        var imageExists = function (image_url) {

            var http = new XMLHttpRequest();

            http.open('HEAD', image_url, false);
            http.send();

            return http.status != 404;

        };

        $scope.getImgSrc = function (imgSrc) {
            if (imageExists(imgSrc)) {
                return imgSrc
            }
            else {
                return "/resources/coolBlackBG.jpg"
            }

        };
    }
    ]);

    app.filter('capitalize', function() {
        return function(input) {
            return (!!input) ? input.charAt(0).toUpperCase() + input.substr(1).toLowerCase() : '';
        }
    });

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

    app.directive("continueButton", function () {
        return {
            restrict: "E",
            templateUrl: "/html/continue-button.html",
            scope: {
                buttonTitle: '@',
                paragraphId: '@',
                storyId: '@'

            }
        };
    });

    app.directive("createStoryForm", function () {
        return {
            restrict: "E",
            templateUrl: "/html/create-story-form.html"
        };
    });

    app.directive("continueStoryForm", function () {
        return {
            restrict: "E",
            templateUrl: "/html/continue-story-form.html"
        };
    });

})();



