package by.kunavich.task_tread;

import by.kunavich.task_tread.data.DataException;
import by.kunavich.task_tread.data.VanParser;
import by.kunavich.task_tread.data.dataAcquirer.DataAcquirer;
import by.kunavich.task_tread.data.dataAcquirer.FileDataAcquirer;
import by.kunavich.task_tread.model.Base;
import by.kunavich.task_tread.model.Van;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Main {

    public static final String FILE_NAME = "text.txt";
    private Logger logger = LogManager.getLogger(Main.class);


    public static void main(String args[]) throws InterruptedException {

        DataAcquirer dataAcquirer = new FileDataAcquirer(FILE_NAME);
        VanParser vanParser=new VanParser();
        Base base = Base.getInstance();
        try {
            for (int i = 0; i < 9; i++) {
                Van van = vanParser.parse(dataAcquirer.getData());
                base.addVan(van);
            }
        } catch (DataException e) {
            e.printStackTrace();
        }

        base.start();
    }

}
