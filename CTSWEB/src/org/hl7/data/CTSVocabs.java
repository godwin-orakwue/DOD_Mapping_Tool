package org.hl7.data;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.hl7.ctsvapi.ArrayOfConceptDesignation;
import org.hl7.ctsvapi.CodeSystemIdAndVersions;
import org.hl7.ctsvapi.CompleteCodedConceptDescription;
import org.hl7.ctsvapi.ConceptDesignation;
import org.hl7.ctsvapi.ConceptId;
import org.hl7.ctsvapi.RelatedCode;
import org.hl7.ctsvapi.UnknownMatchAlgorithm;
import org.hl7.ctsvapi.UnknownMatchAlgorithm_Exception;
import org.jxp.data.JXPMapVersionsFlat;
import org.jxp.data.JXPMapsFlat;

/**
 *  Vocabulary data retrieval class.
 *
 * @author <a href="mailto:karl.a.weller@leidos.com">karl.a.weller</a>
 * @version 1.0
 */
public class CTSVocabs extends CTSBaseDAO {

    /**
     * Logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(CTSVocabs.class.getName());

    /**
     * Exception string.
     */
    private static final String EXCEPTION = "Exception: ";

    /**
     * Version flat file object.
     */
    private static JXPMapVersionsFlat versionflat = JXPMapVersionsFlat.getInstance(); 

    /**
     * Maps flat file object.
     */
    private static JXPMapsFlat mapsflat = JXPMapsFlat.getInstance(); 
    
    /**
     * VA flag.
     */
    private boolean vaflag;

    /**
     * Flat flag.
     */
    private boolean ctsflat;

    /**
     * Constructor.
     */
    public CTSVocabs() {
        super();
        if ("true".equalsIgnoreCase(System.getProperty("ctsflat"))) {
            ctsflat = true;
            try {
                versionflat.openFile();
            } catch (IOException ex) {
                LOGGER.error(EXCEPTION, ex);
            }
            try {
                mapsflat.openFile();
            } catch (IOException ex) {
                LOGGER.error(EXCEPTION, ex);
            }
        }
    }

    /**
     * Constructor.
     * 
     * @param vaflag VA flag
     */
    public CTSVocabs(boolean vaflag) {
        super();
        this.vaflag = vaflag;
        if ("true".equalsIgnoreCase(System.getProperty("ctsflat"))) {
            ctsflat = true;
            try {
                versionflat.openFile();
            } catch (IOException ex) {
                LOGGER.error(EXCEPTION, ex);
            }
            try {
                mapsflat.openFile();
            } catch (IOException ex) {
                LOGGER.error(EXCEPTION, ex);
            }
        }
    }

    /**
     * Constructor.
     *
     * @param connect connect
     * @param user user
     * @param password password
     */
    public CTSVocabs(final String connect, final String user, final String password) {
        super(connect, user, password);
        if ("true".equalsIgnoreCase(System.getProperty("ctsflat"))) {
            ctsflat = true;
            try {
                versionflat.openFile();
            } catch (IOException ex) {
                LOGGER.error(EXCEPTION, ex);
            }
            try {
                mapsflat.openFile();
            } catch (IOException ex) {
                LOGGER.error(EXCEPTION, ex);
            }
        }
    }

