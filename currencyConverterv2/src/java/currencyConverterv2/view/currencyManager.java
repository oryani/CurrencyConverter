package currencyConverterv2.view;

import currencyConverterv2.controller.currencyFacade;
import currencyConverterv2.model.currencyDTO;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;


@Named("currencyManager")
@ConversationScoped
public class currencyManager implements Serializable {
    private static final long serialVersionUID = 16247164405L;
    // Creates a new instance of currencyManager 
    @EJB
    private currencyFacade curFacade;
    private String fromCurrency;
    private String toCurrency;
    private currencyDTO fCurrency;
    private currencyDTO tCurrency;
    private double result=0;
    private String result2;
    double amount;
    private Exception transactionFailure;
    
    
    @Inject 
    private Conversation conversation;

    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }
    
    private void handleException(Exception e) {
        stopConversation();
        e.printStackTrace(System.err);
        transactionFailure = e;
    }
    
    public void findFromToCurrency()
    {
        startConversation();
        fCurrency = curFacade.findCurrency(Integer.parseInt(fromCurrency));
        tCurrency = curFacade.findCurrency(Integer.parseInt(toCurrency));
    }
    
    public void convertCurrency2()
    {
        findFromToCurrency();
        result = this.amount * (fCurrency.getValue()/tCurrency.getValue());
    }
    
    public void createCurrency(String curName, double curValue)
    {
        //startConversation();
        //curFacade.createCurrency(curName, curValue);
       try {
        startConversation();
        curFacade.createCurrency(curName, curValue);
        } catch (Exception e) {
            handleException(e);
        }
    }
    
    public void initialize()
    {
        createCurrency("EUR", 1);
        createCurrency("USD", 0.7712);
        createCurrency("SEK", 0.1162);
        createCurrency("CAD", 0.7762);
    }
    
    
    
    public void setAmount(double amount)
    {
        this.amount= amount;
    }
    public double getAmount()
    {
        return this.amount;
    }
    public void setFromCurrency(String from)
    {
        this.fromCurrency = from;
    }
    
    public String getFromCurrency()
    {
        return this.fromCurrency;
    }
    
    public void setToCurrency(String to)
    {
        this.toCurrency = to;
    }
    
    public String getToCurrency()
    {
        return this.toCurrency;
    }
    public void setResult(double result2)
    {
        this.result=result2;
    }
    
    public double getResult()
    {
        return this.result;
    }
    public currencyManager() {
    }
    
    public void setResult2(String result2)
    {
        this.result2 = result2;
    }
    public String getResult2()
    {
        return this.result2;
    }
    public void convertCurrency()
    {
        this.result=100.5;
        this.result2 =fromCurrency +" "+toCurrency;
    }
}
