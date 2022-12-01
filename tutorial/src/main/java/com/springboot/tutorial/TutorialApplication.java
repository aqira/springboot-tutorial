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

            /**
             * 8. com.springboot.tutorial.TutorialApplication$MyComponent
             * 9. com.springboot.tutorial.TutorialApplication$MyConfiguration
             * 10. firstConfigMethod
             * 11. secondConfigMethod
             * 12. dependentBean
             * 13. thisIsABean
             * 14. mySingleBean
             * 15. mySecondSingleBean
             * 16. myDependentBean
             */

            //Mencoba @Bean (13)
            System.out.println("Bean example :   " + context.getBean("thisIsABean"));

            //Mencoba @Bean dependency (14, 15 (duplicate of 14), 16)
            System.out.printf("""
                    Single Bean : %s
                    Dependent Bean : %s
                    """, context.getBean("mySingleBean"), context.getBean("myDependentBean"));

            //Mencoba @Component (8)
            System.out.println("Objek dari MyComponent.class yang telah menjadi bean : " + context.getBean(MyComponent.class));

            //Mencoba @Configuration (9,10,11)
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
        public int dependentBean() {
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

    @Bean //Qualifier biar ga bingung bean yang mana untuk ambil si simple classnya
    public String myDependentBean(@Qualifier("mySingleBean") SimpleClass simpleClass) {
        return simpleClass.helloWorld();
    }
}
