
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
 *         &lt;element name="expandConcept_id" type="{urn://hl7.org/CTSVAPI}ConceptId"/>
 *         &lt;element name="relationship_code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sourceToTarget" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="directRelationsOnly" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="designationLanguage_code" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "", propOrder = { "expandConceptId", "relationshipCode", "sourceToTarget", "directRelationsOnly", "designationLanguageCode", "timeout", "sizeLimit" })
@XmlRootElement(name = "lookupCodeExpansion")
public class LookupCodeExpansion {

    /**
     * Concept id.
     */
    @XmlElement(name = "expandConcept_id", required = true)
    protected ConceptId expandConceptId;
    
    /**
     * Relationship.
     */
    @XmlElement(name = "relationship_code", required = true)
    protected String relationshipCode;
    
    /**
     * Source to target.
     */
    protected boolean sourceToTarget;
    
    /**
     * Direct only flag.
     */
    protected boolean directRelationsOnly;
    
    /**
     * Language.
     */
    @XmlElement(name = "designationLanguage_code", required = true)
    protected String designationLanguageCode;
    
    /**
     * Timeout.
     */
    protected int timeout;
    
    /**
     * Limit.
     */
    protected int sizeLimit;

    /**
     * Gets the value of the expandConceptId property.
     * 
     * @return
     *     possible object is
     *     {@link ConceptId }
     *     
     */
    public ConceptId getExpandConceptId() {
        return expandConceptId;
    }

    /**
     * Sets the value of the expandConceptId property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConceptId }
     *     
     */
    public void setExpandConceptId(ConceptId value) {
        this.expandConceptId = value;
    }

    /**
     * Gets the value of the relationshipCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelationshipCode() {
        return relationshipCode;
    }

    /**
     * Sets the value of the relationshipCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelationshipCode(String value) {
        this.relationshipCode = value;
    }

    /**
     * Gets the value of the sourceToTarget property.
     * 
     * @return source to target
     */
    public boolean isSourceToTarget() {
        return sourceToTarget;
    }

    /**
     * Sets the value of the sourceToTarget property.
     * 
     * @param value value
     */
    public void setSourceToTarget(boolean value) {
        this.sourceToTarget = value;
    }

    /**
     * Gets the value of the directRelationsOnly property.
     * 
     * @return direct relation
     */
    public boolean isDirectRelationsOnly() {
        return directRelationsOnly;
    }

    /**
     * Sets the value of the directRelationsOnly property.
     * 
     * @param value value
     */
    public void setDirectRelationsOnly(boolean value) {
        this.directRelationsOnly = value;
    }

    /**
     * Gets the value of the designationLanguageCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesignationLanguageCode() {
        return designationLanguageCode;
    }

    /**
     * Sets the value of the designationLanguageCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesignationLanguageCode(String value) {
        this.designationLanguageCode = value;
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
