/**
 * Created by sharonk on 5/21/2016.
 */
app.factory('categories', ['$http', function($http) {
    return $http.get('http://127.0.0.1:8080/rest/stories/categories')
        .success(function(data) {
            return data;
        })
        .error(function(err) {
            return err;
        });
}]);