<?php

include_once "Connection.php";
/**
 * Class ConnectionFactory
 */
class ConnectionFactory
{
    public static function create()
    {
        $instance = null;
        try {
            $instance = new Connection('localhost', 'root', 'root', 'pw');
        } catch (PDOException $e) {
            print_r($e);
        }
        return $instance;
    }
}