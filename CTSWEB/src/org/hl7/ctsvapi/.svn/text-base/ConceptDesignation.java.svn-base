
package org.hl7.ctsvapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConceptDesignation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConceptDesignation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="designation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="language_code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="preferredForLanguage" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConceptDesignation", propOrder = { "designation", "languageCode", "preferredForLanguage" })
public class ConceptDesignation {

    /**
     * Designation.
     */
    @XmlElement(required = true, nillable = true)
    protected String designation;
    
    /**
     * Language.
     */
    @XmlElement(name = "language_code", required = true, nillable = true)
    protected String languageCode;
    
    /**
     * Preferred flag.
     */
    protected boolean preferredForLanguage;

    /**
     * Gets the value of the designation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Sets the value of the designation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesignation(String value) {
        this.designation = value;
    }

    /**
     * Gets the value of the languageCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguageCode() {
        return languageCode;
    }

    /**
     * Sets the value of the languageCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguageCode(String value) {
        this.languageCode = value;
    }

    /**
     * Gets the value of the preferredForLanguage property.
     * 
     * @return preferred language
     */
    public boolean isPreferredForLanguage() {
        return preferredForLanguage;
    }

    /**
     * Sets the value of the preferredForLanguage property.
     * 
     * @param value value
     */
    public void setPreferredForLanguage(boolean value) {
        this.preferredForLanguage = value;
    }

}
