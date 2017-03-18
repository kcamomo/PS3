package pkgLibrary;

import java.util.ArrayList;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Catalog {

	@XmlAttribute
	int id;

	@XmlElement(name = "book")
	ArrayList<Book> books;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	public Book GetBook(String id) throws BookException {
		Book b = null;
		for (Book m : books) {
			if (m.getId().equals(id)) {
				b = m;
			}
		}
		if (b == null) {
			b = new Book(id);
			throw new BookException(b);
		}
		return b;
	}

	public void AddBook(String id) throws BookException {
		for (Book b : books) {
			if (b.getId().equals(id)) {
				throw new BookException(b);
			}
		}
		Book b = new Book(id);
		books.add(b);
	}
}
