package com.example.fsm.FSM;

import com.example.fsm.FSM.enums.Events;
import com.example.fsm.FSM.enums.States;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Roulette {
    private static final String filename = "roulette.txt";
    private static Roulette roulette;
    private byte value = 0;

    private byte userValue = 0;
    private States states = States.READY;

    public static Roulette getRoulette() {
        return roulette;
    }

    public static void setRoulette(Roulette roulette) {
        Roulette.roulette = roulette;
    }

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        final int B = 2396403;
        final int A = 8253729;
        final int C = 100;
        this.value = (byte) ((A * value + B) % C);
    }

    public byte getUserValue() {
        return userValue;
    }

    public void setUserValue(byte userValue) {
        this.userValue = userValue;
    }

    private Roulette() {
        value = 0;
        this.states = States.READY;
    }

    public static Roulette getInstance(){
        if(roulette == null){
            return new Roulette();
        }
        return roulette;
    }

    public String getStep(Events events,byte userValue) throws InterruptedException {
        if(userValue > 100 && userValue < 0){
            return "Не верный ввод пользователя";
        }
        if(!events.equals(Events.START)){
            return "Не верный Event";
        }
        Boolean result = false;
        StringBuilder stringBuilder = new StringBuilder();
        switch (events) {
            case START :
                if(states.equals(States.READY)){
                    states = States.RUNNING;
                    this.setUserValue(userValue);
                    this.setValue(userValue);
                }
                System.out.println("SPIN THE DRUM");
                Thread.sleep(5000);
            case CHECK:
                if(states.equals(States.RUNNING)) {
                    states = States.СHECK;
                    if(value == userValue){
                        result = true;
                        stringBuilder.append("Mashine send : ").append(value)
                                .append("\nUser input : ").append(userValue)
                                .append("\nAnswer : ").append(result).append("\n");
                    } else {
                        stringBuilder.append("Mashine send : ").append(value)
                                .append("\nUser input : ").append(userValue)
                                .append("\nAnswer : ").append(result).append("\n");
                    }
                    System.out.println("CHECK");
                    Thread.sleep(1000);
                }
                states = States.PRINT;
            case PRINT_CHECK:
                if(states.equals(States.PRINT)) {
                    try(Writer writer = new FileWriter(filename,true)) {
                        writer.append(stringBuilder);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("PRINT CHECK");
                    Thread.sleep(1000);
                    System.out.println(stringBuilder);
                    states = States.RUNNING;
                    return stringBuilder.toString();
                }
        }
        return null;
    }



    public String toString(){
        return "Value = " + value + "\nStatement = "+ states;
    }
}
