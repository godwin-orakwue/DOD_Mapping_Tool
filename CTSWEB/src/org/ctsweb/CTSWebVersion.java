package org.ctsweb;

/**
 *  CTSWEB version Information.
 *
 * @author <a href="mailto:karl.a.weller@leidos.com">karl.a.weller</a>
 * @version 1.0
 */
public final class CTSWebVersion {

    /**
     * Version String.
     */
    public static final String VERSION = "3.0.6";

    /**
     *  Display version.
     * 
     * @param args arguments
     */
    public static void main(final String[] args) {
        System.out.println("Version: " + VERSION);
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
