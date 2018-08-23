package async_communicator;


import async_communicator.object_holder.DataObjectHolder;
import async_communicator.object_holder.parameterized_object.ParameterizedObject;
import async_communicator.thread_holder.ThreadIdentHolder;
import async_communicator.thread_holder.ThreadStatusHolder;

public class AsyncCommunicator {
    private static AsyncCommunicator asyncCommunicator;

    private final ThreadIdentHolder threadIdentHolder;
    private final DataObjectHolder dataObjectHolder;

    private AsyncCommunicator(){
        threadIdentHolder = new ThreadIdentHolder();
        dataObjectHolder = new DataObjectHolder();
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
        ThreadStatusHolder threadStatusHolder = threadIdentHolder.getThreadStatusHolder(threadId);

        System.out.println(threadStatusHolder.hasThreadStarted() +" has trhad started     ");
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
        return statusHolder.getThreadResponse();
    }
    public void waitThreadToFinish(Long threadId){
        ThreadStatusHolder statusHolder = threadIdentHolder.getThreadStatusHolder(threadId);
        waitThread(statusHolder);
    }

    public void addResponseToFinishedThread(Long threadId, Object response){
        ThreadStatusHolder statusHolder = threadIdentHolder.getThreadStatusHolder(threadId);
        statusHolder.addResponseToThread(response);
    }
    public void addResponseToCurrentThread(Object response){
        ThreadStatusHolder statusHolder = threadIdentHolder.getThreadStatusHolder(Thread.currentThread().getId());
        statusHolder.addResponseToThread(response);
    }
    public void threadFinished(){
        System.out.println("THREAD FINISHED   "+Thread.currentThread().getId());
        ThreadStatusHolder statusHolder = threadIdentHolder.getThreadStatusHolder(Thread.currentThread().getId());
        statusHolder.threadFinished();
        //OVO TREBA PROVJERITI GDJE I KADA BRISATI OVAJ THREAD ID,OVA METODA SE POZIVA U THREAD CALLERU, PA ZATO MI USPJEVA PROCI,ODNOSNO ZATO USPJEVAM DOBITI RESPONSE
        removeIdThread(Thread.currentThread().getId());
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
        while(!statusHolder.hasThreadFinished()){
            continue;
        }
    }
}
