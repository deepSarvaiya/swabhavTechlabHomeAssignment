package com.foodordering.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeliveryService {
    private final List<String> agents = new ArrayList<>(List.of("aman", "baman", "chaman"));

    public void addAgent(String name) {
        agents.add(name);
        System.out.println("Agent added: " + name);
    }

    public void removeAgent(String name) {
        if (agents.remove(name)) {
            System.out.println("Agent removed: " + name);
        } else {
            System.out.println("Agent not found: " + name);
        }
    }

    public void viewAgents() {
        System.out.println("\n--- Delivery Agents ---");
        if (agents.isEmpty()) {
            System.out.println("No agents available.");
        } else {
            int i = 1;
            for (String agent : agents) {
                System.out.println(i++ + ". " + agent);
            }
        }
    }

    public String assignAgent() {
        if (agents.isEmpty()) return "No agent available";
        Random rand = new Random();
        int index = rand.nextInt(agents.size());
        String agent = agents.get(index);
        System.out.println("Delivery Partner Assigned: " + agent);
        return agent;
    }
}
