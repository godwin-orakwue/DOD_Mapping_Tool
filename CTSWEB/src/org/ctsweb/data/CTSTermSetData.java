package org.ctsweb.data;

import java.io.Serializable;

/**
 * Term set data.
 * 
 * @author wellerk
 *
 */
public class CTSTermSetData implements Serializable {

    /**
     * Class UID.
     */
    private static final long serialVersionUID = 4424622598224531509L;
    
    /**
     * OID.
     */
    private String oid;
    
    /**
     * Site.
     */
    private String site;
    
    /**
     * Name.
     */
    private String name;
    
    /**
     * Connection type.
     */
    private String connectionType;
    
    /**
     * WSDL.
     */
    private String ctsWSDL;
    
    /**
     * OID.
     */
    private String ctsOID;
    
    /**
     * User.
     */
    private String ctsUser;
    
    /**
     * Password.
     */
    private String ctsPwd;
    
    /**
     * Password.
     */
    private String readOnly;
    
    /**
     * Datasource.
     */
    private String jdbcDataSource;
    
    /**
     * Retrieve.
     */
    private String jdbcSQLRetrieve;

    /**
     * Add.
     */
    private String jdbcSQLAdd;
    
    /**
     * Update.
     */
    private String jdbcSQLUpdate;

    /**
     * Delete.
     */
    private String jdbcSQLDelete;

    /**
     * Get OID.
     * 
     * @return the oid
     */
    public String getOid() {
        return oid;
    }

    /**
     * Set OID.
     * 
     * @param oid the oid to set
     */
    public void setOid(String oid) {
        this.oid = oid;
    }

    /**
     * Get site.
     * 
     * @return the site
     */
    public String getSite() {
        return site;
    }

    /**
     * Set site.
     * 
     * @param site the site to set
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * Get name.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name.
     * 
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get connection type.
     * 
     * @return the connectionType
     */
    public String getConnectionType() {
        return connectionType;
    }

    /**
     * Set connection type.
     * 
     * @param connectionType the connectionType to set
     */
    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    /**
     * Get WSDL.
     * 
     * @return the ctsWSDL
     */
    public String getCtsWSDL() {
        return ctsWSDL;
    }

    /**
     * Set WSDL.
     * 
     * @param ctsWSDL the ctsWSDL to set
     */
    public void setCtsWSDL(String ctsWSDL) {
        this.ctsWSDL = ctsWSDL;
    }

    /**
     * Get OID.
     * 
     * @return the ctsOID
     */
    public String getCtsOID() {
        return ctsOID;
    }

    /**
     * Set OID.
     * 
     * @param ctsOID the ctsOID to set
     */
    public void setCtsOID(String ctsOID) {
        this.ctsOID = ctsOID;
    }

    /**
     * Get user.
     * 
     * @return the ctsUser
     */
    public String getCtsUser() {
        return ctsUser;
    }

    /**
     * Set user.
     * 
     * @param ctsUser the ctsUser to set
     */
    public void setCtsUser(String ctsUser) {
        this.ctsUser = ctsUser;
    }

    /**
     * Get password.
     * 
     * @return the ctsPwd
     */
    public String getCtsPwd() {
        return ctsPwd;
    }

    /**
     * Set password.
     * 
     * @param ctsPwd the ctsPwd to set
     */
    public void setCtsPwd(String ctsPwd) {
        this.ctsPwd = ctsPwd;
    }

    /**
     * Get read only.
     * 
     * @return the readOnly
     */
    public String getReadOnly() {
        return readOnly;
    }

    /**
     * Set read only.
     * 
     * @param readOnly the readOnly to set
     */
    public void setReadOnly(String readOnly) {
        this.readOnly = readOnly;
    }

    /**
     * Get datasource.
     * 
     * @return the jdbcDataSource
     */
    public String getJdbcDataSource() {
        return jdbcDataSource;
    }

    /**
     * Set datasource.
     * 
     * @param jdbcDataSource the jdbcDataSource to set
     */
    public void setJdbcDataSource(String jdbcDataSource) {
        this.jdbcDataSource = jdbcDataSource;
    }

    /**
     * Get retrieve.
     * 
     * @return the jdbcSQLRetrieve
     */
    public String getJdbcSQLRetrieve() {
        return jdbcSQLRetrieve;
    }

    /**
     * Set retrieve.
     * 
     * @param jdbcSQLRetrieve the jdbcSQLRetrieve to set
     */
    public void setJdbcSQLRetrieve(String jdbcSQLRetrieve) {
        this.jdbcSQLRetrieve = jdbcSQLRetrieve;
    }

    /**
     * Get add.
     * 
     * @return the jdbcSQLAdd
     */
    public String getJdbcSQLAdd() {
        return jdbcSQLAdd;
    }

    /**
     * Set add.
     * 
     * @param jdbcSQLAdd the jdbcSQLAdd to set
     */
    public void setJdbcSQLAdd(String jdbcSQLAdd) {
        this.jdbcSQLAdd = jdbcSQLAdd;
    }

    /**
     * Get update.
     * 
     * @return the jdbcSQLUpdate
     */
    public String getJdbcSQLUpdate() {
        return jdbcSQLUpdate;
    }

    /**
     * Set update.
     * 
     * @param jdbcSQLUpdate the jdbcSQLUpdate to set
     */
    public void setJdbcSQLUpdate(String jdbcSQLUpdate) {
        this.jdbcSQLUpdate = jdbcSQLUpdate;
    }

    /**
     * Get delete.
     * 
     * @return the jdbcSQLDelete
     */
    public String getJdbcSQLDelete() {
        return jdbcSQLDelete;
    }

    /**
     * Set delete.
     * 
     * @param jdbcSQLDelete the jdbcSQLDelete to set
     */
    public void setJdbcSQLDelete(String jdbcSQLDelete) {
        this.jdbcSQLDelete = jdbcSQLDelete;
    }
}
