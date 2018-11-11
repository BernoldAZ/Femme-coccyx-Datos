package graphic_interface;

import java.awt.Color; 
import java.awt.Component; 
import java.awt.Image; 
import java.io.File; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.InputStream; 
import java.net.MalformedURLException; 
import java.net.URL; 
import java.net.URLConnection; 
import javax.swing.ImageIcon; 
import javax.swing.JOptionPane;

import internet_connection.InternetConection; 

public class Ui extends javax.swing.JFrame {
	private javax.swing.JLabel label; 
    private javax.swing.JLabel label1; 
    private javax.swing.JButton botonCarga; 
    private javax.swing.JButton botonGuarda; 
    private javax.swing.JTextField campo; 
    private java.awt.Container content; 
    private String ex;//Para mostrar extensión de archivo 
    private ImageIcon icon;//Imagen que mostraremos 
    private ImageIcon icono;//Imagen redimensionada 
    private URL url;//Tomara el valor del textField 
    private Component c = this;//Para referenciar al frame 
    private URLConnection urlCon;//Para obtener datos de la conexión 

    public Ui() { 
        inicio(); 

        //Se iguala al Frame 
    } 

    private void inicio() {//Creamos un Frame sus componentes y la acción d elos mismos:
        content = getContentPane();//Se inicia nuestro contenedor 

        //Botón que cargará la URL ingresada 
        botonCarga = new javax.swing.JButton("<html><h4><center><Font color=blue>Cargar URL:"); 
        botonCarga.setBounds(0, 325, 75, 30); 
        botonCarga.setMargin(new java.awt.Insets(0, 0, 0, 0)); 
        botonCarga.setFocusable(false); 
        content.add(botonCarga); 
//Boton para guardar la imagen descargada 
        botonGuarda = new javax.swing.JButton("<html><center><Font color=blue>Guardar<br>imagen"); 
        botonGuarda.setBounds(315, 365, 75, 35); 
        botonGuarda.setMargin(new java.awt.Insets(0, 0, 0, 0)); 
        botonGuarda.setFocusable(false); 
        content.add(botonGuarda); 
//Etiqueta donde se muestra la imagen 
        label = new javax.swing.JLabel(); 
        content.add(label); 
        label.setBounds(0, 0, 400, 320); 
        label.setHorizontalAlignment(0); 
        label.setText("<html><Font color=green><center><h1>Para cargar imagen<br>" 
                + "ingrese URL<br>" 
                + "en el campo de texto<br>" 
                + "y lugo presione<Font color=blue> 'Cargar URL'"); 
        label.setBorder(javax.swing.BorderFactory.createLineBorder(Color.black, 1));//Borde negro de 1px de grosor
//Etiqueta para mostrar info del archivo 
        label1 = new javax.swing.JLabel(); 
        content.add(label1); 
        label1.setBounds(5, 365, 300, 35); 
        label1.setText("<html>" 
                + "Tamaño:<br>" 
                + "Tipo:"); 
        label1.setBorder(javax.swing.BorderFactory.createLineBorder(Color.black, 1));

        //TextField para ingresar la URL 
        campo = new javax.swing.JTextField(); 
        campo.setBounds(80, 325, 310, 30); 
        content.add(campo); 

//Acción del boton de carga 
        botonCarga.addActionListener(new java.awt.event.ActionListener() { 

            public void actionPerformed(java.awt.event.ActionEvent evt) { 
                Runnable miRunnable = new Runnable() {//Clase para ejecutar hilo independiente del main

                    @Override 
                    public void run() { 
                        Carga();//Llamada al método que descarga una imagen 
                    } 
                }; 
                Thread hilo = new Thread(miRunnable);//Instancia del hilo 
                hilo.start();//se inicia el hilo 
                //Todo lo referente al hilo es para que no se "congele" el Frame al cargar una imagen
            } 
        }); 
//Acción del botón guardar 
        botonGuarda.addActionListener(new java.awt.event.ActionListener() { 

            public void actionPerformed(java.awt.event.ActionEvent evt) { 
                Runnable miRunnable = new Runnable() { 

                    @Override 
                    public void run() { 
                        Guarda(); 
                    } 
                }; 
                Thread hilo = new Thread(miRunnable); 
                hilo.start(); 
            } 
        }); 
         
        setResizable(false);//Para no cambiar el tamaño del Frame 
        setSize(400, 440);//Tamaño del Frame 
        setLayout(null);//Sin agrupación de componentes 
        setLocationRelativeTo(null);//Centrado del Frame en la pantalla 
        setDefaultCloseOperation(3);//Botón cerrar (3 = EXIT_ON_CLOSE) 

    } 

    private void debug(String msj) {//Método para mostrar info en la consola 
        System.out.println(msj); 
    } 

    private void Carga() {//Método que abre una conexión y descarga una imagen desde una URL
    	String s = campo.getText();//Texto del campo 
    	System.out.println(s); 
    	
    	setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
    	
    	InternetConection cargar = new InternetConection();
    	try {
			icon = cargar.getImage(s);
			icono = new ImageIcon(icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
            label.setIcon(icono); 
            campo.setText(null); 
            label.setText(null); 
		} catch (IOException e) {
			e.printStackTrace();
		}
    	setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    } 

    private void Guarda() { 
        File f = null; 
        InputStream is = null;//Variable para cargar datos de la imagen 

        try { 
            is = urlCon.getInputStream(); 
            javax.swing.JFileChooser fc = new javax.swing.JFileChooser(); 
            int acep = fc.showSaveDialog(c); 
            if (acep == javax.swing.JFileChooser.APPROVE_OPTION) {//Si damos aceptar al FileChooser
                f = fc.getSelectedFile(); 
                f = new File(fc.getSelectedFile() + ex);//Agregamos la extensión de la imagen (jpg,png,Etc.)
                FileOutputStream fos = new FileOutputStream(f);//Para guardar los datos cargados
                // Lectura de la foto de la web y escritura en fichero local 
                byte[] array = new byte[1000]; // buffer temporal de lectura. 
                int leido = is.read(array); 
                while (leido > 0) { 
                    fos.write(array, 0, leido); 
                    leido = is.read(array); 
                } 
                is.close(); 
                fos.close(); 

                // cierre de conexion y fichero. 

            } 
        } catch (IOException e) { 
            debug(e.getMessage()); 
        } finally { 
            try { 
                if (is != null) { 
                    is.close();//Para que no de error al dar cancel al FileChooser 
                    //aunq si das cancel la próxima vez no guarda la imagen(...?...) 
                } 
            } catch (IOException e) { 
                debug(e.getMessage()); 
            } 
        } 
    } 

    public static void main(String[] args) { 
        new Ui().setVisible(true);//Instancia del Frame 
    } 

}
