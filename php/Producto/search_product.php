<?php
    require 'ClassProducto.php';
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {  
		$NombreProducto = $_POST['Nombre'];
        try {
			$ret = Producto::select_product_by_name($NombreProducto);
			$row = mysqli_num_rows($ret);
			if($row == 1){
				print json_encode(array('status'=>'false'));
			}
			else{
				print json_encode(array('status'=>'true'));
			}
			
        } catch (PDOException $e) {
        	print json_encode(array('status'=>'false'));
        }
    }    
?>