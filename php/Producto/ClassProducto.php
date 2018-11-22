<?php
require 'ClassConnection.php';

class Producto {
    function __construct() {

    }
    /* __________ READS _____________________________________________________ */
    /* Obtiene un producto según el nombre*/
    public static function select_product_by_name($productName) {
        $query = "SELECT * FROM Producto p WHERE p.Nombre = '{$productName}'";
        $command = DB::getInstance()->getDB()->prepare($query);
        $command->execute();
        return $command->fetchAll(PDO::FETCH_ASSOC);
    }
    
    /* Verifica si un producto esta asociado a un pedido*/
    public static function is_product_assigned($productId) {
        $query = "SELECT COUNT(IdProd) FROM Producto p INNER JOIN `Pedido-Producto` pp on pp.IdProdcut = p.IdProd AND p.IdProd = '{$productId}'";
        $command = DB::getInstance()->getDB()->prepare($query);
        $command->execute();
        return $command->fetchAll(PDO::FETCH_ASSOC);
    }
	
	/* Obtiene todos los productos según el Id de pedido*/
	public static function select_product_by_order($orderID) {
        $query = "SELECT pp.Cantidad,p.Nombre,p.PrecioUnitario FROM `Pedido-Producto` pp inner join Producto p on pp.IdProducto = p.IdProd WHERE pp.IdPedido = '{$orderID}'";
        $command = DB::getInstance()->getDB()->prepare($query);
        $command->execute();
        return $command->fetchAll(PDO::FETCH_ASSOC);
    }
	
    /* Obtiene todos los productos según el tipo de producto*/
	public static function select_product_by_productType($productType) {	
		$query = "SELECT p.IdProd, p.Nombre, p.Detalle, p.PrecioUnitario, p.TipoProducto, p.Estado
				  FROM Producto p
				  WHERE p.TipoProducto = '{$productType}'";
		$command = DB::getInstance()->getDB()->prepare($query);
		$command->execute();
		return $command->fetchAll(PDO::FETCH_ASSOC);	
    }
	
	/* __________ INSERTS ___________________________________________________ */
    /* Inserta un producto */
    public static function insert_product($Name, $Description, $Prize, $Type) {
        $query = "INSERT INTO Producto VALUES (NULL,'{$Name}','{$Description}','{$Prize}','{$Type}',1)";
        $command = DB::getInstance()->getDB()->prepare($query);
        $command->execute(array($muestraName, $creationDate, $numObserv, $muestraDescription, $operationName));
        return $command;
    }
}
?>
