<?php
    require 'ClassUsuario.php';
	
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        $NombreUsuario=$_POST['NombreUsuario']; 
        $Contrasenia=$_POST['Contrasenia']; 
        $Correo=$_POST['Correo'];
		$IdCliente=$_POST['IdCliente'];	
        try {
			$ret = Usuario::create_user($NombreUsuario, $Contrasenia, $Correo, $IdCliente);
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