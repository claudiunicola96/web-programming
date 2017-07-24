<?php
include_once "Add.php";

if (isset($_POST['save'])) {
    $team1 = filter_var($_POST['name1'], FILTER_SANITIZE_STRING);
    $team2 = filter_var($_POST['name2'], FILTER_SANITIZE_STRING);
    $scored = filter_var($_POST['goals_scored'], FILTER_SANITIZE_NUMBER_INT);
    $received = filter_var($_POST['goals_received'], FILTER_SANITIZE_NUMBER_INT);

    $add = new Add($team1, $team2, $scored, $received);
    $add->save();
    header("Location: ranking.phtml");
    exit();
}

header("Location: add.html");
exit();