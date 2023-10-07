package templatesTutorial;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.beans.XMLDecoder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PrintInvitations {
    public static void main(String[] args) {
        List<Person> personList;
        VelocityContext context = new VelocityContext();
        try {
            Template template = Velocity.getTemplate("template.vm");
            FileWriter out = new FileWriter("invitations.htm");
            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("friends.xml")));

            personList = (ArrayList<Person>)decoder.readObject();
            for (Person p: personList){
                context.put("Person", p);
                context.put("Gender", p.getGender());
                template.merge(context, out);
            }
            out.close();
            decoder.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
