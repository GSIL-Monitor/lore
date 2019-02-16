package mng.model.common;

import mng.model.base.Base;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
@Entity
@Table(name="toast")
public class Toast extends Base {
    public static final Boolean TOAST_VALID_YES = true;
    public static final Boolean TOAST_VALID_NO = false;

    private String code;
    private String title;
    private String content;
    private String level;
    private Boolean valid;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}
