package ir.maktab.homeServiceProvider.util;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import ir.maktab.homeServiceProvider.dao.Book;
import ir.maktab.homeServiceProvider.dao.BookDao;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestSaveBook {

    private static BookDao bookDAO;

    @BeforeClass
    public static  void runBeforeClass() {
        bookDAO = new BookDao();
    }

    @AfterClass
    public static void runAfterClass() {
        bookDAO = null;
    }


    @Test
    public void testSaveBook(String picName) {

        //File file = new File("images\\extjsfirstlook.jpg"); //windows
        File file = new File("C:\\image\\" + picName + "");
        byte[] bFile = new byte[(int) file.length()];

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Book book = new Book();
        book.setName("Ext JS 4 First Look");
        book.setImage(bFile);

        bookDAO.saveBook(book);

        assertNotNull(book.getId());
    }


    @Test
    public void testGetBook() {

        Book book = bookDAO.getBook((long) 1);

        assertNotNull(book);

        try{
            //FileOutputStream fos = new FileOutputStream("images\\output.jpg");  //windows
            FileOutputStream fos = new FileOutputStream("images/output.jpg");
            fos.write(book.getImage());
            fos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}