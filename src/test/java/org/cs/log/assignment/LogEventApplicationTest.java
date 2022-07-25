package org.cs.log.assignment;

import org.junit.Assert;
import org.junit.Test;

public class LogEventApplicationTest {

    @Test
    public void testProcessFile() {
        LogEventApplication logEventApplication = new LogEventApplication();
        LogEventApplication.processFile("src\\test\\resources\\logfile.txt");
        Assert.assertTrue(true);
    }
}
