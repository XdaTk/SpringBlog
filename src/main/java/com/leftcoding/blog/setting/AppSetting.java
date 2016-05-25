package com.leftcoding.blog.setting;

import com.domingosuarez.boot.autoconfigure.jade4j.JadeHelper;
import org.springframework.stereotype.Service;

/**
 * Created by XdaTk on 16/5/22.
 */
@JadeHelper("App")
@Service
public class AppSetting {

    private String siteName = "SpringBlog";
    private String siteSlogan = "An interesting place to discover";
    private Integer pageSize = 5;

    public String getSiteName(){
       return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSiteSlogan() {
        return siteSlogan;
    }

    public void setSiteSlogan(String siteSlogan) {
        this.siteSlogan = siteSlogan;
    }
}
