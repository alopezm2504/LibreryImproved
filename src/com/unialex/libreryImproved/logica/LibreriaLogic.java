package com.unialex.libreryImproved.logica;

import com.unialex.libreryImproved.modelos.Libro;
import com.unialex.libreryImproved.modelos.Usuario;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class LibreriaLogic {
    public List<Libro> libros;
    public List<Usuario> usuarios;
    public List<List<Object>> librosPrestados;
    public List<List<Object>>librosVencidos;



    public LibreriaLogic() {
        this.libros = llenarLibros();
        this.usuarios = new ArrayList<>();
        this.librosPrestados = new ArrayList<>();
        this.librosVencidos= new ArrayList<>();
    }







    public void recorrerLista2(Date fechaControl){
        for (int i = librosPrestados.size()-1; i >=0 ; i--) {
            for (Object objeto: librosPrestados.get(i)) {
                if(objeto instanceof Libro){
                    if(!((Libro) objeto).isActive()){
                        boolean prestado=((Libro) objeto).isActive();

                    }
                } else if (objeto instanceof Usuario) {

                } else {
                    long diasPrestamo= diasEntreDosFechas(((Date) objeto), fechaControl);
                    JOptionPane.showMessageDialog(null,"los dias vencidos son: "+diasPrestamo);
                    if(diasPrestamo>=30 ){
                        this.librosVencidos.add(librosPrestados.get(i));
                    }
                }
            }
        }


    }

    public static long diasEntreDosFechas(Date fechaDesde, Date fechaHasta){
        long startTime = fechaDesde.getTime() ;
        long endTime = fechaHasta.getTime();
        long diasDesde = (long) Math.floor(startTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora
        long diasHasta = (long) Math.floor(endTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora
        long dias = diasHasta - diasDesde;

        return dias;
    }
    private List<Libro> llenarLibros() {
        Libro libro1 = new Libro("El lobo estepario", "Hess", "1942");
        libro1.setIdLibro(1);
        Libro libro2 = new Libro("Damian", "Hess", "1942");
        libro2.setIdLibro(2);
        Libro libro3 = new Libro("Conjetura de Fermat", "Fermat", "1942");
        libro3.setIdLibro(3);
        Libro libro4 = new Libro("La iliada", "Homero", "1942");
        libro4.setIdLibro(4);
        ArrayList arrayList = new ArrayList();
        arrayList.add(libro1);
        arrayList.add(libro2);
        arrayList.add(libro3);
        arrayList.add(libro4);

        return arrayList;
    }


    public boolean crearUsuario(Usuario usuario) {
        if (!Objects.isNull(usuario) && !Objects.isNull(usuario.getNombre()) && !usuario.getNombre().equals("")) {
            this.usuarios.add(usuario);
            return true;

        }
        return false;
    }

    public int getNewIdLibro() {
        return libros.size() + 1;
    }

    public void cambiarEstadoUsuario(String cedNew) {

        for (Usuario usuario : usuarios) {
            if (usuario.getCed().equals(cedNew)) {
                usuario.prestoLibros = !usuario.prestoLibros;
            }


        }

    }


    public Usuario buscarUsuario(String cedNew) {

        for (Usuario usuario : usuarios) {
            if (usuario.getCed().equals(cedNew)) {
                return usuario;
            }
        }
        return null;
    }

    public void cambiarEstadoLibro(int idLibro) {
        for (int i = 0; i < libros.size(); i++) {
            /*if (libros.get(i).getIdLibro() == idLibro && prestarDevolver.equals("1")) {
                libros.get(i).setActive(false);
            } else if (libros.get(i).getIdLibro() == idLibro && prestarDevolver.equals("2")) {
                libros.get(i).setActive(true);
            }*/
            if (libros.get(i).getIdLibro() == idLibro) {
                libros.get(i).setActive(!libros.get(i).isActive());
            }
        }

    }

    public void removerLibrosPrestados(String ced, int idLibro) {
        boolean aux=false;
        int conta = -1;
        for (int i = librosPrestados.size()-1; i >=0 ; i--) {
            for (Object objeto : librosPrestados.get(i)) {
                if (objeto instanceof Libro) {
                    if (((Libro) objeto).getIdLibro() == idLibro) {
                        aux = true;

                    }
                } else if (objeto instanceof Usuario) {
                    if (((Usuario) objeto).getCed().equals(ced) && aux) {
                        conta = i;
                        break;
                    }
                }
            }
            if (conta >= 0) {
                librosPrestados.remove(conta);
                break;
            }
        }
    }
}
