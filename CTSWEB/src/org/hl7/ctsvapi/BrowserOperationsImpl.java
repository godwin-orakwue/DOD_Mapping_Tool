package org.hl7.ctsvapi;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.jws.WebService;

import org.hl7.data.CTSVocabs;

/**
 * HL7 CTS Vocabulary Browser implementation.
 * 
 * @author <a href="mailto:karl.a.weller@saic.com">karl.a.weller</a>
 * @version 1.0
 *
 */
@WebService(serviceName = "BrowserOperationsService", targetNamespace = "urn://hl7.org/CTSVAPI", portName = "VocabBrowserService", endpointInterface = "org.hl7.ctsvapi.BrowserOperations", wsdlLocation = "/WEB-INF/wsdl/VocabBrowser.wsdl")
public class BrowserOperationsImpl {

    /**
     * SQL Exception.
     */
    private static final String SQLEXCEPTION = "SQLException";
    
    /**
     * Not yet implemented.
     */
    private static final String NOTYETIMPLEMENTED = "NOT YET IMPLEMENTED";

    /**
     * VA flag.
     */
    private boolean vaflag;
    
    /**
     * Constructor.
     */
    public BrowserOperationsImpl() {
        super();
        Properties props = System.getProperties();
        String value = props.getProperty("vaflag");
        if (value != null && value.length() > 0 && "true".equalsIgnoreCase(value)) {
            this.vaflag = true;
        }
    }

    /**
     * Return a list of supported match algorithms.
     * 
     * @return algorithms
     * @throws UnexpectedError_Exception UnexpectedError
     */
    public List<String> getSupportedMatchAlgorithms() throws UnexpectedError_Exception {
//        String[] values = { "IdenticalIgnoreCase", "Identical", "StartsWithIgnoreCase", "StartsWith", "EndsWithIgnoreCase", "EndsWith", "ContainsPhraseIgnoreCase", "ContainsPhrase", "WordsAnyOrderIgnoreCase", "WildCardsIgnoreCase", "RegularExpression", "NYSIIS" };
        String[] values = {"IdenticalIgnoreCase", "Identical", "StartsWithIgnoreCase", "StartsWith", "EndsWithIgnoreCase", "EndsWith", "ContainsPhraseIgnoreCase", "ContainsPhrase"};
        List<String> rtc = Arrays.asList(values);
        return rtc;
    }

    /**
     * NOT IMPLEMENTED - Further expand the results of a previous expandCode call.
     * 
     * @param contextToExpand node context from prior expand code session
     * @return list of expanded codes<p>NOTE: if a sizeLimit has been specified and the number of entries in the list is the same as the size limit, the caller must presume that the list is incomplete.
     * @throws InvalidExpansionContext_Exception expansion context isn't valid or has expired 
     * @throws TimeoutError_Exception time limit was exceeded
     * @throws UnexpectedError_Exception UnexpectedError
     */
    public List<RelatedCode> expandCodeExpansionContext(byte[] contextToExpand) throws InvalidExpansionContext_Exception,
                                                                                       TimeoutError_Exception,
                                                                                       UnexpectedError_Exception {
        UnexpectedError unex = new UnexpectedError();
        unex.setPossibleCause(NOTYETIMPLEMENTED);
        UnexpectedError_Exception err = new UnexpectedError_Exception(NOTYETIMPLEMENTED, unex);
        throw err;
    }

    /**
     * List the children (or parents) of the supplied code.
     * 
     * @param expandConceptId Starting code system / concept code<p>If the concept code is omitted and only the code system is supplied expand code returns all of the 'root' concept codes under the relationship.
     * @param relationshipCode relationship to expand NOT USED
     * @param sourceToTarget true means source to target, false - target to source NOT USED
     * @param directRelationsOnly true means only return directly related nodes.  False means return complete transitive graph (if the relationship is transitive) NOT USED
     * @param designationLanguageCode language to use when returning designations NOT USED
     * @param timeout time limit for operation to complete in milliseconds (0 means unlimited) NOT IMPLEMENTED
     * @param sizeLimit maximum number of elements to return (0 means unlimited)
     * @return list of expanded codes<p>NOTE: if a sizeLimit has been specified and the number of entries in the list is the same as the size limit, the caller must presume that the list is incomplete.
     * @throws TimeoutError_Exception time limit was exceeded NOT USED
     * @throws UnexpectedError_Exception UnexpectedError
     * @throws UnknownCodeSystem_Exception codeSystem is not supported by the service
     * @throws UnknownConceptCode_Exception the supplied concept code isn't valid for the code system
     * @throws UnknownLanguageCode_Exception the supplied language code isn't valid for the code system NOT USED
     * @throws UnknownRelationshipCode_Exception the relation code isn't valid for the code system NOT USED
     */
    public List<RelatedCode> lookupCodeExpansion(ConceptId expandConceptId, String relationshipCode, boolean sourceToTarget, boolean directRelationsOnly,
        String designationLanguageCode, int timeout, int sizeLimit) throws TimeoutError_Exception,
                                                                           UnexpectedError_Exception,
                                                                           UnknownCodeSystem_Exception,
                                                                           UnknownConceptCode_Exception,
                                                                           UnknownLanguageCode_Exception,
                                                                           UnknownRelationshipCode_Exception {
        CTSVocabs vocab = new CTSVocabs(vaflag);
        List<RelatedCode> rtc = null;
        try {
            rtc = vocab.lookupCodeExpansion(expandConceptId,  relationshipCode,  sourceToTarget,  directRelationsOnly,
                     designationLanguageCode,  timeout,  sizeLimit);
            if (rtc == null || rtc.size() == 0) {
                UnknownConceptCode uc = new UnknownConceptCode();
                throw new UnknownConceptCode_Exception("Concept code not found", uc);
            }
        } catch (SQLException ex) {
            UnexpectedError unex = new UnexpectedError();
            unex.setPossibleCause(SQLEXCEPTION);
            UnexpectedError_Exception err = new UnexpectedError_Exception(ex.getMessage(), unex);
            throw err;
        }
        return rtc;
    }

