
package org.hl7.ctsvapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConceptRelationship complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConceptRelationship">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sourceConcept_id" type="{urn://hl7.org/CTSVAPI}ConceptId"/>
 *         &lt;element name="relationship_code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="relationQualifiers" type="{urn://hl7.org/CTSVAPI}ArrayOf_xsd_string"/>
 *         &lt;element name="targetConcept_id" type="{urn://hl7.org/CTSVAPI}ConceptId"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConceptRelationship", propOrder = { "sourceConceptId", "relationshipCode", "relationQualifiers", "targetConceptId" })
public class ConceptRelationship {

    /**
     * ID.
     */
    @XmlElement(name = "sourceConcept_id", required = true, nillable = true)
    protected ConceptId sourceConceptId;
    
    /**
     * Relationship.
     */
    @XmlElement(name = "relationship_code", required = true, nillable = true)
    protected String relationshipCode;
    
    /**
     * Qualifiers.
     */
    @XmlElement(required = true, nillable = true)
    protected ArrayOfXsdString relationQualifiers;
    
    /**
     * Target concept.
     */
    @XmlElement(name = "targetConcept_id", required = true, nillable = true)
    protected ConceptId targetConceptId;

    /**
     * Gets the value of the sourceConceptId property.
     * 
     * @return
     *     possible object is
     *     {@link ConceptId }
     *     
     */
    public ConceptId getSourceConceptId() {
        return sourceConceptId;
    }

    /**
     * Sets the value of the sourceConceptId property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConceptId }
     *     
     */
    public void setSourceConceptId(ConceptId value) {
        this.sourceConceptId = value;
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
     * Gets the value of the relationQualifiers property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfXsdString }
     *     
     */
    public ArrayOfXsdString getRelationQualifiers() {
        return relationQualifiers;
    }

    /**
     * Sets the value of the relationQualifiers property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfXsdString }
     *     
     */
    public void setRelationQualifiers(ArrayOfXsdString value) {
        this.relationQualifiers = value;
    }

    /**
     * Gets the value of the targetConceptId property.
     * 
     * @return
     *     possible object is
     *     {@link ConceptId }
     *     
     */
    public ConceptId getTargetConceptId() {
        return targetConceptId;
    }

    /**
     * Sets the value of the targetConceptId property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConceptId }
     *     
     */
    public void setTargetConceptId(ConceptId value) {
        this.targetConceptId = value;
    }

}
