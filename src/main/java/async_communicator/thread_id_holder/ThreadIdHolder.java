package async_communicator.thread_id_holder;

public class ThreadIdHolder {

    private final long threadId;

    public ThreadIdHolder(long threadId) {
        this.threadId = threadId;
    }

    public long getThreadId() {
        return threadId;
    }

}
