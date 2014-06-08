package dojo.service;

import play.libs.WS;
import dojo.dao.LibroDao;

/**
 * @author rfanego
 */
public class LibroService {
	LibroDao libroDao;
	public Double bookPrice(){
		WS.Response response = WS.url("http://localhost:9000/book/price").get().get(5000L);
		return Double.valueOf(response.getBody());
	}
	
	public Integer discount(int quantity) throws IlegalQuantityException{
		if((quantity < 1) || (quantity > 5)){
			throw new IlegalQuantityException();
		}
		return libroDao.discount(quantity);
	}
	
	public static Integer sarasa(){
		WS.Response response = WS.url("http://localhost:9000/book/price").get().get(5000L);
		return Integer.valueOf(response.getBody());
	}
}
