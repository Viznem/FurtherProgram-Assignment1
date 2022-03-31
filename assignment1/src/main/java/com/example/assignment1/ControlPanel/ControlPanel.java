package com.example.assignment1.ControlPanel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ControlPanel {
    private final String name; //Menu Name
    private int nOptions; //Number of option in the menu
    private final Map<Integer, Data> options; //Map with all option. The key is the id of the option

    public ControlPanel(String newName) {
        this.name = newName;
        this.nOptions = 1;
        this.options = new HashMap<>();

    }

    public void start() {

        String header = "*" + name + "*\n";

        //Body
        StringBuilder body = new StringBuilder();
        //Options
        for (int j = 1; j <= this.options.size(); j++) {
            if (this.options.containsKey(j)) {
                Data data = this.options.get(j);
                body.append("  ");
                body.append(j).append(")  ").append(data.getName()).append("\u001B[0m\n");
            }
        }

        System.out.print(header + body);

        //Scanner
        Scanner in = new Scanner(System.in);
        int op = -1;

        while (op == -1) {
            System.out.print("$ ");
            try {

                String value = in.nextLine();
                op = Integer.parseInt(value);

                if (!this.options.containsKey(op)) {
                    throw new NumberFormatException();
                }
                this.options.get(op).getCallback().run();
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input");
            }
        }
    }

    public void addOption(String name, CallBack callBack) {
        Data data = new Data(name, callBack);
        this.options.put(this.nOptions, data);
        this.nOptions++;
    }

    /**
     * Create callback function for control panel
     */
    public interface CallBack {
        void run();
    }

    /**
     * store Name of the option and callback function
     */
    static class Data {
        private final String name; //Option name
        private final CallBack callback; //Option callback

        /**
         * Constructor that create Data object, representing an option with a name and callback function
         *
         * @param newName Option name

         * @param newCallbacks Option callback
         */
        Data(String newName, CallBack newCallbacks) {
            this.name = newName;
            this.callback = newCallbacks;
        }

        String getName() {
            return this.name;
        }

        CallBack getCallback() {
            return this.callback;
        }
    }
}
