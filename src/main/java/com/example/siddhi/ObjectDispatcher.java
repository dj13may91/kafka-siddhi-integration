package com.example.siddhi;

import com.example.kafka.Consumer;
import com.example.kafka.Person;
import io.siddhi.core.SiddhiAppRuntime;
import io.siddhi.core.SiddhiManager;
import io.siddhi.core.event.Event;
import io.siddhi.core.stream.input.InputHandler;
import io.siddhi.core.stream.output.StreamCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

@Service
public class ObjectDispatcher {

    @Autowired
    Consumer consumer;

    private SiddhiManager siddhiManager = new SiddhiManager();
    private String INPUT_STREAM = "InputStream";
    public String OUTPUT_STREAM = "OutputStream";

    //This query adds last three inputs to the stream
    private String siddhiApp = "" +
            "define stream " + INPUT_STREAM + "( name string, age int); " +
            "@info(name = 'query1') " +
            "from " + INPUT_STREAM + //"#window.length(3) " +
            " select name, age " +
            " insert into " + OUTPUT_STREAM + ";";

    private SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(siddhiApp);
    private InputHandler inputHandler;

    public ObjectDispatcher() {
        System.out.println("Dispatcher constructor");
        //Get InputHandler to push events into Siddhi
        this.inputHandler = siddhiAppRuntime.getInputHandler(INPUT_STREAM);
        //Start processing
        this.getSiddhiAppRuntime().start();

        //Adding callback to retrieve output events from stream
        //Note: adding this call back should be once, else it keeps on stacking up!
        this.getSiddhiAppRuntime().addCallback(OUTPUT_STREAM, new StreamCallback() {
            @Override
            public void receive(Event[] events) {
//                System.out.println("events received: " + events.length);
                for (Event e : events) {
                    System.out.println("Siddhi generated event:" + e);
                    consumer.sendToConsumer((Person) e.getData()[0]);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                //To convert and print event as a map
                //EventPrinter.print(toMap(events));
            }
        });
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public SiddhiAppRuntime getSiddhiAppRuntime() {
        return siddhiAppRuntime;
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destroying siddhi");

        siddhiAppRuntime.shutdown();

        //Shutdown Siddhi Manager
        siddhiManager.shutdown();
    }

}
