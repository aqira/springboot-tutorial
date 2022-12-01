package com.springboot.tutorial;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class TutorialApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TutorialApplication.class, args);

        System.out.println("Jumlah beans yang ada : " + printBeans(context));

        //initially, this app has 132 beans before we add more
        try {

            //Mencoba @Bean (133 beans in total)
            System.out.println("Bean example ");

            //Mencoba @Bean (2 method : 135 beans in total)
            System.out.printf("""
                    Single Bean : %s
                    Dependent Bean : %s
                    """, context.getBean("mySingleBean"), context.getBean("myDependentBean"));

            //Mencoba @Component (136 beans in total)
            System.out.println("Objek dari MyComponent.class yang telah menjadi bean : " + context.getBean(MyComponent.class));

            //Mencoba @Configuration (1 class + 3 method = 4. 140 beans in total)
            System.out.println("Bean dari MyConfiguration : " + context.getBean(MyConfiguration.class));

        } catch (NoSuchBeanDefinitionException noBeanException) {
            System.out.println("Cannot find that bean: " + noBeanException.getBeanName());
        }

    }

    public static int printBeans(ApplicationContext context) {
        int counter = 0;
        String title = "=".repeat(70) + "  APP BEAN NAMES  " + "=".repeat(70);
        System.out.println(title);
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.printf("""
                    %d. %s
                    """, ++counter, beanDefinitionName);
        }
        System.out.println("=".repeat(title.length()));
        return counter;
    }

    @Bean
    public String thisIsABean() {
        return "I am a bean";
    }

    @Component
    public class MyComponent {
        //this method is not a bean, but the component is a bean.
        public int notBeanMethod() {
            return 3;
        }
    }

    @Configuration
    public class MyConfiguration {

        //Dependency Injection
        @Autowired
        private MyComponent myComponent;

        @Bean
        public int firstConfigMethod() {
            return 1;
        }

        @Bean
        public int secondConfigMethod() {
            return 2;
        }

        @Bean
        public int dependantBean() {
            return myComponent.notBeanMethod();
        }

    }

    public class SimpleClass {
        public String helloWorld() {
            return "Hello World";
        }
    }

    //Any Bean that has a param, means it will have a dependency
    @Bean
    public SimpleClass mySingleBean() {
        return new SimpleClass();
    }

    @Bean
    public SimpleClass mySecondSingleBean() {
        return new SimpleClass();
    }

    @Bean
    public String myDependantBean(@Qualifier("mySingleBean") SimpleClass simpleClass) {
        return simpleClass.helloWorld();
    }
}
