package org.apache.geode_examples.lucene;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.config.annotation.EnablePdx;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@ClientCacheApplication
@EnableEntityDefinedRegions(basePackages = {"org.apache.geode_examples.lucene"},
    clientRegionShortcut = ClientRegionShortcut.PROXY)
@EnableGemfireRepositories
@EnablePdx
@RestController
public class Example {

  @Autowired
  private PersonRepository personRepository;

  public static void main(String[] args) {
    SpringApplication.run(Example.class, args);
  }

  @Bean
  public CommandLineRunner doAtStartup() {
    return args -> {
      personRepository.save(new Person(23));
      personRepository.save(new Person(24));
      personRepository.save(new Person(25));
      personRepository.save(new Person(26));
    };
  }

  @GetMapping(path="/showData")
  public List<Person> showData() {
//    List<Person> myPerson = personRepository.findById("23");
//    if (myPerson.isPresent())
//      return myPerson.get();
    Iterable<Person> myPerson = personRepository.findAll();
    List<Person> listOfPersons = new ArrayList<>();
    myPerson.forEach((Person person) -> {
      listOfPersons.add(person);
    });
    return listOfPersons;
  }

  @GetMapping("/showDataById/{id}")
  public @ResponseBody Person showDataById(@PathVariable("id") String id) {
    Optional<Person> myPerson = personRepository.findById(id);
    if (myPerson.isPresent())
      return myPerson.get();
    return null;
  }

}
