package top.ks.yonyou.db.test;

import java.io.Serializable;

public class ToolbarItem implements Serializable {

    private String command;

    private String text;

    private String text_resid;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText_resid() {
        return text_resid;
    }

    public void setText_resid(String text_resid) {
        this.text_resid = text_resid;
    }
}
