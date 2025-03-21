package hello.hello_spring.controller;

public class MemberForm {
    private String name; // createMemberForm.html input에 name= !"name"! !사이에 있는 부분과 동일하게 설정

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
