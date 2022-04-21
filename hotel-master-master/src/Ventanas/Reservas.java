package Ventanas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import PaqC01.*;
import java.io.*;

public class Reservas extends JFrame{

    private JTextField textNombre;
    private JTextField textApellidos;
    private JTextField textDNI;
    private JTextField textTeléfono;
    private JTextField textTarjeta;
    private JTextField textFechaEntrada;
    private JTextField textFechaSalida;
    private JCheckBox estándarCheckBox;
    private JCheckBox balcónCheckBox;
    private JCheckBox suiteCheckBox;
    private JTextField textEstandar;
    private JTextField textBalcon;
    private JTextField textSuite;
    private JComboBox comboBoxRegimen;
    private JTextField textPrecio;
    private JButton calcularButton;
    private JButton limpiarButton;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JPanel reservas;
    private JTextArea mapaDelHotel;
    private JButton AnularBoton;
    private JButton buscarDniButton;

    private static Hotel h1;
    private Cliente c1;


//github

    String[] comida={"","Sin desayuno","Con desayuno","Media pensión", "Pensión completa"};


    public Reservas() throws IOException ,ClassNotFoundException{
        h1= new Hotel();
        c1=new Cliente();

        FileInputStream fis = new FileInputStream("HotelSerializar.dat");
        ObjectInputStream entrada = new ObjectInputStream(fis);


        setContentPane(reservas);
        setTitle("Reservas del hotel");
        setSize(900, 600);
        setBounds(0,0,1500,800);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        comboBoxRegimen.addItem(comida[0]);
        comboBoxRegimen.addItem(comida[1]);
        comboBoxRegimen.addItem(comida[2]);
        comboBoxRegimen.addItem(comida[3]);
        comboBoxRegimen.addItem(comida[4]);

        h1=(Hotel)entrada.readObject();
        mapaDelHotel.setText(h1.toString());


        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainFrame Frame= new MainFrame();
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textNombre.setText("");
                textApellidos.setText("");
                textDNI.setText("");
                textTeléfono.setText("");
                textTarjeta.setText("");
                textFechaEntrada.setText("");
                textFechaSalida.setText("");
                textPrecio.setText("");
                textEstandar.setText("");
                textBalcon.setText("");
                textSuite.setText("");
                estándarCheckBox.setSelected(false);
                balcónCheckBox.setSelected(false);
                suiteCheckBox.setSelected(false);
                comboBoxRegimen.setSelectedItem(null);
            }
        });
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                int precio;
                precio=0;

                if(estándarCheckBox.isSelected()){
                    int n1= Integer.parseInt(textEstandar.getText());
                    for(int i=0;i<n1;i++){
                        precio=precio+30;
                    }
                }
                if(balcónCheckBox.isSelected()){
                    int n2=Integer.parseInt(textBalcon.getText());
                    for (int i = 0; i < n2; i++) {
                        precio=precio+40;
                    }
                }
                if(suiteCheckBox.isSelected()){
                    int n3=Integer.parseInt(textSuite.getText());
                    for (int i = 0; i < n3; i++) {
                        precio=precio+50;
                    }
                }

                if (comboBoxRegimen.getSelectedItem()=="Sin desayuno")precio=precio+10;
                if(comboBoxRegimen.getSelectedItem()=="Con desayuno")precio=precio+12;
                if(comboBoxRegimen.getSelectedItem()=="Media pensión")precio=precio+17;
                if(comboBoxRegimen.getSelectedItem()=="Pensión completa")precio=precio+23;

                textPrecio.setText(String.valueOf(precio));





            }
        });
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream("HotelSerializar.dat");
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                ObjectOutputStream salida = null;
                try {
                    salida = new ObjectOutputStream(fos);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                c1.setNombre(textNombre.getText());
                c1.setApellido(textApellidos.getText());
                c1.setDni(textDNI.getText());
                c1.setTelefono(Integer.parseInt(textTeléfono.getText()));
                c1.setTarjeta(Integer.parseInt(textTarjeta.getText()));
                c1.setFechaEntrada(Integer.parseInt(textFechaEntrada.getText()));
                c1.setFechaSalida(Integer.parseInt(textFechaSalida.getText()));

                String tipo="";

                int numHab=0;
                if(estándarCheckBox.isSelected()){
                    tipo="Estandar";
                    numHab=0;
                    numHab=Integer.parseInt(textEstandar.getText());


                    if(h1.reservar(c1,tipo,numHab))JOptionPane.showMessageDialog(null,"Se ha realizado la reserva de de"+" "+numHab+" "+tipo,"Reserva",JOptionPane.INFORMATION_MESSAGE);
                    else JOptionPane.showMessageDialog(null,"No se ha podido realizar la reserva de de"+" "+numHab+" "+tipo,"Reserva",JOptionPane.INFORMATION_MESSAGE);

                }
                if(balcónCheckBox.isSelected()){
                    tipo="Balcon";
                    numHab=0;
                    numHab=Integer.parseInt(textBalcon.getText());


                    if(h1.reservar(c1,tipo,numHab))JOptionPane.showMessageDialog(null,"Se ha realizado la reserva de de "+numHab+" "+tipo,"Reserva",JOptionPane.INFORMATION_MESSAGE);
                    else JOptionPane.showMessageDialog(null,"No se ha podido realizar la reserva de de "+numHab+" "+tipo,"Reserva",JOptionPane.INFORMATION_MESSAGE);

                }
                if(suiteCheckBox.isSelected()){
                    tipo="Suite";
                    numHab=0;
                    numHab=Integer.parseInt(textSuite.getText());


                    if(h1.reservar(c1,tipo,numHab))JOptionPane.showMessageDialog(null,"Se ha realizado la reserva de de "+numHab+" "+tipo,"Reserva",JOptionPane.INFORMATION_MESSAGE);
                    else JOptionPane.showMessageDialog(null,"No se ha podido realizar la reserva de de "+numHab+" "+tipo,"Reserva",JOptionPane.INFORMATION_MESSAGE);

                }

                /*
                if(h1.reservar(c1,tipo,numHab)){
                    JOptionPane.showMessageDialog(null, "Se ha realizado la reserva.","Reserva",JOptionPane.INFORMATION_MESSAGE );
                }
                else{
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error, pruebe a seleccionar solo un tipo de habitación.","Reserva",JOptionPane.INFORMATION_MESSAGE );
                }

                 */

                try {
                    salida.writeObject(h1);
                    salida.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                mapaDelHotel.setText(h1.toString());

            }
        });

        /*
        estándarCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(estándarCheckBox.isSelected()) JOptionPane.showMessageDialog(null,"Has seleccionado estándar","Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        balcónCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(balcónCheckBox.isSelected()) JOptionPane.showMessageDialog(null, "Has seleccionado balcón", "Aviso",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        suiteCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(suiteCheckBox.isSelected()) JOptionPane.showMessageDialog(null, "Has seleccionado suite","Aviso",JOptionPane.INFORMATION_MESSAGE );
            }
        });

         */

        /*
        comboBoxRegimen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(comboBoxRegimen.getSelectedItem()=="Sin desayuno") JOptionPane.showMessageDialog(null,"Has seleccionado sin desayuno","Aviso", JOptionPane.INFORMATION_MESSAGE);
                if(comboBoxRegimen.getSelectedItem()=="Con desayuno") JOptionPane.showMessageDialog(null,"Has seleccionado con desayuno","Aviso", JOptionPane.INFORMATION_MESSAGE);
                if(comboBoxRegimen.getSelectedItem()=="Media pensión") JOptionPane.showMessageDialog(null,"Has seleccionado media pensión","Aviso", JOptionPane.INFORMATION_MESSAGE);
                if(comboBoxRegimen.getSelectedItem()=="Pensión completa") JOptionPane.showMessageDialog(null,"Has seleccionado pensión completa","Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        });

         */
        AnularBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream("HotelSerializar.dat");
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                ObjectOutputStream salida = null;
                try {
                    salida = new ObjectOutputStream(fos);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Cliente c1=new Cliente();

                c1.setNombre(textNombre.getText());
                c1.setApellido(textApellidos.getText());
                c1.setDni(textDNI.getText());
                c1.setTelefono(Integer.parseInt(textTeléfono.getText()));
                c1.setTarjeta(Integer.parseInt(textTarjeta.getText()));
                c1.setFechaEntrada(Integer.parseInt(textFechaEntrada.getText()));
                c1.setFechaSalida(Integer.parseInt(textFechaSalida.getText()));

                String tipo2="";

                int numHab2=0;
                if(estándarCheckBox.isSelected()){
                    tipo2="Estandar";
                    numHab2=Integer.parseInt(textEstandar.getText());

                    if(h1.anularReserva(c1,tipo2,numHab2))JOptionPane.showMessageDialog(null,"Se ha anulado la reserva de "+numHab2+" habitaciones de tipo"+ tipo2, "Anular",JOptionPane.INFORMATION_MESSAGE);
                    else JOptionPane.showMessageDialog(null,"No se ha podido anular la reserva de "+numHab2+" habitaciones de tipo"+ tipo2, "Anular",JOptionPane.INFORMATION_MESSAGE);
                    numHab2=0;
                }
                if(balcónCheckBox.isSelected()){
                    tipo2="Balcon";
                    numHab2=Integer.parseInt(textBalcon.getText());

                    if(h1.anularReserva(c1,tipo2,numHab2))JOptionPane.showMessageDialog(null,"Se ha anulado la reserva de "+numHab2+" habitaciones de tipo"+ tipo2, "Anular",JOptionPane.INFORMATION_MESSAGE);
                    else JOptionPane.showMessageDialog(null,"No se ha podido anular la reserva de "+numHab2+" habitaciones de tipo"+ tipo2, "Anular",JOptionPane.INFORMATION_MESSAGE);
                    numHab2=0;
                }
                if(suiteCheckBox.isSelected()){
                    tipo2="Suite";
                    numHab2=Integer.parseInt(textSuite.getText());

                    if(h1.anularReserva(c1,tipo2,numHab2))JOptionPane.showMessageDialog(null,"Se ha anulado la reserva de "+numHab2+" habitaciones de tipo"+ tipo2, "Anular",JOptionPane.INFORMATION_MESSAGE);
                    else JOptionPane.showMessageDialog(null,"No se ha podido anular la reserva de "+numHab2+" habitaciones de tipo"+ tipo2, "Anular",JOptionPane.INFORMATION_MESSAGE);
                    numHab2=0;
                }
                /*
                if(h1.anularReserva(c1,tipo2,numHab2)){
                    JOptionPane.showMessageDialog(null,"Se ha anulado la reserva.","Reserva", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                    JOptionPane.showMessageDialog(null,"No se ha podido anular la reserva.","Reserva", JOptionPane.INFORMATION_MESSAGE);


                 */
                try {
                    salida.writeObject(h1);
                    salida.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                mapaDelHotel.setText(h1.toString());

            }
        });


        buscarDniButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream("HotelSerializar.dat");
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                ObjectOutputStream salida = null;
                try {
                    salida = new ObjectOutputStream(fos);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Cliente c1=new Cliente();

                c1.setDni(textDNI.getText());
                h1.aceptadni(c1.getDni());
                if(h1.aceptadni(c1.getDni()).equals(c1.getClass())){
                    c1.setApellido(textApellidos.getText());
                    c1.setDni(textDNI.getText());
                    c1.setTelefono(Integer.parseInt(textTeléfono.getText()));
                    c1.setTarjeta(Integer.parseInt(textTarjeta.getText()));
                    c1.setFechaEntrada(Integer.parseInt(textFechaEntrada.getText()));
                    c1.setFechaSalida(Integer.parseInt(textFechaSalida.getText()));
                }else JOptionPane.showMessageDialog(null,"Este cliente no tiene reservas en el hotel","dni no encontrado",JOptionPane.INFORMATION_MESSAGE);

            }
        });
    }
}
