package Model;

public class TargetModel {
    String Target;
    String Done;
    String LessValueToAchieve;
    String VSTarget;
    String LastYear;
    String Trend;

    public TargetModel() {
    }

    public TargetModel(String target, String done, String lessValueToAchieve, String VSTarget, String lastYear, String trend) {
        Target = target;
        Done = done;
        LessValueToAchieve = lessValueToAchieve;
        this.VSTarget = VSTarget;
        LastYear = lastYear;
        Trend = trend;
    }


    public String getTarget() {
        return Target;
    }

    public void setTarget(String target) {
        Target = target;
    }

    public String getDone() {
        return Done;
    }

    public void setDone(String done) {
        Done = done;
    }

    public String getLessValueToAchieve() {
        return LessValueToAchieve;
    }

    public void setLessValueToAchieve(String lessValueToAchieve) {
        LessValueToAchieve = lessValueToAchieve;
    }

    public String getVSTarget() {
        return VSTarget;
    }

    public void setVSTarget(String VSTarget) {
        this.VSTarget = VSTarget;
    }

    public String getLastYear() {
        return LastYear;
    }

    public void setLastYear(String lastYear) {
        LastYear = lastYear;
    }

    public String getTrend() {
        return Trend;
    }

    public void setTrend(String trend) {
        Trend = trend;
    }
}
