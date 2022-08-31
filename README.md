# jIntl-solon-plugin
![Maven](https://img.shields.io/maven-central/v/com.laylib/jIntl-solon-plugin.svg)
![License](https://img.shields.io/github/license/LayGit/jIntl-solon-plugin.svg)

### Overview

Solon plugin of jIntl

### Installation(Maven)

Add the dependency to your pom.xml file:

```
<dependency>
    <groupId>com.laylib</groupId>
    <artifactId>jIntl-solon-plugin</artifactId>
    <version>2.0.0</version>
</dependency>
```

Then run from the root dir of the project:

```
mvn install
```

### Installation(Gradle)

Add the dependency to your build.gradle file:

```
implementation 'com.laylib:jIntl-solon-plugin:2.0.0'
```

Then load gradle changes

### Usage

```java
import com.laylib.jintl.IntlSource;
import com.laylib.jintl.solon.context.IntlSourceHolder;
import org.noear.solon.annotation.*;

import java.util.Locale;

@Controller
@Mapping("demo")
public class DemoController {

    @Inject
    IntlSource intlSource;

    @Get
    @Mapping("welcome")
    public String welcome() {
        return intlSource.getMessage("demo.welcome", Locale.ENGLISH);
    }

    @Get
    @Mapping("hello")
    public String hello(@Param("name") String name) {
        // static usage
        return sayHello(name);
    }
    
    private static String sayHello(String name) {
        // demo.hello is set to Hello, {0}!
        return IntlSourceHolder.get().getMessage("demo.hello", new Object[]{name}, Locale.ENGLISH);
    }
}
```