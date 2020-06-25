
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
 *         &lt;element name="codeSystem_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="matchText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="matchAlgorithm_code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="language_code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="activeConceptsOnly" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="timeout" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sizeLimit" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "codeSystemId", "matchText", "matchAlgorithmCode", "languageCode", "activeConceptsOnly", "timeout", "sizeLimit" })
@XmlRootElement(name = "lookupConceptCodesByDesignation")
public class LookupConceptCodesByDesignation {

    /**
     * Code system ID.
     */
    @XmlElement(name = "codeSystem_id", required = true)
    protected String codeSystemId;
    
    /**
     * Match text.
     */
    @XmlElement(required = true)
    protected String matchText;
    
    /**
     * Algorithm.
     */
    @XmlElement(name = "matchAlgorithm_code", required = true)
    protected String matchAlgorithmCode;
    
    /**
     * Language.
     */
    @XmlElement(name = "language_code", required = true)
    protected String languageCode;
    
    /**
     * Active concepts only.
     */
    protected boolean activeConceptsOnly;
    
    /**
     * Timeout.
     */
    protected int timeout;
    
    /**
     * Limit.
     */
    protected int sizeLimit;

    /**
     * Gets the value of the codeSystemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeSystemId() {
        return codeSystemId;
    }

    /**
     * Sets the value of the codeSystemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeSystemId(String value) {
        this.codeSystemId = value;
    }

    /**
     * Gets the value of the matchText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatchText() {
        return matchText;
    }

    /**
     * Sets the value of the matchText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatchText(String value) {
        this.matchText = value;
    }

    /**
     * Gets the value of the matchAlgorithmCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatchAlgorithmCode() {
        return matchAlgorithmCode;
    }

    /**
     * Sets the value of the matchAlgorithmCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatchAlgorithmCode(String value) {
        this.matchAlgorithmCode = value;
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
     * Gets the value of the activeConceptsOnly property.
     * 
     * @return active flag
     */
    public boolean isActiveConceptsOnly() {
        return activeConceptsOnly;
    }

    /**
     * Sets the value of the activeConceptsOnly property.
     * 
     * @param value value
     */
    public void setActiveConceptsOnly(boolean value) {
        this.activeConceptsOnly = value;
    }

    /**
     * Gets the value of the timeout property.
     * 
     * @return timeout
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * Sets the value of the timeout property.
     * 
     * @param value value
     */
    public void setTimeout(int value) {
        this.timeout = value;
    }

    /**
     * Gets the value of the sizeLimit property.
     * 
     * @return limit
     */
    public int getSizeLimit() {
        return sizeLimit;
    }

    /**
     * Sets the value of the sizeLimit property.
     * 
     * @param value value
     */
    public void setSizeLimit(int value) {
        this.sizeLimit = value;
    }

}
