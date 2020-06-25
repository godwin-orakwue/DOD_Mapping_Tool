
package org.hl7;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
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
 *         &lt;element name="getServiceDescriptionReturn" type="{urn://cts.hl7.org/types}ST" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@SuppressWarnings("all")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "getServiceDescriptionReturn"
        })
@XmlRootElement(name = "getServiceDescriptionResponse")
public class GetServiceDescriptionResponse {

    /**
     * CTS service description.
     */
    protected ST getServiceDescriptionReturn;

    /**
     * Gets the value of the getServiceDescriptionReturn property.
     *
     * @return
     *     possible object is
     *     {@link ST }
     *
     */
    public ST getGetServiceDescriptionReturn() {
        return getServiceDescriptionReturn;
    }

    /**
     * Sets the value of the getServiceDescriptionReturn property.
     *
     * @param value
     *     allowed object is
     *     {@link ST }
     *
     */
    public void setGetServiceDescriptionReturn(ST value) {
        this.getServiceDescriptionReturn = value;
    }

}