    /**
     * List of code systems and versions supported by this service.
     * 
     * @param timeout time limit for operation to complete in milliseconds (0 means unlimited) NOT IMPLEMENTED
     * @param sizeLimit maximum number of elements to return (0 means unlimited)
     * @return list of code systems
     * @throws TimeoutError_Exception  time limit was exceeded NOT USED
     * @throws UnexpectedError_Exception UnexpectedError
     */
    public List<CodeSystemIdAndVersions> getSupportedCodeSystems(int timeout, int sizeLimit) throws TimeoutError_Exception, UnexpectedError_Exception {
        CTSVocabs vocab = new CTSVocabs(vaflag);
        List<CodeSystemIdAndVersions> rtc = null;
        try {
            rtc = vocab.getSupportedCodeSystems(sizeLimit);
        } catch (SQLException ex) {
            UnexpectedError unex = new UnexpectedError();
            unex.setPossibleCause(SQLEXCEPTION);
            UnexpectedError_Exception err = new UnexpectedError_Exception(ex.getMessage(), unex);
            throw err;
        }
        return rtc;
    }

    /**
     * Retrieve a complete description of a concept code.
     * 
     * @param conceptId concept to retrieve
     * @return complete description of the concept
     * @throws UnexpectedError_Exception UnexpectedError
     * @throws UnknownCodeSystem_Exception codeSystem is not supported by the service
     * @throws UnknownConceptCode_Exception concept code is not valid in the code system
     */
    public CompleteCodedConceptDescription lookupCompleteCodedConcept(ConceptId conceptId) throws UnexpectedError_Exception,
                                                                                                  UnknownCodeSystem_Exception,
                                                                                                  UnknownConceptCode_Exception {
        CTSVocabs vocab = new CTSVocabs(vaflag);
        CompleteCodedConceptDescription rtc = null;
        try {
            rtc = vocab.lookupCompleteCodedConcept(conceptId);
            if (rtc == null) {
                UnknownConceptCode uc = new UnknownConceptCode();
                throw new UnknownConceptCode_Exception("Concept code not found", uc);
            }
        } catch (SQLException ex) {
            UnexpectedError unex = new UnexpectedError();
            unex.setPossibleCause(SQLEXCEPTION);
            UnexpectedError_Exception err = new UnexpectedError_Exception(ex.getMessage(), unex);
            throw err;
        }
        return rtc;
    }

    /**
     * Return the concept codes with matching designations.
     * 
     * @param codeSystemId code system to query
     * @param matchText Match string.  Format depends upon the match algorithm.
     * @param matchAlgorithmCode match algorithm to be used in the search
     * @param languageCode language filter.  If present, only designations / descriptions in this language are searched.  (Default: all languages) NOT USED
     * @param activeConceptsOnly true means only active codes qualify.  False means both. (Default: True)
     * @param timeout time limit for operation to complete in milliseconds (0 means unlimited) NOT IMPLEMENTED
     * @param sizeLimit maximum number of elements to return (0 means unlimited)
     * @return a list of concept codes that match the supplied search criteria<p>NOTE: if a sizeLimit has been specified and the number of entries in the list is the same as the size limit, the caller must presume that the list is incomplete.
     * @throws BadlyFormedMatchText_Exception match text couldn't be parsed
     * @throws TimeoutError_Exception time limit was exceeded NOT USED
     * @throws UnexpectedError_Exception UnexpectedError
     * @throws UnknownCodeSystem_Exception codeSystem is not supported by the service
     * @throws UnknownLanguageCode_Exception language code is not supported by the service NOT USED
     * @throws UnknownMatchAlgorithm_Exception match algorithm isn't supported by the service
     */
    public List<ConceptId> lookupConceptCodesByDesignation(String codeSystemId, String matchText,
                                                           String matchAlgorithmCode, String languageCode, boolean activeConceptsOnly, int timeout, int sizeLimit) throws BadlyFormedMatchText_Exception,
                                                                                                                                                                          TimeoutError_Exception,
                                                                                                                                                                          UnexpectedError_Exception,
                                                                                                                                                                          UnknownCodeSystem_Exception,
                                                                                                                                                                          UnknownLanguageCode_Exception,
                                                                                                                                                                          UnknownMatchAlgorithm_Exception {
        CTSVocabs vocab = new CTSVocabs(vaflag);
        List<ConceptId> rtc = null;
        try {
            rtc = vocab.lookupConceptCodesByDesignation(codeSystemId, matchText, matchAlgorithmCode, languageCode, activeConceptsOnly, timeout, sizeLimit);
        } catch (SQLException ex) {
            UnexpectedError unex = new UnexpectedError();
            unex.setPossibleCause(SQLEXCEPTION);
            UnexpectedError_Exception err = new UnexpectedError_Exception(ex.getMessage(), unex);
            throw err;
        }
        return rtc;
    }

