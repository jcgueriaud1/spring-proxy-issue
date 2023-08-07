package com.example.application.views;

/**
 * @author jcgueriaud
 */
public class FakeBean {

    private Long id = 1L;
    private String name = "na";
    private String name2;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }
}
