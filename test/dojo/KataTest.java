package dojo;

import mockit.Expectations;
import mockit.Mocked;

import org.junit.Test;

import service.LibroService;
import static org.fest.assertions.Assertions.*;

/*
 * Hay una saga que tiene 5 libros en total. Como está de moda, existe una promoción por comprar los distintos libros de la saga
 * 
 * Cada una de las copias cuesta X pesos, sin importar de cual de los 5 libros se trata. Si comprás 2 libros distintos, obtenés un A% de descuento.  Si comprás 3, B% de descuento.
 * 4 libros distintos, C% de descuento.  5 libros, D% de descuento.
 *  
 * Crear un algoritmo que dado una compra de libros, te devuelva el precio total calculando el mayor descuento posible.
 * 
 * Ejemplo: [0,1,1,2,2,2] 
 * El precio sería = (precio de 0+1+2 con un B% de descuento) + (precio de 1+2 con un A% de descuento) + (precio de 2)
 * 
 * Tanto el valor X del libro, como los valores A,B,C y D de los descuentos se obtienen mediante una llamada a los servicios
 * expuestos en LibroService.
 */

public class KataTest {
	@Mocked
	LibroService service;
	@Test
	public void test() {
		service = new LibroService(); 
		new Expectations() {{
			service.bookPrice();
			result = 8.0;
		}};
		assertThat(8.0).isEqualTo(service.bookPrice());
	}

	@Test
	public void test2() {
		new Expectations() {{
			LibroService.sarasa();
			result = 188;
		}};
		assertThat(188).isEqualTo(LibroService.sarasa());
	}
	
	@Test
	public void test3() {
		assertThat(0).isEqualTo(0);
	}
}
