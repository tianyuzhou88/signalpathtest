**java 1.8, maven 3+ is needed to run the test**

steps

1. git clone the project to local (pulic ssh)
2. mvm clean install (if you have maven in your local), from intellij go to view -> maven -> life cyle
3. click clean first to remove all the depency then install



about:

1.  ```pom.xml``` is the derectory root
2.  ```testng.xml``` contains testing parameter, ```$Browser``` takes either chrome or firefox
3. WebDriverManager will control the version of browser using during runtime, it will download lastest relesae version of driver from w3c supported driver
4. test is run regardless of running OS, support windows, linux (*not tested*) and mac os (**recommended**).
5. filter is the generic string to filter whatever user/tester wants to pass in. in this case, i am demostrating useing "t-shirts". but it will take anything. if no found ,will throw ```"filtered item is not found"``` error
6.  ```test.json``` under ```src/core/java/data/``` extracting data from service layer. testing is orientated through service layer and logic validation
7. issues found: starndard_user does not have time out, shipping information page has not regex validation. 
8. I did not use inheritance to extend driver. composition vs inheritance is big thing. I do not want to abuse inheritance usage. the place I demostrator oop is landing page as base page since it contains left menu bar, little cart icon and footer.
  
