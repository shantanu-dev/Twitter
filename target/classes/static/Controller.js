/**
 * Created by shantanu on 10/8/16.
 */
//===============================Insert User Controller ====================================//


app.controller("Insert", function($scope , $http) {


    $scope.submit = function( ) {

        if($scope.name==undefined||$scope.password==undefined||$scope.email==undefined ||$scope.gender==undefined ||
            $scope.name==""||$scope.password==""||$scope.email=="" ||$scope.gender=="")

        {

             $scope.message="fields required by user ";

        }

       else {

            console.log("Insert");
            var name = $scope.name;
            var password = $scope.password;
            var gender = $scope.gender;
            var email = $scope.email;
            var biodetail = $scope.biodetail;



            var datauser = {
                "name": name,
                "email": email,
                "password": password,
                "gender": gender,
                "biodetail": biodetail,


            }
console.log(datauser);

            $scope.name="";
            $scope.password="";
            $scope.email="" ;
            $scope.gender="";
            $scope.roles="";
            $scope.biodetail="";



            var url = "http://localhost:8080/Insert";
            $http({
                url: url,
                data: datauser,
                dataType: 'json',
                method: 'POST',
                headers: {
                    "Content-Type": "application/json"
                }
            }).success(function(response){
                console.log(response);
                $scope.statusTrue=response.status;

            }).error(function(response) {
                console.log(response);
                $scope.statusFalse = response.status;
            })


         $scope.output="Successful registration";
        }}
        });



//=========================Display User Controller ================================///



app.controller("Display", function($scope , $http)
{
    $scope.submit = function () {

        console.log("Display");

        var url = "http://localhost:8080/display";

        $http.get(url).success(function (response) {
            console.log(response);

            if (response.length == 0)
                $scope.role= "Does not Exist";

            else
                $scope.role = response;

            console.log($scope.role);
        });
    }});


//====================tweeting Controller =======================================================//



app.controller("Tweet", function($scope,$http){

    $scope.submit = function () {

        if($scope.content==undefined || $scope.content=="")
        {  console.log("tweet field required") ;
            $scope.message="No Tweet ! :(";
        }
        else {
            console.log("Tweet");
            var content = $scope.content;
            var count = content.length;
            console.log(count);
            if (count > 300) {
              //  window.alert("length exceeded");
                $scope.message = "Oops ! Tweet Too Long ";
            }
            else {


                var url = "http://localhost:8080/tweetInsert";
                $http({
                    url: url,
                    data: content,
                    dataType: 'json',
                    method: 'POST',
                    headers: {
                        "Content-Type": "application/json"
                    }
                });
                $scope.message="Tweeted !";
               // window.alert("submitted");

        }}
    }});



//=====Tweet Display Controller =====================================================//

app.controller("tweetDisplay", function($scope , $http)
{
    $scope.submit = function () {

        console.log("tweetDisplay");

        var url = "http://localhost:8080/tweetDisplay";

        $http.get(url).success(function (response) {
            console.log(response);

            if (response.length == 0)
                $scope.role= "Does not Exist";

            else
                $scope.role = response;

            console.log($scope.role);
        });
    }});




// ========Follow Controller =========================================================================//



app.controller("Follow", function ($scope, $http) {


    $scope.findusers = function () {

        var url = "http://localhost:8080/display";

        $http.get(url).success(function (response) {

            $scope.role = response;

        })


        $scope.submit = function () {

            var following = $scope.following;

            var abc = {
                "following": following,
            }
            console.log(abc);
            var url = "http://localhost:8080/tofollow";
            $http({
                url: url,
                data: abc,
                dataType: 'json',
                method: 'POST',
                headers: {
                    "Content-Type": "application/json"
                }
            }).success(function(response){
                console.log(response);
                $scope.statusTrue=response.status;

            }).error(function(response) {
                console.log(response);
                $scope.statusFalse = response.status;
            })


            console.log("followed");
            //window.alert("Followed");
        }
    }
});








//========================follower list controller  ===============================================//\



app.controller("Follower", function ($scope, $http) {

    $scope.getfollowers=function() {

        var url = "http://localhost:8080/getfollower";

        $http.get(url).success(function (response) {

            $scope.follower = response;
            console.log($scope.follower);

        })

    }
});




//======================= following Controller ========================================//

//==========person whom i follow =========//


app.controller("Following", function ($scope, $http) {

    $scope.findfollowing=function(){

            var url = "http://localhost:8080/findfollowing";

            $http.get(url).success(function (response) {


                $scope.following = response;
                console.log($scope.following);

            });
        }});
