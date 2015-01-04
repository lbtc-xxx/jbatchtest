package jbatchtest;

import javax.batch.operations.*;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.batch.runtime.JobInstance;
import javax.batch.runtime.StepExecution;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;
import java.util.Properties;
import java.util.Set;

@Stateless
@Remote(JobOperator.class)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class JobOperatorFacade implements JobOperator {

    protected JobOperator getJobOperator() {
        return BatchRuntime.getJobOperator();
    }

    @Override
    public Set<String> getJobNames() throws JobSecurityException {
        return getJobOperator().getJobNames();
    }

    @Override
    public int getJobInstanceCount(String jobName) throws NoSuchJobException, JobSecurityException {
        return getJobOperator().getJobInstanceCount(jobName);
    }

    @Override
    public List<JobInstance> getJobInstances(String jobName, int start, int count) throws NoSuchJobException, JobSecurityException {
        return getJobOperator().getJobInstances(jobName, start, count);
    }

    @Override
    public List<Long> getRunningExecutions(String jobName) throws NoSuchJobException, JobSecurityException {
        return getJobOperator().getRunningExecutions(jobName);
    }

    @Override
    public Properties getParameters(long executionId) throws NoSuchJobExecutionException, JobSecurityException {
        return getJobOperator().getParameters(executionId);
    }

    @Override
    public long start(String jobXMLName, Properties jobParameters) throws JobStartException, JobSecurityException {
        return getJobOperator().start(jobXMLName, jobParameters);
    }

    @Override
    public long restart(long executionId, Properties restartParameters) throws JobExecutionAlreadyCompleteException,
            NoSuchJobExecutionException, JobExecutionNotMostRecentException, JobRestartException, JobSecurityException {
        return getJobOperator().restart(executionId, restartParameters);
    }

    @Override
    public void stop(long executionId) throws NoSuchJobExecutionException, JobExecutionNotRunningException, JobSecurityException {
        getJobOperator().stop(executionId);
    }

    @Override
    public void abandon(long executionId) throws NoSuchJobExecutionException, JobExecutionIsRunningException, JobSecurityException {
        getJobOperator().abandon(executionId);
    }

    @Override
    public JobInstance getJobInstance(long executionId) throws NoSuchJobExecutionException, JobSecurityException {
        return getJobOperator().getJobInstance(executionId);
    }

    @Override
    public List<JobExecution> getJobExecutions(JobInstance instance) throws NoSuchJobInstanceException, JobSecurityException {
        return getJobOperator().getJobExecutions(instance);
    }

    @Override
    public JobExecution getJobExecution(long executionId) throws NoSuchJobExecutionException, JobSecurityException {
        return getJobOperator().getJobExecution(executionId);
    }

    @Override
    public List<StepExecution> getStepExecutions(long jobExecutionId) throws NoSuchJobExecutionException, JobSecurityException {
        return getJobOperator().getStepExecutions(jobExecutionId);
    }
}
