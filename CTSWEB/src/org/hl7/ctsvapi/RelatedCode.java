
package org.hl7.ctsvapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RelatedCode complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RelatedCode">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pathLength" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="concept_code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="designation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="relationQualifiers" type="{urn://hl7.org/CTSVAPI}ArrayOf_xsd_string"/>
 *         &lt;element name="canExpand" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="expansionContext" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RelatedCode", propOrder = { "pathLength", "conceptCode", "designation", "relationQualifiers", "canExpand", "expansionContext" })
public class RelatedCode {

    /**
     * Path length.
     */
    @SuppressWarnings("all")
    protected short pathLength;
    
    /**
     * Code.
     */
    @XmlElement(name = "concept_code", required = true, nillable = true)
    protected String conceptCode;
    
    /**
     * Designation.
     */
    @XmlElement(required = true, nillable = true)
    protected String designation;
    
    /**
     * Qualifiers.
     */
    @XmlElement(required = true, nillable = true)
    protected ArrayOfXsdString relationQualifiers;
    
    /**
     * Expansion flag.
     */
    protected boolean canExpand;
    
    /**
     * Expansion context.
     */
    @XmlElement(required = true, nillable = true)
    protected byte[] expansionContext;

    /**
     * Gets the value of the pathLength property.
     * 
     * @return path length
     */
    @SuppressWarnings("all")
    public short getPathLength() {
        return pathLength;
    }

    /**
     * Sets the value of the pathLength property.
     * 
     * @param value value
     */
    @SuppressWarnings("all")
    public void setPathLength(short value) {
        this.pathLength = value;
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
     * Gets the value of the canExpand property.
     * 
     * @return expand flag
     */
    public boolean isCanExpand() {
        return canExpand;
    }

    /**
     * Sets the value of the canExpand property.
     * 
     * @param value value
     */
    public void setCanExpand(boolean value) {
        this.canExpand = value;
    }

    /**
     * Gets the value of the expansionContext property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getExpansionContext() {
        return expansionContext;
    }

    /**
     * Sets the value of the expansionContext property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setExpansionContext(byte[] value) {
        this.expansionContext = ((byte[]) value);
    }

}