    /**
     * NOT IMPLEMENTED - Return the concept codes with matching properties.
     * 
     * @param codeSystemId code system to query
     * @param matchText Match string.  Format depends upon the match algorithm
     * @param matchAlgorithmCode match algorithm to be used in the search
     * @param languageCode language filter.  If present, only properties in this language are searched.  (Default: all languages)
     * @param activeConceptsOnly true means only active codes qualify.  False means both. (Default: True)
     * @param properties List of property codes to search. (Default: All properties)
     * @param mimeTypes only search properties with these mime types. (Default: all mime types)
     * @param timeout time limit for operation to complete in milliseconds (0 means unlimited) NOT IMPLEMENTED
     * @param sizeLimit maximum number of elements to return (0 means unlimited)
     * @return a list of concept codes that match the supplied search criteria<p>NOTE: if a sizeLimit has been specified and the number of entries in the list is the same as the size limit,
     *       the caller must presume that the list is incomplete.
     *       
     * @throws BadlyFormedMatchText_Exception match text syntax is invalid for the match algorithm
     * @throws TimeoutError_Exception time limit exceeded
     * @throws UnexpectedError_Exception UnexpectedError
     * @throws UnknownCodeSystem_Exception codeSystem is not supported by the service
     * @throws UnknownLanguageCode_Exception language code is not supported by the service
     * @throws UnknownMatchAlgorithm_Exception match algorithm isn't supported by the service
     * @throws UnknownMimeTypeCode_Exception mime code is not recognized by the service
     * @throws UnknownPropertyCode_Exception property code is not supported by the service
     */
    public List<ConceptId> lookupConceptCodesByProperty(String codeSystemId, String matchText,
                                                        String matchAlgorithmCode, String languageCode, boolean activeConceptsOnly,
        List<String> properties, List<String> mimeTypes, int timeout, int sizeLimit) throws BadlyFormedMatchText_Exception,
                                                                                            TimeoutError_Exception,
                                                                                            UnexpectedError_Exception,
                                                                                            UnknownCodeSystem_Exception,
                                                                                            UnknownLanguageCode_Exception,
                                                                                            UnknownMatchAlgorithm_Exception,
                                                                                            UnknownMimeTypeCode_Exception,
                                                                                            UnknownPropertyCode_Exception {
        UnexpectedError unex = new UnexpectedError();
        unex.setPossibleCause(NOTYETIMPLEMENTED);
        UnexpectedError_Exception err = new UnexpectedError_Exception(NOTYETIMPLEMENTED, unex);
        throw err;
    }

    /**
     * NOT IMPLEMENTED - Return the designations of an individual concept that match the supplied criteria.
     * 
     * @param conceptCode code system and concept code to match
     * @param matchText Match string.  Format depends upon the match algorithm.
     * @param matchAlgorithmCode match algorithm to use for matching
     * @param languageCode if present, only match text of this language
     * @return designations of an individual concept
     * @throws BadlyFormedMatchText_Exception match text syntax is invalid for the match algorithm
     * @throws UnexpectedError_Exception UnexpectedError
     * @throws UnknownCodeSystem_Exception unrecognized code system in the concept id
     * @throws UnknownConceptCode_Exception unrecognized concept code
     * @throws UnknownLanguageCode_Exception language code is not supported by the service
     * @throws UnknownMatchAlgorithm_Exception match algorithm isn't supported by the service
     */
    public List<ConceptDesignation> lookupDesignations(ConceptId conceptCode, String matchText,
                                                       String matchAlgorithmCode,
                                                       String languageCode) throws BadlyFormedMatchText_Exception,
                                                                                   UnexpectedError_Exception,
                                                                                   UnknownCodeSystem_Exception,
                                                                                   UnknownConceptCode_Exception,
                                                                                   UnknownLanguageCode_Exception,
                                                                                   UnknownMatchAlgorithm_Exception {
        UnexpectedError unex = new UnexpectedError();
        unex.setPossibleCause(NOTYETIMPLEMENTED);
        UnexpectedError_Exception err = new UnexpectedError_Exception(NOTYETIMPLEMENTED, unex);
        throw err;
    }

    /**
     * NOT IMPLEMENTED - Return the properties of an individual concept that match the supplied criteria.
     * 
     * @param conceptId code system and concept code to match
     * @param properties if present, constrain the search to this set of properties
     * @param matchText Match string.  Format depends upon the match algorithm.
     * @param matchAlgorithmCode match algorithm to use for matching
     * @param languageCode if present, only return properties in this language
     * @param mimeTypes constrain search to properties of these types
     * @return properties
     * @throws BadlyFormedMatchText_Exception match text syntax can't be parsed
     * @throws UnexpectedError_Exception UnexpectedError
     * @throws UnknownCodeSystem_Exception codeSystem is not supported by the service
     * @throws UnknownConceptCode_Exception concept code wasn't valid in the code system
     * @throws UnknownLanguageCode_Exception language code is not supported by the service
     * @throws UnknownMatchAlgorithm_Exception match algorithm isn't supported by the service
     * @throws UnknownMimeTypeCode_Exception one of the mime type codes isn't recognized
     * @throws UnknownPropertyCode_Exception one of the property codes wasn't recognized
     */
    public List<ConceptProperty> lookupProperties(ConceptId conceptId, List<String> properties, String matchText,
                                                  String matchAlgorithmCode, String languageCode,
                                                  List<String> mimeTypes) throws BadlyFormedMatchText_Exception,
                                                                                 UnexpectedError_Exception,
                                                                                 UnknownCodeSystem_Exception,
                                                                                 UnknownConceptCode_Exception,
                                                                                 UnknownLanguageCode_Exception,
                                                                                 UnknownMatchAlgorithm_Exception,
                                                                                 UnknownMimeTypeCode_Exception,
                                                                                 UnknownPropertyCode_Exception {
        UnexpectedError unex = new UnexpectedError();
        unex.setPossibleCause(NOTYETIMPLEMENTED);
        UnexpectedError_Exception err = new UnexpectedError_Exception(NOTYETIMPLEMENTED, unex);
        throw err;
    }

