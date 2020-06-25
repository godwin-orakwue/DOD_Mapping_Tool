package org.hl7;


import java.util.Properties;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.hl7.data.CTSMaps;


/**
 *  Code Mapping service implementation class.
 *
 * @author <a href="mailto:karl.a.weller@leidos.com">karl.a.weller</a>
 * @version 1.0
 */
@SuppressWarnings("all")
@WebService(name = "CodeMappingSoapImpl", serviceName = "CodeMappingSoap", endpointInterface = "org.hl7.CodeMappingSoap")
public class CodeMappingSoapImpl implements CodeMappingSoap {

    /**
     * Logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(CodeMappingSoapImpl.class.getName());
    
    /**
     * VA flag.
     */
    private static boolean vaflag;

    /**
     * Constructor.
     */
    public CodeMappingSoapImpl() {
        super();
        Properties props = System.getProperties();
        String value = props.getProperty("vaflag");
        if (value != null && value.length() > 0 && "true".equalsIgnoreCase(value)) {
            this.vaflag = true;
        }
    }
    
    /**
     * Retrieve the CTS version.
     *
     * @return version
     */
    public CTSVersionId getCTSVersion() {
        CTSVersionId version = new CTSVersionId();
        Short major = 1;
        Short minor = 0;
        version.setMajor(major);
        version.setMinor(minor);
        return version;
    }

    /**
     * Retrieve the service name.
     *
     * @return name
     */
    public ST getServiceName() {
        ST retST = new ST();

        retST.setV("Code Mapping Service");
        return retST;
    }

    /**
     * Get the service description.
     *
     * @return description
     */
    public ST getServiceDescription() {
        ST retST = new ST();

        retST.setV("CTS Service for North Chicago");
        return retST;
    }

    /**
     * Retrieve the service version.
     *
     * @return version
     */
    public ST getServiceVersion() {
        ST retST = new ST();

        retST.setV("v1.0 08/18/2011");
        return retST;
    }

    /**
     * Retrieve the supported maps.
     *
     * @param parameters unused
     * @return supported maps
     */
    public GetSupportedMapsResponse getSupportedMaps(GetSupportedMapsVersion parameters)  {
        CTSMaps maps = CTSMaps.getInstance();
        if (vaflag) {
            maps.setVaflag(true);
        }
            
        GetSupportedMapsResponse rtc;
        try {
            rtc = maps.getMaps();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            rtc = null;
        }
        return rtc;
    }

    /**
     * Map a code from one system to another.
     *
     * @param fromConceptId source code
     * @param toCodeSystemId destination system
     * @param mapName no used
     * @return mapped code
     */
    public MappedConceptCode mapCode(ConceptId fromConceptId, String toCodeSystemId, String mapName) {
        LOGGER.info("Get mapped code.");
        CTSMaps maps = CTSMaps.getInstance();
        if (vaflag) {
            maps.setVaflag(true);
        }
        MappedConceptCode retCC = new MappedConceptCode();

        String retCode;
        try {
            retCode = maps.mapCode(fromConceptId.getCodeSystemId(),
                    fromConceptId.getConceptCode(),
                    toCodeSystemId);
            if (retCode != null && retCode.length() > 0) {
                ConceptId conceptid = new ConceptId();
                conceptid.setCodeSystemId(toCodeSystemId);
                retCC.setMappedConceptId(conceptid);
                retCC.setMapQualityCode(retCode);
            }
            if (retCode == null) {
                retCC = null;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            retCC = null;
        }
//        if (retCC == null) {
//            Exception ex = new Exception("mapCode = NULL");
//            LOGGER.error("mapCode = NULL", ex);
//        }
        return retCC;
    }

//    @SuppressWarnings("all")
//    public static void main(final String[] args) {
//        CodeMappingSoapImpl mapper = new CodeMappingSoapImpl();
//        ConceptId conceptid = new ConceptId();
//        conceptid.setCodeSystemId("2.16.840.1.113883.3.42.127.100001.256");
//        conceptid.setConceptCode("2");
//
//        MappedConceptCode retmap = mapper.mapCode(conceptid, "2.16.840.1.113883.12.168", "");
//        if (retmap == null) {
//            System.out.println("RET = null");
//        } else {
//            String ret = retmap.getMapQualityCode();
//            System.out.println("RET = " + ret);
//        }
//    }
}
