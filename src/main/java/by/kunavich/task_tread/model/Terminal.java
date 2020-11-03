package by.kunavich.task_tread.model;

public class Terminal implements Runnable {
    private Van currentVan;

    public void setCurrentVan(Van currentVan) {
        this.currentVan = currentVan;
    }

    public void run() {
        try {
            System.out.println("        Van "+currentVan.getId()+" starts work");
            currentVan.work();
            System.out.println("        Van "+currentVan.getId()+" ends work");
          //  Base base = Base.getInstance();
          //  base.relise();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}