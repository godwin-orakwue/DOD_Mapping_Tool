
package org.hl7.ctsvapi;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * Oracle JAX-WS 2.1.5
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "fault10", targetNamespace = "urn://hl7.org/CTSVAPI")
public class UnknownPropertyCode_Exception extends Exception {

    /**
     * Class UID.
     */
    private static final long serialVersionUID = 5660394684549232688L;
    
    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private UnknownPropertyCode faultInfo;

    /**
     * 
     * @param message message
     * @param faultInfo fault
     */
    public UnknownPropertyCode_Exception(String message, UnknownPropertyCode faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message message
     * @param faultInfo fault
     * @param cause cause
     */
    public UnknownPropertyCode_Exception(String message, UnknownPropertyCode faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: org.hl7.ctsvapi.UnknownPropertyCode
     */
    public UnknownPropertyCode getFaultInfo() {
        return faultInfo;
    }

}
