
package org.hl7.ctsvapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UnknownMimeTypeCode complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UnknownMimeTypeCode">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mimeType_code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnknownMimeTypeCode", propOrder = { "mimeTypeCode" })
public class UnknownMimeTypeCode {

    /**
     * MIME type.
     */
    @XmlElement(name = "mimeType_code", required = true, nillable = true)
    protected String mimeTypeCode;

    /**
     * Gets the value of the mimeTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMimeTypeCode() {
        return mimeTypeCode;
    }

    /**
     * Sets the value of the mimeTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMimeTypeCode(String value) {
        this.mimeTypeCode = value;
    }

}
