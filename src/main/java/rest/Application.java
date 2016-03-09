package rest;

import javax.ws.rs.ApplicationPath;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nhancao on 3/9/16.
 */
@ApplicationPath( "/api" )
public class Application extends javax.ws.rs.core.Application
{
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> returnValue = new HashSet<Class<?>>();
        returnValue.add(FileService.class);
        return returnValue;
    }
}