    /**
     * Get supported maps.
     *
     * @param sizeLimit size limit
     * @return maps
     * @throws SQLException SQL exception
     */
    public final List<CodeSystemIdAndVersions> getSupportedCodeSystems(int sizeLimit) throws SQLException {
        List<CodeSystemIdAndVersions> retMaps = new ArrayList<CodeSystemIdAndVersions>();

        if (ctsflat) {
            try {
                long rows = versionflat.getNumberOfRows();
                ConcurrentHashMap<String, String> maps = new ConcurrentHashMap<String, String>();
                for (long i = 0; i < rows; ++i) {
                    String[] fields = versionflat.getRow(i);
                    if (fields != null && fields.length >= 5) {
                        maps.put(fields[1], fields[2]);
                        maps.put(fields[3], fields[4]);
                    }
                }
                for (Map.Entry<String, String> entry : maps.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    CodeSystemIdAndVersions codemap = new CodeSystemIdAndVersions();
                    codemap.setCodeSystemId(key);
                    codemap.setCodeSystemName(value);
                    retMaps.add(codemap);
                    if (retMaps.size() == sizeLimit) {
                        break;
                    }
                }                
            } catch (Exception ex) {
                LOGGER.error(EXCEPTION + ex.getLocalizedMessage(), ex);
            }
        } else {
            PreparedStatement statement = null;
            ResultSet resultset = null;
    
            try {
                String sql = "";
                if (vaflag) {
                    sql = "select coding_scheme_oid,coding_scheme_name from codingscheme order by coding_scheme_oid";
    
                } else {
                    sql = "select oid,code from jxp_map_vocabs order by oid";
                }
                connect();
    
                statement = this.getConn().prepareStatement(sql);
                resultset = statement.executeQuery();
    
                while (resultset.next()) {
                    CodeSystemIdAndVersions codemap = new CodeSystemIdAndVersions();
                    int fcount = 1;
    
                    codemap.setCodeSystemId(resultset.getString(fcount++));
                    codemap.setCodeSystemName(resultset.getString(fcount++));
                    retMaps.add(codemap);
                    if (retMaps.size() == sizeLimit) {
                        break;
                    }
                }
            } finally {
                try {
                    if (resultset != null) {
                        resultset.close();
                    }
                } catch (Exception ex) {
                    LOGGER.error(EXCEPTION, ex);
                }
    
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (Exception ex) {
                    LOGGER.error(EXCEPTION, ex);
                }
                close();
            }
        }
        return retMaps;
    }
    
    /**
     * Lookup complete concept.
     * 
     * @param conceptId concept id
     * @return concept
     * @throws SQLException SQL exception
     */
    public CompleteCodedConceptDescription lookupCompleteCodedConcept(ConceptId conceptId) throws SQLException {
        CompleteCodedConceptDescription rtc = null;

        if (ctsflat) {
            String desc = mapsflat.getCodeDescription(conceptId.getCodeSystemId(), conceptId.getConceptCode());
            if (desc != null && desc.length() > 0) {
                rtc = new CompleteCodedConceptDescription();
                rtc.setConceptId(conceptId);
                ConceptDesignation designation = new ConceptDesignation();
                designation.setDesignation(desc);
                ArrayOfConceptDesignation designations = new ArrayOfConceptDesignation();
                designations.getItem().add(designation);
                rtc.setDesignatedBy(designations);
            }
        } else {
            PreparedStatement statement = null;
            ResultSet resultset = null;
    
            try {
                String sql = "";
                if (vaflag) {
                    sql = "select csc.displaytext from codingschemecodes csc left outer join codingscheme cs on csc.coding_scheme_id = cs.coding_scheme_id where cs.coding_scheme_oid=? and csc.code=?";
                } else {
                    sql = "select name from jxp_map_vocab_data where oid=? and code=?";
                }
                connect();
                statement = this.getConn().prepareStatement(sql);
                statement.setString(1, conceptId.getCodeSystemId());
                statement.setString(2, conceptId.getConceptCode());
                resultset = statement.executeQuery();
    
                if (resultset.next()) {
                    rtc = new CompleteCodedConceptDescription();
                    rtc.setConceptId(conceptId);
                    ConceptDesignation designation = new ConceptDesignation();
                    designation.setDesignation(resultset.getString(1));
                    ArrayOfConceptDesignation designations = new ArrayOfConceptDesignation();
                    designations.getItem().add(designation);
                    rtc.setDesignatedBy(designations);
                }
                // TODO: add property retrieval
            } finally {
                try {
                    if (resultset != null) {
                        resultset.close();
                    }
                } catch (Exception ex) {
                    LOGGER.error(EXCEPTION, ex);
                }
    
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (Exception ex) {
                    LOGGER.error(EXCEPTION, ex);
                }
                close();
            }
        }
        return rtc;
    }

