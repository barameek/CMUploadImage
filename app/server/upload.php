<?php 
  $to_file = basename($_FILES['uploadedfile']['name']); 
  $from_file = $_FILES['uploadedfile']['tmp_name']; 
  if (move_uploaded_file($from_file, $to_file)) { 
    echo "OK";
  }else{
    echo "NOK";
  }
?>