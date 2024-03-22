package controller;

import entity.Author;
import entity.Book;
import model.AuthorModel;
import model.BooksModel;


import javax.swing.*;
import java.util.List;



public class BookController {

    BooksModel objBookModel=new BooksModel();
    AuthorModel objAuthorModel = new AuthorModel();

    AuthorController objAuthorController = new AuthorController();

    public BookController() {
        //crear una instancia del moder

    }

    public void create() {
        Book objBook = new Book();
        AuthorModel objAuthorModel = new AuthorModel();
        AuthorController objController = new AuthorController();
        String title = JOptionPane.showInputDialog(null, "Insert title: ");
        int publication_year= Integer.parseInt(JOptionPane.showInputDialog(null,"Insert publication year"));
        double price =Double.parseDouble(JOptionPane.showInputDialog(null,"Insert price"));
        int idAuthor = Integer.parseInt(JOptionPane.showInputDialog(null,objController.getAll(objAuthorModel.findAll()),"\nInsert idAuthor"));

        objBook.setTitle(title);
        objBook.setPublication_year(publication_year);
        objBook.setPrice(price);
        objBook.setIdAuthor(idAuthor);


        objBook = (Book) this.objBookModel.create(objBook);

        JOptionPane.showMessageDialog(null, objBook.toString());
    }


    public void getAll(){

        String list= this.getAll(this.objBookModel.findAll());

        JOptionPane.showMessageDialog(null,list);

    }


    public String getAll(List<Object> listObject) {
        String list = "List Books\n";
        //iteramos sobre la lista que devuelve el método findAll
        for (Object obj : listObject) {
            //convertimos o casteamos el objeto tipo Object a un coder
            Book objBook = (Book) obj;
            //concatenamos la información
            list += objBook.toString() + "\n";

        }
        return list;

    }


    public void query(){
        String searchName = JOptionPane.showInputDialog(null,"title search");

        List<Book> resultBooks = this.objBookModel.findByName(searchName);

        StringBuilder bookrl = new StringBuilder();
        resultBooks.forEach((book)->{
            bookrl.append(book.toString()).append("\n");
        });

        JOptionPane.showMessageDialog(null,bookrl.toString());

    }


    public void update(){
        //1. Listamos
        String listBooks = this.getAll(this.objBookModel.findAll());

        // 2.pedimos el id para verificar que el usuario exista
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listBooks+"\nEnter the ID of the Book to edit"));

        //3. verificamos el id
        Book objBook = (Book) this.objBookModel.query(idUpdate);

        if(objBook==null){
            JOptionPane.showMessageDialog(null, "Book not fount");

        }else {
            String title= JOptionPane.showInputDialog(null,"Enter new title", objBook.getTitle());
            int publication_year = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter Publication year", objBook.getPublication_year()));
            double price = Double.parseDouble(JOptionPane.showInputDialog(null,"Enter new price", objBook.getPrice()));
            int idAuthor = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter new idAuthor"));

            objBook.setTitle(title);
            objBook.setPublication_year(publication_year);
            objBook.setPrice(price);
            objBook.setIdAuthor(idAuthor);

            this.objBookModel.update(objBook);

        }

    }


    public void delete(){
        String listBookString = this.getAll(this.objBookModel.findAll());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listBookString+"Enter the ID of the Book to delete"));

        Book objBook =(Book) this.objBookModel.query(idDelete);

        if(objBook== null){
            JOptionPane.showMessageDialog(null,"Book not found");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Are you sure want to delete the Author: \n"+objBook.toString());
            // si el usuario escoge que si, entonces eliminamos y el equipo lo entiende como cero
            if(confirm==0){
                this.objBookModel.delete(objBook);
            }
        }



    }


    public void BooksByAuthor(){
        String list = "";

        String listAuthors= objAuthorController.getAll(objAuthorModel.findAll());

        int idAuthor =Integer.parseInt(JOptionPane.showInputDialog(null,listAuthors+ "Insert Id Author"));

        Author objAuthor = (Author) this.objAuthorModel.query(idAuthor);

        if(objAuthor==null){
            JOptionPane.showMessageDialog(null,"Author not found");

        }else {
            if(objBookModel.findBookByAuthor(idAuthor).isEmpty()){
                list += "Not books";
            } else{
                for(Object obj : this.objBookModel.findBookByAuthor(idAuthor)){

                    Book objBook = (Book) obj;

                    list += objBook.toString() + "\n";

                }
            }
        }

        JOptionPane.showMessageDialog(null,list);

    }

}
