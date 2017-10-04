package currencyConverterv2.controller;

import currencyConverterv2.model.currency;
import currencyConverterv2.model.currencyDTO;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
//@LocalBean
public class currencyFacade {
    //check currencyConverterv2PU
    @PersistenceContext(unitName = "currencyConverterv2PU")
    private EntityManager em;

    public void createCurrency(String currName, double curValue)
    {
        currency newcur = new currency(currName, curValue);
        em.persist(newcur);
    }
    
    public currencyDTO findCurrency(int currName)
    {
        currency curfound = em.find(currency.class, currName);
        return curfound;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}
