
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
 *         &lt;element name="lookupCodeExpansionReturn" type="{urn://hl7.org/CTSVAPI}RelatedCode" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "lookupCodeExpansionReturn" })
@XmlRootElement(name = "lookupCodeExpansionResponse")
public class LookupCodeExpansionResponse {

    /**
     * Lookup code expansion.
     */
    @XmlElement(required = true)
    protected List<RelatedCode> lookupCodeExpansionReturn;

    /**
     * Gets the value of the lookupCodeExpansionReturn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lookupCodeExpansionReturn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLookupCodeExpansionReturn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RelatedCode }
     * 
     * @return list
     */
    public List<RelatedCode> getLookupCodeExpansionReturn() {
        if (lookupCodeExpansionReturn == null) {
            lookupCodeExpansionReturn = new ArrayList<RelatedCode>();
        }
        return this.lookupCodeExpansionReturn;
    }

}
