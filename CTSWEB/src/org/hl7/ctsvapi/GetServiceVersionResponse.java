
package org.hl7.ctsvapi;

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
 *         &lt;element name="getServiceVersionReturn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "getServiceVersionReturn" })
@XmlRootElement(name = "getServiceVersionResponse")
public class GetServiceVersionResponse {

    /**
     * Service version.
     */
    @XmlElement(required = true)
    protected String getServiceVersionReturn;

    /**
     * Gets the value of the getServiceVersionReturn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetServiceVersionReturn() {
        return getServiceVersionReturn;
    }

    /**
     * Sets the value of the getServiceVersionReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetServiceVersionReturn(String value) {
        this.getServiceVersionReturn = value;
    }

}
