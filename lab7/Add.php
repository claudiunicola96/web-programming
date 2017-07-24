<?php

include_once "ConnectionFactory.php";

/**
 * Class Add
 */
class Add
{
    private $team1;
    private $team2;
    private $scoredTeam1;
    private $receivedTeam1;
    private $connection;

    /**
     * Add constructor.
     * @param $team1
     * @param $team2
     * @param $scoredTeam1
     * @param $receivedTeam1
     */
    public function __construct($team1, $team2, $scoredTeam1, $receivedTeam1)
    {
        $this->team1 = $team1;
        $this->team2 = $team2;
        $this->scoredTeam1 = $scoredTeam1;
        $this->receivedTeam1 = $receivedTeam1;
        $this->connection = ConnectionFactory::create()->getConnection();
    }

    public function save()
    {
        $team1 = $this->getTeamByName($this->team1);
        $team2 = $this->getTeamByName($this->team2);

        $team1['goals_scored'] += $this->scoredTeam1;
        $team1['goals_received'] += $this->receivedTeam1;
        $team1['played'] += 1;
        $team2['played'] += 1;
        $team2['goals_scored'] += $this->receivedTeam1;
        $team2['goals_received'] += $this->scoredTeam1;

        if ($this->scoredTeam1 == $this->receivedTeam1) {
            $team1['score'] += 1;
            $team2['score'] += 1;
            $team1['draws'] += 1;
            $team2['draws'] += 1;
        } elseif ($this->scoredTeam1 > $this->receivedTeam1) {
            $team1['score'] += 3;
            $team1['wins'] += 1;
            $team2['defeats'] += 1;
        } else {
            $team2['score'] += 3;
            $team1['defeats'] += 1;
            $team2['wins'] += 1;
        }

        try {
            $this->connection->beginTransaction();
            $this->saveTeam($team1);
            $this->saveTeam($team2);
            $this->connection->commit();
        } catch (Exception $e) {
            $this->connection->rollBack();
            print_r($e->getTraceAsString());
        }

    }

    protected function getTeamByName($name)
    {
        $query = "select * from teams where name=:name";
        $statement = $this->connection->prepare($query);
        $statement->bindParam(':name', $name);
        $statement->execute();

        return $statement->fetch();
    }

    protected function saveTeam($team)
    {
        $query = "update teams set " .
            "played=:played," .
            "wins=:wins," .
            "draws=:draws," .
            "defeats=:defeats," .
            "score=:score," .
            "goals_scored=:scored," .
            "goals_received=:received " .
            "where name=:name";
        $statement = $this->connection->prepare($query);

        $statement->bindParam(':played', $team['played']);
        $statement->bindParam(':wins', $team['wins']);
        $statement->bindParam(':draws', $team['draws']);
        $statement->bindParam(':defeats', $team['defeats']);
        $statement->bindParam(':score', $team['score']);
        $statement->bindParam(':name', $team['name']);
        $statement->bindParam(':scored',$team['goals_scored']);
        $statement->bindParam(':received', $team['goals_received']);

        $statement->execute();
    }
}
