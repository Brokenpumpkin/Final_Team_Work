package com.hunter.computerhunter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class computerService {
    @Autowired
    private computerRepository computerrepository;

    /**
     * 关键字匹配
     */
    public List<computer> keywordSearch(String one, String two, String three) {

        List<computer> list1 = new ArrayList<computer>();
        list1 = computerrepository.findAllByCharacterOneOrCharacterTwoOrCharacterThree(one, one, one);

        //无关键字1
        if(list1.isEmpty()){
            list1 = computerrepository.findAllByCharacterOneOrCharacterTwoOrCharacterThree(two,two,two);
            //无关键字1、2
            if(list1.isEmpty()) {
                list1 = computerrepository.findAllByCharacterOneOrCharacterTwoOrCharacterThree(three,three,three);
                //无关键字1、2、3
                if(list1.isEmpty()) {
                    return null;
                }
                //只有关键字3
                else {
                    return list1;
                }
            }
            //有关键字2,无关键字1，关键字3未知
            else {
                list1 = computerrepository.findAllByCharacterOneOrCharacterTwoOrCharacterThree(two,two,two);
                List<computer> list2 = new ArrayList<computer> (list1);
                for(int i = list1.size() - 1; i >= 0 ; i--) {
                    computer tempComputer = (computer)list1.get(i);
                    if(!tempComputer.getCharacterOne().equals(three) && !tempComputer.getCharacterTwo().equals(three) && !tempComputer.getCharacterThree().equals(three)) {
                        list1.remove(i);
                    }
                }
                //有关键字2,无关键字1、3
                if(list1.isEmpty()) {
                    return list2;
                }
                //有关键字2、3,无关键字1
                else {
                    return list1;
                }
            }
        }
        //有关键字1，关键字2、3未知
        else {
            List<computer> list2 = new ArrayList<computer> (list1);
            //在list1中删除没有关键字2的computer
            for(int i = list1.size() - 1; i >= 0 ; i--) {
                computer tempComputer = (computer)list1.get(i);
                if(!tempComputer.getCharacterOne().equals(two) && !tempComputer.getCharacterTwo().equals(two) && !tempComputer.getCharacterThree().equals(two)) {
                    list1.remove(i);
                }
            }
            //有关键字1，无关键字2，关键字3未知
            if(list1.isEmpty()) {
                List<computer> list3 = new ArrayList<computer> (list2);
                //在list1中删除没有关键字3的computer
                for(int i = list2.size() - 1; i >= 0 ; i--) {
                    computer tempComputer = (computer)list2.get(i);
                    if(!tempComputer.getCharacterOne().equals(three) && !tempComputer.getCharacterTwo().equals(three) && !tempComputer.getCharacterThree().equals(three)) {
                        list2.remove(i);
                    }
                }
                //有关键字1，无关键字2、3
                if(list2.isEmpty()) {
                    return list3;
                }
                //有关键字1、3，无关键字2
                else {
                    return list2;
                }
            }
            //有关键字1、2，关键字3未知
            else {
                List<computer> list3 = new ArrayList<computer> (list2);
                //在list1中删除没有关键字3的computer
                for(int i = list1.size() - 1; i >= 0 ; i--) {
                    computer tempComputer = (computer)list1.get(i);
                    if(!tempComputer.getCharacterOne().equals(three) && !tempComputer.getCharacterTwo().equals(three) && !tempComputer.getCharacterThree().equals(three)) {
                        list1.remove(i);
                    }
                }
                //有关键字1、2、3
                if(!list1.isEmpty()) {
                    return list1;
                }
                //只有关键字1、2
                else {
                    return list3;
                }
            }
        }
    }

    /**
     * 硬件匹配算法
     */

}
