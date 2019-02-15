package mng.enums;

public enum ApplyState {
    /** 申请状态 */
    UNREIMBURSE("01", "未报销"),
    HADREIMBURSE("02", "已报销");
    private final String code;
    private final String name;
    ApplyState(String code, String name){
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
