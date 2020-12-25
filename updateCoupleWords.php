<?php 

if($_SERVER["REQUEST_METHOD"]=="POST") {

    include 'connection.php';

    $con = mysqli_connect(hostname, user, password, databaseName);

    $id = $_POST['id'];
    $S_word = $_POST['word'];
    $S_translation = $_POST['translation'];
    $S_passed = $_POST['passed'];

    $Sql_Query = "UPDATE vocabulary set word= '$S_word', translation = '$S_translation', passed = '$S_passed' WHERE id=$id";

    if(mysqli_query($con, $Sql_Query)) {
        echo 'Record Updated Successfully';
    } else {
        echo 'Something went wrong';
    }
}
mysqli_close($con);

?>