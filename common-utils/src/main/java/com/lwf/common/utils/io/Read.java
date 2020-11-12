package com.lwf.common.utils.io;

import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2020-11-12 19:32
 */
public class Read {
    public List<String> readCSV()  {

        List<String> list=new ArrayList<>();
        FileReader fileReader=null;
        BufferedReader bufferedReader=null;
        try {


//            File file = new ClassPathResource(contentFileName).getFile();
//            fileReader = new FileReader(file);
            String contentFileName=null;
            InputStream inputStream = new ClassPathResource(contentFileName).getInputStream();

            bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
            String row=null;
            while ((row= bufferedReader.readLine())!=null){
                list.add(row);
            }


        }catch (Exception e){
            System.out.println("读取csv文件出错"+e);
        }finally {
            if (bufferedReader!=null){

                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (fileReader!=null){

                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;

    }
}
