package by.kunavich.task_tread.data;

public class DataException extends Exception{
    public DataException(String message,Exception e)    {
        super(message,e);
    }
}
