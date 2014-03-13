package org.daybreak.coccinella.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;

/**
 *
 * @author Alan
 */
@Entity
@SecondaryTable(name = "T_HTML_PARSERS", pkJoinColumns = @PrimaryKeyJoinColumn(name = "PARSER_ID"))
public class HtmlParser extends Parser {
    
    @Column(name = "NAME_KEY", table="T_HTML_PARSERS")
    private String nameKey;
    
    @Column(name = "XPATH", table="T_HTML_PARSERS")
    private String xpath;
    
    @Column(name = "REGEX", table="T_HTML_PARSERS")
    private String regex;

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }
    
    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }
}
