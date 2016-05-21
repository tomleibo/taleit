/**
 * Created by sharonk on 5/20/2016.
 */
app.controller('MainController', ['$scope', function($scope) {
    $scope.title = 'Tale-it categories';
    $scope.promo = 'what a wonderful categories';
    $scope.products = [
        {
            name: 'Horror',
            pubdate: new Date('2014', '03', '08'),
            cover: 'img/the-book-of-trees.jpg',
            likes : 0,
            dislikes: 0

        },
        {
            name: 'Adults',
            pubdate: new Date('2013', '08', '01'),
            cover: 'img/program-or-be-programmed.jpg',
            likes :0,
            dislikes: 0

        },
        {
            name: 'Adventure',
            pubdate: new Date('2014', '03', '08'),
            cover: 'img/the-book-of-trees.jpg',
            likes: 0,
            dislikes: 0

        },
        {
            name: 'Comedy',
            pubdate: new Date('2013', '08', '01'),
            cover: 'img/program-or-be-programmed.jpg',
            likes: 0,
            dislikes: 0

        }
    ];


}]);