package com.lms.library;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class BookDTO 
{
	private static ArrayList<Book> bookList = new ArrayList<Book>();
	public BookDAO bookDAO()
	{ 
		return new BookDAOImp();
	}	
	private class BookDAOImp implements BookDAO
	{
		private Scanner scan = new Scanner(System.in);
		@Override
		public void addBook(Book book) {
			deserializes();
			bookList.add(book);	
			serializes();
		}

		@Override
		public ArrayList<Book> searchBookByTitle(String bookTitle) {
			deserializes();
			ArrayList<Book> tempBookList = new ArrayList<Book>();
			for (int i = 0; i < bookList.size(); i++)
			{
	            if (bookList.get(i).getBookTitle().equalsIgnoreCase(bookTitle))
	            {
	            	tempBookList.add(bookList.get(i));
	            }
	            
			}
			return tempBookList;
		}

		@Override
		public ArrayList<Book> searchBookByAuthor(String bookAuthor) {
			deserializes();
			ArrayList<Book> tempBookList = new ArrayList<Book>();
			for (int i = 0; i < bookList.size(); i++)
			{
	            if (bookList.get(i).getBookAuthor().equalsIgnoreCase(bookAuthor))
	            {
	            	tempBookList.add(bookList.get(i));
	            }
	            
			}
			return tempBookList;
		}

		@Override
		public Book search(String bookTitle, String bookAuthor, int edition) {
			deserializes();
			for (int i = 0; i < bookList.size(); i++)
			{
	            if ((bookList.get(i).getBookAuthor().equalsIgnoreCase(bookAuthor))&&(bookList.get(i).getBookAuthor().equalsIgnoreCase(bookAuthor))&&(bookList.get(i).getBookEdition()==edition))
	            {
	            	return bookList.get(i);
	            }
	            
			}
			return null;
			
		}

		@Override
		public boolean updateBook(Book book) {
			deserializes();
			int index=bookList.indexOf(search(book.getBookTitle(),book.getBookAuthor(),book.getBookEdition()));
			if(index>=0)
			{
				System.out.println("enter the updated edition");
				int update_edition = scan.nextInt();
				System.out.println("Enter the price..."); 
				int update_price = scan.nextInt();
				bookList.get(index).setBookEdition(update_edition);
				bookList.get(index).setBookPrice(update_price);
				serializes();
				return true;
			}
			return false;
		}

		@Override
		public boolean removeBook(Book book) {
			deserializes();
			if(bookList.remove(book))
			{
				serializes();
				return true;
			}
			return false;
		} 
		public void serializes()
		{
			 try
		        {
		            FileOutputStream fos = new FileOutputStream("LibraryData.ser");
		            ObjectOutputStream oos = new ObjectOutputStream(fos);
		            oos.writeObject(bookList);
		            oos.close();
		            fos.close();
		        } 
		        catch (IOException ioe) 
		        {
		            ioe.printStackTrace();
		        }
		}
		public void deserializes()
		{
			try
	        {
	            FileInputStream fis = new FileInputStream("LibraryData.ser");
	            ObjectInputStream ois = new ObjectInputStream(fis);
	 
	            bookList = (ArrayList) ois.readObject();
	 
	            ois.close();
	            fis.close();
	        } 
	        catch (IOException ioe) 
	        {
	        	FileOutputStream fos;
				try {
					fos = new FileOutputStream("LibraryData.ser");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	            return;
	        } 
	        catch (ClassNotFoundException c) 
	        {
	            System.out.println("Class not found");
	            c.printStackTrace();
	            return;
	        }
		}
		
	}
	
}
