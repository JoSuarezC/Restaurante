<?php
    $host_name='localhost';
    $db_name='u371424461_rest';
    $db_user_name='u371424461_adm';
    $db_password='adm123';

    if ($_SERVER['REQUEST_METHOD'] == 'POST') {

        $Ejemplo=$_POST['Ejemplo']; // Id Creador
      /*  $Nombre=$_POST['Nombre']; // Comunidad
		$Descripcion=$_POST['Descripcion'];
        $Provincia=$_POST['Provincia'];
    	$Canton=$_POST['Canton'];*/
        
	//	$connection = mysqli_connect($host_name,$db_user_name,$db_password,$db_name);
      
	/*	$query="INSERT INTO COMUNIDAD VALUES(NULL,'{$IdPersona}','{$Provincia}','{$Canton}','{$Nombre}','{$Descripcion}')";
		$ret = mysqli_query($connection,$query);
		if($ret) {
	
		  mysqli_close($connection);
		  print json_encode(array('status'=>'true'));
	
		} else {
	
		  mysqli_close($connection);
		  print json_encode(array('status'=>'false'));
	
		}*/

         
    } else { print json_encode(array('status'=>'false')); }
?>
