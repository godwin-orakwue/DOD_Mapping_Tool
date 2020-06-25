
package org.hl7.ctsvapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UnknownPropertyCode complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UnknownPropertyCode">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="property_code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnknownPropertyCode", propOrder = { "propertyCode" })
public class UnknownPropertyCode {

    /**
     * Property code.
     */
    @XmlElement(name = "property_code", required = true, nillable = true)
    protected String propertyCode;

    /**
     * Gets the value of the propertyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropertyCode() {
        return propertyCode;
    }

    /**
     * Sets the value of the propertyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropertyCode(String value) {
        this.propertyCode = value;
    }

}
