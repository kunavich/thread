package by.kunavich.task_tread.data;

import by.kunavich.task_tread.model.Van;

public class VanParser {
    public Van parse(String line){
        String[] strings = line.split(" ");
        int id = Integer.parseInt(strings[1]);
        boolean perishableGoods = Boolean.parseBoolean(strings[0]);
        Van van= new Van(perishableGoods,id);
        return van;
    }
}
