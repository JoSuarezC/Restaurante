<?php
    require 'ClassProducto.php';
	
    if (isset($_GET['IdPedido'])) {  
        $IdPedido = $_GET['IdPedido'];
        try {
			$ret = Producto::select_product_by_order($IdPedido);
			if ($ret) {
				$data['status']='true';
				$data['value']=$ret;
				print json_encode($data);
			} else {
				print json_encode(array('status'=>'false'));
			}	
        } catch (PDOException $e) {
        	print json_encode(array('status'=>'false'));
        }
    }    
?>