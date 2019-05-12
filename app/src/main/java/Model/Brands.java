package Model;

public class Brands {

    private String location;
    private String target;
    private String mtd;
    private String brand_name;
    private String trend;
    private String less_value;

    public Brands() {
    }

    public Brands(String location, String target, String mtd, String brand_name, String trend, String less_value) {
        this.location = location;
        this.target = target;
        this.mtd = mtd;
        this.brand_name = brand_name;
        this.trend = trend;
        this.less_value = less_value;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getMtd() {
        return mtd;
    }

    public void setMtd(String mtd) {
        this.mtd = mtd;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getTrend() {
        return trend;
    }

    public void setTrend(String trend) {
        this.trend = trend;
    }

    public String getLess_value() {
        return less_value;
    }

    public void setLess_value(String less_value) {
        this.less_value = less_value;
    }
}
