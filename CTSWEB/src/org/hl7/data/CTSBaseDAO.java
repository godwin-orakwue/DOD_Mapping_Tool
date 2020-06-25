package org.hl7.data;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.sql.DataSource;

import oracle.jdbc.driver.OracleDriver;
import org.apache.log4j.Logger;

/**
 *  Base DAO Class provides connection to database objects.
 *
 * @author <a href="mailto:karl.a.weller@leidos.com">karl.a.weller</a>
 * @version 1.0
 */
public class CTSBaseDAO  {


    /**
     * Logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(CTSBaseDAO.class.getName());

    /**
     * Exception string.
     */
    private static final String EXCEPTION = "Exception: ";

    /**
     * Default connection string.
     */
    private String defaultConnect = "jdbc:oracle:thin:@localhost:1521:reposdb";

    /**
     * Default user.
     */
    private String defaultUser = "tms";

    /**
     * Default password.
     */
    private String defaultPassword = "tms";

    /**
     * Connection to the database.
     */
    private Connection conn;

    /**
     *  Connection pool.
     */
    private DataSource pool;

    /**
     *  Data source.
     */
    private String datasource = "jdbc/tmsDS";

    /**
     * Constructor.
     */
    public CTSBaseDAO() {
        super();
        LOGGER.debug("CTSBaseDAO Constructor");
    }

    /**
     * Constructor.
     *
     * @param inDatasource data source
     */
    public CTSBaseDAO(final String inDatasource) {
        super();
        this.datasource = inDatasource;
        LOGGER.debug("CTSBaseDAO Constructor");
    }

    /**
     * Constructor.
     *
     * @param connect connect
     * @param user user
     * @param password password
     */
    public CTSBaseDAO(final String connect,
            final String user, final String password) {
        super();
        this.defaultConnect = connect;
        this.defaultUser = user;
        this.defaultPassword = password;
        LOGGER.debug("CTSBaseDAO Constructor");
    }

    /**
     * Connects to database.
     */
    public final void connect() {
        LOGGER.debug("CTSBaseDAO Connect begin");
        if (this.conn != null) {
            close();
        }

        try {
            Context env;

            env = new InitialContext();
            this.pool = (DataSource) env.lookup(datasource);
            this.conn = this.pool.getConnection();
        } catch (Exception ex) {
            LOGGER.error(EXCEPTION, ex);
        }

        if (this.conn == null) {
            try {
                DriverManager.registerDriver(new OracleDriver());
                this.conn = DriverManager.getConnection(
                        defaultConnect, defaultUser, defaultPassword);
            } catch (Exception ex) {
                LOGGER.error(EXCEPTION, ex);
            }
        }
        LOGGER.debug("CTSBaseDAO Connect end");
    }

    /**
     * connect to database using user and password.
     *
     * @param user User used to connect to the database
     * @param password Password used to connect to the database
     */
    public final void connect(final String user, final String password) {
        LOGGER.debug("CTSBaseDAO Connect(2) begin");
        if (this.conn != null) {
            close();
        }

        try {
            Context env;

            env = new InitialContext();
            this.pool = (DataSource) env.lookup(datasource);
            this.conn = this.pool.getConnection(user, password);
        } catch (Exception ex) {
            LOGGER.error(EXCEPTION, ex);
        }

        if (this.conn == null) {
            try {
                DriverManager.registerDriver(new OracleDriver());
                this.conn = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:XE", user, password);
            } catch (Exception ex) {
                LOGGER.error(EXCEPTION, ex);
            }
        }
        LOGGER.debug("CTSBaseDAO Connect(2) end");
    }

    /**
     * close connection to database.
     */
    public final void close() {
        LOGGER.debug("CTSBaseDAO close begin");
        try {
            if (this.conn != null) {
                this.conn.close();
            }
        } catch (Exception ex) {
            LOGGER.error(EXCEPTION, ex);
        }
        this.conn = null;
        this.pool = null;
        LOGGER.debug("CTSBaseDAO close end");
    }

    /**
     * Get connection.
     *
     * @return the connection
     */
    protected final Connection getConn() {
        return conn;
    }

    /**
         * Set connection.
         *
     * @param inconn the connection to set
     */
    protected final void setConn(final Connection inconn) {
        this.conn = inconn;
    }

    /**
     * Get default connect.
     *
     * @return the defaultConnect
     */
    public final String getDefaultConnect() {
        return defaultConnect;
    }

    /**
     * Set default connect.
     *
     * @param inDefaultConnect the defaultConnect to set
     */
    public final void setDefaultConnect(final String inDefaultConnect) {
        this.defaultConnect = inDefaultConnect;
    }

    /**
     * Get default user.
     *
     * @return the defaultUser
     */
    public final String getDefaultUser() {
        return defaultUser;
    }

    /**
     * Set default user.
     *
     * @param inDefaultUser the defaultUser to set
     */
    public final void setDefaultUser(final String inDefaultUser) {
        this.defaultUser = inDefaultUser;
    }

    /**
     * Get default password.
     *
     * @return the defaultPassword
     */
    public final String getDefaultPassword() {
        return defaultPassword;
    }

    /**
     * Set default password.
     *
     * @param inDefaultPassword the defaultPassword to set
     */
    public final void setDefaultPassword(final String inDefaultPassword) {
        this.defaultPassword = inDefaultPassword;
    }

    /**
     * Get data source.
     *
     * @return the data source
     */
    public final String getDatasource() {
        return datasource;
    }

    /**
     * Set data source.
     *
     * @param dsource the data source to set
     */
    public final void setDatasource(final String dsource) {
        this.datasource = dsource;
    }

    /**
     * Get pool.
     *
     * @return the pool
     */
    public final DataSource getPool() {
        return pool;
    }

    /**
     * Set pool.
     *
     * @param inPool the pool to set
     */
    public final void setPool(final DataSource inPool) {
        this.pool = inPool;
    }

    /**
     * Make sure things get cleaned up.
     *
     * @throws Throwable throw
     */
    protected final void finalize() throws Throwable {
        LOGGER.debug("CTSBaseDAO finialize");
        try {
            close();
        } finally {
            super.finalize();
        }
    }
}
