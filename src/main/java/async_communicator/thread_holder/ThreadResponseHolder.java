package async_communicator.thread_holder;

public class    ThreadResponseHolder{
    private Object response;

    protected ThreadResponseHolder(){

    }

    public <B> B getResponse() {
        return (B) response;
    }

    public <B> void setResponse(B response) {
        this.response = response;
    }
}
