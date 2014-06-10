package models;

import java.util.List;

import dojo.service.IlegalQuantityException;
import dojo.service.LibroService;

public class BookStore {
	
	private LibroService juanito;
	
	public BookStore(LibroService juanitoHuerfanito) {
		juanito = juanitoHuerfanito;
	}
	
	public Double buyPrice(Integer[] libros) throws IlegalQuantityException {
		
		int i = 0;
		int quanty = 0;
		double price = 0;
		while(i < 5){
			if(libros[i] > 0){
				quanty++;
				price += libros[i] * juanito.bookPrice();
			}
			i++;
		}
		
		Integer d = juanito.discount(quanty);
		
		return price - (price * d/100);
	}

}
