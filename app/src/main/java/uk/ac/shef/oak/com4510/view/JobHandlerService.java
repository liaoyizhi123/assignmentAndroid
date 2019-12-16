package uk.ac.shef.oak.com4510.view;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class JobHandlerService extends JobService {
    private JobScheduler jobScheduler;
    private MapsActivity mapsActivity;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.e("service", "GuardJobService--onStartJob");
        mapsActivity = new MapsActivity();
        mapsActivity.startmap();

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.e("service", "GuardJobService--onStopJob");
        mapsActivity = new MapsActivity();
        mapsActivity.stopmap();
        return false;
    }

    public void setJobInfo() {
        JobInfo.Builder ex = new JobInfo.Builder(1, new ComponentName(this.getPackageName(), MapsActivity.class.getName()));

        ex.setPeriodic(500L);
        //ex.setRequiredNetworkType(JobInfo.NETWORK_TYPE_NONE);
        //ex.setMinimumLatency(3000);
        //ex.setRequiresCharging(true);

        jobScheduler = (JobScheduler) this.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        int ret = jobScheduler.schedule(ex.build());  //设置给JobScheduler

        Log.e("service", "GuardJobService--getJobInfo -- ret::" + ret);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //getJobInfo();
        Log.e("service", "GuardJobService--onCreate");
    }

}
