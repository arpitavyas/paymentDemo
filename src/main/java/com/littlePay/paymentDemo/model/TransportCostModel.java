package com.littlePay.paymentDemo.model;

import javax.persistence.*;

@Entity(name = "TBL_TRANSPORT_COST")
public class TransportCostModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pair_id;

    @Column(name="pair_name")
    private String pairName;

    @Column(name="pair_cost")
    private Float pairCost;

    public int getPair_id() {
        return pair_id;
    }

    public void setPair_id(int pair_id) {
        this.pair_id = pair_id;
    }

    public String getPairName() {
        return pairName;
    }

    public void setPairName(String pairName) {
        this.pairName = pairName;
    }

    public Float getPairCost() {
        return pairCost;
    }

    public void setPairCost(Float pairCost) {
        this.pairCost = pairCost;
    }

    @Override
    public String toString() {
        return "TransportCostModel{" +
                "pair_id=" + pair_id +
                ", pairName='" + pairName + '\'' +
                ", pairCost=" + pairCost +
                '}';
    }
}
