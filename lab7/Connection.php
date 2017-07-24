<?php

/**
 * Class Connection
 */
class Connection
{
    private $connection;
    private $host;
    private $username;
    private $password;
    private $database;

    /**
     * Connection constructor.
     * @param $host
     * @param $username
     * @param $password
     * @param $database
     */
    public function __construct($host, $username, $password, $database)
    {
        $this->host = $host;
        $this->username = $username;
        $this->password = $password;
        $this->database = $database;
    }

    public function getConnection()
    {
        $this->connection = new PDO('mysql:dbname=' . $this->database, $this->username, $this->password);
        return $this->connection;
    }
}
