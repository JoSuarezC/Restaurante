<?php
 
  define('DB_HOST','localhost');
  define('DB_NAME','u371424461_rest');
  define('DB_USER','u371424461_adm');
  define('DB_PASSWORD','adm123');

  if (isset($_GET['Email']) && isset($_GET['Password'])) {
    $Email=$_GET['Email'];
    $Password=$_GET['Password'];

    $pdo=new PDO('mysql:dbname='.DB_NAME.';host='.DB_HOST.';',DB_USER,DB_PASSWORD,array(PDO::MYSQL_ATTR_INIT_COMMAND=>'SET NAMES utf8'));
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $query="SELECT * FROM PERSONA WHERE Email='{$Email}' AND Pas='{$Contrasena}' AND Estado='Activo'";

    try {
      $command=$pdo->prepare($query);
      $command->execute(array($Correo,$Contrasena));
      $ret=$command->fetch(PDO::FETCH_ASSOC);

      if ($ret) {
        $data['status']='true';
        $data['value']=$ret;
        print json_encode($data);

      } else { print json_encode(array('status'=>'false')); }

    } catch (PODException $e) { print json_encode(array('status'=>'false')); }

  } else { print json_encode(array('status'=>'false')); }
  
?>
