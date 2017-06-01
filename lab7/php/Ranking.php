<?php

include_once "ConnectionFactory.php";

/**
 * Class Ranking
 */
class Ranking
{

    public function getRanking()
    {
        return $this->getAllTeamsOrderByScore();
    }

    protected function getAllTeamsOrderByScore()
    {
        $connection = ConnectionFactory::create()->getConnection();
        $query = "select * from teams order by score desc";
        $statement = $connection->prepare($query);
        $statement->execute();

        return $statement->fetchAll();
    }

    protected function sortBasedOnScore()
    {

    }

}
