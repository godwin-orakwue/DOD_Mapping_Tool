
package org.hl7.ctsvapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CTSVersionId complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CTSVersionId">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="major" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="minor" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CTSVersionId", propOrder = { "major", "minor" })
public class CTSVersionId {

    /**
     * Major.
     */
    @SuppressWarnings("all")
    protected short major;
    
    /**
     * Minor.
     */
    @SuppressWarnings("all")
    protected short minor;

    /**
     * Gets the value of the major property.
     * 
     * @return version
     */
    @SuppressWarnings("all")
    public short getMajor() {
        return major;
    }

    /**
     * Sets the value of the major property.
     * 
     * @param value value
     */
    @SuppressWarnings("all")
    public void setMajor(short value) {
        this.major = value;
    }

    /**
     * Gets the value of the minor property.
     * 
     * @return version
     */
    @SuppressWarnings("all")
    public short getMinor() {
        return minor;
    }

    /**
     * Sets the value of the minor property.
     * 
     * @param value value
     */
    @SuppressWarnings("all")
    public void setMinor(short value) {
        this.minor = value;
    }

}
