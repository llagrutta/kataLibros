package service;

import play.libs.WS;
import dao.LibroDao;

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
		return libroDao.discount(quantity);
	}
	
	public static Integer sarasa(){
		WS.Response response = WS.url("http://localhost:9000/book/price").get().get(5000L);
		return Integer.valueOf(response.getBody());
	}
}
