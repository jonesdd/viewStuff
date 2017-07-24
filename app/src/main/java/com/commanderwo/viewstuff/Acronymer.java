package com.commanderwo.viewstuff;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by jonesdd on 7/24/17.
 */

public class Acronymer {
    private String mRootWord;
    private ArrayList<String> mAcronym = new ArrayList<>();
    private Map<String, ArrayList<String>> mWordList = new HashMap<>();
    public Acronymer(){
        String emptyRoot = null;
        setRoot(emptyRoot);
    }
    public Acronymer(String word){
        setRoot(word);
    }


    public  void dictonaryBuilder(String aFileName){

        try {
            FileReader file = new FileReader(aFileName);
            BufferedReader br = new BufferedReader(file);
            String line;
            while((line = br.readLine())!=null){
                if(line.length()>=1) {
                    String lineFirstLeter = new String(line.substring(0, 1));

                    if (mWordList.containsKey(lineFirstLeter)) {
                        mWordList.get(lineFirstLeter).add(line);
                    } else {

                        mWordList.put(lineFirstLeter, new ArrayList<String>());
                        mWordList.get(lineFirstLeter).add(line);
                    }
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }


    }
    public  void dictonaryBuilder(Context ctx, int resourceID){

        try {
            InputStream is = ctx.getResources().openRawResource(resourceID);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while((line = br.readLine())!=null){
                if(line.length()>=1) {
                    String lineFirstLeter = new String(line.substring(0, 1));

                    if (mWordList.containsKey(lineFirstLeter)) {
                        mWordList.get(lineFirstLeter).add(line);
                    } else {

                        mWordList.put(lineFirstLeter, new ArrayList<String>());
                        mWordList.get(lineFirstLeter).add(line);
                    }
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }


    }

    public void setRoot(String newRoot){
        mRootWord = newRoot.toLowerCase();
        mAcronym.clear();
    }

    public String getRoot(){
        return mRootWord;
    }
    public ArrayList<String> getmAcronym(){
        return mAcronym;
    }

    public ArrayList<String> generateNewAcronym(){

        for(int i = 0; i < mRootWord.length(); i++){
            String leter = new String(mRootWord.substring(i,i+1));
            if(mWordList.containsKey(leter)) {
                ArrayList<String> letterList = mWordList.get(leter);
                Random random = new Random();
                mAcronym.add(letterList.get(random.nextInt(letterList.size())));
            }else{
                mAcronym.add("");
            }

        }
        return  mAcronym;
    }
}
