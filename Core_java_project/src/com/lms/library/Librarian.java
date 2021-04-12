package com.lms.library;
import java.util.ArrayList;
import java.util.Scanner;

public class Librarian extends Thread {
	private Scanner scan = new Scanner(System.in); 
	public void access()
	{
		BookDTO bookDTO = new BookDTO(); 
		BookDAO bookAccessObject = bookDTO.bookDAO(); 
		System.out.println("Press 1 to add a book..."); 
		System.out.println("Press 2 to search book..."); 
		System.out.println("Press 3 to search book using author name");
		System.out.println("Press 4 to search book using book title");
		System.out.println("Press 5 to update book..."); 
		System.out.println("Press 6 to remove book..."); 
		System.out.println("Enter your choice..."); 
		int choice = scan.nextInt(); 
		if(choice == 1) 
		{ 
			bookAccessObject.addBook(details()); 
		} 
		else if(choice == 2) 
		{
			System.out.println("---Please Enter Details To Search Book---");
			System.out.println("Enter book title..."); 
			String title = scan.next(); 
			System.out.println("Enter author name...");
			String author = scan.next();
			System.out.println("Enter the edition...");
			int edition = scan.nextInt();
			Book ob=bookAccessObject.search(title, author, edition);
			if(ob!=null)
			System.out.println(ob);
			else
			System.out.println("No record found");
		}
		else if(choice == 3) 
		{
			System.out.println("Enter author name...");
			String author = scan.next();
			ArrayList<Book> ob=bookAccessObject.searchBookByAuthor(author);
			display(ob);
		}
		else if(choice == 4) 
		{
			System.out.println("Enter book title..."); 
			String title = scan.next(); 
			ArrayList<Book> ob=bookAccessObject.searchBookByTitle(title);
			display(ob);
		}
		else if(choice == 5) 
		{
			if(bookAccessObject.updateBook(details()))
				System.out.println("Book has been updated");
			else
				System.out.println("no records found to update...");
		}
		else if(choice == 6) 
		{
			if(bookAccessObject.removeBook(details()))
				System.out.println("Book has been removed");
			else
				System.out.println("no records found to remove...");
				
		}
		else
		{
			throw new InvalidChoiceException();
		}
	}
	Book details()
	{
		System.out.println("---Please Enter Book Details---");
		System.out.println("Enter book title..."); 
		String title = scan.next(); 
		System.out.println("Enter author name...");
		String author = scan.next(); 
		System.out.println("Enter genre..."); 
		String genre = scan.next(); 
		System.out.println("Enter the edition...");
		int edition = scan.nextInt();
		System.out.println("Enter the price..."); 
		int price = scan.nextInt(); 
		Book book = new Book(title, author, genre, edition,price);
		return book;
	}
	void display(ArrayList<Book> ob)
	{
		if(!ob.isEmpty()) {
			for(int i = 0; i < ob.size(); i++)
				System.out.println(ob.get(i));
		}
		else
			System.out.println("No records found");
		
	}
	@Override 
	public void run() 
	{ 
		System.out.println("Welcome to Library Management System "); 
		do 
		{ 
			access();  
			System.out.println("Press 'Y' to continue..."); 
			System.out.println("Press 'N' to stop..."); 
		}while(scan.next().charAt(0) == 'Y' || scan.next().charAt(0)=='y');
		System.out.println("Thank you visit us again...."); 
	}

}
