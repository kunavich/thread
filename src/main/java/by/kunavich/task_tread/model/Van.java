package by.kunavich.task_tread.model;

import java.util.concurrent.TimeUnit;

public class Van {
    private long timeToWork = 10L;
    private boolean perishableGoods;
    private int id;
    private TimeUnit time;

    public Van (boolean perishableGoods,int id){
        this.perishableGoods = perishableGoods;
        this.id = id;
        time= TimeUnit.SECONDS;
    }

    public int getId() {
        return id;
    }

    public boolean isPerishableGoods() {
        return perishableGoods;
    }

    public void work() throws InterruptedException {
        time.sleep(timeToWork);
    }
}
