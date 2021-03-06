package indi.dwq.orderingSys.data.pojo;
/*
*  private Integer id;

    private String areaname;

    private Integer parentid;

    private String shortname;

    private String lng;

    private String lat;

    private Boolean level;

    private String position;

    private Byte sort;
* */
public class City {
    private Integer id;

    private String areaname;

    private Integer parentid;

    private String shortname;

    private String lng;

    private String lat;

    private Boolean level;

    private String position;

    public int hashCode()
    {

        return this.id;
    }
    public boolean equals(Object obj)
    {
        if(id.equals(obj))return true;
        if(!(obj instanceof City))
            return false;
        City city = (City)obj;
        return this.id.equals(city.id);// && this.age == p.age;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname == null ? null : areaname.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname == null ? null : shortname.trim();
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng == null ? null : lng.trim();
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }

    public Boolean getLevel() {
        return level;
    }

    public void setLevel(Boolean level) {
        this.level = level;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }
}