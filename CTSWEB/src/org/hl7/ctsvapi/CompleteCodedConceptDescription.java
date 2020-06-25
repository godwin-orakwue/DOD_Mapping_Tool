
package org.hl7.ctsvapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CompleteCodedConceptDescription complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CompleteCodedConceptDescription">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="concept_id" type="{urn://hl7.org/CTSVAPI}ConceptId"/>
 *         &lt;element name="conceptStatus_code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codeSystem_version" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="designatedBy" type="{urn://hl7.org/CTSVAPI}ArrayOfConceptDesignation"/>
 *         &lt;element name="hasProperties" type="{urn://hl7.org/CTSVAPI}ArrayOfConceptProperty"/>
 *         &lt;element name="sourceFor" type="{urn://hl7.org/CTSVAPI}ArrayOfConceptRelationship"/>
 *         &lt;element name="targetOf" type="{urn://hl7.org/CTSVAPI}ArrayOfConceptRelationship"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompleteCodedConceptDescription", propOrder = { "conceptId", "conceptStatusCode", "codeSystemVersion", "designatedBy", "hasProperties", "sourceFor", "targetOf" })
public class CompleteCodedConceptDescription {

    /**
     * Concept ID.
     */
    @XmlElement(name = "concept_id", required = true, nillable = true)
    protected ConceptId conceptId;
    
    /**
     * Status.
     */
    @XmlElement(name = "conceptStatus_code", required = true, nillable = true)
    protected String conceptStatusCode;
    
    /**
     * Version.
     */
    @XmlElement(name = "codeSystem_version", required = true, nillable = true)
    protected String codeSystemVersion;
    
    /**
     * Designated by.
     */
    @XmlElement(required = true, nillable = true)
    protected ArrayOfConceptDesignation designatedBy;
    
    /**
     * Properties flag.
     */
    @XmlElement(required = true, nillable = true)
    protected ArrayOfConceptProperty hasProperties;
    
    /**
     * Source for.
     */
    @XmlElement(required = true, nillable = true)
    protected ArrayOfConceptRelationship sourceFor;
    
    /**
     * Target of.
     */
    @XmlElement(required = true, nillable = true)
    protected ArrayOfConceptRelationship targetOf;

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
     * Gets the value of the conceptStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConceptStatusCode() {
        return conceptStatusCode;
    }

    /**
     * Sets the value of the conceptStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConceptStatusCode(String value) {
        this.conceptStatusCode = value;
    }

    /**
     * Gets the value of the codeSystemVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeSystemVersion() {
        return codeSystemVersion;
    }

    /**
     * Sets the value of the codeSystemVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeSystemVersion(String value) {
        this.codeSystemVersion = value;
    }

    /**
     * Gets the value of the designatedBy property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfConceptDesignation }
     *     
     */
    public ArrayOfConceptDesignation getDesignatedBy() {
        return designatedBy;
    }

    /**
     * Sets the value of the designatedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfConceptDesignation }
     *     
     */
    public void setDesignatedBy(ArrayOfConceptDesignation value) {
        this.designatedBy = value;
    }

    /**
     * Gets the value of the hasProperties property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfConceptProperty }
     *     
     */
    public ArrayOfConceptProperty getHasProperties() {
        return hasProperties;
    }

    /**
     * Sets the value of the hasProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfConceptProperty }
     *     
     */
    public void setHasProperties(ArrayOfConceptProperty value) {
        this.hasProperties = value;
    }

    /**
     * Gets the value of the sourceFor property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfConceptRelationship }
     *     
     */
    public ArrayOfConceptRelationship getSourceFor() {
        return sourceFor;
    }

    /**
     * Sets the value of the sourceFor property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfConceptRelationship }
     *     
     */
    public void setSourceFor(ArrayOfConceptRelationship value) {
        this.sourceFor = value;
    }

    /**
     * Gets the value of the targetOf property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfConceptRelationship }
     *     
     */
    public ArrayOfConceptRelationship getTargetOf() {
        return targetOf;
    }

    /**
     * Sets the value of the targetOf property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfConceptRelationship }
     *     
     */
    public void setTargetOf(ArrayOfConceptRelationship value) {
        this.targetOf = value;
    }

}
