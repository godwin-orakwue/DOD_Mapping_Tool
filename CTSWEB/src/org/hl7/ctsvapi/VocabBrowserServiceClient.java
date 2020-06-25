package org.hl7.ctsvapi;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.faces.model.SelectItem;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.log4j.Logger;
// This source file is generated by Oracle tools.
// Contents may be subject to change.
// For reporting problems, use the following:
// Generated by Oracle JDeveloper 11g Release 2 11.1.2.3.0.6276

/**
 * Vocabulary client.
 * 
 */
public class VocabBrowserServiceClient {

    /**
     * Logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(VocabBrowserServiceClient.class.getName());

    /**
     * Q Name.
     */
    private static QName qname = new QName("urn://hl7.org/CTSVAPI", "BrowserOperationsService");

    /**
     * SQL Exception.
     */
    private static final String EXCEPTION = "Exception";
    
    /**
     * Service WSDL.
     */
    private String valoc = "https://vhawasapp01.vha.med.va.gov:443/TerminologyBrowserWS/BrowserOperationsService?WSDL";
    
    /**
     * Properties containing URLs and OIDs.
     */
    private Properties vocabprops;
    
    /**
     * Constructor.
     */
    public VocabBrowserServiceClient() {
        FileInputStream fis = null;
        try {
            vocabprops = new Properties();
            fis = new FileInputStream("vocab.properties");
            vocabprops.load(fis);
//            String dodloc = vocabprops.getProperty("dodwsdl");
            valoc = vocabprops.getProperty("vawsdl", valoc);
        } catch (Exception e) {
            LOGGER.error(EXCEPTION + e.getMessage(), e);
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (Exception ex) {
                LOGGER.error(EXCEPTION + ex.getMessage(), ex);
            }
        }
    }

    /**
     * Get CTS lookup.
     * 
     * @param inoid OID
     * @return lookup
     */
    @SuppressWarnings("unchecked")
    public List<SelectItem> getCTSLookup(String inoid) {
        List<SelectItem> rtc = new ArrayList<SelectItem>();
        boolean doit = false;
        String oid = inoid;
        
        System.out.println("---------OID = " + inoid);
        if ("4.3.2.1.13".equals(inoid)) {
            // user
            oid = "4.3.7.1";
            doit = true;
        } else if ("4.3.2.1.8".equals(inoid)) {
            // VA HOSPITAL LOCATION
            doit = true;
        } else if ("4.3.7.30".equals(inoid)) {
            // VA Priority
            doit = true;
        } else if ("2.16.840.1.113883.3.42.127.56.850".equals(inoid)) {
            oid = "4.3.7.29";
            //va consult procedure
            doit = true;
        } else if ("4.3.2.1.6".equals(inoid)) {
            // VA result category
            doit = true;
        } else if ("4.3.2.1.14".equals(inoid)) {
            // VA collection sample
            oid = "4.3.5.3";                 // wires are crossed between VA & DoD
            doit = true;
        } else if ("4.3.5.3".equals(inoid)) {
            // specimen
            oid = "4.3.2.1.14";              // wires are crossed between VA & DoD
            doit = true;
        } else if ("4.3.7.18".equals(inoid)) {
            // micro result codes
            doit = true;
        } else if ("4.3.2.1.9".equals(inoid)) {
            // lab test
            doit = true;
        } else if ("4.3.2.1.12".equals(inoid)) {
            // rad test
            doit = true;
        }
        if (doit) {
            try {
                BrowserOperationsService browserOperationsService = new BrowserOperationsService(new URL(valoc), qname);
                BrowserOperations browserOperations = browserOperationsService.getVocabBrowserService();
                ((BindingProvider) browserOperations).getRequestContext().put("com.sun.xml.ws.connect.timeout", 30000);
                ((BindingProvider) browserOperations).getRequestContext().put("com.sun.xml.ws.request.timeout", 30000);
                ((BindingProvider) browserOperations).getRequestContext().put("com.sun.xml.internal.ws.connect.timeout", 30000);
                ((BindingProvider) browserOperations).getRequestContext().put("com.sun.xml.internal.ws.request.timeout", 30000);
                ConceptId expandConceptId = new ConceptId();
                expandConceptId.setCodeSystemId(oid);
                List<RelatedCode> rcode = browserOperations.lookupCodeExpansion(expandConceptId, null, false, false, null, 0, 0);
                if (rcode != null && rcode.size() > 0) {
                    Iterator<RelatedCode> iter = rcode.iterator();
                    while (iter.hasNext()) {
                        RelatedCode rc = iter.next();
                        SelectItem sitem = new SelectItem();
                        sitem.setLabel(rc.getDesignation() + " (" + rc.getConceptCode() + ")");
                        sitem.setValue(rc.getConceptCode() + "|" + rc.getDesignation());
//                  System.out.println("code "+rc.getConceptCode() + " - "+rc.getDesignation());
                        rtc.add(sitem);
                    }
                    if (rtc.size() > 1) {
                        Collections.sort(rtc, new LabelComparator());
                    }
//                for(SelectItem label: rtc)
//                {
//                    System.out.println(label.getLabel());
//                }
                }
            } catch (Exception e) {
                LOGGER.error("Exception: " + e.getMessage(), e);
                e.printStackTrace();
            }
        }
        return rtc;
    }

