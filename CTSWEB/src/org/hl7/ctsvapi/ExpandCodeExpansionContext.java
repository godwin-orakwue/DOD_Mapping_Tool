
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
 *         &lt;element name="contextToExpand" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "contextToExpand" })
@XmlRootElement(name = "expandCodeExpansionContext")
public class ExpandCodeExpansionContext {

    /**
     * Context.
     */
    @XmlElement(required = true)
    protected byte[] contextToExpand;

    /**
     * Gets the value of the contextToExpand property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getContextToExpand() {
        return contextToExpand;
    }

    /**
     * Sets the value of the contextToExpand property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setContextToExpand(byte[] value) {
        this.contextToExpand = ((byte[]) value);
    }

}
