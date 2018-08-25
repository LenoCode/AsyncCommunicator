package async_communicator;


import async_communicator.object_holder.DataObjectHolder;
import async_communicator.object_holder.parameterized_object.ParameterizedObject;
import async_communicator.thread_holder.ThreadIdentHolder;
import async_communicator.thread_holder.ThreadResponseHolder;
import async_communicator.thread_holder.ThreadStatusHolder;

public class AsyncCommunicator {
    private static AsyncCommunicator asyncCommunicator;

    private final ThreadIdentHolder threadIdentHolder;
    private final ThreadResponseHolder threadResponseHolder;
    private final DataObjectHolder dataObjectHolder;

    private AsyncCommunicator(){
        threadIdentHolder = new ThreadIdentHolder();
        dataObjectHolder = new DataObjectHolder();
        threadResponseHolder = new ThreadResponseHolder();
    }

    public static AsyncCommunicator getAsyncCommunicator() {
        if (asyncCommunicator == null){
            return asyncCommunicator = new AsyncCommunicator();
        }
        return asyncCommunicator;
    }

    public void initNewThread(Long threadId){
        System.out.println("ADDING THREAD   "+threadId);
        threadIdentHolder.addThread(threadId);
    }
    public ThreadStatusHolder getThreadStatusHolder(Long threadId){
        return threadIdentHolder.getThreadStatusHolder(threadId);
    }
    public void threadStarted(){
        ThreadStatusHolder threadStatusHolder = threadIdentHolder.getThreadStatusHolder(Thread.currentThread().getId());
        threadStatusHolder.threadStarted();
    }

    public <A> A waitThreadForResponse(Long threadId){
        ThreadStatusHolder statusHolder = threadIdentHolder.getThreadStatusHolder(threadId);
        waitThread(statusHolder);
        return threadResponseHolder.getResponse(threadId);
    }
    public void waitThreadToFinish(Long threadId){
        ThreadStatusHolder statusHolder = threadIdentHolder.getThreadStatusHolder(threadId);
        waitThread(statusHolder);
    }

    public <A> void addResponseToFinishedThread(Long threadId,A response){
        threadResponseHolder.setResponse(threadId,response);
    }
    public <A> void addResponseToCurrentThread(A response){
        threadResponseHolder.setResponse(Thread.currentThread().getId(),response);
    }
    public void threadFinished(){
        long threadId = Thread.currentThread().getId();
        ThreadStatusHolder statusHolder = threadIdentHolder.getThreadStatusHolder(threadId);
        statusHolder.threadFinished();
        removeIdThread(threadId);
    }
    public void threadFinished(Long threadId){
        ThreadStatusHolder statusHolder = threadIdentHolder.getThreadStatusHolder(threadId);
        statusHolder.threadFinished();
        removeIdThread(threadId);
    }
    public boolean hasThreadFinished(Long threadId){
        ThreadStatusHolder statusHolder = threadIdentHolder.getThreadStatusHolder(threadId);
        return statusHolder.hasThreadFinished();
    }
    public boolean hasThreadStarted(Long threadId){
        ThreadStatusHolder statusHolder = threadIdentHolder.getThreadStatusHolder(threadId);
        return statusHolder.hasThreadStarted();
    }

    public void removeIdThread(Long threadId){
        threadIdentHolder.removeThreadId(threadId);
    }


    public <A> void addParameterizedObject(String id,Object object ){
        ParameterizedObject<A> parameterizedObject = new ParameterizedObject(object,id);
        dataObjectHolder.addObject(parameterizedObject);
    }
    public <A> A getParameterizedObject(String id){
        try {
            return (A) dataObjectHolder.getObject(id).getObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void removeParameterizedObject(String id){
        dataObjectHolder.removeObject(id);
    }
    public void resetDataHolder(){
        dataObjectHolder.reset();
    }

    public void addFlag(Long threadId,String id,boolean flag){
        ThreadStatusHolder statusHolder = threadIdentHolder.getThreadStatusHolder(threadId);
        statusHolder.addFlag(id,flag);
    }
    public void waitForFlag(Long threadId,String id){
        ThreadStatusHolder statusHolder = threadIdentHolder.getThreadStatusHolder(threadId);
        while(!statusHolder.containsFlag(id)){
            continue;
        }
    }
    public boolean checkIfFlagExists(Long threadId,String id){
        ThreadStatusHolder statusHolder = threadIdentHolder.getThreadStatusHolder(threadId);
        return statusHolder.containsFlag(id);
    }
    public boolean getFlag(Long threadId,String id){
        ThreadStatusHolder statusHolder = threadIdentHolder.getThreadStatusHolder(threadId);
        return statusHolder.getFlag(id);
    }


    private void waitThread(ThreadStatusHolder statusHolder){
        if (statusHolder == null){
            return;
        }
        while(!statusHolder.hasThreadFinished()){
            continue;
        }
    }
}
