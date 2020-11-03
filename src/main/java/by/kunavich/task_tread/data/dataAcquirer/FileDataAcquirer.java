/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.kunavich.task_tread.data.dataAcquirer;

import by.kunavich.task_tread.data.DataException;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Lord
 */
public class FileDataAcquirer implements DataAcquirer{

    private String fileName ;


    private  int iterator =0;

    public FileDataAcquirer(String fileName){
        this.fileName=fileName;
    }

    @Override
    public String getData() throws DataException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            for (int i = 0; i < iterator; i++) {
                reader.readLine();
            }
            iterator++;
            String data = (reader.readLine());
            return data;
        }
        catch (IOException e) {
            throw new DataException(e.getMessage(), e);
        }


    }
}