    /**
     * Retrieve code by description.
     * 
     * @param codeSystemId OID
     * @param matchText text to match
     * @param matchAlgorithmCode matching algorithm
     * @param languageCode language code (not used)
     * @param activeConceptsOnly active flag
     * @param timeout time out (not used)
     * @param sizeLimit response size limit
     * @return array of codes
     * @throws SQLException SQL Exception
     * @throws UnknownMatchAlgorithm_Exception unknown match type supplied
     */
    public List<ConceptId> lookupConceptCodesByDesignation(String codeSystemId, String matchText,
            String matchAlgorithmCode, String languageCode, boolean activeConceptsOnly, int timeout, int sizeLimit) throws SQLException, UnknownMatchAlgorithm_Exception {
        List<ConceptId> rtc = null;
        if (ctsflat) {
            ConcurrentHashMap<String, String> codes = mapsflat.getCodes(codeSystemId);
            for (Map.Entry<String, String> entry : codes.entrySet()) {
                String key = entry.getKey();
                String[] keys = key.split("\\|");
                String value = entry.getValue();
                if (value == null) {
                    value = "";
                }
                if (matchText != null && matchAlgorithmCode != null && matchText.length() > 0 && matchAlgorithmCode.length() > 0) {
                    if ("IdenticalIgnoreCase".equalsIgnoreCase(matchAlgorithmCode)) {
                        if (value.equalsIgnoreCase(matchText)) {
                            if (rtc == null) {
                                rtc = new ArrayList<ConceptId>();
                            }
                            ConceptId concept = new ConceptId();
                            concept.setCodeSystemId(codeSystemId);
                            concept.setConceptCode(keys[1]);
                            rtc.add(concept);
                            if (rtc.size() == sizeLimit) {
                                break;
                            }
                            
                        }
                    } else if ("Identical".equalsIgnoreCase(matchAlgorithmCode)) {
                        if (value.equals(matchText)) {
                            if (rtc == null) {
                                rtc = new ArrayList<ConceptId>();
                            }
                            ConceptId concept = new ConceptId();
                            concept.setCodeSystemId(codeSystemId);
                            concept.setConceptCode(keys[1]);
                            rtc.add(concept);
                            if (rtc.size() == sizeLimit) {
                                break;
                            }
                        }
                    } else if ("StartsWithIgnoreCase".equalsIgnoreCase(matchAlgorithmCode)) {
                        if (value.toUpperCase().startsWith(matchText.toUpperCase())) {
                            if (rtc == null) {
                                rtc = new ArrayList<ConceptId>();
                            }
                            ConceptId concept = new ConceptId();
                            concept.setCodeSystemId(codeSystemId);
                            concept.setConceptCode(keys[1]);
                            rtc.add(concept);
                            if (rtc.size() == sizeLimit) {
                                break;
                            }
                        }
                    } else if ("StartsWith".equalsIgnoreCase(matchAlgorithmCode)) {
                        if (value.startsWith(matchText)) {
                            if (rtc == null) {
                                rtc = new ArrayList<ConceptId>();
                            }
                            ConceptId concept = new ConceptId();
                            concept.setCodeSystemId(codeSystemId);
                            concept.setConceptCode(keys[1]);
                            rtc.add(concept);
                            if (rtc.size() == sizeLimit) {
                                break;
                            }
                        }
                    } else if ("EndsWithIgnoreCase".equalsIgnoreCase(matchAlgorithmCode)) {
                        if (value.toUpperCase().endsWith(matchText.toUpperCase())) {
                            if (rtc == null) {
                                rtc = new ArrayList<ConceptId>();
                            }
                            ConceptId concept = new ConceptId();
                            concept.setCodeSystemId(codeSystemId);
                            concept.setConceptCode(keys[1]);
                            rtc.add(concept);
                            if (rtc.size() == sizeLimit) {
                                break;
                            }
                        }
                    } else if ("EndsWith".equalsIgnoreCase(matchAlgorithmCode)) {
                        if (value.endsWith(matchText)) {
                            if (rtc == null) {
                                rtc = new ArrayList<ConceptId>();
                            }
                            ConceptId concept = new ConceptId();
                            concept.setCodeSystemId(codeSystemId);
                            concept.setConceptCode(keys[1]);
                            rtc.add(concept);
                            if (rtc.size() == sizeLimit) {
                                break;
                            }
                        }
                    } else if ("ContainsPhraseIgnoreCase".equalsIgnoreCase(matchAlgorithmCode)) {
                        if (value.toUpperCase().indexOf(matchText.toUpperCase()) != -1) {
                            if (rtc == null) {
                                rtc = new ArrayList<ConceptId>();
                            }
                            ConceptId concept = new ConceptId();
                            concept.setCodeSystemId(codeSystemId);
                            concept.setConceptCode(keys[1]);
                            rtc.add(concept);
                            if (rtc.size() == sizeLimit) {
                                break;
                            }
                        }
                    } else if ("ContainsPhrase".equalsIgnoreCase(matchAlgorithmCode)) {
                        if (value.indexOf(matchText) != -1) {
                            if (rtc == null) {
                                rtc = new ArrayList<ConceptId>();
                            }
                            ConceptId concept = new ConceptId();
                            concept.setCodeSystemId(codeSystemId);
                            concept.setConceptCode(keys[1]);
                            rtc.add(concept);
                            if (rtc.size() == sizeLimit) {
                                break;
                            }
                        }
                    } else {
                        UnknownMatchAlgorithm ua = new UnknownMatchAlgorithm();
                        ua.setMatchAlgorithmCode(matchAlgorithmCode);
                        UnknownMatchAlgorithm_Exception ex = new UnknownMatchAlgorithm_Exception("Unknown match algorithm", ua);
                        throw ex;
                    }
                } else {
                    if (rtc == null) {
                        rtc = new ArrayList<ConceptId>();
                    }
                    ConceptId concept = new ConceptId();
                    concept.setCodeSystemId(codeSystemId);
                    concept.setConceptCode(keys[1]);
                    rtc.add(concept);
                    if (rtc.size() == sizeLimit) {
                        break;
                    }
                }
            }
        } else {
            String match = matchText;        
    
            PreparedStatement statement = null;
            ResultSet resultset = null;
    
            try {
                String sql = "";
                if (vaflag) {
                    sql = "select csc.code from codingschemecodes csc left outer join codingscheme cs on csc.coding_scheme_id = cs.coding_scheme_id where cs.coding_scheme_oid=? order by 1";
                } else {
                    sql = "select code from jxp_map_vocab_data where oid=? order by code";
                }
                if (matchText != null && matchAlgorithmCode != null && matchText.length() > 0 && matchAlgorithmCode.length() > 0) {
                    if ("IdenticalIgnoreCase".equalsIgnoreCase(matchAlgorithmCode)) {
                        if (vaflag) {
                            sql = "select csc.code from codingschemecodes csc left outer join codingscheme cs on csc.coding_scheme_id = cs.coding_scheme_id where cs.coding_scheme_oid=?  and upper(csc.displaytext) = ? order by 1";
                        } else {
                            sql = "select code from jxp_map_vocab_data where oid=? and upper(name) = ?";
                        }
                        match = matchText.toUpperCase();
                    } else if ("Identical".equalsIgnoreCase(matchAlgorithmCode)) {
                        if (vaflag) {
                            sql = "select csc.code from codingschemecodes csc left outer join codingscheme cs on csc.coding_scheme_id = cs.coding_scheme_id where cs.coding_scheme_oid=?  and csc.displaytext = ? order by 1";
                        } else {
                            sql = "select code from jxp_map_vocab_data where oid=? and name = ?";
                        }
                    } else if ("StartsWithIgnoreCase".equalsIgnoreCase(matchAlgorithmCode)) {
                        if (vaflag) {
                            sql = "select csc.code from codingschemecodes csc left outer join codingscheme cs on csc.coding_scheme_id = cs.coding_scheme_id where cs.coding_scheme_oid=?  and upper(csc.displaytext) like ? order by 1";
                        } else {
                            sql = "select code from jxp_map_vocab_data where oid=? and upper(name) like ?";
                        }
                        match = matchText.toUpperCase() + "%";
                    } else if ("StartsWith".equalsIgnoreCase(matchAlgorithmCode)) {
                        if (vaflag) {
                            sql = "select csc.code from codingschemecodes csc left outer join codingscheme cs on csc.coding_scheme_id = cs.coding_scheme_id where cs.coding_scheme_oid=?  and csc.displaytext like ? order by 1";
                        } else {
                            sql = "select code from jxp_map_vocab_data where oid=? and name like ?";
                        }
                        match = matchText + "%";
                    } else if ("EndsWithIgnoreCase".equalsIgnoreCase(matchAlgorithmCode)) {
                        if (vaflag) {
                            sql = "select csc.code from codingschemecodes csc left outer join codingscheme cs on csc.coding_scheme_id = cs.coding_scheme_id where cs.coding_scheme_oid=?  and upper(csc.displaytext) like ? order by 1";
                        } else {
                            sql = "select code from jxp_map_vocab_data where oid=? and upper(name) like ?";
                        }
                        match = "%" + matchText.toUpperCase();
                    } else if ("EndsWith".equalsIgnoreCase(matchAlgorithmCode)) {
                        if (vaflag) {
                            sql = "select csc.code from codingschemecodes csc left outer join codingscheme cs on csc.coding_scheme_id = cs.coding_scheme_id where cs.coding_scheme_oid=?  and csc.displaytext like ? order by 1";
                        } else {
                            sql = "select code from jxp_map_vocab_data where oid=? and name like ?";
                        }
                        match = "%" + matchText;
                    } else if ("ContainsPhraseIgnoreCase".equalsIgnoreCase(matchAlgorithmCode)) {
                        if (vaflag) {
                            sql = "select csc.code from codingschemecodes csc left outer join codingscheme cs on csc.coding_scheme_id = cs.coding_scheme_id where cs.coding_scheme_oid=?  and upper(csc.displaytext) like ? order by 1";
                        } else {
                            sql = "select code from jxp_map_vocab_data where oid=? and upper(name) like ?";
                        }
                        match = "%" + matchText.toUpperCase() + '%';
                    } else if ("ContainsPhrase".equalsIgnoreCase(matchAlgorithmCode)) {
                        if (vaflag) {
                            sql = "select csc.code from codingschemecodes csc left outer join codingscheme cs on csc.coding_scheme_id = cs.coding_scheme_id where cs.coding_scheme_oid=?  and csc.displaytext like ? order by 1";
                        } else {
                            sql = "select code from jxp_map_vocab_data where oid=? and name like ?";
                        }
                        match = "%" + matchText + '%';
                    } else {
                        UnknownMatchAlgorithm ua = new UnknownMatchAlgorithm();
                        ua.setMatchAlgorithmCode(matchAlgorithmCode);
                        UnknownMatchAlgorithm_Exception ex = new UnknownMatchAlgorithm_Exception("Unknown match algorithm", ua);
                        throw ex;
                    }
                    connect();
    
                    statement = this.getConn().prepareStatement(sql);
                    statement.setString(1, codeSystemId);
                    statement.setString(2, match);
                } else {
                    connect();
    
                    statement = this.getConn().prepareStatement(sql);
                    statement.setString(1, codeSystemId);
                }
                resultset = statement.executeQuery();
    
                while (resultset.next()) {
                    if (rtc == null) {
                        rtc = new ArrayList<ConceptId>();
                    }
                    ConceptId concept = new ConceptId();
                    concept.setCodeSystemId(codeSystemId);
                    concept.setConceptCode(resultset.getString(1));
                    rtc.add(concept);
                    if (rtc.size() == sizeLimit) {
                        break;
                    }
                }
                // TODO: add property retrieval
            } finally {
                try {
                    if (resultset != null) {
                        resultset.close();
                    }
                } catch (Exception ex) {
                    LOGGER.error(EXCEPTION, ex);
                }
    
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (Exception ex) {
                    LOGGER.error(EXCEPTION, ex);
                }
                close();
            }
        }
        return rtc;
    }
    
