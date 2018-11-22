<?php
    require 'ClassUsuario.php';
	
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        $Nombre=$_POST['Nombre']; 
        $Apellido=$_POST['Apellido']; 
        $Cedula=$_POST['Cedula']; 
		$Telefono=$_POST['Telefono']; 
        try {
			$ret = Usuario::insert_client($Nombre, $Apellido, $Cedula, $Telefono);
			if ($ret) {
				$data['status']='true';
				$data['value']=$ret;
				print json_encode($data);
			} else {
				print json_encode(array('status'=>'false'));
			}
        } catch (PDOException $e) {
            print $e;
        	//print json_encode(array('status'=>'false'));
        }
    }    
?>