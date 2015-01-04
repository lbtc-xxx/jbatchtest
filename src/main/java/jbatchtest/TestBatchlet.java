package jbatchtest;

import javax.batch.api.Batchlet;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
public class TestBatchlet implements Batchlet {

    Logger log = Logger.getLogger(TestBatchlet.class.getName());

    @Inject
    JobContext jobContext;
    Thread currentThread;

    @Override
    public String process() throws Exception {
        currentThread = Thread.currentThread();

        long executionId = jobContext.getExecutionId();
        log.log(Level.INFO, "executionId={0}: the batch has started.", executionId);
        log.log(Level.INFO, "executionId={0}: job properties: {1}",
                new Object[]{executionId, BatchRuntime.getJobOperator().getParameters(executionId)});

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            log.log(Level.INFO, "executionId={0}: stopped", executionId);
            return "INTERRUPTED";
        }

        log.log(Level.INFO, "executionId={0}: done.", executionId);
        return null;
    }

    @Override
    public void stop() throws Exception {
        long executionId = jobContext.getExecutionId();
        log.log(Level.INFO, "executionId={0}: stopping...", executionId);
        currentThread.interrupt();
    }
}
