<?php
    $host_name='localhost';
    $db_name='u371424461_rest';
    $db_user_name='u371424461_adm';
    $db_password='adm123';

    if ($_SERVER['REQUEST_METHOD'] == 'POST') {

        $ClientID=$_POST['ClientID']; 
        $Datetime=$_POST['Datetime']; 
        $OrderType=$_POST['OrderType']; 
        $OrderType=$_POST['Sucursal'];
        $TotalAPagar=$_POST['TotalAPagar']; 
        $IdSucursal=$_POST['IdSucursal']; 
        $DireccionEntrega=$_POST['DireccionEntrega']; 
        
        
		$connection = mysqli_connect($host_name,$db_user_name,$db_password,$db_name);
      
		$query="INSERT INTO Pedido VALUES(null,'{$ClientID}','{$Datetime}','{$OrderType}',null,null,'Pendiente','{$TotalAPagar}','{$DireccionEntrega}')";
		$query2= "SELECT LAST_INSERT_ID() as orderID";
		$query3="INSERT INTO `Sucursal-Pedido` VALUES($IdSucursal, LAST_INSERT_ID())";
		       
		try {
		  $ret=mysqli_query($connection,$query);
		  $ret2=mysqli_query($connection,$query2);
		  $ret3=mysqli_query($connection,$query3);
		  $rows = array();
		  $rows['status']='true';
        
		  if ($ret and $ret2) {
			while($row = mysqli_fetch_assoc($ret2)) {
				$rows['value'] = $row;
			}

			mysqli_close($connection);
			print json_encode($rows);

		  } else {
			mysqli_close($connection);
			print json_encode(array('status'=>'false'));
		  }
		} catch (PODException $e) {
			mysqli_close($connection);
			//print $e;
			print json_encode(array('status'=>'false'));
		}
    } else { print json_encode(array('status'=>'false')); }
?>
