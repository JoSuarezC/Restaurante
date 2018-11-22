<?php
    $host_name='localhost';
    $db_name='u371424461_rest';
    $db_user_name='u371424461_adm';
    $db_password='adm123';

    if (isset($_GET['IdPedido'])) {  
    
        $IdPedido = $_GET['IdPedido'];
     
        $connection = mysqli_connect($host_name,$db_user_name,$db_password,$db_name);
        
        $query_temp = "UPDATE Pedido SET Estado = 'Entregado' WHERE IdPedido='{$IdPedido}'";
    	$result = mysqli_query($connection,$query_temp);
    	$row = mysqli_num_rows($result);
    
    	if($row == 1){
			print json_encode(array('status'=>'false'));
    	}
		else{
			print json_encode(array('status'=>'true'));
		}
	
    }else { print json_encode(array('status'=>'false')); }
?>