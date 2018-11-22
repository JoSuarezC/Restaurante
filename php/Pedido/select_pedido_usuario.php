<?php
    $host_name='localhost';
    $db_name='u371424461_rest';
    $db_user_name='u371424461_adm';
    $db_password='adm123';

    if (isset($_GET['IdCliente'])) {  
    
        $IdCliente = $_GET['IdCliente'];
     
        $connection = mysqli_connect($host_name,$db_user_name,$db_password,$db_name);
        
        $query = "SELECT * FROM Pedido p WHERE p.IdCliente = '{$IdCliente}'";
		try{
			$ret=mysqli_query($connection,$query);
			$rows = array();
			$rows['status']='true';
			if ($ret) {
				while($row = mysqli_fetch_assoc($ret)) {
				  $rows['value'][] = $row;
				}

				mysqli_close($connection);
				print json_encode($rows);

			} else {
				mysqli_close($connection);
				print json_encode(array('status'=>'false'));
			}
		}catch (PODException $e) {
		  mysqli_close($connection);
		  print json_encode(array('status'=>'false'));
		}
    	
    }else { print json_encode(array('status'=>'false')); }
?>