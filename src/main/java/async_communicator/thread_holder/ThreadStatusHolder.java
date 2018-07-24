package async_communicator.thread_holder;

import async_communicator.object_holder.flag_object.FlagObject;

public class ThreadStatusHolder {
    private final int THREAD_INITIALIZED = 0;
    private final int THREAD_START = 1;
    private final int THREAD_FINISHED = 2;
    private final FlagObject flagObject;
    private int currentStatus;
    private ThreadResponseHolder threadResponseHolder;

    protected ThreadStatusHolder(){
        currentStatus = THREAD_INITIALIZED;
        threadResponseHolder = new ThreadResponseHolder();
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
    public <A> void addResponseToThread(A threadResponse){
        threadResponseHolder.setResponse(threadResponse);
    }
    public void threadFinished(){
        currentStatus = THREAD_FINISHED;
    }
    public <A> A getThreadResponse(){
        return threadResponseHolder.getResponse();
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
