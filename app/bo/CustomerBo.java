package bo;

import models.Customer;
import utils.SarasaUtil;
import api.CardsAPI;
import dao.CustomerDao;

public class CustomerBo {
	public static String STATUS_BLOQUEADO = "BLOQUEADO";
	public static String STATUS_PENDIENTE = "PENDIENTE";
	public static String STATUS_ACTIVO = "ACTIVO";
	private CardsAPI cardsAPIClient = new CardsAPI();
	
	public void validateStatusOnInsert(int customerId, Customer customer) throws Exception {
		if (CustomerDao.canBeUser(customerId)){
			String status = customer.getStatus();

			if (!hasDocumentation(customerId, customer.getSiteID())){
				status = STATUS_BLOQUEADO;
			}

			if (!hasGoodCreditLevel(customerId) && !cardsAPIClient.hasActiveCards(customerId)){
				status = STATUS_PENDIENTE;
				if (!SarasaUtil.validSiteIdForCreditCard(customer.getSiteID())){
					throw new RuntimeException("Estado invalido");
				}
			}

			customer.setStatus(status);
		}else{
			throw new RuntimeException("El custID no tiene los requerimientos minimos para usar MercadoClics");
		}
	}

	public boolean hasDocumentation(int advID, Object siteID) {
		//LLAMADA A LA BASE
		return false;
	}

	public boolean hasGoodCreditLevel(int advID) {
		//LLAMADA A LA BASE
		return false;
	}
}
