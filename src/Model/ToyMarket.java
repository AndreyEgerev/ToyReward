package Model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

public class ToyMarket {
    private ArrayList<Toy> toyList;
    Queue<Toy> queueDropToy;

    public ToyMarket(ArrayList<Toy> toyList) {
        this.toyList = toyList;
        this.queueDropToy = new ArrayDeque<>();
    }

    public ToyMarket() {
        this.toyList = new ArrayList<>();
        this.queueDropToy = new ArrayDeque<>();
    }

    public ArrayList<Toy> getToyList() {
        return toyList;
    }

    public String createRewardToy(){
        AllChanceToy chanceToy = new AllChanceToy(toyList);
        Random random = new Random();
        double currentChange = random.nextDouble();
        queueDropToy.add(toyList.get(chanceToy.selectSector(currentChange)));
        return "Выпал(а) " + toyList.get(chanceToy.selectSector(currentChange));
    }
    public String showToys(){
        StringBuilder toys = new StringBuilder();
        if (toyList!=null) {
            if (!toyList.isEmpty()) {
                toys.append("Все доступные игрушки\n");
                int index = 1;
                for (Toy toy :
                        toyList) {
                    toys.append(index).append(". ").append(toy.toString()).append("\n");
                    index++;
                }

                return toys.toString();
            }else {
                toys.append("В магазине игрушек нет");
                return toys.toString();
            }
        }
        else {
            throw new NullPointerException("Ошибка с игрушками. Их украли. Мы работаем над этим");
        }
    }
    public String showRewards(){
        StringBuilder rewards = new StringBuilder();
        if (queueDropToy!=null) {
            if (!queueDropToy.isEmpty()) {
                rewards.append("Призы\n");
                int index = 1;
                for (Toy toy :
                        queueDropToy) {
                    rewards.append(index).append(". ").append(toy.toString()).append("\n");
                    index++;
                }
                return rewards.toString();
            }else {
                rewards.append("Призов нет");
                return rewards.toString();
            }
        }
        else {
            throw new NullPointerException("Ошибка с призами. Их украли");
        }
    }
    public void updateChanceDropToy() throws NullPointerException{
        int weightToys = 0;
        if (!toyList.isEmpty() || (toyList != null)){
            for (Toy toy:
                 toyList) {
                weightToys += toy.getWeightDrop();
            }
            for (Toy toy:
                 toyList) {
                toy.setChanceDrop(toy.getUserWeightDrop()/weightToys);// for looking in % -> *100
            }
        }else {
            throw new NullPointerException("List empty. Update chance fail");
        }
    }
    public String addToy(int id, String name, int weightDrop, int qt){
        toyList.add(new Toy(id,name,weightDrop,qt));
        try {
            updateChanceDropToy();
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        return "Игрушка " + name + " добавлена";
    }
    public String setupToyWeightDrop(int index, int newValue){
        try {
            int oldValue = toyList.get(index).getWeightDrop();
            toyList.get(index).setWeightDrop(newValue);
            updateChanceDropToy();
            return "Значение веса игрушки " + toyList.get(index).getNameToy() +" изменено с " + oldValue + " на " + newValue;
        }catch (IndexOutOfBoundsException | NullPointerException e){
            throw new IndexOutOfBoundsException("Некоректный номер игрушки");
        }
    }

    public String setupToyQuantity(int index, int newValue){
        try {
            int oldValue = toyList.get(index).getQuantity();
            toyList.get(index).setQuantity(newValue);
            return "Количество игрушек " + toyList.get(index).getNameToy() +" изменено с " + oldValue + " на " + newValue;
        }catch (IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("Некоректный номер игрушки");
        }
    }

    public String removeToy(int index){
        try {
            String name = toyList.get(index).getNameToy();
            toyList.remove(index);
            updateChanceDropToy();
            return "Игрушка " + name +" удалена";
        }catch (IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("Некоректный номер игрушки");
        }
    }

    public String getToy(){
        StringBuilder rewardToy = new StringBuilder();
        if (queueDropToy!=null) {
            if (!queueDropToy.isEmpty()) {
                Toy toy = queueDropToy.poll();
                rewardToy.append(toy.toString()).append("\n");
                return rewardToy.toString();
            }else {
                rewardToy.append("Призов нет");
                return rewardToy.toString();
            }
        }
        else {
            throw new NullPointerException("Ошибка с призами. Их украли");
        }

    }

}
