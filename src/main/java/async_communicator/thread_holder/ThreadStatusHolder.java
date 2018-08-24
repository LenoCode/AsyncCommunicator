package async_communicator.thread_holder;

import async_communicator.object_holder.flag_object.FlagObject;

public class ThreadStatusHolder {
    private final int THREAD_INITIALIZED = 0;
    private final int THREAD_START = 1;
    private final int THREAD_FINISHED = 2;
    private final FlagObject flagObject;
    private int currentStatus;


    protected ThreadStatusHolder(){
        currentStatus = THREAD_INITIALIZED;
        flagObject = new FlagObject();
    }

    public boolean hasThreadFinished(){
        return (currentStatus == THREAD_FINISHED);
    }
    public boolean hasThreadStarted(){
        return (currentStatus == THREAD_START);
    }
    public void threadStarted(){
        currentStatus = THREAD_START;
    }
    public void threadFinished(){
        currentStatus = THREAD_FINISHED;
    }
    public void addFlag(String id,boolean flag){
        flagObject.addFlag(id,flag);
    }
    public boolean containsFlag(String id){
        return flagObject.checkIfFlagExist(id);
    }
    public boolean getFlag(String id){
        return flagObject.getFlag(id);
    }
}
