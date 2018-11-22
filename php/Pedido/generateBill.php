<?php
    $host_name='localhost';
    $db_name='u371424461_rest';
    $db_user_name='u371424461_adm';
    $db_password='adm123';

    if ($_SERVER['REQUEST_METHOD'] == 'POST') {

        $IdPedido=$_POST['IdPedido']; 
        $TarjetaBancaria=$_POST['TarjetaBancaria']; 
        $FormaPago=$_POST['FormaPago']; 
		$Total=$_POST['Total']; 
      
		$connection = mysqli_connect($host_name,$db_user_name,$db_password,$db_name);
      
		$query="INSERT INTO Factura VALUES (NULL,'{$IdPedido}','Pagado','{$Total}','{$FormaPago}','{$TarjetaBancaria}','Orden pagada vía Express')";
		$ret = mysqli_query($connection,$query);
		if($ret) {
		  mysqli_close($connection);
		  print json_encode(array('status'=>'true'));
		} else {
	
		  mysqli_close($connection);
		  print json_encode(array('status'=>'false'));
		}
    } else { print json_encode(array('status'=>'false')); }
?>