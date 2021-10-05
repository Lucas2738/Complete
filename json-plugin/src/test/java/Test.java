import com.fasterxml.jackson.databind.ObjectMapper;
import it.sisal.json.deserialize.Deserialize;
import it.sisal.json.serialize.Serialize;
import it.sisal.model.Event;
import it.sisal.model.Model;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.jeasy.random.EasyRandom;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

public class Test extends AbstractMojoTestCase {

    protected void setUp() throws Exception
    {
        // required for mojo lookups to work
        super.setUp();
    }

    public void testMojoGoal1() throws Exception
    {
        File testPom = new File( getBasedir(),
                "src/test/resources/project-to-test/pom.xml" );

        Serialize mojo = (Serialize) lookupMojo( "serialize", testPom );
        mojo.execute();
        assertNotNull(mojo);

        Deserialize mojoDes = (Deserialize) lookupMojo( "deserialize", testPom );
        mojoDes.execute();
        assertNotNull(mojoDes);
    }
/*
    public void testMojoGoal2() throws Exception
    {
        reloadClasses(Model.class);

        EasyRandom easyRandom = new EasyRandom();
        Model model = easyRandom.nextObject(Model.class);
        Map<String, List<Event>> maps = new HashMap<>();
        List<Event> list1 = new ArrayList<>();
        list1.add(easyRandom.nextObject(Event.class));
        list1.add(easyRandom.nextObject(Event.class));
        List<Event> list2 = new ArrayList<>();
        list2.add(easyRandom.nextObject(Event.class));
        list2.add(easyRandom.nextObject(Event.class));
        list2.add(easyRandom.nextObject(Event.class));
        list2.add(easyRandom.nextObject(Event.class));
        maps.put("as", list1);
        maps.put("ff", list2);
        model.setMap(maps);

        List<Map<String, Event>> lists = new ArrayList<>();
        Map<String, Event> m1 = new HashMap<>();
        m1.put("sdf", easyRandom.nextObject(Event.class));
        m1.put("123", easyRandom.nextObject(Event.class));
        Map<String, Event> m2 = new HashMap<>();
        m2.put("as", easyRandom.nextObject(Event.class));
        m2.put("asd", easyRandom.nextObject(Event.class));
        lists.add(m1);
        lists.add(m2);
        model.setList(lists);

        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(4);
        model.setNums(nums);

        Map<String, Integer> intes = new HashMap<>();
        intes.put("1", 1);
        intes.put("2", 13);
        intes.put("3", 12);
        model.setIntes(intes);

        ObjectMapper om =  new ObjectMapper();
        String json = om.writeValueAsString(model);

        assertEquals(json, model.toJson());

        Model des = Model.toObject(model.toJson());

        assertNotNull(des);
    }
*/
    private void reloadClasses(Class... classes) {

        Arrays.asList(classes).forEach(myClass -> {
            System.out.printf("my class is Class@%x%n", myClass.hashCode());
            System.out.println("reloading");

            final String dir = System.getProperty("user.dir");
            URL[] urls= new URL[0];
            try {
                urls = new URL[]{ new URL("file:" + dir + "/target/test-classes/"),
                        new URL("file:" + dir + "/target/classes/") };
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            ClassLoader delegateParent = myClass.getClassLoader().getParent();
            try {
                try(URLClassLoader cl=new URLClassLoader(urls, delegateParent)) {
                    Class<?> reloaded=cl.loadClass(myClass.getName());
                    System.out.printf("reloaded my class: Class@%x%n", reloaded.hashCode());
                    System.out.println("Different classes: "+(myClass!=reloaded));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

}
