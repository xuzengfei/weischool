package com.bugframework.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Xuzengfei
 * @version v1.0
 * @since 2016/12/13.
 */
public class DateTag extends TagSupport {
    private Long value;
    private String pattern;

    /**
     * 获取{@link DateTag#value}
     */
    public Long getValue() {
        return value;
    }

    /**
     * 设置{@link DateTag#value}
     */
    public void setValue(Long value) {
        this.value = value;
    }

    /**
     * 获取{@link DateTag#pattern}
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * 设置{@link DateTag#pattern}
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public int doStartTag() throws JspException {
        if (value!=null&&value!=0) {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(value);
            SimpleDateFormat dateformat = new SimpleDateFormat(pattern);
            String s = dateformat.format(c.getTime());
            try {
                pageContext.getOut().write(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.doStartTag();
    }
}
