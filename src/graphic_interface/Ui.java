package graphic_interface;

import java.awt.Color; 
import java.awt.Component; 
import java.awt.Image; 

import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import TextStuff.TextAnalyzer;
import image_analysis.ImageAnalyzer;
import internet_connection.Azure;
import internet_connection.InternetConection; 

public class Ui extends javax.swing.JFrame {
	private javax.swing.JLabel label; 
    private javax.swing.JButton botonCarga; 
    private javax.swing.JButton botonAlterar; 
    private javax.swing.JTextField campo; 
    private java.awt.Container content; 
    private ImageIcon icon;//Imagen que mostraremos 
    private ImageIcon icono;//Imagen redimensionada 
    private Component c = this;//Para referenciar al frame 


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
//Boton para alterar la imagen descargada 
        botonAlterar = new javax.swing.JButton("<html><center><Font color=blue>Alterar<br>imagen"); 
        botonAlterar.setBounds(315, 365, 75, 35); 
        botonAlterar.setMargin(new java.awt.Insets(0, 0, 0, 0)); 
        botonAlterar.setFocusable(false); 
        content.add(botonAlterar); 
//Etiqueta donde se muestra la imagen 
        label = new javax.swing.JLabel(); 
        content.add(label); 
        label.setBounds(0, 0, 400, 320); 
        label.setHorizontalAlignment(0); 
        label.setText("<html><Font color=green><center><h1>Para cargar imagen<br>" 
                + "ingrese URL<br>" 
                + "en el campo de texto<br>" 
                + "y luego presione<Font color=blue> 'Cargar URL'"); 
        label.setBorder(javax.swing.BorderFactory.createLineBorder(Color.black, 1));//Borde negro de 1px de grosor

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
//Acción del botón alteracion 
        botonAlterar .addActionListener(new java.awt.event.ActionListener() { 

            public void actionPerformed(java.awt.event.ActionEvent evt) { 
                Runnable miRunnable = new Runnable() { 

                    @Override 
                    public void run() { 
                    	Alterar(); 
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
            
            Image asd = icono.getImage();
            ImageAnalyzer das = new ImageAnalyzer(asd);
            
            ImageIcon alterado = new ImageIcon(das.getImageToAnalize());
            label.setIcon(alterado);
            
            TextAnalyzer text = new TextAnalyzer("C:\\Users\\usuario\\Desktop\\TEC\\Estructuras de datos\\proyecto 2\\Femme-coccyx Datos\\src\\text1.txt");
            
            Azure azure = new Azure(s);
            azure.getTags();
            //azure.getConfidence();
            
		} catch (IOException e) {
			e.printStackTrace();
		}
    	setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    } 

    private void Alterar() { 
    	//Se va a alterar la imagen aqui, se llaman a las funciones que lo hacen
 
    } 

    public static void main(String[] args) { 
        new Ui().setVisible(true);//Instancia del Frame 
    } 

}
