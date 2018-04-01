package indi.dwq.orderingSys.data.pojo;

public class Eatery {
    private Integer id;

    private String eaterName;

    private String address;

    private String des;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEaterName() {
        return eaterName;
    }

    public void setEaterName(String eaterName) {
        this.eaterName = eaterName == null ? null : eaterName.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }
}