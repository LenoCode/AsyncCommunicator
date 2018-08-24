package async_communicator.thread_holder;

import java.util.HashMap;
import java.util.Map;

public class ThreadResponseHolder{
    private final Map<Long,Object> threadResponses = new HashMap();

    public ThreadResponseHolder(){

    }
    public <B> B getResponse(long id) {
        if (threadResponses.containsKey(id)){
            B response = (B) threadResponses.get(id);
            threadResponses.remove(id);
            return response;
        }else{
            return null;
        }
    }

    public <B> void setResponse(long id,B response) {
        threadResponses.put(id,response);
    }
}
