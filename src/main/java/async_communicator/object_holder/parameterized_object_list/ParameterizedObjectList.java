package async_communicator.object_holder.parameterized_object_list;


import async_communicator.object_holder.parameterized_object.ParameterizedObject;

import java.util.ArrayList;
import java.util.List;


public class ParameterizedObjectList {
    private final List<ParameterizedObject> parameterizedObjectList;

    public ParameterizedObjectList(){
        parameterizedObjectList = new ArrayList<>();
    }

    public void addParameterizedObject(ParameterizedObject parameterizedObject){
        String id = parameterizedObject.getId();
        if (containsId(id)){
            removeParameterizedObject(id);
        }
        parameterizedObjectList.add(parameterizedObject);
    }

    public <A> ParameterizedObject<A> getParameterizedObjectList(String id) throws Exception {
        return searchForObject(id);
    }

    public void removeParameterizedObject(String id){
        try{
            ParameterizedObject parameterizedObject = searchForObject(id);
            parameterizedObjectList.remove(parameterizedObject);
        }catch (NullPointerException e){
            return;
        }
    }

    public void resetList(){
        parameterizedObjectList.clear();
    }


    private boolean containsId(String  id){
        for (ParameterizedObject parameterizedObject : parameterizedObjectList){
            if (parameterizedObject.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    private ParameterizedObject searchForObject(String id){
        for (ParameterizedObject parameterizedObject : parameterizedObjectList){
            if (parameterizedObject.getId().equals(id)){
                return parameterizedObject;
            }
        }
        throw new NullPointerException(String.format("No object with that %s id found",id));
    }
}
