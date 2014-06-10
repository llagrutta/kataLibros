package dojo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.AssertTrue;

import junit.framework.Assert;

import mockit.Expectations;
import mockit.Mocked;
import models.BookStore;
import models.DependencyAbc;

import org.junit.Test;

import dojo.dao.LibroDao;
import dojo.service.IlegalQuantityException;
import dojo.service.LibroService;

/*
 * Hay una saga que tiene 5 libros en total. Como está de moda, existe una promoción por comprar los distintos libros de la saga
 * 
 * Cada una de las copias cuesta X pesos, sin importar de cual de los 5 libros se trata. Si comprás 2 libros distintos, obtenés un A% de descuento.
 * Si comprás 3, B% de descuento. 4 libros distintos, C% de descuento.  5 libros, D% de descuento.
 *  
 * Crear un algoritmo que dado una compra de libros, te devuelva el precio total calculando el mayor descuento posible.
 * 
 * Ejemplo: [0,1,1,2,2,2] (un libro de la clase 0, dos de la clase 1 y tres de la clase 2 )
 * El precio sería = (precio de 0+1+2 con un B% de descuento) + (precio de 1+2 con un A% de descuento) + (precio de 2)
 * 
 * Tanto el valor X del libro, como los valores A,B,C y D de los descuentos se obtienen mediante una llamada a los servicios
 * expuestos en LibroService.
 * 
 * El algoritmo debe contemplar que el método del servicio que calcula el descuento para una cantidad dada puede levantar una excepción,
 * y las cantidades para las cuales la levanta podrían variar en un futuro cercano
 * 
 */

public class KataTest {
	
	
	
	@Test
	public void baby1(@Mocked final LibroService service) throws IlegalQuantityException {
		Integer[] libros = new Integer[5];
		libros[0] = 1;
		libros[1] = 0;
		libros[2] = 0;
		libros[3] = 0;
		libros[4] = 0;
		
		new Expectations() {{
			service.bookPrice(); result = 3.0;
			service.discount(1); result = 0;
		}};
		
		BookStore bs = new BookStore(service);
		
		Double price = bs.buyPrice(libros);
		
		Assert.assertEquals(3.0, price);
		
	}
	
	@Test
	public void baby2(@Mocked final LibroService service) throws IlegalQuantityException {
		Integer[] libros = new Integer[5];
		libros[0] = 2;
		libros[1] = 0;
		libros[2] = 0;
		libros[3] = 0;
		libros[4] = 0;
		
		new Expectations() {{
			service.bookPrice(); result = 3.0;
			service.discount(1); result = 0;
		}};
		
		BookStore bs = new BookStore(service);
		
		Double price = bs.buyPrice(libros);
		
		Assert.assertEquals(6.0, price);
	}
	
	@Test
	public void baby3(@Mocked final LibroService service) throws IlegalQuantityException {
		Integer[] libros = new Integer[5];
		libros[0] = 1;
		libros[1] = 1;
		libros[2] = 0;
		libros[3] = 0;
		libros[4] = 0;
		
		new Expectations() {{
			service.bookPrice(); result = 3.0;
			service.bookPrice(); result = 3.0;
			service.discount(2); result = 20;
		}};
		BookStore bs = new BookStore(service);
		
		Double price = bs.buyPrice(libros);
		
		Assert.assertEquals(4.8, price);
	}
	
	@Test
	public void baby4(@Mocked final LibroService service) throws IlegalQuantityException {
		Integer[] libros = new Integer[5];
		libros[0] = 1;
		libros[1] = 1;
		libros[2] = 1;
		libros[3] = 0;
		libros[4] = 0;
		
		new Expectations() {{
			service.bookPrice(); result = 3.0;
			service.bookPrice(); result = 3.0;
			service.bookPrice(); result = 3.0;
			service.discount(3); result = 20;
		}};
		BookStore bs = new BookStore(service);
		
		Double price = bs.buyPrice(libros);
		
		Assert.assertEquals(7.2, price);
	}
	
}
