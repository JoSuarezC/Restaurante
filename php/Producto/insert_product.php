<?php
    require 'ClassProducto.php';
	
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        $Nombre=$_POST['Nombre']; 
        $Tipo=$_POST['Tipo']; 
        $Descripcion=$_POST['Descripcion']; 
		$Precio=$_POST['Precio']; 
        try {
			$ret = Producto::insert_product($Nombre, $Descripcion, $Precio, $Tipo);
			if ($ret) {
				print json_encode(array('status'=>'true'));
			} else {
				print json_encode(array('status'=>'false'));
			}	
        } catch (PDOException $e) {
        	print json_encode(array('status'=>'false'));
        }
    }    
?>