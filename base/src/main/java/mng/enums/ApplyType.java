package mng.enums;

public enum ApplyType {
    /** 餐费 */
    MEAL("01", "餐费"),
    /** 出租 */
    CAR("02" ,"出租"),
    /** 油费 */
    OIL("03", "油费");
    private final String code;
    private final String name;
    ApplyType(String code, String name){
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
