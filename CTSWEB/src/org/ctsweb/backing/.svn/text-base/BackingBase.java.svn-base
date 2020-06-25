package org.ctsweb.backing;


import javax.faces.component.html.HtmlOutputText;

import org.jxp.data.JXPProperties;

/**
 * Base backing class.
 *
 * @author <a href="mailto:karl.a.weller@leidos.com">karl.a.weller</a>
 * @version 1.0
 */
public class BackingBase {

    /**
     * Exception string.
     */
    protected static final String EXCEPTION = "Exception: ";

    /**
     * Failure.
     */
    protected static final String FAILURE = "failure";
    
    /**
     * Error field.
     */
    private HtmlOutputText saveError;

    /**
     * Application text.
     */
    private HtmlOutputText appNameText;

    /**
     * Application name.
     */
    private String appName = "jXP";
    
    /**
     * Constructor.
     */
    public BackingBase() {
        JXPProperties jprops = JXPProperties.getInstance();
        JXPProperties.getInstance().setDatasource("jdbc/jXPDS");
        jprops.getProperties().setProperty("datasource", "jdbc/jXPDS");
        jprops.getProperties().setProperty("jxp.applicationname", "CTS Management");
    }
    
    /**
     * Get save error.
     * 
     * @return the saveError
     */
    public HtmlOutputText getSaveError() {
        if (saveError == null) {
            saveError = new HtmlOutputText();
        }
        return saveError;
    }

    /**
     * Set save error.
     * 
     * @param saveError the saveError to set
     */
    public void setSaveError(HtmlOutputText saveError) {
        this.saveError = saveError;
    }

    /**
     * Get application name.
     * 
     * @return the appName application name
     */
    public String getAppName() {
        appName = JXPProperties.getInstance().getProperties().getProperty("jxp.applicationname", appName);
        return appName;
    }

    /**
     * Set application name.
     * 
     * @param appName the appName to set
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * Get application name text.
     * 
     * @return the appNameText app name
     */
    public HtmlOutputText getAppNameText() {
        appNameText = new HtmlOutputText();
        appNameText.setValue(getAppName());
        return appNameText;
    }

    /**
     * Set application name text.
     * 
     * @param appNameText the appNameText to set
     */
    public void setAppNameText(HtmlOutputText appNameText) {
        this.appNameText = appNameText;
    }

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
