<?php
    require 'ClassUsuario.php';
	
    if (isset($_GET['UserId']) && isset($_GET['Password'])) {
        $UserId = $_GET['UserId'];
        $Password = $_GET['Password'];
        try {
			$ret = Usuario::login($UserId, $Password);
            if ($ret) {
                $data['status']='true';
                $data['value']=$ret;
                print json_encode($data);
            } else {
                print json_encode(array('status'=>'false'));
            }
        } catch (PDOException $e) {
        	print json_encode(array('status'=>'false'));
        }
    }    
?>