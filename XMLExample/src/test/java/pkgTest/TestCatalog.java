package pkgTest;

import static org.junit.Assert.*;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import pkgLibrary.Book;
import pkgLibrary.BookException;
import pkgLibrary.Catalog;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCatalog {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testgetbook() throws BookException {
		Catalog cat = ReadXMLFile();
		cat.GetBook("bk101");
	}

	@Test(expected = BookException.class) // exception
	public void testgetbook2() throws BookException {
		Catalog cat = ReadXMLFile();
		cat.GetBook("bk5326523");
	}

	@Test
	public void testaddbook1() throws BookException {
		Catalog cat = ReadXMLFile();
		Book b = new Book("bk999");
		cat.AddBook("bk999");

	}

	@Test(expected = BookException.class) // exception
	public void testaddbook2() throws BookException {
		Catalog cat = ReadXMLFile();
		Book b = new Book("bk101");
		cat.AddBook("bk101");
	}

	private static void WriteXMLFile(Catalog cat) {
		try {

			String basePath = new File("").getAbsolutePath();
			basePath = basePath + "/src/main/resources/XMLFiles/Books.xml";
			File file = new File(basePath);

			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(cat, file);
			jaxbMarshaller.marshal(cat, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	private static Catalog ReadXMLFile() {

		Catalog cat = null;

		String basePath = new File("").getAbsolutePath();
		basePath = basePath + "/src/main/resources/XMLFiles/Books.xml";
		File file = new File(basePath);

		System.out.println(file.getAbsolutePath());
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			cat = (Catalog) jaxbUnmarshaller.unmarshal(file);
			System.out.println(cat);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return cat;

	}

	// method to set our costs, 20% lower than price
	public static Catalog calcCosts(Catalog cat, double costof) {
		for (Book b : cat.getBooks()) {
			double newPrice = (b.getPrice() * costof) - b.getPrice();
			b.setCost(Math.round(newPrice * 100.0) / 100.0);
		}

		return cat;
	}

}