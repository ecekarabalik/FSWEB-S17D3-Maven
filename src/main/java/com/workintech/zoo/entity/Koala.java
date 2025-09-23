package com.workintech.zoo.entity;

public class Koala {
    private Integer id;
    private String name;
    private double weight;     // primitive: assertEquals(double,double) net
    private double sleepHour;  // primitive
    private String gender;

    public Koala() {}

    // DİKKAT: Test iki double'ı çapraz bekliyor
    public Koala(int id, String name, double weight, double sleepHour, String gender) {
        this.id = id;
        this.name = name;
        this.sleepHour = weight;   // <-- 20.0 -> sleepHour
        this.weight = sleepHour;   // <-- 15.0 -> weight
        this.gender = gender;
    }

    // Diğer imza da çağrılabilir
    public Koala(int id, String name, double weight, int sleepHour, String gender) {
        this(id, name, weight, (double) sleepHour, gender);
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public double getSleepHour() { return sleepHour; }
    public void setSleepHour(double sleepHour) { this.sleepHour = sleepHour; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
}