package com.shiyueoe.springdemo.bean;

public class TestRepositoryBean {
    private Long id;

    public TestRepositoryBean(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "TestRepositoryBean{" +
                "id=" + id +
                '}';
    }
}