
package org.hl7.ctsvapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConceptId complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConceptId">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codeSystem_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="concept_code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConceptId", propOrder = { "codeSystemId", "conceptCode" })
public class ConceptId {

    /**
     * System ID.
     */
    @XmlElement(name = "codeSystem_id", required = true, nillable = true)
    protected String codeSystemId;
    
    /**
     * Concept code.
     */
    @XmlElement(name = "concept_code", required = true, nillable = true)
    protected String conceptCode;

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
     * Gets the value of the conceptCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConceptCode() {
        return conceptCode;
    }

    /**
     * Sets the value of the conceptCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConceptCode(String value) {
        this.conceptCode = value;
    }

}
