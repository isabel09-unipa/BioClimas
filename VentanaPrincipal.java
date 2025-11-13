/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bioclima;

/**
 *
 * @author Maria Isabel
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaPrincipal extends JFrame {
    private final Ecosistema eco;
    private JTextArea areaInfo;
    private JButton btnInfo, btnClima, btnEstacion, btnAgregar, btnEliminar, btnSimular, btnGuardar, btnSalir;

    public VentanaPrincipal() {
        eco = Ecosistema.cargar();
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout());

        
        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(new Color(27, 94, 32));
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel lblTitulo = new JLabel("SIMULADOR BIOCLIMA", SwingConstants.LEFT);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 28));

        JLabel lblSubtitulo = new JLabel("Control ambiental del ecosistema - Proyecto ecológico", SwingConstants.LEFT);
        lblSubtitulo.setForeground(new Color(210, 255, 210));
        lblSubtitulo.setFont(new Font("Segoe UI", Font.ITALIC, 14));

        JPanel panelTextoTitulo = new JPanel(new GridLayout(2, 1));
        panelTextoTitulo.setOpaque(false);
        panelTextoTitulo.add(lblTitulo);
        panelTextoTitulo.add(lblSubtitulo);

       
        JLabel lblIcono = new JLabel("");
        lblIcono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        lblIcono.setHorizontalAlignment(SwingConstants.CENTER);

        panelTitulo.add(lblIcono, BorderLayout.WEST);
        panelTitulo.add(panelTextoTitulo, BorderLayout.CENTER);
        add(panelTitulo, BorderLayout.NORTH);

       
        areaInfo = new JTextArea(20, 60);
        areaInfo.setEditable(false);
        areaInfo.setFont(new Font("Consolas", Font.PLAIN, 14));
        areaInfo.setForeground(new Color(30, 60, 30));
        areaInfo.setBackground(new Color(242, 248, 245));
        areaInfo.setMargin(new Insets(10, 10, 10, 10));
        areaInfo.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 100), 1),
                " Consola del ecosistema ",
                0, 0, new Font("Segoe UI", Font.BOLD, 12),
                new Color(50, 90, 50)
        ));
        JScrollPane scroll = new JScrollPane(areaInfo);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        add(scroll, BorderLayout.CENTER);

       
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(8, 1, 10, 10));
        panelBotones.setBackground(new Color(240, 250, 242));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        btnInfo = crearBoton(" Información", new Color(56, 142, 60));
        btnClima = crearBoton(" Estado Clima", new Color(76, 175, 80));
        btnEstacion = crearBoton(" Cambiar Estación", new Color(67, 160, 71));
        btnAgregar = crearBoton(" Agregar Ser Vivo", new Color(46, 125, 50));
        btnEliminar = crearBoton(" Eliminar Ser Vivo", new Color(200, 60, 60));
        btnSimular = crearBoton(" Simular Días", new Color(100, 136, 74));
        btnGuardar = crearBoton(" Guardar", new Color(63, 81, 181));
        btnSalir = crearBoton(" Salir", new Color(33, 33, 33));

        panelBotones.add(btnInfo);
        panelBotones.add(btnClima);
        panelBotones.add(btnEstacion);
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnSimular);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnSalir);

        add(panelBotones, BorderLayout.WEST);

        configurarEventos();
    }

  
    private JButton crearBoton(String texto, Color colorBase) {
        JButton boton = new JButton(texto);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        boton.setBackground(colorBase);
        boton.setForeground(Color.WHITE);
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 255, 200), 1),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setOpaque(true);

        // Hover
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(colorBase.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(colorBase);
            }
        });

        return boton;
    }

 
    private void configurarEventos() {
        btnInfo.addActionListener(e -> mostrarInformacion());
        btnClima.addActionListener(e -> mostrarClima());
        btnEstacion.addActionListener(e -> cambiarEstacion());
        btnAgregar.addActionListener(e -> agregarSerVivo());
        btnEliminar.addActionListener(e -> eliminarSerVivo());
        btnSimular.addActionListener(e -> simularDias());
        btnGuardar.addActionListener(e -> guardarEcosistema());
        btnSalir.addActionListener(e -> confirmarSalida());
    }

    private void mostrarInformacion() {
        StringBuilder info = new StringBuilder();
        info.append("=== INFORMACIÓN DEL ECOSISTEMA ===\n\n");
        info.append("Ecosistema: Amazonas\n");
        info.append("Seres vivos registrados: ").append(eco.getCantidadSeresVivos()).append("\n\n");
        info.append("--- CLIMA ACTUAL ---\n");
        info.append(eco.getClima().toString()).append("\n");
        areaInfo.setText(info.toString());
    }

    private void mostrarClima() {
        areaInfo.setText("=== ESTADO DEL CLIMA ===\n\n" + eco.getClima().toString());
    }

    private void cambiarEstacion() {
        String[] estaciones = {"Primavera", "Verano", "Otoño", "Invierno"};
        String estacion = (String) JOptionPane.showInputDialog(this,
                "Selecciona la nueva estación:",
                "Cambiar Estación",
                JOptionPane.QUESTION_MESSAGE,
                null,
                estaciones,
                estaciones[0]);

        if (estacion != null) {
            eco.getClima().setEstacion(estacion);
            areaInfo.setText(" SEstación cambiada a: " + estacion);
        }
    }

    private void agregarSerVivo() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Tipo:"));
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Animal", "Planta"});
        panel.add(comboTipo);

        panel.add(new JLabel("Nombre:"));
        JTextField txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Energía:"));
        JTextField txtEnergia = new JTextField("50");
        panel.add(txtEnergia);

        panel.add(new JLabel("Especie:"));
        JTextField txtEspecie = new JTextField();
        panel.add(txtEspecie);

        int result = JOptionPane.showConfirmDialog(this, panel, 
                "Agregar Ser Vivo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String tipo = comboTipo.getSelectedItem().toString();
                String nombre = txtNombre.getText().trim();
                double energia = Double.parseDouble(txtEnergia.getText());
                String especie = txtEspecie.getText().trim();

                if (nombre.isEmpty() || especie.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Nombre y especie son obligatorios", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (tipo.equals("Animal")) {
                    String[] alimentaciones = {"Carnívoro", "Herbívoro", "Omnívoro"};
                    String alimentacion = (String) JOptionPane.showInputDialog(this,
                            "Selecciona el tipo de alimentación:",
                            "Tipo de Alimentación",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            alimentaciones,
                            alimentaciones[0]);

                    if (alimentacion != null) {
                        Animal animal = new Animal(nombre, energia, especie, alimentacion);
                        eco.agregar(animal);
                        areaInfo.setText("🦋 Animal '" + nombre + "' agregado exitosamente\n\n" +
                                "Especie: " + especie + "\nEnergía: " + energia + "\nAlimentación: " + alimentacion);
                    }
                } else {
                    Planta planta = new Planta(nombre, energia, especie);
                    eco.agregar(planta);
                    areaInfo.setText("🌿 Planta '" + nombre + "' agregada exitosamente\n\n" +
                            "Especie: " + especie + "\nEnergía: " + energia);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "La energía debe ser un número válido", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarSerVivo() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del ser vivo a eliminar:");
        if (nombre != null && !nombre.trim().isEmpty()) {
            boolean eliminado = eco.eliminar(nombre.trim());
            if (eliminado)
                areaInfo.setText("🗑 Ser vivo '" + nombre + "' eliminado exitosamente");
            else
                areaInfo.setText("⚠️ No se encontró ningún ser vivo con el nombre: '" + nombre + "'");
        }
    }

    private void simularDias() {
        String input = JOptionPane.showInputDialog(this, "¿Cuántos días deseas simular?");
        if (input != null && !input.trim().isEmpty()) {
            try {
                int dias = Integer.parseInt(input.trim());
                if (dias <= 0) {
                    JOptionPane.showMessageDialog(this, "El número de días debe ser mayor que cero");
                    return;
                }

                StringBuilder resultado = new StringBuilder();
                resultado.append("=== SIMULACIÓN DE ").append(dias).append(" DÍAS ===\n\n");

                for (int i = 1; i <= dias; i++) {
                    resultado.append("--- Día ").append(i).append(" ---\n");
                    eco.simularDia();
                }

                resultado.append("\n Simulación completada exitosamente");
                areaInfo.setText(resultado.toString());

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Debes ingresar un número válido");
            }
        }
    }

    private void guardarEcosistema() {
        eco.guardar();
        areaInfo.setText(" Ecosistema guardado exitosamente.\n\nLos datos han sido almacenados correctamente.");
    }

    private void confirmarSalida() {
        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Deseas salir del simulador?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (opcion == JOptionPane.YES_OPTION) {
            eco.guardar();
            JOptionPane.showMessageDialog(this, " Ecosistema guardado. ¡Hasta luego!");
            System.exit(0);
        }
    }

    private void configurarVentana() {
        setTitle("Simulador BioClima - Ecosistema Amazónico");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmarSalida();
            }
        });
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}
        SwingUtilities.invokeLater(VentanaPrincipal::new);
    }
}
