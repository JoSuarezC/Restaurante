<?php
    $host_name='localhost';
    $db_name='u371424461_rest';
    $db_user_name='u371424461_adm';
    $db_password='adm123';

    if ($_SERVER['REQUEST_METHOD'] == 'POST') {

        $ComboDesc=$_POST['ComboDesc']; 
        $Datetime=$_POST['Datetime']; 
        $Discount=$_POST['Discount']; 
        
		$connection = mysqli_connect($host_name,$db_user_name,$db_password,$db_name);
      
		$query="INSERT INTO `Combo`(`Descripcion`, `Fecha`, `Descuento`) VALUES ('{$ComboDesc}','{$Datetime}'
		    ,'{$Discount}')";
		$query2= "SELECT LAST_INSERT_ID() as comboID";
		       
		try {
		  $ret=mysqli_query($connection,$query);
		  $ret2=mysqli_query($connection,$query2);
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