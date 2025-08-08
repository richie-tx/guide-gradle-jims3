package mojo.km.naming;

/**
 * @author Jim Fisher
 */
public class ConnectionConstants
{
    public static final String WHEN_EXHAUSTED_FAIL = "FAIL";

    public static final String WHEN_EXHAUSTED_BLOCK = "BLOCK";

    public static final String WHEN_EXHAUSTED_GROW = "GROW";

    /** default total number of active instances from the pool is 1. */
    public static final String DEFAULT_MAX_ACTIVE = "1";

    /** default cap on number of "sleeping" instances in the pool is 1. */
    public static final String DEFAULT_MAX_IDLE = "1";

    /** default to block pool borrow method indefinitely. */
    public static final String DEFAULT_MAX_WAIT_TIME = "-1";

    /**
     * when the pool is exhausted (i.e., maximum number of active objects has
     * been reached), the borrowObject() method should simply create a new
     * object anyway.
     */
    public static final String DEFAULT_WHEN_EXHAUSTED = WHEN_EXHAUSTED_GROW;

    /** Default for no object to be dropped from connection pool due to idle time */
    public static final String DEFAULT_EVICTABLE_IDLE_TIME = "-1";

    public static final String DEFAULT_TEST_ON_BORROW = "false";

    public static final String DEFAULT_TEST_ON_RETURN = "false";

    public static final String DEFAULT_TIME_BETWEEN_EVICTION_RUNS = "-1";

    public static final String DEFAULT_NUM_TESTS_PER_EVICTION_RUN = "5";

    public static final String DEFAULT_TEST_WHILE_IDLE = "false";

    public static final String DEFAULT_MIN_IDLE = "1";
}
