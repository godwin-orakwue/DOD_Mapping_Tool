
package org.hl7.ctsvapi;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * Oracle JAX-WS 2.1.5
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "fault7", targetNamespace = "urn://hl7.org/CTSVAPI")
public class BadlyFormedMatchText_Exception extends Exception {

    /**
     * Class UID.
     */
    private static final long serialVersionUID = -5092697050861304313L;
    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private BadlyFormedMatchText faultInfo;

    /**
     * 
     * @param message message
     * @param faultInfo fault
     */
    public BadlyFormedMatchText_Exception(String message, BadlyFormedMatchText faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message message
     * @param faultInfo fault
     * @param cause cause
     */
    public BadlyFormedMatchText_Exception(String message, BadlyFormedMatchText faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: org.hl7.ctsvapi.BadlyFormedMatchText
     */
    public BadlyFormedMatchText getFaultInfo() {
        return faultInfo;
    }

}
