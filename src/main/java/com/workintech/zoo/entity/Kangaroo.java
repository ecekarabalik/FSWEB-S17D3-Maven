package com.workintech.zoo.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Kangaroo {
    private Integer id;
    private String name;
    private double height;
    private double weight;
    private String gender;
    private boolean isAggressive;

    public Kangaroo(int id, String name, double height, double weight, String gender, boolean isAggressive) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.isAggressive = isAggressive;
    }

    // Test özellikle getIsAggressive() bekliyor
    public boolean getIsAggressive() { return isAggressive; }
    public void setIsAggressive(boolean val) { this.isAggressive = val; }
}
