package utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import dao.RoleDAO;
import impl.RoleDAOImpl;
import model.Role;

public class UserRolesMappingUtil {

    public static boolean isContains(String pathToCheck , Map<Role, List<String>> roleURLMap) {
        for(Entry<Role, List<String>> entry : roleURLMap.entrySet()) {
            if(entry.getValue().contains(pathToCheck)) {
                return true;
            }
        }
        return false;
    }
    
    public static Map<Role, List<String>> getRoleURLMap(){
        Map<Role, List<String>> map = new HashMap<Role, List<String>>();
        String fileName = "/roles_urls_mapping.properties";
        Properties props = new Properties();
        InputStream in = null;
        RoleDAO roleDAO = new RoleDAOImpl();
        try {
            in = UserRolesMappingUtil.class.getResourceAsStream(fileName);
            if (in == null) {
                System.out.println("No resource (" + fileName + ") is found");
            } else {
                props.load(in);
                Enumeration<?> propNames = props.propertyNames();
                while(propNames.hasMoreElements()) {
                    String roleName = (String)propNames.nextElement();// "ADMIN"
                    String roleValue = props.getProperty(roleName); // "/admin;/home;..."
                    map.put(roleDAO.getRoleByName(new Role(roleName)),
                            Arrays.asList(roleValue.split(";")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
        
    }
}