    /**
     * Label comparison.
     * 
     * @author wellerk
     *
     */
    @SuppressWarnings("unchecked")
    static class LabelComparator implements Comparator {

        /**
         * Compare.
         * 
         * @param obj1 object one
         * @param obj2 object two
         * @return compare
         */
        public int compare(Object obj1, Object obj2) {
            // Get labels
            SelectItem label1 = (SelectItem) obj1;
            SelectItem label2 = (SelectItem) obj2;

            // Get text
            String label1Text = label1.getLabel();
            String label2Text = label2.getLabel();
            if (label1Text == null) {
                label1Text = "";
            }
            if (label2Text == null) {
                label2Text = "";
            }

            return label1Text.compareToIgnoreCase(label2Text);
        }
    }
    
//  public static void main(String [] args) throws TimeoutError_Exception, UnexpectedError_Exception, UnknownCodeSystem_Exception, UnknownConceptCode_Exception, UnknownLanguageCode_Exception, UnknownRelationshipCode_Exception, MalformedURLException
//  {
//      VocabBrowserServiceClient client = new VocabBrowserServiceClient();
//      client.getCTSLookup("4.3.7.28");
//      client.getCTSLookup("4.3.2.1.8");
//      client.getCTSLookup("4.3.7.30");
//      client.getCTSLookup("4.3.2.1.14");
//      client.getCTSLookup("4.3.2.1.6");
//      client.getCTSLookup("4.3.2.1.13");
//      
//      
////      BrowserOperationsService browserOperationsService = new BrowserOperationsService(new URL("http://172.18.15.174:8080/vadodproxy/VocabBrowserService?WSDL"), qname);
////      BrowserOperations browserOperations = browserOperationsService.getVocabBrowserService();
////      List<CodeSystemIdAndVersions> rtc = browserOperations.getSupportedCodeSystems(0, 0);
////      Iterator<CodeSystemIdAndVersions> iter = rtc.iterator();
////      while(iter.hasNext()) {
////          CodeSystemIdAndVersions rcode = iter.next();
////          System.out.println("code "+rcode.getCodeSystemId() + " - "+rcode.getCodeSystemName());
////      }
//      
////      ConceptId expandConceptId = new ConceptId();
////      expandConceptId.setCodeSystemId("4.3.7.28");
////
////      List<RelatedCode> rtc = browserOperations.lookupCodeExpansion(expandConceptId, null, false, false, null, 0, 0);
////      Iterator<RelatedCode> iter = rtc.iterator();
////      while(iter.hasNext()) {
////          RelatedCode rcode = iter.next();
////          System.out.println("code "+rcode.getConceptCode() + " - "+rcode.getDesignation());
////      }
//  }
}

//code 2.16.840.1.113883.3.42.127.56.255.99 - DOD_CONSULT_PROCEDURE
//code 2.16.840.1.113883.3.42.127.100001.256 - DOD_PRIORITY
//code 4.3.2.1.13 - VA_USER
//code 2.16.840.1.113883.3.42.127.56.232 - DOD_USER
//code 2.16.840.1.113883.3.42.127.100001.293 - DOD_RESULT_CATEGORY
//code 4.3.2.1.8 - VA_HOSPITAL_LOCATION
//code 4.3.7.30 - VA_PRIORITY
//code 4.3.7.28 - VA_CONSULT_PROCEDURE
//code 4.3.2.1.6 - VA_RESULT_CATEGORY
//code 2.16.840.1.113883.3.42.127.56.282 - DOD_HOSPITAL_LOCATION