package indi.dwq.orderingSys.data.pojo;

public class Food {
    private Integer id;

    private String name;

    private Float price;

    private String des;

    private Integer eateryId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }

    public Integer getEateryId() {
        return eateryId;
    }

    public void setEateryId(Integer eateryId) {
        this.eateryId = eateryId;
    }
}