package com.tss.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Feedback implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
    private LocalDate sessionDate;
    private int sessionContent;
    private int queryResolution;
    private int interactivity;
    private int impactfulLearning;
    private int contentDelivery;

    public Feedback() {}

    public Feedback(String name, LocalDate sessionDate, int sessionContent, int queryResolution,
                    int interactivity, int impactfulLearning, int contentDelivery) {
        this.name = name;
        this.sessionDate = sessionDate;
        this.sessionContent = sessionContent;
        this.queryResolution = queryResolution;
        this.interactivity = interactivity;
        this.impactfulLearning = impactfulLearning;
        this.contentDelivery = contentDelivery;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getSessionDate() { return sessionDate; }
    public void setSessionDate(LocalDate sessionDate) { this.sessionDate = sessionDate; }

    public int getSessionContent() { return sessionContent; }
    public void setSessionContent(int sessionContent) { this.sessionContent = sessionContent; }

    public int getQueryResolution() { return queryResolution; }
    public void setQueryResolution(int queryResolution) { this.queryResolution = queryResolution; }

    public int getInteractivity() { return interactivity; }
    public void setInteractivity(int interactivity) { this.interactivity = interactivity; }

    public int getImpactfulLearning() { return impactfulLearning; }
    public void setImpactfulLearning(int impactfulLearning) { this.impactfulLearning = impactfulLearning; }

    public int getContentDelivery() { return contentDelivery; }
    public void setContentDelivery(int contentDelivery) { this.contentDelivery = contentDelivery; }
}
