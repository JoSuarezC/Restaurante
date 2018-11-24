package Model;

import javafx.collections.ObservableList;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Email {

    public static void sendEmail(Address destinatario, String subject, String body) throws MessagingException {
        // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
        String remitente = "cuentawebhost@gmail.com";  //Para la dirección nomcuenta@gmail.com
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", "25916319WebHost");    //La clave de la cuenta
        props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, destinatario);   //Se podrían añadir varios de la misma manera
            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, "25916319WebHost");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (MessagingException me) {
            me.printStackTrace();   //Si se produce un error
        }
    }

    public static void createBill(String fecha, ObservableList<ShoppingList_Product> listaProductos, String montoTotal, String sucursal){
        String factura = "";
        factura += "Fecha: "+fecha+"\r\n";
        factura+= "\r\n";
        factura += "Sucursal: "+ sucursal+"\r\n";
        factura+= "\r\n";
        String productos="Productos: \n";
        for(ShoppingList_Product producto : listaProductos){
            productos+=producto.getProductName()+"    Cantidad:"+ producto.getProductQuantity()+"    Precio unitario:"+producto.getProductPrize()+"\r\n";
        }
        factura+= productos+"\r\n";
        factura+= "\r\n";
        factura+= "Monto total: "+montoTotal+"\r\n";
        factura+= "\r\n";
        factura+= "¡Muchas gracias por su compra!";

        try {
            sendEmail(new InternetAddress(User.getCurrentUser().getUser_Email()),"Factura de compra",factura);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
