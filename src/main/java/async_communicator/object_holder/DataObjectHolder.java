package async_communicator.object_holder;


import async_communicator.object_holder.parameterized_object.ParameterizedObject;
import async_communicator.object_holder.parameterized_object_list.ParameterizedObjectList;

public class DataObjectHolder {
    private final ParameterizedObjectList parameterizedObjectList;

    public DataObjectHolder(){
        this.parameterizedObjectList = new ParameterizedObjectList();
    }
    public void addObject(ParameterizedObject parameterizedObject){
        parameterizedObjectList.addParameterizedObject(parameterizedObject);
    }
    public void removeObject(String id){
        parameterizedObjectList.removeParameterizedObject(id);
    }
    public <A> ParameterizedObject<A> getObject(String id) throws Exception {
        ParameterizedObject<A> object = parameterizedObjectList.getParameterizedObjectList(id);
        return object;
    }
    public void reset(){
        parameterizedObjectList.resetList();
    }


}
