
<?php
    define('DB_HOST','localhost');
    define('DB_NAME','u371424461_rest');
    define('DB_USER','u371424461_adm');
    define('DB_PASSWORD','adm123');

    if (isset($_GET['UserId']) && isset($_GET['Password'])) {
        $UserId = $_GET['UserId'];
        $Password = $_GET['Password'];
    
        $pdo=new PDO('mysql:dbname='.DB_NAME.';host='.DB_HOST.';',DB_USER,DB_PASSWORD,array(PDO::MYSQL_ATTR_INIT_COMMAND=>'SET NAMES utf8'));
        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
			
		$query="SELECT tu.NombreTipoUsuario FROM TipoUsuario tu
				 inner join Usuario u on tu.IdTipoUsuario = u.TipoUsuario
				 WHERE u.IdUsuario='{$UserId}' AND u.Contraseña='{$Password}' AND u.Estado='Activo'";
		
		try {
			$command=$pdo->prepare($query);
			$command->execute(array($UserId,$Password));
			$ret=$command->fetch(PDO::FETCH_ASSOC);
			if ($ret) {
				if($ret == array("NombreTipoUsuario" => "Empleado")){
					$query="SELECT * FROM Usuario u
					 inner join Empleado e on e.IdEm = u.IdTipoUsuario
					 inner join `Sucursal-Empleado` se on se.IdEmpleado = e.IdEm
					 WHERE u.IdUsuario='{$UserId}' AND u.Contraseña='{$Password}' AND u.Estado='Activo'";
			
					try {
						$command=$pdo->prepare($query);
						$command->execute(array($UserId,$Password));
						$ret=$command->fetch(PDO::FETCH_ASSOC);
						
						if ($ret) {
							$data['status']='true';
							$data['value']=$ret;
							print json_encode($data);

						} else { print json_encode(array('status'=>'false')); }
				
					} catch (PODException $e) { print json_encode(array('status'=>'false')); }
				}elseif($ret == array("NombreTipoUsuario" => "Cliente")){
					$query="SELECT * FROM Usuario u
					 inner join Cliente c on c.IdCliente = u.IdTipoUsuario
					 inner join TipoUsuario tu on tu.IdTipoUsuario = u.TipoUsuario
					 WHERE u.IdUsuario='{$UserId}' AND u.Contraseña='{$Password}' AND u.Estado='Activo'";
			
					try {
						$command=$pdo->prepare($query);
						$command->execute(array($UserId,$Password));
						$ret=$command->fetch(PDO::FETCH_ASSOC);
						
						if ($ret) {
							$data['status']='true';
							$data['value']=$ret;
							print json_encode($data);

						} else { print json_encode(array('status'=>'false')); }
				
					} catch (PODException $e) { print json_encode(array('status'=>'false')); }
				}else{ print json_encode(array('status'=>'false'));}
			
			} else { print json_encode(array('status'=>'false')); }
	
		} catch (PODException $e) { print json_encode(array('status'=>'false')); }
		
    } else { print json_encode(array('status'=>'false')); }
  
?>