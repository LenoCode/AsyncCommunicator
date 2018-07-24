package async_communicator.object_holder.parameterized_object;

public class ParameterizedObject<A> {

    private final A object;
    private final String id;

    public ParameterizedObject(A object,String id){
        this.object = object;
        this.id = id;
    }
    public A getObject() {
        return object;
    }

    public String getId(){
        return id;
    }

}
