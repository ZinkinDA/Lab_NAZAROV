package com.example.fsm;

import com.example.fsm.FSM.Roulette;
import com.example.fsm.FSM.enums.Events;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FsmRouletkaApplication {

    public static void main(String[] args) throws InterruptedException {

        Roulette roulette = Roulette.getInstance();
        roulette.getStep(Events.START,(byte)40);


//        SpringApplication.run(FsmRouletkaApplication.class, args);
    }

}
