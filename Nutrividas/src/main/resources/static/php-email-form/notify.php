<?php
  // Replace contact@example.com with your real receiving email address
  $receiving_email_address = 'srotaka@gmail.com';

  if( file_exists($php_email_form = '../static/php-email-form/php-email-form.php' )) {
    include( $php_email_form );
  } else {
    die( 'Unable to load the "PHP Email Form" Library!');
  }

  $contact = new PHP_Email_Form;
  $contact->ajax = true;
  
  $contact->to = $receiving_email_address;
  $contact->from_name = "Suscriptor";
  $contact->from_email = $_POST['email'];
  $contact->subject ="Solicitud de Notificación";

  // Uncomment below code if you want to use SMTP to send emails. You need to enter your correct SMTP credentials

// $contact->smtp = array(
// 'host' => 'smtp.gmail.com',
// 'username' => 'nutrividas.info@gmail.com',
// 'password' => 'Nutrividas2021',
// 'port' => '25'
// );

  $contact->add_message( $_POST['name'], 'De');
  $contact->add_message( $_POST['email'], 'Email');

  echo $contact->send();
?>
