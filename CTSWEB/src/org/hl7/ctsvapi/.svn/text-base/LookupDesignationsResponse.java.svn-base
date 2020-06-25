
package org.hl7.ctsvapi;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lookupDesignationsReturn" type="{urn://hl7.org/CTSVAPI}ConceptDesignation" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "lookupDesignationsReturn" })
@XmlRootElement(name = "lookupDesignationsResponse")
public class LookupDesignationsResponse {

    /**
     * Designations.
     */
    @XmlElement(required = true)
    protected List<ConceptDesignation> lookupDesignationsReturn;

    /**
     * Gets the value of the lookupDesignationsReturn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lookupDesignationsReturn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLookupDesignationsReturn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConceptDesignation }
     * 
     * 
     * @return list
     */
    public List<ConceptDesignation> getLookupDesignationsReturn() {
        if (lookupDesignationsReturn == null) {
            lookupDesignationsReturn = new ArrayList<ConceptDesignation>();
        }
        return this.lookupDesignationsReturn;
    }

}
