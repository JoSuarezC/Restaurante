<?php
require 'ClassConnection.php';

class Usuario {
    function __construct() {

    }
    /* __________ READS _____________________________________________________ */
    /* Login*/
    public static function login($UserId,$Password) {
        $query="SELECT tu.NombreTipoUsuario FROM TipoUsuario tu
				inner join Usuario u on tu.IdTipoUsuario = u.TipoUsuario
				WHERE u.IdUsuario='{$UserId}' AND u.Contraseña='{$Password}' AND u.Estado='Activo'";
        $command = DB::getInstance()->getDB()->prepare($query);
        $command->execute();
		$ret = $command->fetch(PDO::FETCH_ASSOC);
		if ($ret) {
			if($ret == array("NombreTipoUsuario" => "Empleado")){
				$query=" SELECT * FROM Usuario u
				         inner join Empleado e on e.IdEm = u.IdTipoUsuario
						 inner join `Sucursal-Empleado` se on se.IdEmpleado = e.IdEm
						 inner join Sucursal s on se.IdSuc = s.IdSuc
						 inner join Puesto p on p.IdPuesto = e.Puesto
						 inner join TipoUsuario tu on tu.IdTipoUsuario = u.TipoUsuario
						 WHERE u.IdUsuario='{$UserId}' AND u.Contraseña='{$Password}' AND u.Estado='Activo'";
			}elseif($ret == array("NombreTipoUsuario" => "Cliente")){
				$query=" SELECT * FROM Usuario u
						 inner join Cliente c on c.IdCliente = u.IdTipoUsuario
						 inner join TipoUsuario tu on tu.IdTipoUsuario = u.TipoUsuario
						 WHERE u.IdUsuario='{$UserId}' AND u.Contraseña='{$Password}' AND u.Estado='Activo'";
			}else{ return false;}
			
			$command = DB::getInstance()->getDB()->prepare($query);
			$command->execute();
			return $command->fetch(PDO::FETCH_ASSOC);
		}else{return false;}
    }
	
	/* __________ INSERTS ___________________________________________________ */
	/* Crear usuario*/
	public static function create_user($NombreUsuario, $Contrasenia, $Correo, $IdCliente) {
        $query = "INSERT INTO Usuario VALUES ('{$NombreUsuario}','{$Contrasenia}','{$Correo}','Activo','1','{$IdCliente}')";
        $command = DB::getInstance()->getDB()->prepare($query);
        $command->execute();
       // return $command->fetch(PDO::FETCH_ASSOC);
       return true;
    }
    
    /* Crear cliente*/
	public static function insert_client($Nombre, $Apellido, $Cedula, $Telefono) {
        $query = "INSERT INTO Cliente(Cedula,Apellidos,Nombre,Telefono1,Telefono2) VALUES ('{$Cedula}','{$Apellido}','{$Nombre}','{$Telefono}','{$Telefono}')";
        $command = DB::getInstance()->getDB()->prepare($query);
        $command->execute();
		$query2 = "SELECT LAST_INSERT_ID() as orderID";
		$command = DB::getInstance()->getDB()->prepare($query2);
		$command->execute();
		return $command->fetch(PDO::FETCH_ASSOC);

    }
}
?>
