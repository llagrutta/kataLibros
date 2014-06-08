package mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import models.Customer;

import org.junit.Test;

import utils.SarasaUtil;
import api.CardsAPI;
import bo.CustomerBo;
import dao.CustomerDao;

public class CustomerTest {

	@Test
	public void testWhenValidatingStatusOnInsertTheCustomerHasNoDocumentationChangeHisStatusToBlocked(
			@Mocked({"verifyAccountStatus(int,String)", "hasDocumentation(int,String)","hasGoodCreditLevel(int)"}) CustomerBo bo,
			@Mocked CustomerDao custDao,@Mocked CardsAPI cardsAPI) throws  Exception{
		final int advertiserId = 1;
		
		try {
			mockForValidateStatusOnInsertTests(bo,advertiserId,cardsAPI,true, false, true,true);
		}catch(Exception e){
			fail();
		}
		
		Customer cust = new Customer();
		cust.setStatus(CustomerBo.STATUS_ACTIVO);
		cust.setSiteID("MLA");
		
		bo.validateStatusOnInsert(advertiserId, cust);
		
		assertEquals(CustomerBo.STATUS_BLOQUEADO, cust.getStatus());
	}

	private void mockForValidateStatusOnInsertTests(final CustomerBo bo,final int advertiserId,final CardsAPI cardsAPI, final boolean canBeMLinksUser, final boolean hasDocumentation, 
													final boolean hasGoodCreditLevel, final boolean hasActiveCards) throws Exception{
		new NonStrictExpectations() {{
			CustomerDao.canBeUser(advertiserId);
			result = canBeMLinksUser;

			bo.hasDocumentation(advertiserId, "MLA");
			result = hasDocumentation;

			bo.hasGoodCreditLevel(advertiserId);
			result = hasGoodCreditLevel;
			
			cardsAPI.hasActiveCards(anyInt);
			result = hasActiveCards;
			
			SarasaUtil.validSiteIdForCreditCard(anyString);
		}};
	}
}
