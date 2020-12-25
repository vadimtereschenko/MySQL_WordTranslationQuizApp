<?php 

if($_SERVER["REQUEST_METHOD"]=="POST") {
    require 'connection.php';
    createCoupleWords();
}

function createCoupleWords() {
    global $connect;

    $word = $_POST["word"];
    $translation = $_POST["translation"];
    $passed = $_POST["passed"];

    $query = " Insert into vocabulary(word,translation,passed) values ('$word','$translation','$passed');";
    mysqli_query($connect, $query) or die (mysqli_error($connect));
    mysqli_close($connect);
}

?>