    /**
     * Get CTS version.
     * 
     * @return version
     * @throws UnexpectedError_Exception UnexpectedError
     */
    @SuppressWarnings("all")
    public CTSVersionId getCTSVersion() throws UnexpectedError_Exception {
        CTSVersionId rtc = new CTSVersionId();
        rtc.setMajor((short) 1);
        rtc.setMinor((short) 0);
        return rtc;
    }

    /**
     * Get service description.
     * 
     * @return description
     * @throws UnexpectedError_Exception UnexpectedError
     */
    public String getServiceDescription() throws UnexpectedError_Exception {
        return "CTS Service for North Chicago";
    }

    /**
     * Get service name.
     * 
     * @return service name
     * @throws UnexpectedError_Exception UnexpectedError
     */
    public String getServiceName() throws UnexpectedError_Exception {
        return "Vocabulary Browsing Service";
    }

    /**
     * Get service version.
     * 
     * @return service version
     * @throws UnexpectedError_Exception UnexpectedError
     */
    public String getServiceVersion() throws UnexpectedError_Exception {
        return "v1.0 10/30/2012";
    }
    
    /**
     * List of CHCS reference tables.
     */
//    private static String[] chcstables = new String[] {
//        "CHCS.0056.8131", "ABSENT_STATUS_8131",
//        "CHCS.0056.8510", "ACCESS_TO_CARE_CATEGORY_8510",
//        "CHCS.0056.8985", "ACTION_BAR_8985",
//        "CHCS.0056.8986", "ACTION_BAR_OPERATIONS_8986",
//        "CHCS.0056.105.7", "ACUITY_SELECTION_105_7",
//        "CHCS.0056.8101.1", "ADDITIONAL_MEDICAL_TREATMENT_FACILITY_8101_1",
//        "CHCS.0056.51.1", "ADMINISTRATION_SCHEDULE_51_1",
//        "CHCS.0056.43", "ADT_PARAMETERS_43",
//        "CHCS.0056.108.3", "ADT_PROCEDURE_108_3",
//        "CHCS.0056.108.1", "ANCILLARY_PROCEDURE_108_1",
//        "CHCS.0056.39.9", "ANCILLARY_TRACKING_39_9",
//        "CHCS.0056.44.5", "APPOINTMENT_TYPE_44_5",
//        "CHCS.0056.8702.3", "AP_CONVERSION_UTILITY_8702_3",
//        "CHCS.0056.8702.1", "AP_DESCRIPTIONDIAGNOSIS_8702_1",
//        "CHCS.0056.8461", "AP_SQL_PATH_FILE_8461",
//        "CHCS.0056.8461.1", "AP_SQL_PATH_LINK_8461_1",
//        "CHCS.0056.8461.2", "AP_SQL_PATH_MASK_8461_2",
//        "CHCS.0056.8461.3", "AP_SQL_PATH_TREE_8461_3",
//        "CHCS.0056.8460", "AP_SQL_SITE_8460",
//        "CHCS.0056.8460.9", "AP_SQL_SITE_LOG_8460_9",
//        "CHCS.0056.8949", "ARCHIVE_TEMPLATE_8949",
//        "CHCS.0056.62.4", "AUTO_INSTRUMENT_62_4",
//        "CHCS.0056.4004", "BACKGROUND_PROCESS_CONTROL_4004",
//        "CHCS.0056.50.2", "BAKER_CELL_50_2",
//        "CHCS.0056.3.8001", "BANNER_3_8001",
//        "CHCS.0056.8910", "BARCODE_CHARACTER_SET_8910",
//        "CHCS.0056.195.9", "BORROWERSFILE_AREAS_195_9",
//        "CHCS.0056.23", "BRANCH_OF_SERVICE_23",
//        "CHCS.0056.3.6", "BULLETIN_3_6",
//        "CHCS.0056.8106", "CASUALTY_PROGNOSIS_8106",
//        "CHCS.0056.8139", "CASUALTY_STATUS_8139",
//        "CHCS.0056.8137", "CAUSE_OF_INJURY_8137",
//        "CHCS.0056.8507", "CBS_DENTAL_TYPES_8507",
//        "CHCS.0056.8593", "CBS_OPERATORY_TREATMENT_ROOM_8593",
//        "CHCS.0056.8911", "CHECK_DIGIT_ALGORITHM_8911",
//        "CHCS.0056.8098", "CLAIM_FILING_CODE_8098",
//        "CHCS.0056.8821", "CLINICAL_ACTION_8821",
//        "CHCS.0056.8822", "CLINICAL_FUNCTION_8822",
//        "CHCS.0056.8810", "CLINICAL_HISTORY_8810",
//        "CHCS.0056.101.1", "CLINICAL_SITE_PARAMETERS_101_1",
//        "CHCS.0056.8820", "CLINICAL_USER_8820",
//        "CHCS.0056.8930", "CODING_TYPE_8930",
//        "CHCS.0056.62", "COLLECTION_SAMPLE_62",
//        "CHCS.0056.88", "COMMAND_ACTION__88",
//        "CHCS.0056.8114", "COMMAND_INTEREST_CATEGORY_8114",
//        "CHCS.0056.889", "COMMAND_SCREEN_EXEC_LOC__889",
//        "CHCS.0056.89", "COMMAND_SCREEN__89",
//        "CHCS.0056.8105", "COMMAND_SECURITY_8105",
//        "CHCS.0056.50.6", "CONRX_ROBOT_50_6",
//        "CHCS.0056.59", "CONTROLLED_INVENTORY_VAULT_59",
//        "CHCS.0056.15010", "DATABLADE_MFN_15010",
//        "CHCS.0056.15015", "DATABLADE_OBJECT_OPTIONS_15015",
//        "CHCS.0056.15001", "DATABLADE_REG_MENU_15001",
//        "CHCS.0056.4090.1", "DATA_ELEMENT_VALUE_MAP_4090_1",
//        "CHCS.0056.8909", "DEERS_ELIGREG_RESPONSES_8909",
//        "CHCS.0056.8537", "DEERS_GRADE_RANK_8537",
//        "CHCS.0056.8526", "DENTAL_SCHEDULING_RESOURCE_8526",
//        "CHCS.0056.45.7", "DEPARTMENT_AND_SERVICE_45_7",
//        "CHCS.0056.3.5", "DEVICE_3_5",
//        "CHCS.0056.8993.1", "DEVICE_ATTRIBUTE_8993_1",
//        "CHCS.0056.3.2", "DEVICE_TYPE_3_2",
//        "CHCS.0056.8410", "DIET_8410",
//        "CHCS.0056.31", "DISABILITY_CONDITION_31",
//        "CHCS.0056.42.2", "DISCHARGE_TYPE_42_2",
//        "CHCS.0056.4.2", "DOMAIN_4_2",
//        "CHCS.0056.50", "DRUG_50",
//        "CHCS.0056.8200", "DRUG_AUTHORIZATION_KEY_8200",
//        "CHCS.0056.51.6", "DRUG_NAMEFORM_51_6",
//        "CHCS.0056.51.5", "DRUG_NAMEROUTE_51_5",
//        "CHCS.0056.8017", "DUPLICATE_PATIENT_MATCH_CRITERIA_8017",
//        "CHCS.0056.3020", "DUPLICATE_PATIENT_SCRATCH_3020",
//        "CHCS.0056.39.4", "EMBOSSER_FORMAT_39_4",
//        "CHCS.0056.39.1", "EMBOSSER_TYPE_39_1",
//        "CHCS.0056.4732001", "ER_FOLLOWUP_4732001",
//        "CHCS.0056.61.2", "ETIOLOGY_FIELD_61_2",
//        "CHCS.0056.62.07", "EXECUTE_CODE_62_07",
//        "CHCS.0056.8183", "FETAL_PRESENTATION_TYPE_8183",
//        "CHCS.0056.5", "FUNCTION__5",
//        "CHCS.0056.109.9", "FUTURE_PAGE_CROSS_REFERENCE_109_9",
//        "CHCS.0056.5", "GEOGRAPHIC_LOCATION_5",
//        "CHCS.0056.52120", "GP_EXTRACTOR_ERRORS_52120",
//        "CHCS.0056.52012.2", "GP_IP_SERVICES_52012_2",
//        "CHCS.0056.52115", "GP_MINIREG_ERROR_52115",
//        "CHCS.0056.52116", "GP_MINIREG_ERROR_TYPES_52116",
//        "CHCS.0056.1.51", "GRAPHIC_TYPE_1_51",
//        "CHCS.0056.50079", "GTGIAT_SUPPORT_CENTER_CALL_STATUS_50079",
//        "CHCS.0056.51105.3", "GT_XM_BULLETIN_BOARD_SUB_TOPIC_51105_3",
//        "CHCS.0056.51105.1", "GT_XM_BULLETIN_BOARD_TOPIC_51105_1",
//        "CHCS.0056.8095", "HIC_STATUS_CODE_8095",
//        "CHCS.0056.40.5", "HOLIDAY_40_5",
//        "CHCS.0056.44", "HOSPITAL_LOCATION_44",
//        "CHCS.0056.79.3", "IMAGING_OVERALL_PARAMETERS_79_3",
//        "CHCS.0056.52016.4", "IMMUNIZATION_SCHEDULE_TEMPLATES_52016_4",
//        "CHCS.0056.52016.5", "IMMUNIZATION_SITES_52016_5",
//        "CHCS.0056.59.4", "INPATIENT_SITE_59_4",
//        "CHCS.0056.62.7", "INSTRUMENT_INTERFACE_PARAMETERS_62_7",
//        "CHCS.0056.4005", "INTERFACE_DESTINATION_4005",
//        "CHCS.0056.4003.1", "INTERFACE_ERROR_LOCATION_4003_1",
//        "CHCS.0056.4007", "INTERFACE_NAME_SPACE_4007",
//        "CHCS.0056.4006", "INTERFACE_SCRIPT_4006",
//        "CHCS.0056.4002", "INTERFACE_SITE_PARAMETERS_4002",
//        "CHCS.0056.4000", "INTERFACE_TRANSACTION_TYPE_4000",
//        "CHCS.0056.8201", "IV_LOCATION_GROUP_8201",
//        "CHCS.0056.52.8", "IV_RECIPE_52_8",
//        "CHCS.0056.59.5", "IV_ROOM_59_5",
//        "CHCS.0056.1.3", "KEY_FUNCTIONALITY_1_3",
//        "CHCS.0056.1.35", "KEY_FUNCTIONALITY_GROUP_1_35",
//        "CHCS.0056.19.2", "KEY_GROUP_19_2",
//        "CHCS.0056.100504.9", "KG_ADS_APPOINTMENT_STATUS_100504_9",
//        "CHCS.0056.100508", "KG_ADS_MAILGROUP_PREFERENCES_100508",
//        "CHCS.0056.100502", "KG_CPT_SELECTION_LIST_100502",
//        "CHCS.0056.100418", "KG_DIAGNOSIS_SELECTION_LIST_100418",
//        "CHCS.0056.101920", "KP_GENERIC_OUTPT_PHR_INTERFACE_101920",
//        "CHCS.0056.111666", "KW_TOOL_GIS_BGRD_MON_111666",
//        "CHCS.0056.52.4", "LABEL_52_4",
//        "CHCS.0056.194.4", "LABEL_FORMAT_194_4",
//        "CHCS.0056.8737", "LAB_AI_SIMULATION_8737",
//        "CHCS.0056.62.9", "LAB_CATEGORY_62_9",
//        "CHCS.0056.65.1", "LAB_CUMULATIVE_HEADER_65_1",
//        "CHCS.0056.65", "LAB_CUMULATIVE_TEST_65",
//        "CHCS.0056.62.5", "LAB_DESCRIPTION_62_5",
//        "CHCS.0056.69.91", "LAB_DIVISION_69_91",
//        "CHCS.0056.62.2", "LAB_SECTION_62_2",
//        "CHCS.0056.69.5", "LAB_STATUS_69_5",
//        "CHCS.0056.62.6", "LAB_SYSTEM_INTERFACE_62_6",
//        "CHCS.0056.60", "LAB_TEST_60",
//        "CHCS.0056.69.92", "LAB_WORK_ELEMENT_69_92",
//        "CHCS.0056.3.7", "MAIL_BOX_3_7",
//        "CHCS.0056.3.8", "MAIL_GROUP_3_8",
//        "CHCS.0056.62.8", "MANUFACTURER_62_8",
//        "CHCS.0056.52123", "MARC_FIELD_LOCATION_52123",
//        "CHCS.0056.52117", "MARC_SNOMED_CODE_52117",
//        "CHCS.0056.11", "MARITAL_STATUS_11",
//        "CHCS.0056.8028", "MASCAL_EVENT_8028",
//        "CHCS.0056.8587", "MCP_FORMAT_SPECIFICATION_8587",
//        "CHCS.0056.8556", "MCP_HCF_8556",
//        "CHCS.0056.8555", "MCP_OFFICE_8555",
//        "CHCS.0056.8579", "MCP_UNIT_PCM_8579",
//        "CHCS.0056.8588", "MCSC_INTERFACE_PARAMETERS_8588",
//        "CHCS.0056.8406.8", "MEAL_8406_8",
//        "CHCS.0056.8138", "MEB_CANDIDATE_STATUS_8138",
//        "CHCS.0056.40.8", "MEDICAL_CENTER_DIVISION_40_8",
//        "CHCS.0056.8125", "MEDICAL_RECORD_TYPE_8125",
//        "CHCS.0056.4", "MEDICAL_TREATMENT_FACILITY_4",
//        "CHCS.0056.51", "MEDICATION_INSTRUCTION_51",
//        "CHCS.0056.51.2", "MEDICATION_ROUTE_51_2",
//        "CHCS.0056.8147", "METHOD_OF_TRANSIT_8147",
//        "CHCS.0056.30205", "MHCMIS_DATA_EXCHANGE_TRACKING_30205",
//        "CHCS.0056.8104", "MILITARY_GRADE_RANK_8104",
//        "CHCS.0056.8136", "MILITARY_THEATER_OF_OPERATIONS_8136",
//        "CHCS.0056.190.2", "MISSING_RECORDS_190_2",
//        "CHCS.0056.61.1", "MORPHOLOGY_FIELD_61_1",
//        "CHCS.0056.8051", "MSA_CORRECTION_REASONS_8051",
//        "CHCS.0056.8518", "MTF_RIS_MESSAGE_OPTION_8518",
//        "CHCS.0056.7", "MUMPS_OPERATING_SYSTEM__7",
//        "CHCS.0056.8583", "NAS_ISSUING_OFFICER_8583",
//        "CHCS.0056.8574.1", "NCA_DMIS_ID_TABLE_8574_1",
//        "CHCS.0056.8535", "NDI_MESSAGE_8535",
//        "CHCS.0056.106.7", "NIO_CATEGORIES_106_7",
//        "CHCS.0056.3.75", "NOTIFICATION_MESSAGE_3_75",
//        "CHCS.0056.8059", "NOTIFY_MESSAGES_8059",
//        "CHCS.0056.106.4", "NRS_CATEGORIES_106_4",
//        "CHCS.0056.108.2", "NURSINGINTRAWARD_PROCEDURE_108_2",
//        "CHCS.0056.30211", "OFFBOARD_SYSTEMS_30211",
//        "CHCS.0056.8096", "OHICARRIER_COVERAGE_TYPE_CODE_8096",
//        "CHCS.0056.8097", "OHI_END_REASON_CODE_8097",
//        "CHCS.0056.106.6", "OLOE_USER_PREFERENCE_106_6",
//        "CHCS.0056.8945", "ONLINE_DOC_ERROR_TYPES_8945",
//        "CHCS.0056.8940.2", "ONLINE_DOC_FRAMES_8940_2",
//        "CHCS.0056.8944", "ONLINE_DOC_SUBSYSTEM_8944",
//        "CHCS.0056.19", "OPTION_19",
//        "CHCS.0056.19.4", "OPTION_AUDIT_19_4",
//        "CHCS.0056.103.1", "ORDERSET_ENTITY_103_1",
//        "CHCS.0056.104", "ORDER_ACTION_104",
//        "CHCS.0056.102.4", "ORDER_APPOINTMENTREVIEW_STATUS_102_4",
//        "CHCS.0056.101.2", "ORDER_COMMENT_101_2",
//        "CHCS.0056.102.3", "ORDER_PRIORITY_102_3",
//        "CHCS.0056.103", "ORDER_SET_103",
//        "CHCS.0056.102.2", "ORDER_TIMING_102_2",
//        "CHCS.0056.102", "ORDER_TYPE_102",
//        "CHCS.0056.102.1", "ORDER_WARNINGS_102_1",
//        "CHCS.0056.111", "ORIGIN_OF_ORDER_111",
//        "CHCS.0056.106.2", "ORO_PARSER_106_2",
//        "CHCS.0056.106.3", "ORO_PLUS_TEXT_106_3",
//        "CHCS.0056.106.1", "ORO_SUBCLASSES_106_1",
//        "CHCS.0056.106", "ORO_WORDS_AND_PHRASES_106",
//        "CHCS.0056.59.2", "OUTPATIENT_SITE_59_2",
//        "CHCS.0056.30210", "PATIENTS_OF_INTEREST_30210",
//        "CHCS.0056.2", "PATIENT_2",
//        "CHCS.0056.44.2", "PATIENT_APPOINTMENT_44_2",
//        "CHCS.0056.8156", "PATIENT_CATEGORY_8156",
//        "CHCS.0056.108.4", "PATIENT_INSTRUCTIONS_108_4",
//        "CHCS.0056.8063", "PAY_MODE_8063",
//        "CHCS.0056.59.6", "PHARMACY_ACTION_59_6",
//        "CHCS.0056.59.8", "PHARMACY_MTF_PARAMETERS_59_8",
//        "CHCS.0056.55", "PHARMACY_PATIENT_55",
//        "CHCS.0056.52.9", "PHARMACY_PRINTED_QUEUE_52_9",
//        "CHCS.0056.8203", "PHARMACY_SPOOL_REPORT_8203",
//        "CHCS.0056.8111.1", "PRIMARY_UNITSHIP_ID_8111_1",
//        "CHCS.0056.8993.2", "PRINT_JOB_SETTING_8993_2",
//        "CHCS.0056.52007", "PROBLEM_SELECTION_LIST_52007",
//        "CHCS.0056.71.3", "PROCEDURE_CLASS_71_3",
//        "CHCS.0056.6", "PROVIDER_6",
//        "CHCS.0056.7", "PROVIDER_CLASS_7",
//        "CHCS.0056.8181.1", "PROVIDER_MERGE_STATUS_8181_1",
//        "CHCS.0056.8181.4", "PROVIDER_UNMERGE_STATUS_8181_4",
//        "CHCS.0056.59.9", "PS_CLINICAL_SCREENING_PARAMETERS_59_9",
//        "CHCS.0056.8890.6", "QAF_EVENT_FOLLOWUP_ACTION_8890_6",
//        "CHCS.0056.8890.5", "QAF_EVENT_LOCATION_TYPE_8890_5",
//        "CHCS.0056.8890.9", "QAF_EVENT_TYPE_8890_9",
//        "CHCS.0056.62.3", "QUALITY_CONTROL_62_3",
//        "CHCS.0056.70.5", "RADIOLOGY_EXAM_70_5",
//        "CHCS.0056.77.3", "RADIOLOGY_MASTER_SCHEDULE_TEMPLATE_77_3",
//        "CHCS.0056.71", "RADIOLOGY_PROCEDURES_71",
//        "CHCS.0056.72.1", "RADIOLOGY_REPEAT_REASON_72_1",
//        "CHCS.0056.78.8", "RADIOLOGY_SCHEDULE_TEMPLATE_78_8",
//        "CHCS.0056.74.1", "RADIOLOGY_STANDARD_TEXT_74_1",
//        "CHCS.0056.75", "RAD_SPECIAL_INTEREST_CODE_75",
//        "CHCS.0056.78.1", "RAD_WORKSHEET_FORMAT_78_1",
//        "CHCS.0056.78", "RAD_WORKSHEET_PRINT_FIELDS_78",
//        "CHCS.0056.195.6", "REASONS_195_6",
//        "CHCS.0056.190.4", "RECORD_BATCH_PRINT_190_4",
//        "CHCS.0056.190.3", "RECORD_MOVEMENT_HISTORY_190_3",
//        "CHCS.0056.195.3", "RECORD_MOVEMENT_TYPES_195_3",
//        "CHCS.0056.195.4", "RECORD_TRACKING_SYSTEM_PARAMETERS_195_4",
//        "CHCS.0056.190.5", "RECORD_TRANSFERRETIRE_LIST_190_5",
//        "CHCS.0056.195.2", "RECORD_TYPES_195_2",
//        "CHCS.0056.8140", "RELATIONSHIP_8140",
//        "CHCS.0056.8084", "RELATIONSHIP_TO_INSURED_8084",
//        "CHCS.0056.8948.2", "RELEASE_NOTES_8948_2",
//        "CHCS.0056.8947", "RELEASE_NOTES_SUBSYSTEM_8947",
//        "CHCS.0056.13", "RELIGION_13",
//        "CHCS.0056.74.3", "REPORT_DISTRIBUTION_QUEUE_74_3",
//        "CHCS.0056.190.1", "REQUESTED_RECORDS_190_1",
//        "CHCS.0056.8376", "RT_FILEROOM_SPECIFIC_LOCATIONS_FILE_8376",
//        "CHCS.0056.54", "RX_CONSULT_54",
//        "CHCS.0056.53", "RX_PATIENT_STATUS_53",
//        "CHCS.0056.3007", "SCRATCH_PATIENT_3007",
//        "CHCS.0056.8", "SCREEN__8",
//        "CHCS.0056.4012.1", "SCRIPT_GENERATOR_DATA_TYPE_4012_1",
//        "CHCS.0056.4012", "SCRIPT_GENERATOR_FIELD_4012",
//        "CHCS.0056.4011", "SCRIPT_GENERATOR_MESSAGE_4011",
//        "CHCS.0056.19.1", "SECURITY_KEY_19_1",
//        "CHCS.0056.1.401", "SELECTION_CRITERIA_1_401",
//        "CHCS.0056.8596", "SERVICE_TYPE_8596",
//        "CHCS.0056.8931", "SOFTWARE_PACKAGE_ELEMENTS_8931",
//        "CHCS.0056.8935", "SOFTWARE_PACKAGE_ELEMENT_TYPES_8935",
//        "CHCS.0056.401", "SORT_TEMPLATE__401",
//        "CHCS.0056.42.1", "SOURCE_OF_ADMISSION_42_1",
//        "CHCS.0056.3.51", "SPOOL_DOCUMENT_3_51",
//        "CHCS.0056.52010", "STANDARD_IMMUNIZATIONPANEL_52010",
//        "CHCS.0056.757.2", "SUBSET_DEFINITIONS_757_2",
//        "CHCS.0056.1.23", "SUB_FIELD_1_23",
//        "CHCS.0056.8950.02", "SUB_FIELD_8950_02",
//        "CHCS.0056.8587.04", "SUB_FIELD_FORMATS_8587_04",
//        "CHCS.0056.41", "SUB_FORMATTERFIELD__41",
//        "CHCS.0056.8820.052", "SUB_MACRO_COMMAND_8820_052",
//        "CHCS.0056.8920.04", "SUB_PATIENT_OVERRIDE_8920_04",
//        "CHCS.0056.8921.04", "SUB_PATIENT_OVERRIDE_8921_04",
//        "CHCS.0056.8950.01", "SUB_PRODUCTION_SUBFILE_8950_01",
//        "CHCS.0056.190.51", "SUB_RECORD_190_51",
//        "CHCS.0056.8009.01", "SUB_REGISTER_NUMBER_8009_01",
//        "CHCS.0056.8820.012", "SUB_VIEW_TEXT_8820_012",
//        "CHCS.0056.8370.703", "SYS_ERROR_TRAP_8370_703",
//        "CHCS.0056.52004", "TCPR_SITE_PARAMETER_52004",
//        "CHCS.0056.4090.22", "TEMPORARY_VALUE_MAP_4090_22",
//        "CHCS.0056.3.1", "TITLE_3_1",
//        "CHCS.0056.61", "TOPOGRAPHY_FIELD_61",
//        "CHCS.0056.8222.1", "TRANSLUX_8222_1",
//        "CHCS.0056.8222.3", "TRANSLUX_CONTROL_8222_3",
//        "CHCS.0056.8222.2", "TRANSLUX_TEMP_ARRAY_8222_2",
//        "CHCS.0056.8133", "TYPE_CASE_8133",
//        "CHCS.0056.8936", "UDK_LIBRARY_8936",
//        "CHCS.0056.8937", "UDK_LIBRARY_USER_8937",
//        "CHCS.0056.8111", "UNIT_SHIP_ID_8111",
//        "CHCS.0056.3", "USER_3",
//        "CHCS.0056.3.76", "USER_NOTIFICATION_3_76",
//        "CHCS.0056.52016.1", "VACCINE_MANUFACTURERS_52016_1",
//        "CHCS.0056.52016.3", "VACCINE_REACTIONS_52016_3",
//        "CHCS.0056.52016.2", "VACCINE_TYPE_52016_2",
//        "CHCS.0056.57.5", "WARD_GROUP_57_5",
//        "CHCS.0056.42", "WARD_LOCATION_42",
//        "CHCS.0056.1.2", "WINDOW_1_2",
//        "CHCS.0056.68.2", "WORK_DOCUMENT_68_2",
//        "CHCS.0056.15003", "YBO_ENOSIS_METHODS_15003",
//        "CHCS.0056.15004", "YBO_ENOSIS_OBJECTS_15004"
//            
//    };

    /**
     * Returns a text description of the class.
     *
     * @return Class description
     */
    @Override
    public String toString() {
        return getClass().getName() + '@' + Integer.toHexString(hashCode());
    }
}
