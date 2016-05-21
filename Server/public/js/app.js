/**
 * Created by sharonk on 5/20/2016.
 */
var app = angular.module('taleItApp', []);


app.controller('CategoriesControllerOne', function() {
    this.categoryInstancesOne = allCategories;
});

app.controller('CategoriesController', ['$http', function($http){
    var catSection = this;
    catSection.categoryInstances = [];

    $http.get('/store-products.json').success(function(data){
        catSection.categoryInstances = data;
    });
}]);

var allCategories = [
    {
        name: 'Horror',
        images: [
            "images/gem-02.gif",
            "images/gem-05.gif",
            "images/gem-09.gif"
        ]
    }, {
        name: 'Comedy',
        images: [
            "images/gem-02.gif",
            "images/gem-05.gif",
            "images/gem-09.gif"
        ]
    },{
        name: 'Adults',
        images: [
            "images/gem-02.gif",
            "images/gem-05.gif",
            "images/gem-09.gif"
        ]
    }
];