    /**
     * Lookup code descriptions.
     * 
     * @param conceptCode code
     * @param matchText match text
     * @param matchAlgorithmCode matching algorithm
     * @param languageCode language (not used)
     * @return descriptions
     * @throws SQLException SQL exception
     * @throws UnknownMatchAlgorithm_Exception unknown match type supplied
     */
    public List<ConceptDesignation> lookupDesignations(ConceptId conceptCode, String matchText,
            String matchAlgorithmCode,
            String languageCode) throws SQLException, UnknownMatchAlgorithm_Exception {
        List<ConceptDesignation> rtc = null;
        
        return rtc;
    }
    
    /**
     * Lookup code expansion.
     * 
     * @param expandConceptId concept id
     * @param relationshipCode relationship code (not used)
     * @param sourceToTarget source to target (not used)
     * @param directRelationsOnly direct only (not used)
     * @param designationLanguageCode language code (not used)
     * @param timeout time out (not used)
     * @param sizeLimit size limit
     * @return list of codes
     * @throws SQLException SQL exception
     */
    public List<RelatedCode> lookupCodeExpansion(ConceptId expandConceptId, String relationshipCode, boolean sourceToTarget, boolean directRelationsOnly,
            String designationLanguageCode, int timeout, int sizeLimit) throws SQLException {
        List<RelatedCode> rtc = null;
        PreparedStatement statement = null;
        ResultSet resultset = null;

        if (ctsflat) {
            ConcurrentHashMap<String, String> codes = mapsflat.getCodes(expandConceptId.getCodeSystemId());
            if (expandConceptId.getConceptCode() == null || expandConceptId.getConceptCode().length() == 0) {
                for (Map.Entry<String, String> entry : codes.entrySet()) {
                    String key = entry.getKey();
                    String[] keys = key.split("\\|");
                    String value = entry.getValue();
                    if (value == null) {
                        value = "";
                    }
                    if (value.length() > 0) {
                        if (rtc == null) {
                            rtc = new ArrayList<RelatedCode>();
                        }
                        RelatedCode code = new RelatedCode();
                        code.setConceptCode(keys[1]);
                        code.setDesignation(value);
                        rtc.add(code);
                        if (rtc.size() == sizeLimit) {
                            break;
                        }
                    }
                }
            } else {
                String value = codes.get(expandConceptId.getCodeSystemId() + "|" + expandConceptId.getConceptCode());
                if (value != null && value.length() > 0) {
                    if (rtc == null) {
                        rtc = new ArrayList<RelatedCode>();
                    }
                    RelatedCode code = new RelatedCode();
                    code.setConceptCode(expandConceptId.getConceptCode());
                    code.setDesignation(value);
                    rtc.add(code);
                }
            }
        } else {
            try {
                String sql = "";
                if (vaflag) {
                    if (expandConceptId.getConceptCode() == null || expandConceptId.getConceptCode().length() == 0) {
                        sql = "select csc.code,csc.displaytext from codingschemecodes csc left outer join codingscheme cs on csc.coding_scheme_id = cs.coding_scheme_id where cs.coding_scheme_oid=? order by 1";
                    } else {
                        sql = "select csc.code,csc.displaytext from codingschemecodes csc left outer join codingscheme cs on csc.coding_scheme_id = cs.coding_scheme_id where cs.coding_scheme_oid=? and csc.code=?";
                    }
                } else {
                    if (expandConceptId.getConceptCode() == null || expandConceptId.getConceptCode().length() == 0) {
                        sql = "select code,name from jxp_map_vocab_data where oid=?";
                    } else {
                        sql = "select code,name from jxp_map_vocab_data where oid=? and code=?";
                    }
                }
                connect();
    
                statement = this.getConn().prepareStatement(sql);
                statement.setString(1, expandConceptId.getCodeSystemId());
                if (expandConceptId.getConceptCode() != null && expandConceptId.getConceptCode().length() > 0) {
                    statement.setString(2, expandConceptId.getConceptCode());
                }
                resultset = statement.executeQuery();
    
                while (resultset.next()) {
                    if (rtc == null) {
                        rtc = new ArrayList<RelatedCode>();
                    }
                    RelatedCode code = new RelatedCode();
                    code.setConceptCode(resultset.getString(1));
                    code.setDesignation(resultset.getString(2));
                    rtc.add(code);
                    if (rtc.size() == sizeLimit) {
                        break;
                    }
                }
            } finally {
                try {
                    if (resultset != null) {
                        resultset.close();
                    }
                } catch (Exception ex) {
                    LOGGER.error(EXCEPTION, ex);
                }
    
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (Exception ex) {
                    LOGGER.error(EXCEPTION, ex);
                }
                close();
            }
        }
        return rtc;
    }
    
    /**
     * Set VA flag.
     * 
     * @param vaflag the vaflag to set
     */
    public void setVaflag(boolean vaflag) {
        this.vaflag = vaflag;
    }

    /**
     * Get VA flag.
     * 
     * @return the vaflag
     */
    public boolean isVaflag() {
        return vaflag;
    }
}
