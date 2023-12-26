package com.example.demo.home;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    int indexNumber = 1;
    List<Person> personList = new ArrayList<>();
    @GetMapping("/hello")
    @ResponseBody
    public String index() {
        return "안녕하세요";
    }
    @GetMapping("/name")
    @ResponseBody
    public String index2() {
        return "저는 김두환입니다.";
    }
    @GetMapping("/age")
    @ResponseBody
    public String index3() {
        return "저는 30살 입니다.";
    }
    @GetMapping("/home/increase")
    @ResponseBody
    public int index4() {
        this.indexNumber+=1;

        return indexNumber;
    }
    @GetMapping("/home/decrease")
    @ResponseBody
    public int index5() {
        this.indexNumber-=1;

        return indexNumber;
    }
    @GetMapping("/home/addPerson")
    @ResponseBody
    public String addPerson(@RequestParam(value = "name",defaultValue = "이름") String name, @RequestParam(value = "age",defaultValue = "0") int age) {
        Person person = new Person(indexNumber,name,age);
        personList.add(person);
        indexNumber++;

        return (indexNumber-1) + "번 사람이 추가되었습니다.";
    }
    @GetMapping("/home/removePerson")
    @ResponseBody
    public String removePerson(@RequestParam(value = "id",defaultValue = "0") int id) {
        Person person = findById(id);
        if (person==null) {
            return id + "번 사람이 존재하지 않습니다.";
        }
        personList.remove(person);

        return id + "번 사람이 삭제되었습니다.";
    }
    public Person findById(int id) {
        for (Person person : personList) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
    }
    @GetMapping("/home/people")
    @ResponseBody
    public List<Person> personList() {
        return personList;
    }
    @GetMapping("/home/modifyPerson")
    @ResponseBody
    public String modifyPerson(@RequestParam(value = "id") int id, @RequestParam(value = "name") String name, @RequestParam(value = "age") int age) {
        Person person = this.findById(id);

        if(person == null) {
            return id + "번 사람이 존재하지 않습니다.";
        }

        person.setName(name);
        person.setAge(age);

        return id + "번 사람이 수정되었습니다.";
    }

//    for(int i =0; i<personList.size(); i++) {
//        return "id : " + personList.get(i).getId() + " / name : " + personList.get(i).getName() + " / age : " + personList.get(i).getAge();
//    }
}