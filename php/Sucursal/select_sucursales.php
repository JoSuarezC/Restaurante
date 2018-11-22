<?php
    require 'ClassSucursal.php';
    try {
    	$ret = Sucursal::selectAll_Sucursales();
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
?>