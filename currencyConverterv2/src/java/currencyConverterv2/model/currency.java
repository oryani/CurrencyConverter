package currencyConverterv2.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class currency implements currencyDTO, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private double value;
    private String currencyName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public currency(){
        
    }
    public currency(String currName, double curValue){
        this.currencyName = currName;
        this.value = curValue;
    }
    @Override
    public double getValue()
    {
        return this.value;
    }
    
    @Override
    public String getCurrency()
    {
        return this.currencyName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof currency)) {
            return false;
        }
        currency other = (currency) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "currencyConverterv2.model.currency[ id=" + id + " ]";
    }
    
}
