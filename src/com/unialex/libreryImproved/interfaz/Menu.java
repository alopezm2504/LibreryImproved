package com.unialex.libreryImproved.interfaz;

import com.unialex.libreryImproved.logica.LibreriaLogic;
import com.unialex.libreryImproved.modelos.Libro;
import com.unialex.libreryImproved.modelos.Usuario;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Menu {
    public Menu() {
        iniciar();
    }

    private void iniciar() {
        String opcion = JOptionPane.showInputDialog(retornarMensaje());
        LibreriaLogic libreria = new LibreriaLogic();
        do {
            switch (opcion) {
                case "1":
                    boolean isCreate = libreria.crearUsuario(ingresarUsuario());
                    if (isCreate) {
                        JOptionPane.showMessageDialog(null, "el cliente se creo correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "el cliente no se creo correctamente");
                    }
                    break;
                case "2":
                    mostrarLista(libreria.usuarios);
                    break;
                case "3":
                    Libro libro = ingresarLibro();
                    adicionarLibro(libro,libreria);
                    JOptionPane.showMessageDialog(null, "el libro se creo correctamente");
                    break;
                case "4":
                    String cedNew = JOptionPane.showInputDialog("Digite la cedula del usuario");
                    String prestarDevolver = JOptionPane.showInputDialog("digite segun la opcion que desee\n 1. para prestar \n 2. para devolver")
                            libreria.prestarLibro()
                    Usuario usuario = libreria.buscarUsuario(cedNew);
                    if (Objects.nonNull(usuario) && prestarDevolver.equals("1")) {

                        Libro libroPrestar = retornarMensaje4(libreria.libros);
                        libreria.cambiarEstadoUsuario(cedNew);
                        if (Objects.isNull(libroPrestar)) {
                            JOptionPane.showMessageDialog(null, "el libro no existe");
                            break;
                        }
                        libreria.librosPrestados.add(List.of(libroPrestar
                                , usuario, new Date()));
                        //libreria.transaciones.add(new Transacion(libroPrestar, usuario, new Date()));
                        libreria.cambiarEstadoLibro(libroPrestar.getIdLibro());
                        JOptionPane.showMessageDialog(null, "el libro se presto con exito que disfrute la lectura");
                    } else if (Objects.nonNull(usuario) && prestarDevolver.equals("2")) {
                        Libro libroDevolver = retornarMensaje4(libreria.libros);
                        libreria.cambiarEstadoUsuario(cedNew);
                        if (Objects.isNull(libroDevolver)) {
                            JOptionPane.showMessageDialog(null, "el libro no existe");
                            break;
                        }

                        libreria.cambiarEstadoLibro(libroDevolver.getIdLibro());
                        String fechaEntrega = JOptionPane.showInputDialog("digite la fecha de entrega\ncon el siguiente formato\n dd/mm/yy ");
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                       // Date fechaFormateada = formato.parse(fechaEntrega);
                        libreria.removerLibrosPrestados(usuario.getCed(), libroDevolver.getIdLibro());
                        //libreria.transaciones.add(new Transacion(libroDevolver, usuario, fechaFormateada));


                    } else {
                        JOptionPane.showMessageDialog(null, "el usuario no esta registrado");
                    }
                    break;
                case "5":
                    for (int i = 0; i < libreria.libros.size(); i++) {
                        if (!libreria.libros.get(i).isActive()) {
                            JOptionPane.showMessageDialog(null, libreria.libros.get(i));
                        }
                    }
                    break;
                case "6":

                    for (int i = 0; i < libreria.libros.size(); i++) {
                        if (libreria.libros.get(i).isActive()) {
                            JOptionPane.showMessageDialog(null, libreria.libros.get(i));
                        }
                    }

                    break;
                case "7":
                    for (int i = 0; i < libreria.usuarios.size(); i++) {
                        if (libreria.usuarios.get(i).prestoLibros) {
                            JOptionPane.showMessageDialog(null, libreria.usuarios.get(i));
                        }
                    }

                    break;
                case "8":
                    // mostrarLista(libreria.librosPrestados);
                    String fechaControl = JOptionPane.showInputDialog("digite la fecha \n use este formato dd/mm/yy ");
                    SimpleDateFormat fechaFormateada = new SimpleDateFormat("dd/MM/yyyy");
                    //Date fecha = fechaFormateada.parse(fechaControl);
                    //libreria.recorrerLista2(fecha);
                    mostrarLista(libreria.librosVencidos);
                    break;


                default:
                    opcion = JOptionPane.showInputDialog(retornarMensaje());

            }
            opcion = JOptionPane.showInputDialog(retornarMensaje());

        } while (!opcion.equals("10"));

    }

    private void adicionarLibro(Libro libro,LibreriaLogic libreria) {
        libro.setIdLibro(libreria.getNewIdLibro());
        libreria.libros.add(libro);
    }

    private static Libro retornarMensaje4(List<Libro> libros) {
        String opcion4 = JOptionPane.showInputDialog("por favor digite el titulo del libro");
        opcion4 = opcion4.toUpperCase();
        String opcion5 = "";
        for (Libro objet : libros) {
            opcion5 = objet.getTitulo().toUpperCase();
            if (opcion5.contains(opcion4)) {

                return objet;

            }


        }
        return null;
    }

    private static Libro ingresarLibro() {
        String titulo = JOptionPane.showInputDialog("ingrese el titulo del libro");
        String autor = JOptionPane.showInputDialog("ingrese el autor del libro");
        String anio = JOptionPane.showInputDialog("ingrese el año en que se hizo");
        Libro libro = new Libro(titulo, autor, anio);
        return libro;


    }

    private static Usuario ingresarUsuario() {
        String nombre = JOptionPane.showInputDialog("ingrese el nombre del usuario");
        String direccion = JOptionPane.showInputDialog("ingrese la direccion del usuario");
        String telefono = JOptionPane.showInputDialog("ingrese el telefono del usuario");
        String ced = JOptionPane.showInputDialog("ingrese la cedula del usuario");
        long telefonoNew = 0L;
        try {
            telefonoNew = Long.parseLong(telefono);
            Usuario usuario = new Usuario(nombre, direccion, telefonoNew, ced);
            return usuario;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "el telefono no es correcto");
        }
        return null;

    }

    private static void mostrarLista(List<?> lista) {
        for (Object objects : lista) {
            JOptionPane.showMessageDialog(null, objects);
        }
    }

    private static String retornarMensaje() {
        return "Hola ingrese una opcion\n" +
                "1. Ingresar usuario\n" +
                "2. lista de usuarios\n" +
                "3. Ingrese libro\n" +
                "4. Prestar libro o devolver libro\n" +
                "5. lista de libros prestados\n" +
                "6. lista de libros en tienda \n" +
                "7. lista de usuarios con libros prestados\n" +
                "8. prestamo vencidos \n" +
                "9. usuarios con mas de un libro\n" +
                "10. Salir\n";

    }


}

