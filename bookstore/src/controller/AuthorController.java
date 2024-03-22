package controller;

import entity.Author;
import model.AuthorModel;

import javax.swing.*;
import java.util.List;

public class AuthorController {

    AuthorModel objAuthorModel;

    public AuthorController() {
        //crear una instancia del moder
        this.objAuthorModel = new AuthorModel();
    }

    public void create() {
        Author objAuthor = new Author();
        String name = JOptionPane.showInputDialog(null, "Insert name: ");
        String nationality = JOptionPane.showInputDialog(null, "Insert Nationality: ");

        objAuthor.setName(name);
        objAuthor.setNationality(nationality);

        objAuthor = (Author) this.objAuthorModel.create(objAuthor);

        JOptionPane.showMessageDialog(null, objAuthor.toString());
    }


    public String getAll(){

        String list= this.getAll(this.objAuthorModel.findAll());

        JOptionPane.showMessageDialog(null,list);

        return list;

    }


    public String getAll(List<Object> listObject) {
        String list = "List Authors\n";
        //iteramos sobre la lista que devuelve el método findAll
        for (Object obj : listObject) {
            //convertimos o casteamos el objeto tipo Object a un coder
            Author objAuthor = (Author) obj;
            //concatenamos la información
            list += objAuthor.toString() + "\n";

        }
        return list;

    }


    public void query(){
        String searchName = JOptionPane.showInputDialog(null,"name search");

        List<Author> resultCoders = this.objAuthorModel.findByName(searchName);

        StringBuilder authorl = new StringBuilder();
        resultCoders.forEach((author)->{
            authorl.append(author.toString()).append("\n");
        });

        JOptionPane.showMessageDialog(null,authorl.toString());

    }


    public void update(){
        //1. Listamos
        String listAuthors = this.getAll(this.objAuthorModel.findAll());

        // 2.pedimos el id para verificar que el usuario exista
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listAuthors+"\nEnter the ID of the author to edit"));

        //3. verificamos el id
        Author objAuthor = (Author) this.objAuthorModel.query(idUpdate);

        if(objAuthor==null){
            JOptionPane.showMessageDialog(null, "Author not fount");

        }else {
            String name= JOptionPane.showInputDialog(null,"Enter new name", objAuthor.getName());
            String nationality = JOptionPane.showInputDialog(null,"Enter new nationality", objAuthor.getNationality());
            objAuthor.setName(name);
            objAuthor.setNationality(nationality);

            this.objAuthorModel.update(objAuthor);

        }

    }


    public void delete(){
        String listAuthorString = this.getAll(this.objAuthorModel.findAll());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listAuthorString+"Enter the ID of the Author to delete"));

        Author objAuthor =(Author) this.objAuthorModel.query(idDelete);

        if(objAuthor== null){
            JOptionPane.showMessageDialog(null,"Author not found");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Are you sure want to delete the Author: \n"+objAuthor.toString());
            // si el usuario escoge que si, entonces eliminamos y el equipo lo entiende como cero
            if(confirm==0){
                this.objAuthorModel.delete(objAuthor);
            }
        }



    }


}
