<?php
    require 'ClassProducto.php';
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {  
		$productId = $_POST['Id'];
        try {
			$ret = Producto::is_product_assigned($productId);
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