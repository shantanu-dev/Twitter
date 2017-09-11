/**
 * Created by shantanu on 10/8/16.
 */
app = angular.module('myapp', ['ngRoute']  );
app.config(['$routeProvider',function($routeProvider){
    $routeProvider

        .when('/Insert',{
            templateUrl: 'insertUser.html'})
        .when('/Display',{
            templateUrl: 'displayUser.html'})
        .when('/Tweet',{
            templateUrl: 'tweets.html'})
        .when('/disp',{
            templateUrl: 'tweetDisp.html'})
        .when('/Follower',{
            templateUrl: 'follower.html'})
        .when('/Follow',{
            templateUrl: 'follow.html'})
        .when('/Following',{
            templateUrl: 'following.html'})
        .otherwise(
            { redirectTo: '/'}
        );
}]);
