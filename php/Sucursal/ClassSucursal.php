<?php
require 'ClassConnection.php';

class Sucursal {
    function __construct() {

    }
    /* __________ READS _____________________________________________________ */
    /* Obtiene todas las sucursales*/
    public static function selectAll_Sucursales() {
        $query = "SELECT * FROM Sucursal";
        $command = DB::getInstance()->getDB()->prepare($query);
        $command->execute();
        return $command->fetchAll(PDO::FETCH_ASSOC);
    }
}
?>
