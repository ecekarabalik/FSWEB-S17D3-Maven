package com.workintech.zoo.entity;

public class Koala {
    private Integer id;
    private String name;
    private double weight;     // primitive -> assertEquals(double,double) net seçilir
    private double sleepHour;  // <-- primitive double (kritik)
    private String gender;

    public Koala() {}

    public Koala(int id, String name, double weight, int sleepHour, String gender) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.sleepHour = sleepHour;
        this.gender = gender;
    }

    // Test bazen double sleepHour geçiriyor
    public Koala(int id, String name, double weight, double sleepHour, String gender) {
        this(id, name, weight, (int) Math.round(sleepHour), gender);
        this.sleepHour = sleepHour; // zaten double olduğu için doğrudan atanır
    }

    // Getters / Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public double getSleepHour() { return sleepHour; }         // <-- primitive double
    public void setSleepHour(double sleepHour) { this.sleepHour = sleepHour; } // double setter
    public void setSleepHour(Integer sleepHour) {               // int/Integer çağrıları da uyusun
        this.sleepHour = sleepHour == null ? 0 : sleepHour;
    }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
}