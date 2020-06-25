
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
 *         &lt;element name="concept_id" type="{urn://hl7.org/CTSVAPI}ConceptId"/>
 *         &lt;element name="properties" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="matchText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="matchAlgorithm_code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="language_code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mimeTypes" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "conceptId", "properties", "matchText", "matchAlgorithmCode", "languageCode", "mimeTypes" })
@XmlRootElement(name = "lookupProperties")
public class LookupProperties {

    /**
     * Concept ID.
     */
    @XmlElement(name = "concept_id", required = true)
    protected ConceptId conceptId;
    
    /**
     * Properties.
     */
    @XmlElement(required = true)
    protected List<String> properties;
    
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
     * MIME types.
     */
    @XmlElement(required = true)
    protected List<String> mimeTypes;

    /**
     * Gets the value of the conceptId property.
     * 
     * @return
     *     possible object is
     *     {@link ConceptId }
     *     
     */
    public ConceptId getConceptId() {
        return conceptId;
    }

    /**
     * Sets the value of the conceptId property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConceptId }
     *     
     */
    public void setConceptId(ConceptId value) {
        this.conceptId = value;
    }

    /**
     * Gets the value of the properties property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the properties property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProperties().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return list
     */
    public List<String> getProperties() {
        if (properties == null) {
            properties = new ArrayList<String>();
        }
        return this.properties;
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
     * Gets the value of the mimeTypes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mimeTypes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMimeTypes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return list
     */
    public List<String> getMimeTypes() {
        if (mimeTypes == null) {
            mimeTypes = new ArrayList<String>();
        }
        return this.mimeTypes;
    }

}
