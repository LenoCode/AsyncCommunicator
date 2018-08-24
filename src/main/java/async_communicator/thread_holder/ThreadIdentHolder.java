package async_communicator.thread_holder;

import java.util.HashMap;

public class ThreadIdentHolder {
    private final HashMap<Long,ThreadStatusHolder> activatedThread;

    public ThreadIdentHolder(){
        activatedThread = new HashMap<>();
    }

    public void addThread(Long threadId){
        activatedThread.put(threadId,new ThreadStatusHolder());
    }

    public ThreadStatusHolder getThreadStatusHolder(Long threadId){
        try{
            return activatedThread.get(threadId);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
    public void removeThreadId(Long threadId){
        activatedThread.remove(threadId);
    }

    @Override
    public String toString() {
        return activatedThread.toString();
    }
}
