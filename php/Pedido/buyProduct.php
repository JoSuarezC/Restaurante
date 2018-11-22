<?php
    $host_name='localhost';
    $db_name='u371424461_rest';
    $db_user_name='u371424461_adm';
    $db_password='adm123';

    if ($_SERVER['REQUEST_METHOD'] == 'POST') {

        $IdProducto=$_POST['IdProducto']; 
        $IdPedido=$_POST['IdPedido']; 
        $Cantidad=$_POST['Cantidad']; 
		$Precio=$_POST['Precio']; 
      
		$connection = mysqli_connect($host_name,$db_user_name,$db_password,$db_name);
      
		$query="INSERT INTO `Pedido-Producto` VALUES ('{$IdPedido}','{$IdProducto}','{$Cantidad}','{$Precio}')";
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