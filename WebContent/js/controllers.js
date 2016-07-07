'use strict';

/* Controllers */

angular
  .module('BeerControllers', [])
  .controller('BeerListCtrl', ['$scope', '$http', function($scope, $http) {

    $http.get('BeerList').success(function(data) {
      $scope.beers = data;
    });

    $scope.orderProp = 'alcohol';
  }])
  .controller('BeerDetailCtrl', ['$scope', '$routeParams', '$http', function($scope, $routeParams, $http) {
    $http.get('BeerDetail?id=' + $routeParams.beerId).success(function(data) {
      $scope.beer = data;
    });
  }])
  
  .controller('BeerCreateCtrl',  ['$scope', '$http', '$location', function($scope, $http, $location) {
 $scope.submit = function() {
      /*$http.post('BeerAdd?id=' + $routeParams.beerId ).success(function(data){
          console.log("submit");
          $scope.beer = data;
      });*/
      console.log("submit " + $scope.name);
      $http({
          method: 'POST',
          url: 'BeerCreate?',
          data: {
              'name': $scope.name,
              'alcohol': $scope.alcohol,
              'description': $scope.description,
              'id': $scope.id,
              'img': $scope.img,
              'availability': $scope.availability,
              'bewery': $scope.bewery,
              'label': $scope.label,
              'serving': $scope.serving,
              'style': $scope.style
              }
          });
      
      success(function(data) {location.path("/beers")} );
    }
}]);

