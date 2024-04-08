package Controller;

import Entity.Tienda;

import javax.swing.*;
import java.util.List;

public class TiendaController {

    public  String getAll(List<Object> listObject){
        String list= "Lista Tiendas\n";
        //iteramos sobre la lista que devuelve el método findAll
        for (Object obj: listObject){
            //convertimos o casteamos el objeto tipo Object a un coder
            Tienda objTienda =(Tienda) obj;
            //concatenamos la información
            list +=objTienda.toString()+"\n";

        }
        return list;

    }
}
