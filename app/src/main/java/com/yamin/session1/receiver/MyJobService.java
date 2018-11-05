package com.yamin.session1.receiver;


public class MyJobService extends com.firebase.jobdispatcher.JobService {

    private static final String TAG = "MyJobService";

    @Override
    public boolean onStartJob(com.firebase.jobdispatcher.JobParameters job) {
        return false;
    }

    @Override
    public boolean onStopJob(com.firebase.jobdispatcher.JobParameters job) {
        return false;
    }
}
