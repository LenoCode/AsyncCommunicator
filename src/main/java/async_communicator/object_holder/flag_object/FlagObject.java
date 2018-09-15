package async_communicator.object_holder.flag_object;

import java.util.HashMap;
import java.util.Map;

public class FlagObject {
    private final Map<String,Boolean> flags;

    public FlagObject() {
        this.flags = new HashMap<>();
    }

    public void addFlag(String id,boolean flag){
        if (checkIfFlagExist(id)){
            flags.replace(id,flag);
        }else{
            flags.put(id,flag);
        }
    }
    public boolean checkIfFlagExist(String id){
        return flags.containsKey(id);
    }
    public void removeFlag(String id){
        if (flags.containsKey(id)){
            flags.remove(id);
        }
    }
    public boolean getFlag(String id){
        if (flags.containsKey(id)){
            boolean flag = flags.get(id);
            return flag;
        }else{
            return false;
        }
    }
}
